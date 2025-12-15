package molly;

import commands.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Handler for Discord's native slash commands
 */
public class SlashCommandHandler extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        Molly.logger.info("User " + event.getUser().getName() + " invoked slash command: " + commandName);

        switch (commandName) {
            case "info":
                new Info(event).run();
                break;
            case "ping":
                new Pong(event).run();
                break;
            case "sad":
                new Sad(event).run();
                break;
            case "annoy":
                new Annoy(event).run();
                break;
            case "say":
                new Say(event).run();
                break;
            case "summon":
                new Summon(event).run();
                break;
            default:
                event.reply("Unknown command!").setEphemeral(true).queue();
        }
    }
}
