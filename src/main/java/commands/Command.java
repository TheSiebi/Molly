package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Command {
    protected String[] args;
    protected GuildMessageReceivedEvent event;

    public Command (String[] args, GuildMessageReceivedEvent event) {
        this.args = args;
        this.event = event;
    }

    public void run() {
        System.out.println("Not yet implemented.");
    }
}
