package molly;

import commands.Info;
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
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        System.out.println(args[0]);

        if (args[0].startsWith(Molly.prefix)) { // Check if prefix is correct
            switch (args[0].substring(1).toLowerCase(Locale.ROOT)) {
                case "info":
                    new Info(args, event).run();
                    break;
                case "ping":
                    event.getChannel().sendMessage("Pong!").queue();
                    break;
                default:
                    System.out.println("test");
                    event.getChannel().sendMessage("I don't know this command. Type **,info** for a list of all commands.").queue();
            }
        }
    }
}
