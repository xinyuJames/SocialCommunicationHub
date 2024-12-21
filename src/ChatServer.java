import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

//TODO: view chat history only for that person
//TODO: friend and block is not working
//TODO: dont add myself to recipient
public class ChatServer {
    private static final int PORT = 8888;
    private final ConcurrentHashMap<String, ClientHandler> connections = new ConcurrentHashMap<>();
    private ServerSocket server;
    private MessageDatabase messageDatabase;

    public ChatServer() {
        //initialize server
        messageDatabase = new MessageDatabase("MessageList.txt");
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started on port: " + PORT);
        } catch (IOException e) {
            System.out.println("Connection failed.");
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.startServer();
    }

    public void startServer() { //start server by accepting client's connection
        try {
            while (true) {
                Socket client = server.accept();
                ClientHandler ch = new ClientHandler(client, this);
                //start different threads for each ClientHandler
                new Thread(ch).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addClient(String username, ClientHandler clientHandler) { //connected users are stored in connections
        connections.put(username, clientHandler);
    }

    public boolean sendMessageToClient(String username, String message) { //send message to target client
        if (connections.containsKey(username)) {
            connections.get(username).sendMessage(message);
            return true;
        } else {
            System.out.println(username + " is not connected yet");
            return false;
        }
    }

    public void broadcastMessageToFriends(String message, String username, ArrayList<String> friends) {
        for (ClientHandler ch : connections.values()) {
            if (!ch.getUsername().contentEquals(username)) {
                if (friends.contains(ch.getUsername())) {
                    ch.sendMessage(message);
                }
            }
        }
    }

    public void broadcastMessageToAll(String message, String username, ArrayList<String> blocks) {
        for (ClientHandler ch : connections.values()) {
            if (!ch.getUsername().contentEquals(username)) {
                if (!blocks.contains(ch.getUsername())) {
                    ch.sendMessage(message);
                }
            }
        }
    }

    public void removeClient(String username) { //user disconnected and be removed from connections
        connections.remove(username);
    }

    public MessageDatabase getMessageDatabase() {
        return messageDatabase;
    }
}
