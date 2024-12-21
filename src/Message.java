import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Team Project -- Message
 * <p>
 * Message class that stores essential information about a message
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 24, 2024
 */
public class Message implements MessageInterface {
    private final String sender;
    private final ArrayList<String> recipient;
    private final String content;
    private LocalDateTime time;

    public Message(String sender, ArrayList<String> recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.time = LocalDateTime.now();
    }

    public Message(String sender, ArrayList<String> recipient, String content, LocalDateTime time) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public ArrayList<String> getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Sender: " + sender + "\nRecipients: " + String.join(",", recipient)
                + "\nContent: " + content + "\nTime: " + time.format(timeFormatter) + "\n";
    }

    public boolean equals(Object o) {
        if (o instanceof Message) {
            Message oMessage = (Message) o;
            return oMessage.getSender().contentEquals(this.sender) && oMessage.getRecipient().equals(this.recipient)
                    && oMessage.getContent().contentEquals(this.content);
        }
        return false;
    }
}
