package molly;

import components.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;

/**
 * The main instance handling user-inputted commands (legacy text-based commands)
 * Kept for backwards compatibility, but slash commands are now preferred
 */
public class CommandHandler extends ListenerAdapter {

    public static String summonReaction = "U+2705";
    public static String prefix = "/";
    /**
     * The first string represents the channel, the Message keeps track of reactions
     */
    public static HashMap<String, Message> reactLog = new HashMap<>();

    /**
     * Creates a new Command object depending on the given input and runs it
     *
     * @param event holds reference to message which was received
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) { // ignore other bots and ourselves too
            return;
        }

        if (!event.isFromGuild()) { // Only handle guild messages
            return;
        }

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].startsWith(CommandHandler.prefix)) { // Check if prefix is correct
            Molly.logger.info("User " + event.getAuthor().getName() + " tried to invoke legacy command " + args[0].substring(1).toLowerCase());
            event.getChannel().sendMessage("Please use slash commands instead! Type `/info` to see all available commands.").queue();
        }
    }
}
