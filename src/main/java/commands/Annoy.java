package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

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
        if (args.length == 1) {
            event.getChannel().sendMessage("Missing parameter: victim").queue();
            return;
        }

        // Setup
        String victim = args[1];

        if (!victim.equalsIgnoreCase("TheSiebi")) {
            Member vict;

            try {
                vict = event.getGuild().getMembersByName(victim, true).get(0);
            } catch (IndexOutOfBoundsException e) {
                event.getChannel().sendMessage("Unknown user " + victim).queue();
                return;
            }

            AtomicReference<String> pingId = new AtomicReference<>();
            AtomicReference<String> pongId = new AtomicReference<>();

            event.getGuild().createVoiceChannel("Ping").queue(voiceChannel -> pingId.set(voiceChannel.getId()));
            event.getGuild().createVoiceChannel("Pong").queue(voiceChannel -> pongId.set(voiceChannel.getId()));

            // Busy wait until done
            while (pongId.get() == null || pingId.get() == null) ;

            // Annoy the poor fella
            for (int i = 0; i < 5; i++) {
                try {
                    event.getGuild().moveVoiceMember(vict, event.getGuild().getVoiceChannelById(pingId.get())).queue();
                    Thread.sleep(500);
                    event.getGuild().moveVoiceMember(vict, event.getGuild().getVoiceChannelById(pongId.get())).queue();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("EXCEEEEPTION"); // exception handling like a pro
                }
            }

            for (VoiceChannel vc : event.getGuild().getVoiceChannelsByName("ping", true)) {
                System.out.println(vc.getName());
                vc.delete().queue();
            }

            for (VoiceChannel vc : event.getGuild().getVoiceChannelsByName("pong", true)) {
                System.out.println(vc.getName());
                vc.delete().queue();
            }

                /*try {
                    event.getGuild().getVoiceChannelById(pingId.get()).delete().queue();
                    event.getGuild().getVoiceChannelById(pongId.get()).delete().queue();
                } catch (Exception e) { // idk what exception this throws
                    System.out.println("Channels were probably deleted manually");
                }*/

        } else {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Haha, you have no power here.").queue();
        }
    }
}
