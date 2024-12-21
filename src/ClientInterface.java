/**
 * Team Project -- ClientInterface
 * <p>
 * Interface that stores all the methods in client class
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30 am
 * @version Apr 16, 2024
 */
public interface ClientInterface {

    /**
     * Start the client by initialize it and connect with socket and server
     */
    void startClient();

    /**
     * Read messages from socket
     */
    void readMessages();

    /**
     * Write messages to server and quit when commanded
     */
    void writeMessages();
}
