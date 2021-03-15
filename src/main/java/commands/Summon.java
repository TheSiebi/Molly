package commands;

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
        String id;

        invite.setColor(0xf542e6);
        invite.setTitle(author + " invites you to a a gaming session this evening!");
        invite.setDescription("To participate, react with the provided emoji below.");

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(invite.build()).queue(message -> {
            message.addReaction(CommandHandler.summonReaction).queue();
            //add reaction log
            HashMap<String, HashSet<String>> tmp = new HashMap<>();
            tmp.put(message.getId(), new HashSet<String>());
            CommandHandler.reactLog.put(message.getChannel().getId(), tmp);
        });

        invite.clear();
    }
}
