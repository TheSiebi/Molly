package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Say extends Command {
    public Say(SlashCommandInteractionEvent event) {
        super(event);
    }

    @Override
    public void run() {
        // If the command was used in the test guild
        System.out.println(event.getGuild().getId());

        if (event.getGuild().getId().equals("818531386599538688")) {
            OptionMapping messageOption = event.getOption("message");
            if (messageOption != null) {
                String message = messageOption.getAsString();
                // Send a message to the "Krasse Channel"
                event.getJDA().getGuildById("315190207664226305")
                    .getTextChannelById("315190207664226305")
                    .sendMessage(message).queue();
                event.reply("Message sent!").setEphemeral(true).queue();
            } else {
                event.reply("Please provide a message!").setEphemeral(true).queue();
            }
        } else {
            event.reply("This command is not available in this server.").setEphemeral(true).queue();
        }
    }
}
