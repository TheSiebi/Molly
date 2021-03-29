package commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Command {
    protected String[] args;
    protected GuildMessageReceivedEvent event;
    protected Guild guild;
    protected TextChannel textChannel;
    protected User author;

    public Command (String[] args, GuildMessageReceivedEvent event) {
        this.args = args;
        this.event = event;
        this.guild = event.getGuild();
        this.textChannel = event.getChannel();
        this.author = event.getAuthor();
    }

    public void run() {
        System.out.println("Not yet implemented.");
    }
}
