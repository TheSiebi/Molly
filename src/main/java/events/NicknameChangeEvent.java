package events;

import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class NicknameChangeEvent extends ListenerAdapter {

    /**
     * Reset nickname back to 'Molly' when someone tries to change it.
     *
     * @param event
     */
    @Override
    public void onGuildMemberUpdateNickname(@NotNull GuildMemberUpdateNicknameEvent event) {
        if (event.getMember().getUser() == event.getJDA().getSelfUser()) {
            event.getMember().modifyNickname("Molly").queue();
        }
    }
}
