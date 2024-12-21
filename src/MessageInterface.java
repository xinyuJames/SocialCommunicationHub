import java.util.ArrayList;

/**
 * Team Project -- MessageInterface
 * <p>
 * Interface that contains important methods in Message class
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 15, 2024
 */
public interface MessageInterface {

    /**
     * Get sender of this message
     * @return sender's username of this message
     */
    String getSender();

    /**
     * Get recipients of this message
     * @return ArrayList of the recipients of this message
     */
    ArrayList<String> getRecipient();

    /**
     * Get content of this message
     * @return  content of this message in String
     */
    String getContent();

    /**
     * Make message toString in a specific format
     * @return a format more readable
     */
    String toString();

    /**
     * Check if the object is equals to a Message class
     * @param o object to compare
     * @return true if they are the same, otherwise false
     */
    boolean equals(Object o);

}
