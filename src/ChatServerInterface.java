import java.util.ArrayList;

/**
 * Team Project -- ChatServerInterface
 * <p>
 * Interface that stored methods in chat server
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30 am
 * @version Apr 16, 2024
 */
public interface ChatServerInterface {
    /**
     * Starts the server to accept client connections.
     */
    void startServer();

    /**
     * Adds a new client handler to manage messages for a connected client.
     *
     * @param username      the username of the client
     * @param clientHandler the client handler associated with the client
     */
    void addClient(String username, ClientHandler clientHandler);

    /**
     * Send message to a specific client using client handler
     *
     * @param username the username of recipient client
     * @param message  the message be sent
     * @return true if the message is sent, otherwise false
     */
    boolean sendMessageToClient(String username, String message);

    /**
     * Broadcast the message to a specific group of friends
     *
     * @param message  the message be broadcast
     * @param username the username of sender
     * @param friends  the list of friends
     */
    void broadcastMessageToFriends(String message, String username, ArrayList<String> friends);

    /**
     * Broadcast message to every connected users, except for users being blocked
     *
     * @param message  message be broadcast
     * @param username username of the sender
     * @param blocks   list of users being blocked
     */
    void broadcastMessageToAll(String message, String username, ArrayList<String> blocks);

    /**
     * Remove a client from the server
     *
     * @param username username of client being removed
     */
    void removeClient(String username);


}
