package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Sad extends Command {

    public Sad(SlashCommandInteractionEvent event) {
        super(event);
    }

    @Override
    public void run() {
        String author = event.getUser().getName();
        event.reply(author + " would like to let you know that they are sad about no one reacting to their invitation.").queue();
    }
}
