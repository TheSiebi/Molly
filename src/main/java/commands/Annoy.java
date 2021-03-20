package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.atomic.AtomicReference;

public class Annoy extends Command {
    int num = 5; // default value

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
        StringBuilder sb = new StringBuilder();
        sb.append(args[1]);

        for (int i = 2; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                try {
                    num = Integer.parseInt(args[i].substring(1));
                } catch (NumberFormatException e) {
                    event.getChannel().sendMessage("Gimme a correct number u fockin donkay").queue();
                }
            } else {
                sb.append(" ").append(args[i]);
            }
        }

        String victim = sb.toString();

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

            if (num > 100) {
                event.getChannel().sendMessage("Whoa, that's too much, chill.").queue();
                num = 5;
            }

            // Annoy the poor fella
            for (int i = 0; i < num; i++) {
                try {
                    event.getGuild().moveVoiceMember(vict, event.getGuild().getVoiceChannelById(pingId.get())).queue();
                    Thread.sleep(250);
                    event.getGuild().moveVoiceMember(vict, event.getGuild().getVoiceChannelById(pongId.get())).queue();
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    System.out.println("Thread was somehow interrupted ¯\\_(ツ)_/¯"); // exception handling like a pro
                }
            }

            event.getGuild().moveVoiceMember(vict, event.getGuild().getVoiceChannelById("323918708559052810")).queue();

            for (VoiceChannel vc : event.getGuild().getVoiceChannelsByName("ping", true)) {
                System.out.println(vc.getName());
                vc.delete().queue();
            }

            for (VoiceChannel vc : event.getGuild().getVoiceChannelsByName("pong", true)) {
                System.out.println(vc.getName());
                vc.delete().queue();
            }

        } else {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Haha, you have no power here.").queue();
        }
    }
}
