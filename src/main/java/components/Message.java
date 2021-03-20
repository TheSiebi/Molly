package components;

import net.dv8tion.jda.api.entities.TextChannel;
import java.util.HashMap;

public class Message {
    private HashMap<String, String> oldLeaveMessageID;
    private HashMap<String, String> oldJoinMessageID;
    private String summonMsgID;
    private TextChannel channel;

    /**
     * Constructs a new message instance which represents Molly's last "/summon" message in a channel.
     * It keeps track of all old join and leave notifications of each user which reacted to this message.
     *
     * @param msgID the ID of the "/summon" msg
     * @param channel the textchannel in which the "/summon" msg was sent
     */
    public Message(String msgID, TextChannel channel) {
        this.summonMsgID = msgID;
        this.channel = channel;
        oldJoinMessageID = new HashMap<>();
        oldLeaveMessageID = new HashMap<>();
    }

    /**
     * To keep track of the users old join messages
     *
     * @param user the user which added a reaction
     * @param msgID the reaction message
     */
    public void join(String user, String msgID){

        oldJoinMessageID.put(user, msgID);

        if(oldLeaveMessageID.containsKey(user)){
            channel.deleteMessageById(oldLeaveMessageID.get(user)).queue();
            oldLeaveMessageID.remove(user);
        }
    }

    /**
     * To keep track of the users old leave messages
     *
     * @param user the user which withdrew his reaction
     * @param msgID the reaction message
     */
    public void leave(String user, String msgID){

        oldLeaveMessageID.put(user, msgID);

        if (oldJoinMessageID.containsKey(user)){
            channel.deleteMessageById(oldJoinMessageID.get(user)).queue();
            oldJoinMessageID.remove(user);
        }
    }

    /**
     * Deletes the old summon message including all it's reaction messages
     */
    public void delete(){

        for (String user : oldLeaveMessageID.keySet()){
            channel.deleteMessageById(oldLeaveMessageID.get(user)).queue();
        }

        for (String user : oldJoinMessageID.keySet()){
            channel.deleteMessageById(oldJoinMessageID.get(user)).queue();
        }

        channel.deleteMessageById(summonMsgID).queue();
    }
}
