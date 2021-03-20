package commands;

import molly.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * An info panel with all available commands
 */
public class Info extends Command {

    public Info (String[] args, GuildMessageReceivedEvent event) {
        super(args, event);
    }

    /**
     * Show the user an embedded view with a list of possible commands
     */
    public void run() {
        event.getChannel().sendTyping().queue();
        EmbedBuilder info = new EmbedBuilder();

        info.setTitle("List of commands");
        info.setDescription("Required prefix: " +  "\"" + CommandHandler.prefix + "\"");

        info.setColor(0x43cccc);
        info.addField("info", "Shows this manual.", false);
        info.addField("ping", "Hit a ping-pong ball against Molly.", false);
        info.addField("summon", "Invite your friends to a gaming session.", false);
        info.addField("annoy [user] -numberOfRepetitions", "Annoys the specified user by moving him back and forth between two voice channels. " +
                "User must be in a voice channel for this to work.", false);

        event.getChannel().sendMessage(info.build()).queue();
        info.clear(); // saves system resources
    }
}
