package commands;

import components.Message;
import molly.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.jetbrains.annotations.NotNull;

public class Summon extends Command {
    public Summon (@NotNull SlashCommandEvent event) {
        super(event);
    }

    /**
     * Creates a new summon message and keeps track of stuff
     */
    @Override
    public void run() {
        String author = event.getUser().getName();
        EmbedBuilder invite = new EmbedBuilder();

        invite.setColor(0xf542e6);
        invite.setTitle(author + " invites you to a a gaming session this evening!");
        invite.setDescription("To participate, react with the provided emoji below.");

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(invite.build()).queue(message -> {
            message.addReaction(CommandHandler.summonReaction).queue();

            //Keep track of reaction messages
            Message tmp = new Message(message.getId(), textChannel);
            //delete previous summon message
            if (CommandHandler.reactLog.get(event.getChannel().getId()) != null){
                CommandHandler.reactLog.get(event.getChannel().getId()).delete();
            }
            CommandHandler.reactLog.put(event.getChannel().getId(), tmp);
        });

        event.reply("Summon success").queue();

        invite.clear();
    }
}
