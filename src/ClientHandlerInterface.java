import java.util.ArrayList;

/**
 * Team Project -- ClientHandlerInterface
 * <p>
 * Interface that stores all the methods in client handler
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30 am
 * @version Apr 16, 2024
 */
public interface ClientHandlerInterface {
    /**
     * Main logic for client handler, this will handle command lines, reading input and communicate with Client
     */
    void run();

    /**
     * Send message to the client paired with this handler
     *
     * @param message message being
     */
    void sendMessage(String message);

    /**
     * Get current user's username, corresponded with client handler
     *
     * @return return the username
     */
    String getUsername();

    /**
     * Shutdown the client handler thread for corresponded client
     */
    void shutdown();

    /**
     * Find the user if users file, and read the data line
     *
     * @param username     username of target user
     * @param userDatabase user database initialized in the class
     * @return return the Profile class of specific user
     */
    Profile findUser(String username, UserDatabase userDatabase);

    /**
     * Determine whether this user already registered in the users file
     *
     * @param username     username of target user
     * @param userDatabase user database initialized in the class
     * @return true if user already registered, otherwise false
     */
    boolean isExist(String username, UserDatabase userDatabase);

    /**
     * Get the current user's friend list
     *
     * @param user     Profile class of current user
     * @param filename filename where friends data are stored
     * @return Arraylist of friends
     */
    ArrayList<String> getFriendList(Profile user, String filename);

    /**
     * Get te current user's block list
     *
     * @param user     Profile class of current user
     * @param filename filename where blocks are stored
     * @return ArrayList of blocks
     */
    ArrayList<String> getBlockList(Profile user, String filename);
}
