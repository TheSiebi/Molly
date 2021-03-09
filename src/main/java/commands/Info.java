package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import molly.Molly;

import java.util.Arrays;

/**
 * An info panel with all available commands
 */
public class Info {

    private final String[] args;
    private final GuildMessageReceivedEvent event;

    public Info (String[] args, GuildMessageReceivedEvent event) {
        this.args = Arrays.copyOfRange(args, 1, args.length);
        this.event = event;
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

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(info.build()).queue();
        info.clear(); // saves system resources
    }
}