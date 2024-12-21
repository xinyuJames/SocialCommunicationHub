import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Team Project -- Client
 * <p>
 * This class is the Concurrent Client class
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Saturday 4:30pm
 * @version Apr 13, 2024
 */

public class Client {
    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private int threadNum;
    private final Scanner scanner;
    private String username;
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel usernameLabel;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JLabel passwordLabel;
    private static JButton login;
    private static Client client;
    private static JLabel loggedIn;
    private static JLabel account;
    private static JButton yes;
    private static JButton no;
    private static JLabel emailLabel;
    private static JTextField emailField;
    private static JLabel ageLabel;
    private static JTextField ageField;
    private static JLabel createPasswordLabel;
    private static JTextField createPasswordField;
    private static JLabel bioLabel;
    private static JTextField bioField;
    private static JLabel options;
    private static JButton createAccount;
    private static JButton addFriend;
    private static JButton removeFriend;
    private static JButton directMessage;
    private static JButton quit;
    private static JButton blockUser;
    private static JButton removeBlock;
    private static JButton viewFriends;
    private static JButton viewBlock;
    private static JButton chatHistory;
    private static JButton deleteMessage;
    private JTextField messageField;
    private JTextArea chatArea;
    private JLabel statusLabel;
    private JButton backButtonCa;
    private JTextArea usersTextArea;

    public Client(int serverPort) throws IOException {

        socket = new Socket("localhost", serverPort);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        scanner = new Scanner(System.in);

        String isCorrect;
    }

    public static void main(String[] args) throws IOException {
        client = new Client(8888);
        client.createGUI();
        //client.startThread();
    }

    /*public void startThread() {
        new Thread(this::updating).start();
    }

    private void updating() {

    } */

    public void createGUI() throws IOException {
        frame = new JFrame("Social Media");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        //Add more based on examples
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel optionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Welcome to Social Media");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 30));
        gbc.gridwidth = 2;
        optionsPanel.add(titleLabel, gbc);

        JLabel accountLabel = new JLabel("Do you have an Account?");
        accountLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        gbc.gridwidth = 1;
        optionsPanel.add(accountLabel, gbc);

        JButton yesButton = new JButton("Yes");
        yesButton.setFont(new Font("Arial", Font.PLAIN, 16));
        optionsPanel.add(yesButton, gbc);

        JButton noButton = new JButton("No");
        noButton.setFont(new Font("Arial", Font.PLAIN, 16));
        optionsPanel.add(noButton, gbc);

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        yesButton.addActionListener(e -> {
            client.goToLogin();
            writer.println("login");
        });

        noButton.addActionListener(e -> {
            client.createAccount();
            writer.println("Create");
        });
        frame.setVisible(true);
    }

    public void createAccount() {
        frame.getContentPane().removeAll();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        usernameLabel = new JLabel("Enter your username");
        usernameField = new JTextField();
        panel.add(createFieldPanel(usernameLabel, usernameField));

        emailLabel = new JLabel("Enter your email: ");
        emailField = new JTextField();
        panel.add(createFieldPanel(emailLabel, emailField));

        ageLabel = new JLabel("Enter your age: ");
        ageField = new JTextField();
        panel.add(createFieldPanel(ageLabel, ageField));

        createPasswordLabel = new JLabel("Create your password: ");
        createPasswordField = new JTextField();
        panel.add(createFieldPanel(createPasswordLabel, createPasswordField));

        bioLabel = new JLabel("Type out a bio: ");
        bioField = new JTextField();
        panel.add(createFieldPanel(bioLabel, bioField));

        createAccount = new JButton("Create Account!");
        panel.add(createAccount);

        statusLabel = new JLabel("");
        panel.add(statusLabel);

        createAccount.addActionListener(e -> {
            try {
                if (!emailField.getText().contains("@")) {
                    statusLabel.setText("Email must contain @");
                    return;
                }

                try {
                    int age = Integer.parseInt(ageField.getText());
                    if (age < 0) {
                        statusLabel.setText("Age must be a positive integer");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Age must be a valid integer");
                    return;
                }

                client.submitAccount(usernameField.getText(),
                        emailField.getText(), ageField.getText(), createPasswordField.getText(),
                        bioField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public void submitAccount(String username, String email, String age, String password, String bio) throws IOException {
        writer.println(username);
        writer.println(email);
        writer.println(age);
        writer.println(password);
        writer.println(bio);
        this.username = username;

        client.goToLogin();
    }

    private JPanel createFieldPanel(JLabel label, JTextField textField) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.add(label);
        fieldPanel.add(textField);

        Dimension labelSize = label.getPreferredSize();
        label.setPreferredSize(new Dimension(150, labelSize.height));

        textField.setPreferredSize(new Dimension(200, textField.getPreferredSize().height));

        fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        return fieldPanel;
    }

    public void goToLogin() {
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Login to Your Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(loginButton, gbc);

        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.RED);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(statusLabel, gbc);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        loginButton.addActionListener(e -> {
            try {
                client.login(usernameField, passwordField);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        frame.revalidate();
        frame.repaint();
    }


    public void login(JTextField username, JPasswordField password) throws IOException {
        String response;
        this.username = username.getText();

        this.writer.println(username.getText());
        this.writer.println(password.getPassword());

        response = this.reader.readLine();

        if (response.equalsIgnoreCase("true")) {
            client.options();
        } else {
            statusLabel.setText("Try again");
        }
    }

    public void options() {
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel optionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel optionsLabel = new JLabel("What would you like to do?");
        optionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        optionsPanel.add(optionsLabel, gbc);

        JButton directMessageButton = new JButton("Direct Message");
        stylizeButton(directMessageButton);
        optionsPanel.add(directMessageButton, gbc);
        directMessageButton.addActionListener(e -> {
            try {
                client.directMessage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton viewFriendsButton = new JButton("View Friends");
        stylizeButton(viewFriendsButton);
        optionsPanel.add(viewFriendsButton, gbc);
        viewFriendsButton.addActionListener(e -> {
            try {
                client.viewFriends();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton viewBlockButton = new JButton("View Blocks");
        stylizeButton(viewBlockButton);
        optionsPanel.add(viewBlockButton, gbc);
        viewBlockButton.addActionListener(e -> {
            client.viewBlocks();
        });

        JButton goPublic = new JButton("Go Public");
        stylizeButton(goPublic);
        optionsPanel.add(goPublic, gbc);
        goPublic.addActionListener(e -> {
            try {
                client.goPublic();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton quitButton = new JButton("Exit");
        stylizeButton(quitButton);
        optionsPanel.add(quitButton, gbc);
        quitButton.addActionListener(e -> {
            client.quit();
        });

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back"); //Put the back button in a different area.
        backButtonPanel.add(backButton);
        backButton.addActionListener(e -> {
            writer.println("back");
            client.goToLogin();
        });

        mainPanel.add(backButtonPanel, BorderLayout.SOUTH);


        frame.setLocationRelativeTo(null);

        frame.revalidate();
        frame.repaint();
    }

    private void goPublic() throws IOException {
        writer.println("public");

        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel messagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 5, 10);

        JLabel instructionLabel = new JLabel("Select a friend to send a message:");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 10, 5, 10);
        messagePanel.add(instructionLabel, gbc);

        String users = reader.readLine();
        System.out.println(users);
        String[] usersList = users.split(", ");

        for (String user : usersList) {
            System.out.println("Username: " + username);
            System.out.println("User: " + user);
            if (!user.equalsIgnoreCase(username)) {
                JButton userButton = new JButton(user.trim());
                gbc.gridy++;
                gbc.gridx = 0;
                gbc.weightx = 0;
                gbc.insets = new Insets(40, 10, 5, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.WEST;
                messagePanel.add(userButton, gbc);

                userButton.addActionListener(e -> {
                    try {
                        client.messaging(user.trim());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }

        mainPanel.add(messagePanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        frame.revalidate();
        frame.repaint();
    }

    private void stylizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void directMessage() throws IOException {
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel messagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 5, 10);

        JLabel instructionLabel = new JLabel("Select a friend to send a message:");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 10, 5, 10);
        messagePanel.add(instructionLabel, gbc);

        writer.println("directMessage");
        String friends = reader.readLine();
        String[] friendsList = friends.split(",");


        for (String friend : friendsList) {
            JButton friendButton = new JButton(friend.trim());
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.weightx = 0;
            gbc.insets = new Insets(40, 10, 5, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            messagePanel.add(friendButton, gbc);

            friendButton.addActionListener(e -> {
                try {
                    client.messaging(friend.trim());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        mainPanel.add(messagePanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        frame.revalidate();
        frame.repaint();
    }

    public void messaging(String friend) throws IOException {
        frame.getContentPane().removeAll();
        panel = new JPanel(new GridLayout(3, 2));

        writer.println(friend);
        writer.println("messaging");

        frame.setTitle("Chat Room");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        client.updateChat();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        messageField = new JTextField();
        bottomPanel.add(messageField, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        JButton sendButton = new JButton("Send");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.println("sendInfo");
                    client.updateChat();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendMessage(friend);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bottomPanel.add(refreshButton, BorderLayout.WEST);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }


    private void sendMessage(String friend) throws IOException {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            messageField.setText("");
            writer.println(message + " : from " + username);
            client.updateChat();
        }
    }

    private void updateChat() throws IOException {
        chatArea.setText("");
        try {
            String fromServer;
            int counter = 0;
            String sender = "";
            String messageContent = "";
            String time;
            System.out.println("outside while loop");
            while (!(fromServer = reader.readLine()).contentEquals("END_OF_HISTORY")) {
                System.out.println("Inside while loop");
                if (counter == 0) {
                    sender = fromServer.split(":")[1].substring(1);
                    counter++;
                    reader.readLine(); //This is to skip the recipient
                } else if (counter == 1) {
                    messageContent = fromServer.split(":")[1].substring(1);
                    counter++;
                } else if (counter == 2) {
                    time = fromServer.substring(5);

                    if (sender.equalsIgnoreCase(username)) {
                        chatArea.append(time + " You: " + messageContent + "\n");
                    } else {
                        chatArea.append(time + " " + sender + ": " + messageContent + "\n");
                    }
                    reader.readLine(); //This is to skip the whitespace.
                    counter = 0;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void quit() {
        writer.println("quit");
        frame.dispose();
    }

    private void viewBlocks() {
        frame.getContentPane().removeAll();
        panel = new JPanel(new GridLayout(3, 2));

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        writer.println("viewBlocks");
        ArrayList<String> blocks = new ArrayList<>();
        try {
            String fromServer;
            while (!(fromServer = reader.readLine()).contentEquals("END_OF_BLOCK")) {
                blocks.add(fromServer);
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        JTextArea blocksTextArea = new JTextArea(String.valueOf(blocks));
        blocksTextArea.setEditable(false);
        JScrollPane blocksScrollPane = new JScrollPane(blocksTextArea);
        blocksScrollPane.setPreferredSize(new Dimension(400, 200));
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(blocksScrollPane, gbc);

        JTextField addBlockField = new JTextField(20);
        gbc.gridx++;
        contentPanel.add(addBlockField, gbc);

        JButton addBlockButton = new JButton("Add");
        gbc.gridx++;
        contentPanel.add(addBlockButton, gbc);
        addBlockButton.addActionListener(e -> {
            client.addBlock(addBlockField.getText());
        });

        JButton removeBlockButton = new JButton("Remove");
        gbc.gridx++;
        contentPanel.add(removeBlockButton, gbc);
        removeBlockButton.addActionListener(e -> {
            client.removeBlock(addBlockField.getText());
        });

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButtonPanel.add(backButton);
        backButton.addActionListener(e -> {
            writer.println("back");
            client.options();
        });

        mainPanel.add(backButtonPanel, BorderLayout.SOUTH);


        frame.setLocationRelativeTo(null);

        frame.revalidate();
        frame.repaint();

    }

    private void removeBlock(String block) {
        writer.println("removeBlock");
        writer.println(block);
        client.viewBlocks();
    }

    private void addBlock(String block) {
        writer.println("addBlock");
        writer.println(block);
        client.viewBlocks();
    }

    public void viewFriends() throws IOException {
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 10, 10, 10);

        writer.println("viewFriends");
        String friends = reader.readLine();
        String users = reader.readLine();

        JLabel friendsLabel = new JLabel("Friends:");
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(friendsLabel, gbc);

        JLabel usersLabel = new JLabel("Users:");
        gbc.gridx = 0;
        contentPanel.add(usersLabel, gbc);

        usersTextArea = new JTextArea(users);
        usersTextArea.setEditable(false);
        JScrollPane usersScrollPane = new JScrollPane(usersTextArea);
        usersScrollPane.setPreferredSize(new Dimension(300, 200));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(usersScrollPane, gbc);

        JTextArea friendsTextArea = new JTextArea(friends);
        friendsTextArea.setEditable(false);
        JScrollPane friendsScrollPane = new JScrollPane(friendsTextArea);
        friendsScrollPane.setPreferredSize(new Dimension(300, 200));
        gbc.gridx = 1;
        contentPanel.add(friendsScrollPane, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel addFriendLabel = new JLabel("Add Friend:");
        gbc.gridy++;
        contentPanel.add(addFriendLabel, gbc);

        JTextField addFriendField = new JTextField(30);
        gbc.gridy++;
        contentPanel.add(addFriendField, gbc);

        JButton addFriendButton = new JButton("Add");
        gbc.gridy++;
        contentPanel.add(addFriendButton, gbc);
        addFriendButton.addActionListener(e -> {
            try {
                client.addFriend(addFriendField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton removeFriendButton = new JButton("Remove");
        gbc.gridy++;
        contentPanel.add(removeFriendButton, gbc);
        removeFriendButton.addActionListener(e -> {
            try {
                client.removeFriend(addFriendField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButtonPanel.add(backButton);
        backButton.addActionListener(e -> {
            writer.println("back");
            client.options();
        });
        mainPanel.add(backButtonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    private void removeFriend(String friend) throws IOException {
        writer.println("removeFriend");
        writer.println(friend);
        client.viewFriends();
    }

    private void addFriend(String friend) throws IOException {
        writer.println("addFriend");
        writer.println(friend);
        client.viewFriends();
    }

    private void readMessages() {
        String fromServer;
        try {
            while ((fromServer = reader.readLine()) != null) {
                System.out.println(fromServer);
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    private void writeMessages() {
        while (true) {

            String message = scanner.nextLine();
            writer.println(message + " : from " + username);
            System.out.println("Message sent.");
            if (message.equalsIgnoreCase("/quit")) {
                break;
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Socket close error: " + e.getMessage());
        }
    }
}



