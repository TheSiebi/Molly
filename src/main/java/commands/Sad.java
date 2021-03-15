package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Sad extends Command {

    public Sad(String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    @Override
    public void run() {
        String author = event.getAuthor().getName();

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(author + " would like to let you know that he is sad about no one reacting to his invitation.").queue();
    }
}
