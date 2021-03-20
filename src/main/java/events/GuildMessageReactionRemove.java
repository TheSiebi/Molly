package events;

import molly.CommandHandler;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GuildMessageReactionRemove extends ListenerAdapter {
    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {

        User remover = event.retrieveUser().complete(); // if we don't load the user first, it possibly returns null

        if (event.getReactionEmote().getAsCodepoints().equals(CommandHandler.summonReaction) && !remover.isBot()) {

            String id = event.getMessageId();
            event.getChannel().sendTyping().queue();

            event.getChannel().retrieveMessageById(id).queue(message -> {
                if (!message.getEmbeds().isEmpty()) {
                    if (message.getEmbeds().get(0).getTitle().toLowerCase().endsWith("invites you to a a gaming session this evening!")) { // dirty fix
                        String leaver = remover.getName();

                        event.getChannel().sendMessage(leaveMessage(leaver)).queue(reactMessage -> {
                            //keep track of reaction history
                            CommandHandler.reactLog.get(message.getChannel().getId()).leave(leaver, reactMessage.getId());
                        });
                    }
                }
            });
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