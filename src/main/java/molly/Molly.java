package molly;

import events.GuildMessageReactionAdd;
import events.GuildMessageReactionRemove;
import events.NicknameChangeEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.security.auth.login.LoginException;

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
     * @throws LoginException in case the token is incorrect
     */
    public static void main(String[] args) throws LoginException {
        // Init
        String token = Token.getToken();
        jda = JDABuilder.createDefault(token).build();
        logger = LoggerFactory.getLogger(Molly.class);

        // Add Listeners
        jda.addEventListener(new CommandHandler());
        jda.addEventListener(new GuildMessageReactionAdd());
        jda.addEventListener(new GuildMessageReactionRemove());
        jda.addEventListener(new NicknameChangeEvent());

        // Activity
        //jda.getPresence().setIdle(true);
        jda.getPresence().setActivity(Activity.streaming("your most embarrassing memories", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
    }
}