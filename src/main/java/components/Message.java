package components;

import java.util.HashMap;

public class Message {
    public HashMap<String, Integer> reactors;

    public void addReactor(String user){
        reactors.put(user, 1);
    }

    public void removeReactor(String user){
        try {
            reactors.remove(user);
        } catch(NullPointerException e) {

        }
    }
}
