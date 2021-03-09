package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Summon extends Command {
    public Summon (String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    @Override
    public void run() {}
}
