package molly;

import events.GuildMessageReactionAdd;
import events.GuildMessageReactionRemove;
import events.NicknameChangeEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main JDA instance handling the bot
 */
public class Molly {

    public static JDA jda;
    public static Logger logger;

    /**
     * Main method initializing Molly
     *
     * @param args idk
     */
    public static void main(String[] args) {
        // Init
        String token = Token.getToken();
        logger = LoggerFactory.getLogger(Molly.class);

        jda = JDABuilder.createDefault(token)
                .enableIntents(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MEMBERS
                )
                .addEventListeners(
                        new SlashCommandHandler(),
                        new CommandHandler(), // Keep for backwards compatibility
                        new GuildMessageReactionAdd(),
                        new GuildMessageReactionRemove(),
                        new NicknameChangeEvent()
                )
                .setActivity(Activity.streaming("your most embarrassing memories", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
                .build();

        // Register slash commands globally and to specific guilds
        try {
            jda.awaitReady();
            
            // Register globally (takes up to 1 hour to sync)
            jda.updateCommands().addCommands(
                    Commands.slash("info", "Shows a list of all available commands"),
                    Commands.slash("ping", "Hit a ping-pong ball against Molly"),
                    Commands.slash("summon", "Invite your friends to a gaming session"),
                    Commands.slash("sad", "Express your sadness about no one joining"),
                    Commands.slash("annoy", "Annoy a user by moving them between voice channels")
                            .addOption(OptionType.USER, "user", "The user to annoy", true),
                    Commands.slash("say", "Make the bot say something")
                            .addOption(OptionType.STRING, "message", "The message to send", true)
            ).queue();
            
        //     // Register to test guild (instant)
        //     jda.getGuildById("818531386599538688").updateCommands().addCommands(
        //             Commands.slash("info", "Shows a list of all available commands"),
        //             Commands.slash("ping", "Hit a ping-pong ball against Molly"),
        //             Commands.slash("summon", "Invite your friends to a gaming session"),
        //             Commands.slash("sad", "Express your sadness about no one joining"),
        //             Commands.slash("annoy", "Annoy a user by moving them between voice channels")
        //                     .addOption(OptionType.USER, "user", "The user to annoy", true),
        //             Commands.slash("say", "Make the bot say something")
        //                     .addOption(OptionType.STRING, "message", "The message to send", true)
        //     ).queue();
            
        //     // Register to Krasse Channel guild (instant)
        //     jda.getGuildById("315190207664226305").updateCommands().addCommands(
        //             Commands.slash("info", "Shows a list of all available commands"),
        //             Commands.slash("ping", "Hit a ping-pong ball against Molly"),
        //             Commands.slash("summon", "Invite your friends to a gaming session"),
        //             Commands.slash("sad", "Express your sadness about no one joining"),
        //             Commands.slash("annoy", "Annoy a user by moving them between voice channels")
        //                     .addOption(OptionType.USER, "user", "The user to annoy", true),
        //             Commands.slash("say", "Make the bot say something")
        //                     .addOption(OptionType.STRING, "message", "The message to send", true)
        //     ).queue();
            
            logger.info("Successfully registered slash commands globally and to guilds!");
        } catch (InterruptedException e) {
            logger.error("Failed to register slash commands", e);
        }
    }
}