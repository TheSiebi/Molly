package commands;

import components.Message;
import molly.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Summon extends Command {
    public Summon (SlashCommandInteractionEvent event) {
        super(event);
    }

    @Override
    public void run() {
        String author = event.getUser().getName();
        EmbedBuilder invite = new EmbedBuilder();

        invite.setColor(0xf542e6);
        invite.setTitle(author + " invites you to a gaming session this evening!");
        invite.setDescription("To participate, react with the provided emoji below.");

        event.replyEmbeds(invite.build()).queue(hook -> {
            hook.retrieveOriginal().queue(message -> {
                message.addReaction(net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode(CommandHandler.summonReaction)).queue();

                //Keep track of reaction messages
                Message tmp = new Message(message.getId(), event.getChannel());
                //delete previous summon message
                if (CommandHandler.reactLog.get(event.getChannel().getId()) != null){
                    CommandHandler.reactLog.get(event.getChannel().getId()).delete();
                }
                CommandHandler.reactLog.put(event.getChannel().getId(), tmp);
            });
        });

        invite.clear();
    }
}
