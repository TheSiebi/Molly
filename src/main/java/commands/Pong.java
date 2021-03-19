package commands;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

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

        double x = Math.random();
        if (x <= 0.1) {
            event.getChannel().sendMessage("Ouch, that hurt!").queue();
        } else {
            event.getChannel().sendMessage("Pong! :ping_pong:").queue();
        }
    }
}
