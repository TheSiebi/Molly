package commands;

import molly.Molly;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class Annoy extends Command {

    public Annoy(SlashCommandInteractionEvent event) {
        super(event);
    }

    /**
     * Creates two channels, ping and pong, and repeatedly moves the mentioned user back and forth between those channels
     */
    @Override
    public void run() {
        // Get user to annoy from slash command option
        OptionMapping userOption = event.getOption("user");
        
        if (userOption == null) {
            event.reply("Missing parameter: user").setEphemeral(true).queue();
            return;
        }

        User victimUser = userOption.getAsUser();
        
        if (victimUser.getName().equalsIgnoreCase("TheSiebi")) {
            event.deferReply().queue();
            
            Member vict = guild.getMember(victimUser);
            
            if (vict == null) {
                event.getHook().editOriginal("User not found in this server!").queue();
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
                action.join();
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
            
            event.getHook().editOriginal("Mission accomplished! 😈").queue();

        } else {
            event.reply("Haha, you have no power here.").setEphemeral(true).queue();
        }
    }
}
