package molly;

import commands.Info;
import commands.Sad;
import commands.Summon;
import commands.Pong;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * The main instance handling user-inputted commands
 */
public class CommandHandler extends ListenerAdapter {

    /**
     * Creates a new Command object depending on the given input and runs it
     *
     * @param event
     */
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) { // ignore other bots and ourselves too
            return;
        }

        String[] args = event.getMessage().getContentRaw().split("\\s+");
        System.out.println(args[0]);

        if (args[0].startsWith(Molly.prefix)) { // Check if prefix is correct
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
                default:
                    System.out.println("test");
                    event.getChannel().sendMessage("I don't know this command. Type **,info** for a list of all commands.").queue();
            }
        }
    }
}
