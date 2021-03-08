import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.*;


public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        String token = "";
        JDA jda = JDABuilder.createDefault(token).build();
        jda.addEventListener(new Main());
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        // Ignore other bots and ourselves
        if (event.getAuthor().isBot()) {
            return;
        }

        System.out.println("We received a message from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());

        if (event.getAuthor().getName().equals("Marc")) {
            String msg = event.getMessage().getContentRaw();
            String newMsg = "";

            for (int i = 0; i < msg.length(); i++) {
                if (i%2 == 0) {
                    newMsg += Character.toUpperCase(msg.charAt(i));
                } else {
                    newMsg += msg.charAt(i);
                }
            }

            event.getMessage().reply(newMsg).queue();
        }

        if (event.getMessage().getAuthor().getName().equals("TheSiebi")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("**MESSAGE CENSORED BY MOLLY INDUSTRIES**").queue();
        }
    }

    @Override
    public void onGuildMemberUpdateNickname(@NotNull GuildMemberUpdateNicknameEvent event) {
        if (event.getMember().getUser() == event.getJDA().getSelfUser()) {
            event.getMember().modifyNickname("Molly").queue();
        }
    }
}