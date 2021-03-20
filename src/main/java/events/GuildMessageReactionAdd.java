package events;

import molly.CommandHandler;
import molly.Molly;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class GuildMessageReactionAdd extends ListenerAdapter {
    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if (event.getReactionEmote().getAsCodepoints().equals(CommandHandler.summonReaction) && !event.getUser().isBot()) {
            //System.out.println("Yay");
            String id = event.getMessageId();

            event.getChannel().retrieveMessageById(id).queue(message -> {
                //System.out.println(message.getEmbeds().get(0).getTitle().toLowerCase());
                if (!message.getEmbeds().isEmpty()) {
                    if (message.getEmbeds().get(0).getTitle().toLowerCase().endsWith("invites you to a a gaming session this evening!")) {
                        String participant = event.getMember().getUser().getName();

                        event.getChannel().sendTyping().queue();
                        event.getChannel().sendMessage(joinMessage(participant)).queue(reactMessage -> {
                            //keep track of reaction history
                            CommandHandler.reactLog.get(message.getChannel().getId()).join(participant, reactMessage.getId());
                        });
                    }
                }
            });
        }
    }

    private String joinMessage(String participant) {
        String[] messages = {
                "**" + participant + "** will join the gaming session!",
                "Guess who's gonna gift us his presence... It's **" + participant + "**!",
                "YAY! I can't believe **" + participant + "** will manage to make it too!",
                "Uhh... " + participant + " is gonna join too. Ok then."
        };

        Random rand = new Random();
        return messages[rand.nextInt(messages.length)];
    }
}
