package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Pong extends Command {
    public Pong (SlashCommandInteractionEvent event) {
        super(event);
    }

    /**
     * Molly's response to the incoming ping-pong ball.
     */
    @Override
    public void run() {
        final long t0 = System.currentTimeMillis();
        double x = Math.random();

        String response;
        if (x <= 0.1) {
            response = "Ouch, that hurt!";
        } else {
            response = "Pong! :ping_pong:";
        }

        event.reply(response).queue(hook -> {
            long latency = System.currentTimeMillis() - t0;
            hook.editOriginal(response + " (" + latency + " ms)").queue();
        });
    }
}
