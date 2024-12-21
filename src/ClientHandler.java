import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientHandler implements Runnable, ClientHandlerInterface {
    private final Socket socket;
    private final ChatServer server;
    private final UserDatabase userDatabase;
    private final String friendListFilename;
    private final String blockListFilename;
    private PrintWriter out;
    private BufferedReader in;
    private Profile user;
    private boolean done;
    private String message;
    private String recipient;
    public ClientHandler(Socket socket, ChatServer server) throws IOException {
        //initialize fields
        this.done = false;
        this.friendListFilename = "FriendList.txt";
        this.blockListFilename = "BlockList.txt";
        this.socket = socket;
        this.server = server;
        this.userDatabase = new UserDatabase("UsersFile.txt");
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public void run() {
        try {
            //read lines from Client end until it is done
            String line = in.readLine();

            while (line != null && !done) {
                if (line.equalsIgnoreCase("Create")) {
                    createAccount();
                } else if (line.equalsIgnoreCase("login")) {
                    login();
                }
                line = in.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewBlocks() throws IOException {
        ArrayList<String> blocks = getBlockList(user, blockListFilename);
        for (String username: blocks) {
            out.println(username);
        }
        out.println("END_OF_BLOCK");

        String line = in.readLine();
        while (line != null & !done) {
            if (line.equalsIgnoreCase("addBlock")) {
                addBlock();
            } else if (line.equalsIgnoreCase("removeBlock")) {
                removeBlock();
            } else if (line.equalsIgnoreCase("back")) {
                options();
            }
            line = in.readLine();
        }
    }
    private void addBlock() throws IOException {
        String block = in.readLine();
        user.addBlock(block, "BlockList.txt");
        findUser(block, userDatabase).removeFriend(getUsername(), "FriendList.txt");
        viewBlocks();
    }

    private void removeBlock() throws IOException {
        String block = in.readLine();
        if (getBlockList(user, blockListFilename).contains(block)) {
            user.removeBlock(block, "BlockList.txt");
        }
        viewBlocks();
    }

    private void viewFriends() throws IOException {
        String friends = String.valueOf(getFriendList(user, friendListFilename));
        out.println(friends);

        String users = String.valueOf(getAllUserList(userDatabase));
        System.out.println("user sent");
        out.println(users);

        String line = in.readLine();
        while (line != null & !done) {
            if (line.equalsIgnoreCase("addFriend")) {
                addFriend();
            } else if (line.equalsIgnoreCase("removeFriend")) {
                removeFriend();
            } else if (line.equalsIgnoreCase("back")) {
                options();
            }
            line = in.readLine();
        }
    }

    public void removeFriend() throws IOException {
        String friend = in.readLine();
        if (getFriendList(user, "FriendList.txt").contains(friend)) {
            user.removeFriend(friend, "FriendList.txt");
        }
        viewFriends();
    }

    public void addFriend() throws IOException {
        String friend = in.readLine();
//        if (isExist(friend, userDatabase)) {
//
//        } else {
//            System.out.println("friend doesn't exist");
//        }
        user.addFriend(friend, friendListFilename);
        viewFriends();
    }

    public void login() {
        String username = null;
        String password = null;
        try {
            do {
                username = in.readLine();
                password = in.readLine();

                System.out.println("username: " + username);
                System.out.println("password: " + password);

                user = findUser(username, userDatabase);

                if (user != null && password.contentEquals(user.getPassword())) {
                    out.println("true");
                    break;
                } else {
                    out.println("false");
                }
            } while (true);
            options();
        } catch (Exception e) {
            shutdown();
        }
    }

    public void options() throws IOException {
        String line = in.readLine();

        while (line != null & !done) {
            if (line.equalsIgnoreCase("viewFriends")) {
                viewFriends();
            } else if (line.equalsIgnoreCase("viewBlocks")) {
                viewBlocks();
            } else if (line.equalsIgnoreCase("quit")) {
                quit();
            } else if (line.equalsIgnoreCase("directMessage")) {
                directMessage();
            } else if (line.equalsIgnoreCase("back")) {
                login();
            } else if (line.equalsIgnoreCase("public")) {
                publicOption();
            }
            line = in.readLine();
        }

    }

    private void publicOption() throws IOException {
        String users = String.valueOf(getAllUserList(userDatabase)).replace("[", "").replace("]", "");

        out.println(users);

        recipient = in.readLine();
        String line = in.readLine();

        while (line != null) {
            if (line.equalsIgnoreCase("messaging")) {
                messaging();
            } else if (line.equalsIgnoreCase("sendInfo")) {
                updateChat(recipient);
            }
            line = in.readLine();
        }
    }

    private void directMessage() throws IOException {
        String friends = String.valueOf(getFriendList(user, friendListFilename)).replace("[", "").replace("]", "");

        out.println(friends);

        recipient = in.readLine();
        String line = in.readLine();

        while (line != null) {
            if (line.equalsIgnoreCase("messaging")) {
                messaging();
            } else if (line.equalsIgnoreCase("sendInfo")) {
                updateChat(recipient);
            }
            line = in.readLine();
        }
    }

    public void messaging() throws IOException {
        ArrayList<String> recipients = new ArrayList<>();

        System.out.println(recipient);
        if (isExist(recipient, userDatabase)) {
            recipients.add(recipient);
        }
        System.out.println(recipients);
        updateChat(recipient);

        String messageContent;
        Message messageToAdd;


        while ((message = in.readLine()) != null) {
            System.out.println(message);
            if (message.equalsIgnoreCase("sendInfo")) {
                updateChat(recipient);
            } else if (message.substring(0,2).equals("/d")) {
                String sender = message.split(":")[1].substring(6);
                messageContent = message.split(":")[0].substring(2);
                server.getMessageDatabase().deleteMessage(new Message(sender, recipients, messageContent));
                updateChat(recipient);
            }
            else {

                String sender = message.split(":")[1].substring(6);
                messageContent = message.split(":")[0];

                messageToAdd = new Message(sender, recipients, messageContent);
                server.getMessageDatabase().addMessage(messageToAdd);
                updateChat(recipient);
            }

        }
    }


    private void updateChat(String recipient) {
        ArrayList<Message> history = server.getMessageDatabase().getChatHistory(getUsername(), recipient);
        for (Message historyMessage : history) {
            out.println(historyMessage);
        }
        out.println("END_OF_HISTORY");
    }


    private void quit() {
        server.removeClient(getUsername());
        shutdown();
    }


    public void createAccount() throws IOException {
        String username = in.readLine();
        String email = in.readLine();
        String age = in.readLine();
        String password = in.readLine();
        String bio = in.readLine();

        user = findUser(username, userDatabase);
        userDatabase.createNewUser(username, Integer.parseInt(age), email, password, bio);

        login();
    }

    public void sendMessage(String message) { //sent message to Client
        this.message = message;
        out.println(message);
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() { //get Client's username
        return user.getUsername();
    }

    public void shutdown() { //shutdown Client
        try {
            done = true;
            in.close();
            out.close();
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Profile findUser(String username, UserDatabase userDatabase) { //find specific user in database
        //userDatabase.readUsers();
        ArrayList<Profile> allUsers = userDatabase.viewAllUsers();
        for (Profile user : allUsers) {
            if (user.getUsername().contentEquals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean isExist(String username, UserDatabase userDatabase) { //whether the user exist in the database
        //userDatabase.readUsers();
        ArrayList<Profile> allUsers = userDatabase.viewAllUsers();
        for (Profile user : allUsers) {
            if (user.getUsername().contentEquals(username)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getFriendList(Profile user, String filename) { //get user's friend list
        user.readFriends(filename);
        return user.getFriends();
    }

    public ArrayList<String> getBlockList(Profile user, String filename) { //get user's block list
        user.readBlocks(filename);
        return user.getBlocks();
    }

    public ArrayList<String> getAllUserList(UserDatabase userDatabase) {
        ArrayList<String> allUser = new ArrayList<>();
        ArrayList<Profile> allUserProfile = userDatabase.viewAllUsers();
        for (Profile user : allUserProfile) {
            allUser.add(user.getUsername());
        }
        return allUser;
    }

}
