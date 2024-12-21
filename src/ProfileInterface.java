import java.util.ArrayList;

/**
 * Team Project -- ProfileInterface
 *
 * Interface for the Profile class. This involved all the important methods in Profile class.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 *
 * @version Apr 16, 2024
 *
 */

public interface ProfileInterface {
    String getUsername();

    void setUsername(String username);

    int getAge();

    void setAge(int age);

    String getEmail();

    void setEmail(String email);

    void resetPassword(String password);

    String getBio();

    void setBio(String bio);

    boolean readFriends(String filename); //read FriendList file, initialize friends field

    boolean addFriend(String friend, String filename); //add friends username to field friends, and reprint all the lines back to file

    boolean removeFriend(String friendUsername, String filename); // remove friends username from field friends, and reprint all the lines back to file

    boolean readBlocks(String filename); //read BlockList file, initialize blocks field

    boolean addBlock(String blockUsername, String filename); //add blocks username to field blocks, and reprint all the lines back to file

    boolean removeBlock(String blockUsername, String filename); //remove friends username from field blocks, and reprint all the lines back to file

    ArrayList<String> getFriends(); //return field friends

    ArrayList<String> getBlocks(); //return field blocks

}
