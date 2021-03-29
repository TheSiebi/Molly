package commands;

import molly.Molly;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class Annoy extends Command {

    public Annoy(String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    /**
     * Creates two channels, ping and pong, and repeatedly moves the mentioned user back and forth between those channels
     */
    @Override
    public void run() {

        // Missing input
        if (args.length == 1) {
            textChannel.sendMessage("Missing parameter: victim").queue();
            return;
        }

        // Get user to annoy
        StringBuilder sb = new StringBuilder();
        sb.append(args[1]);
        for (int i = 2; i < args.length; i++) {
            sb.append(" ").append(args[i]);
        }
        String victim = sb.toString();

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
                    guild.moveVoiceMember(vict, event.getGuild().getVoiceChannelById(pingId.get())).submit().join();
                    Thread.sleep(250);
                    guild.moveVoiceMember(vict, event.getGuild().getVoiceChannelById(pongId.get())).submit().join();
                    Thread.sleep(250);
                } catch (Exception e) {}
            }

            try {
                CompletableFuture<Void> action = event.getGuild().moveVoiceMember(vict, event.getGuild().getVoiceChannelById("818531386599538692")).submit();
                action.join(); // TODO: figure out why the join takes so long
            } catch (IllegalStateException e) {

            }

            for (VoiceChannel vc : event.getGuild().getVoiceChannelsByName("ping", true)) {
                Molly.logger.info("Deleted channel " + vc.getName());
                vc.delete().queue();
            }

            for (VoiceChannel vc : event.getGuild().getVoiceChannelsByName("pong", true)) {
                Molly.logger.info("Deleted channel " + vc.getName());
                vc.delete().queue();
            }

        } else {
            textChannel.sendTyping().queue();
            textChannel.sendMessage("Haha, you have no power here.").queue();
        }
    }
}
