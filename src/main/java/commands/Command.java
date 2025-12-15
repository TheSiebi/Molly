package commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Command {
    protected SlashCommandInteractionEvent event;
    protected Guild guild;
    protected User author;

    public Command (SlashCommandInteractionEvent event) {
        this.event = event;
        this.guild = event.getGuild();
        this.author = event.getUser();
    }

    public void run() {
        System.out.println("Not yet implemented.");
    }
}
