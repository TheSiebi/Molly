package commands;

import components.Message;
import molly.CommandHandler;
import molly.Molly;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.HashSet;

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
        event.getChannel().sendMessage(invite.build()).queue(message -> {
            message.addReaction(CommandHandler.summonReaction).queue();

            //Keep track of reaction messages
            Message tmp = new Message(message.getId(), event.getChannel());
            //delete previous summon message
            if (CommandHandler.reactLog.get(event.getChannel().getId()) != null){
                CommandHandler.reactLog.get(event.getChannel().getId()).delete();
            }
            CommandHandler.reactLog.put(event.getChannel().getId(), tmp);
        });

        invite.clear();
    }
}
