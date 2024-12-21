import java.util.ArrayList;

/**
 * Team Project -- MessageDatabaseInterface
 *
 * Interface for MessageDatabase class. This involved all the important methods in Message class.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 *
 * @version Apr 16, 2024
 *
 */

public interface MessageDatabaseInterface {

    /**
     * Read the messages from message file, and initialize the field messages in the class
     * @return true if messages are read successfully, otherwise false
     */
    boolean readMessages();

    /**
     * Get a specific user's chat history with others
     * @param username username of chat history that you want to find
     * @return ArrayList of Message that he communicate with other users
     */
    ArrayList<Message> getChatHistory(String username, String recipient);

    /**
     * Delete a message for a specific user and specific message
     * @param message message you want to delete
     */
    boolean deleteMessage(Message message);

    /**
     * Add a message into field messages in class for the sender and each recipient
     * @param message
     */
    void addMessage(Message message);

    /**
     * Save the message into the message file, by rewriting everything again
     */
    void saveMessages();
}
