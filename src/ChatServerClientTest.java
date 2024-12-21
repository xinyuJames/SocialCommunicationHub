/*
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChatServerClientTest {
    private ChatServer chatServer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        chatServer = new ChatServer();
        System.setOut(new PrintStream(outContent)); // Capture System.out prints
    }

    @Test
    public void testAddAndRemoveClient() {
        String username = "testUser";
        chatServer.addClient(username, new ManualMockClientHandler(username, chatServer));
        assertTrue("Client should be added", chatServer.connections.containsKey(username));

        chatServer.removeClient(username);
        assertFalse("Client should be removed", chatServer.connections.containsKey(username));
    }

    @Test
    public void testSendMessageToClient() {
        String username = "testUser";
        ManualMockClientHandler mockClientHandler = new ManualMockClientHandler(username, chatServer);
        chatServer.addClient(username, mockClientHandler);

        chatServer.sendMessageToClient(username, "Hello");
        assertTrue("Client should receive message", mockClientHandler.receivedMessages.contains("Hello"));
    }

    @Test
    public void testBroadcastMessageToAllExceptSender() {
        String sender = "sender";
        String receiver = "receiver";
        chatServer.addClient(sender, new ManualMockClientHandler(sender, chatServer));
        ManualMockClientHandler receiverHandler = new ManualMockClientHandler(receiver, chatServer);
        chatServer.addClient(receiver, receiverHandler);

        chatServer.broadcastMessageToAll("Hello World", sender, new ArrayList<>());

        assertTrue("Receiver should receive message", receiverHandler.receivedMessages.contains("Hello World"));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut); 
    }

    static class ManualMockClientHandler extends ClientHandler {
        public ArrayList<String> receivedMessages = new ArrayList<>();

        public ManualMockClientHandler(String username, ChatServer server) {
            super(null, server);
            this.user = new Profile(username, 20, "test@test.com", "pass", "bio", "pic.jpg");
        }

        @Override
        public void sendMessage(String message) {
            receivedMessages.add(message);
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }
    }
}
*/
