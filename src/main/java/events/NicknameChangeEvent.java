package events;

import molly.Molly;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class NicknameChangeEvent extends ListenerAdapter {

    /**
     * Reset nickname back to 'Molly' when someone tries to change it.
     *
     * @param event GuildMemberUpdateNicknameEvent
     */
    @Override
    public void onGuildMemberUpdateNickname(@NotNull GuildMemberUpdateNicknameEvent event) {
        if (event.getMember().getUser() == event.getJDA().getSelfUser()) {
            event.getMember().modifyNickname("Molly").queue();
        }

        System.out.println(event.getMember().getId());
        // Someone changed my name, how illegal!
        if (event.getMember() == event.getGuild().getMemberById("315151980731301898")) {
            Molly.logger.info("Someone tried to change your name to " + event.getUser().getName() + "!");
            event.getMember().modifyNickname("Michael").queue();
        }
    }
}
