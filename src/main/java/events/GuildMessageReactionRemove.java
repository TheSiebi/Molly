package events;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GuildMessageReactionRemove extends ListenerAdapter {
    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        if (event.getReactionEmote().getAsCodepoints().equals("U+2705") && !event.getUser().isBot()) {
            System.out.println("Aww");

            // TODO: Marc pls figure this out
            //if (event.getMessageId().equals("id of invite")) {
            String leaver = event.getMember().getUser().getName();

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(leaveMessage(leaver)).queue();
            //}

        }
    }

    private String leaveMessage(String leaver) {
        String[] messages = {
                leaver + " is not feeling it anymore. Goodbye!",
                "Aww man! " + leaver + " has just left.",
                "Sad news: " + leaver + " is not gonna make it.",
                "Whew, " + leaver + " has left. We were lucky."
        };

        Random rand = new Random();
        return messages[rand.nextInt(messages.length)];
    }
}
