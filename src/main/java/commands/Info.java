package commands;

import molly.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.jetbrains.annotations.NotNull;


/**
 * An info panel with all available commands
 */
public class Info extends Command {

    public Info (@NotNull SlashCommandEvent event) {
        super(event);
    }

    /**
     * Show the user an embedded view with a list of possible commands
     */
    public void run() {
        EmbedBuilder info = new EmbedBuilder();

        info.setTitle("List of commands");
        info.setDescription("Required prefix: " +  "\"" + CommandHandler.prefix + "\"");

        info.setColor(0x43cccc);
        info.addField("info", "Shows this manual.", false);
        info.addField("ping", "Hit a ping-pong ball against Molly.", false);
        info.addField("summon", "Invite your friends to a gaming session.", false);
        info.addField("annoy [user]", "Annoys the specified user by moving him back and forth between two voice channels. " +
                "User must be in a voice channel for this to work.", false);
        info.addField("sad", "Let your friends know that you are sad.", false);

        event.reply(info.build()).queue();
        info.clear(); // saves system resources
    }
}
