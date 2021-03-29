package commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.jetbrains.annotations.NotNull;


public class Pong extends Command {
    public Pong (@NotNull SlashCommandEvent event) {
        super(event);
    }

    /**
     * Molly's response to the incoming ping-pong ball.
     */
    @Override
    public void run() {
        double x = Math.random();

        if (x <= 0.1) {
            event.reply("Ouch, that hurt!").queue();
        } else {
            event.reply("Pong! :ping_pong:").queue();
        }
    }
}
