import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Team Project -- Client
 * <p>
 * This class is the Concurrent Client class
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Saturday 4:30pm
 * @version Apr 13, 2024
 */

public class Client2 {
    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private int threadNum;
    private final Scanner scanner;
    private String username;

    public Client2(int serverPort) throws IOException {
        socket = new Socket("localhost", serverPort);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        scanner = new Scanner(System.in);

        String isCorrect;

        System.out.println("Welcome to chatroom, here are the commands you can use:\n" +
                "1. type in format \"DM:username:message\" to send a direct message\n" +
                "2. just type the message to send it to every user except for people you blocked\n" +
                "That's it.");
        System.out.println("Type in your username:");
        String response = scanner.nextLine();
        username = response;
        writer.println(response);

        String output = reader.readLine();
        System.out.println(output);

        if (output.equalsIgnoreCase("User found!")) {
            do {
                System.out.println("Type in your password: ");
                response = scanner.nextLine();
                writer.println(response);

                isCorrect = reader.readLine();
            } while (isCorrect.equals("N"));
            System.out.println(isCorrect);
        } else {
            System.out.println("Create a new account");

            System.out.println("Enter in an email");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Enter your age");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Create a password: ");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Enter a bio: ");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Enter a profilePicture file");
            response = scanner.nextLine();
            writer.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        Client2 client = new Client2(8888);
        client.startClient();
    }

    public void startClient() {
        System.out.println("Start messaging!");
        new Thread(this::readMessages).start();
        writeMessages();
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



/*
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

*/
/**
 * Team Project -- Client
 * <p>
 * This class is the Concurrent Client class
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 16, 2024
 *//*


public class Client implements ClientInterface {
    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;
    private final Scanner scanner;
    private static JFrame frame;
    private static JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Client(int serverPort) throws IOException {
        socket = new Socket("localhost", serverPort);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        scanner = new Scanner(System.in);

        String isCorrect;

        System.out.println("Type in your username:");
        String response = scanner.nextLine();
        writer.println(response);

        String output = reader.readLine();
        System.out.println(output);

        if (output.equalsIgnoreCase("User found!")) {
            do {
                System.out.println("Type in your password: ");
                response = scanner.nextLine();
                writer.println(response);

                isCorrect = reader.readLine();
            } while (isCorrect.equals("N"));
            System.out.println(isCorrect);
        } else {
            System.out.println("Create a new account");

            System.out.println("Enter in an email");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Enter your age");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Create a password: ");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Enter a bio: ");
            response = scanner.nextLine();
            writer.println(response);

            System.out.println("Enter a profilePicture file");
            response = scanner.nextLine();
            writer.println(response);
        }

        while (true) {
            System.out.println("Type something");
            String message = scanner.nextLine();
            writer.println(message);
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

    public static void main(String[] args) throws IOException {
        createGUI();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Client client = null;
                try {
                    client = new Client(8888);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                client.startClient();
            }
        });
    }

    public static void createGUI() {
        frame = new JFrame("Social Media");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        //Add more based on examples
        panel = new JPanel(new GridLayout(3, 4));
        panel.setBackground(Color.black);

        JButton button = new JButton("button");
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);

    }


    public void startClient() {
        new Thread(this::readMessages).start();
        writeMessages();
    }

    public void readMessages() {
        String fromServer;
        try {
            while ((fromServer = reader.readLine()) != null) {
                System.out.println("Server: " + fromServer);
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    public void writeMessages() {
        while (true) {
            String message = scanner.nextLine();
            writer.println(message);
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
*/


