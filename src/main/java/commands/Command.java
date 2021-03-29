package commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Super class of all SlashCommands containing relevant fields
 */
public class Command {
    protected SlashCommandEvent event;
    protected Guild guild;
    protected MessageChannel textChannel;

    public Command (@NotNull SlashCommandEvent event) {
        this.event = event;
        this.guild = event.getGuild();
        this.textChannel = event.getChannel();
    }

    /**
     * Method to be overwritten by each command
     */
    public void run() {
        System.out.println("Not yet implemented.");
    }
}
