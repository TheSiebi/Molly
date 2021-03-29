package commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.jetbrains.annotations.NotNull;


public class Sad extends Command {

    public Sad(@NotNull SlashCommandEvent event) {
        super(event);
    }

    /**
     * I'm sad. You're sad. We should all be sad about this event.
     */
    @Override
    public void run() {
        event.reply( "I would like to let you know that I am sad about no one reacting to my invitation.").queue();
    }
}
