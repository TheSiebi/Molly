package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Summon extends Command {
    public Summon (String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    @Override
    public void run() {
        String author = event.getAuthor().getName();
        EmbedBuilder invite = new EmbedBuilder();

        invite.setColor(0xf542e6);
        invite.setTitle(author + " invites you to a a gaming session this evening!");
        invite.setDescription("To participate, react with the provided emoji below.");

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(invite.build()).queue(message -> message.addReaction("U+2705").queue());

        invite.clear();
    }
}
