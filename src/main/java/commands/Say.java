package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Say extends Command {
    public Say(String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    @Override
    public void run() {
        // If the message was sent in the test channel
        System.out.println(event.getGuild().getId());

        if (event.getGuild().getId().equals("818531386599538688")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i] + " ");
            }

            // Send a message to the "Krasse Channel"
            event.getJDA().getGuildById("315190207664226305").getTextChannelById("315190207664226305").sendMessage(sb.toString()).queue();
        }
    }
}
