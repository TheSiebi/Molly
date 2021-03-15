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
    public Message(String msgID, TextChannel channel){
        this.summonMsgID = msgID;
        this.channel = channel;
        oldJoinMessageID = new HashMap<>();
        oldLeaveMessageID = new HashMap<>();
    }

    /**
     * To keep track of the users old join messages
     *
     * @param user the user which added a reaction
     * @param msgID the message to which the user reacted
     */
    public void join(String user, String msgID){
        if (!oldJoinMessageID.containsKey(user)){
            oldJoinMessageID.put(user, msgID);
        }
        else{
            channel.deleteMessageById(oldJoinMessageID.get(user));
            oldJoinMessageID.replace(user, msgID);
        }
    }

    /**
     * To keep track of the users old leave messages
     *
     * @param user the user which withdrew his reaction
     * @param msgID the message to which the user reacted
     */
    public void leave(String user, String msgID){
        if(!oldLeaveMessageID.containsKey(user)){
            oldLeaveMessageID.put(user, msgID);
        }
        else{
            channel.deleteMessageById(oldLeaveMessageID.get(user));
            oldLeaveMessageID.replace(user, msgID);
        }
    }
}
