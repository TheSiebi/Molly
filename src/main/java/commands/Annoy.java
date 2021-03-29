package commands;

import molly.Molly;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class Annoy extends Command {

    public Annoy(@NotNull SlashCommandEvent event) {
        super(event);
    }

    /**
     * Creates two channels, ping and pong, and repeatedly moves the mentioned user back and forth between those channels
     */
    @Override
    public void run() {
        event.reply("Let the fun begin... :smiling_imp:").queue();

        // Get user to annoy
        String victim = Objects.requireNonNull(Objects.requireNonNull(event.getOption("user")).getAsUser()).getName(); // object require nonnull-ception
        System.out.println(victim);

        if (victim.equalsIgnoreCase("TheSiebi")) {
            Member vict;

            // Check if user actually exists
            try {
                vict = guild.getMembersByName(victim, true).get(0);
            } catch (IndexOutOfBoundsException e) {
                textChannel.sendMessage("Unknown user " + victim).queue();
                return;
            }

            AtomicReference<String> pingId = new AtomicReference<>();
            AtomicReference<String> pongId = new AtomicReference<>();

            guild.createVoiceChannel("Ping").queue(voiceChannel -> pingId.set(voiceChannel.getId()));
            guild.createVoiceChannel("Pong").queue(voiceChannel -> pongId.set(voiceChannel.getId()));

            // Busy wait until done
            while (pongId.get() == null || pingId.get() == null) ;

            // Annoy the poor fella
            for (int i = 0; i < 5; i++) {
                try {
                    guild.moveVoiceMember(vict, guild.getVoiceChannelById(pingId.get())).submit().join();
                    Thread.sleep(250);
                    guild.moveVoiceMember(vict, guild.getVoiceChannelById(pongId.get())).submit().join();
                    Thread.sleep(250);
                } catch (IllegalStateException e) {
                    Molly.logger.error("User " + vict + " not in VC while being annoyed");
                    break;
                } catch (InterruptedException e) {
                    Molly.logger.error("Thread was interrupted");
                    break;
                }
            }

            try {
                CompletableFuture<Void> action = guild.moveVoiceMember(vict, guild.getVoiceChannelById("818531386599538692")).submit();
                action.join(); // TODO: figure out why the join takes so long
            } catch (IllegalStateException e) {

            }

            for (VoiceChannel vc : guild.getVoiceChannelsByName("ping", true)) {
                Molly.logger.info("Deleted channel " + vc.getName());
                vc.delete().queue();
            }

            for (VoiceChannel vc : guild.getVoiceChannelsByName("pong", true)) {
                Molly.logger.info("Deleted channel " + vc.getName());
                vc.delete().queue();
            }

        } else {
            textChannel.sendTyping().queue();
            textChannel.sendMessage("Haha, you have no power here.").queue();
        }
    }
}
