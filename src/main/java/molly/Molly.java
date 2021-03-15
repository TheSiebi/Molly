package molly;

import events.GuildMessageReactionAdd;
import events.GuildMessageReactionRemove;
import events.NicknameChangeEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

/**
 * The main JDA instance handling the bot
 */
public class Molly extends ListenerAdapter {

    public static JDA jda;
    public static String prefix = "/";

    /**
     * Main method initializing Molly
     *
     * @param args
     * @throws LoginException in case the token is incorrect
     */
    public static void main(String[] args) throws LoginException {
        // Init
        String token = Token.getToken();
        jda = JDABuilder.createDefault(token).build();

        // Add Listeners
        jda.addEventListener(new CommandHandler());
        jda.addEventListener(new Molly());
        jda.addEventListener(new GuildMessageReactionAdd());
        jda.addEventListener(new GuildMessageReactionRemove());
        jda.addEventListener(new NicknameChangeEvent());

        // Activity
        //jda.getPresence().setIdle(true);
        jda.getPresence().setActivity(Activity.streaming("your most embarrassing memories", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
    }
}