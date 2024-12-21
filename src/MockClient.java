/*
import java.util.ArrayList;

public class MockClient {
    private String username;
    private ChatServer server;
    private ArrayList<String> receivedMessages = new ArrayList<>();

    public MockClient(String username, ChatServer server) {
        this.username = username;
        this.server = server;
    }

    public void registerToServer() {
        server.addClient(username, new ManualMockClientHandler(username, server));
    }

    public void sendMessageToServer(String message) {
        server.broadcastMessageToAll(message, username, new ArrayList<>());
    }

    public boolean hasReceivedMessage(String message) {
        return receivedMessages.contains(message);
    }
}
*/
