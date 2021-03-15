package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import molly.Molly;

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
        EmbedBuilder info = new EmbedBuilder();

        info.setTitle("List of commands");
        info.setDescription("Required prefix: " +  "\"" + Molly.prefix + "\"");

        info.setColor(0x43cccc);
        info.addField("info", "Shows this manual.", false);
        info.addField("ping", "Hit a ping-pong ball against Molly.", false);
        info.addField("summon", "Invite your friends to a gaming session.", false);

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(info.build()).queue();
        info.clear(); // saves system resources
    }
}
