package molly;

import events.GuildMessageReactionAdd;
import events.GuildMessageReactionRemove;
import events.NicknameChangeEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Command;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.security.auth.login.LoginException;
import java.util.EnumSet;

/**
 * The main JDA instance handling the bot
 */
public class Molly {

    /**
     * Core of the JDA, all parts can be accessed through this instance
     */
    public static JDA jda;

    /**
     * Used to log all kinds of information or errors, used tool is logback
     */
    public static Logger logger;

    /**
     * Main method initializing Molly
     *
     * @param args idk
     * @throws LoginException in case the token is incorrect
     */
    public static void main(String[] args) throws LoginException {
        // Establish a connection to the Discord API
        String token = Token.getToken();
        jda = JDABuilder.createDefault(token).build();

        // Initialize the logger
        logger = LoggerFactory.getLogger(Molly.class);

        // Add Event Listeners
        jda.addEventListener(new CommandHandler());
        jda.addEventListener(new GuildMessageReactionAdd());
        jda.addEventListener(new GuildMessageReactionRemove());
        jda.addEventListener(new NicknameChangeEvent());

        // Set Molly's activity (rick roll)
        jda.getPresence().setActivity(Activity.streaming("your most embarrassing memories", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));

        // These commands take up to an hour to be activated after creation / update / deletion
        CommandUpdateAction commands = jda.updateCommands();

        commands.addCommands(
                new CommandUpdateAction.CommandData("annoy", "Annoy a user")
                    .addOption(new CommandUpdateAction.OptionData(Command.OptionType.USER, "user", "the user to annoy")
                            .setRequired(true))
        );

        commands.addCommands(
                new CommandUpdateAction.CommandData("info", "Displays a list of commands"));

        commands.addCommands(
                new CommandUpdateAction.CommandData("ping", "Shoot a Ping-Pong Ball at Molly"));

        commands.addCommands(
                new CommandUpdateAction.CommandData("sad", "Let the others know how you feel"));

        commands.addCommands(
                new CommandUpdateAction.CommandData("summon", "Invite your friends to a gaming session"));

        // Send the new set of commands to discord, this will override any existing global commands with the new set provided here
        commands.queue();
    }
}