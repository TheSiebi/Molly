package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Pong extends Command {
    public Pong (String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    @Override
    public void run() {
        event.getChannel().sendMessage("Pong!").queue();
    }
}
