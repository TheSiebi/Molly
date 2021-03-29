package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class Say {
    private GuildMessageReceivedEvent event;

    public Say(@NotNull GuildMessageReceivedEvent event) {
        this.event = event;
    }

    public void run() {

        // If the message was sent in the test channel
        if (event.getGuild().getId().equals("818531386599538688")) {
            String msg = event.getMessage().getContentRaw().substring(5);

            // Send a message to the "Krasse Channel"
            event.getJDA().getGuildById("315190207664226305").getTextChannelById("315190207664226305").sendMessage(msg).queue();
        }

    }
}
