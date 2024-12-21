import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Team Project -- MessageDatabase
 * <p>
 * MessageDatabase class will store the messages sent by the clients and store information and have methods.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 24, 2024
 */
public class MessageDatabase implements MessageDatabaseInterface {
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Message>> messages = new ConcurrentHashMap<>();
    private final String messageFilename;

    public MessageDatabase(String messageFilename) {
        this.messageFilename = messageFilename;
        System.out.println(readMessages());
//        ArrayList<Message> history = getChatHistory(messageFilename);
//        for (Message historyMessage : history) {
//            System.out.println(historyMessage);
//        }
    }

    public boolean readMessages() {
        File file = new File(messageFilename);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!file.exists()) {
            return false; // Return false if the file does not exist.
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String sender = null;
            ArrayList<String> recipients = new ArrayList<>();
            String content = null;
            LocalDateTime time = null;
            boolean completeMessage = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Trim whitespace
                if (line.isEmpty()) {
                    if (completeMessage) {
                        // Process the complete message
                        Message message = new Message(sender, recipients, content, time);
                        storeMessage(message, sender); // Store message under sender's key
                        // Reset variables
                        sender = null;
                        recipients = new ArrayList<>();
                        content = null;
                        time = null;
                        completeMessage = false;
                    }
                } else {
                    // Parse each line based on its prefix
                    if (line.startsWith("Sender: ")) {
                        sender = line.substring(8);
                    } else if (line.startsWith("Recipients: ")) {
                        String[] parts = line.substring(12).split(",");
                        for (String part : parts) {
                            recipients.add(part.trim());
                        }
                    } else if (line.startsWith("Content: ")) {
                        content = line.substring(9);
                    } else if (line.startsWith("Time: ")) {
                        time = LocalDateTime.parse(line.substring(6), formatter);
                        completeMessage = true; // Marks that the next empty line should trigger processing
                    }
                }
            }

            // Check if the last message needs to be processed (no trailing newline)
            if (completeMessage) {
                Message message = new Message(sender, recipients, content, time);
                storeMessage(message, sender); // Store the last message under sender's key
            }

            return true;
        } catch (IOException e) {
            System.err.println("Failed to read messages: " + e.getMessage());
            return false;
        }
    }

    private void storeMessage(Message message, String senderKey) {
        // Store the message under the sender's key only
        messages.putIfAbsent(senderKey, new CopyOnWriteArrayList<>());
        messages.get(senderKey).add(message);
    }

    public synchronized ArrayList<Message> getChatHistory(String username, String recipient) {
        ArrayList<Message> userMessages = new ArrayList<>();
        for (CopyOnWriteArrayList<Message> messageList : messages.values()) {
            for (Message message : messageList) {
                if ((message.getSender().equals(username) || message.getRecipient().contains(username)) &&
                        (message.getSender().equals(recipient) || message.getRecipient().contains(recipient))) {
                    userMessages.add(message);
                }
            }
        }
        sortMessageByTime(userMessages);

        return userMessages;


    }

    public void sortMessageByTime(ArrayList<Message> messages) {
        Collections.sort(messages, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return  o1.getTime().compareTo(o2.getTime());
            }
        });
    }

    public synchronized boolean deleteMessage(Message message) {
        for (Map.Entry<String, CopyOnWriteArrayList<Message>> entry: messages.entrySet()) {
            if (entry.getKey().equals(message.getSender())) {
                for (Message message1:entry.getValue()) {
                    if (message1.getContent().equals(message.getContent())) {
                        messages.get(message.getSender()).remove(message);
                    }
                }
            }
            
            //if (entry.getKey().equals(message.getSender() && entry.))
        }
        saveMessages();
        return true;
      /*  if (messages.containsKey(message.getSender())) {
            messages.get(message.getSender()).remove(message);
            saveMessages();
            return true;
        }
        return false;*/
    }

    public void addMessage(Message message) { //add message to the field messages for each recipient and sender

        messages.putIfAbsent(message.getSender(), new CopyOnWriteArrayList<>());
        messages.get(message.getSender()).add(message);
        saveMessages();
    }

    public void saveMessages() { //save messages into the Message file by rewriting them again
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(messageFilename, false), true);
            for (Map.Entry<String, CopyOnWriteArrayList<Message>> entry : messages.entrySet()) {
                for (Message message : entry.getValue()) {
                    writer.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
