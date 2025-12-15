package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * An info panel with all available commands
 */
public class Info extends Command {

    public Info (SlashCommandInteractionEvent event) {
        super(event);
    }

    /**
     * Show the user an embedded view with a list of possible commands
     */
    public void run() {
        EmbedBuilder info = new EmbedBuilder();

        info.setTitle("List of commands");
        info.setDescription("These are Discord's native slash commands!");

        info.setColor(0x43cccc);
        info.addField("/info", "Shows this manual.", false);
        info.addField("/ping", "Hit a ping-pong ball against Molly.", false);
        info.addField("/summon", "Invite your friends to a gaming session.", false);
        info.addField("/annoy", "Annoys the specified user by moving them back and forth between voice channels.", false);

        event.replyEmbeds(info.build()).queue();
        info.clear(); // saves system resources
    }
}
