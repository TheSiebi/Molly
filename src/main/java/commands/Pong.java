package commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.RestAction;

public class Pong extends Command {
    public Pong (String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    /**
     * Molly's response to the incoming ping-pong ball.
     */
    @Override
    public void run() {
        event.getChannel().sendTyping().queue();

        RestAction<Message> action;
        double x = Math.random();

        if (x <= 0.1) {
            action = textChannel.sendMessage("Ouch, that hurt!");
        } else {
            action = textChannel.sendMessage("Pong! :ping_pong:");
        }

        final long t0 = System.currentTimeMillis();
        Message message = action.complete();
        message.editMessage(message.getContentDisplay() + " (" + (System.currentTimeMillis() - t0) + " ms)").queue(); // End with queue() to not block the callback thread!
    }
}
