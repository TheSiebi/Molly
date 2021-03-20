package molly;

import commands.*;
import components.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;

/**
 * The main instance handling user-inputted commands
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
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) { // ignore other bots and ourselves too
            return;
        }

        String[] args = event.getMessage().getContentRaw().split("\\s+");
        System.out.println(args[0]);

        if (args[0].startsWith(CommandHandler.prefix)) { // Check if prefix is correct
            switch (args[0].substring(1).toLowerCase()) {
                case "info":
                    new Info(args, event).run();
                    break;
                case "summon":
                    new Summon(args, event).run();
                    break;
                case "ping":
                    new Pong(args, event).run();
                    break;
                case "sad":
                    new Sad(args, event).run();
                    break;
                case "annoy":
                    new Annoy(args, event).run();
                    break;
                default:
                    System.out.println("test");
                    event.getChannel().sendMessage("I don't know this command. Type ** " + CommandHandler.prefix +  "info** for a list of all commands.").queue();
            }
        }
    }
}
