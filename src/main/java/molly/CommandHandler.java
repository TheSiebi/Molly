package molly;

import commands.*;
import components.Message;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * The main instance handling user-inputted commands
 */
public class CommandHandler extends ListenerAdapter {

    /**
     * Default reaction for summon messages
     */
    public static String summonReaction = "U+2705";

    /**
     *  Default prefix of Molly
     */
    public static String prefix = "/";

    /**
     * The first string represents the channel, the Message keeps track of reactions
     */
    public static HashMap<String, Message> reactLog = new HashMap<>();

    /**
     * Creates a new Command object depending on the given input and runs it
     *
     * @param event SlashCommandEvent containing all relevant data
     */
    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        Molly.logger.info("Invoked command: " + event.getName());
        switch (event.getName()) {
            case "info":
                new Info(event).run();
                break;
            case "summon":
                new Summon(event).run();
                break;
            case "ping":
                new Pong(event).run();
                break;
            case "sad":
                new Sad(event).run();
                break;
            case "annoy":
                new Annoy(event).run();
                break;
            default: // unknown command
                event.reply("I dont know this command... Type /info for a list of commands.").setEphemeral(true).queue();
        }
    }

    /**
     * This seperate listener is only for the say command, as it should not be displayed publicly
     *
     * @param event GuildMessageReceivedEvent containing all relevant data
     */
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith(CommandHandler.prefix + "say")) {
            new Say(event).run();
        }
    }
}
