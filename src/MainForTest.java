/*
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
*/
/*
 * Team Project -- MainForTest
 *
 * Main method for test cases.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 *
 * @version Apr 1, 2024
 *
*//*


public class MainForTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set name for the first user: ");
        String firstUserName = scanner.nextLine();
        //initialize UserDatabase with first user
        Profile firstUser = new Profile(firstUserName, 18, "", "",
                "","");
        UserDatabase userDatabase = new UserDatabase(firstUser.getUsername());
        //test for "createNewUser
        System.out.println("Set name for new user: ");
        String newUser = scanner.nextLine();

        System.out.println("What's your age?");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("What's your email?");
        String email = scanner.nextLine();

        System.out.println("What's your password?");
        String password = scanner.nextLine();

        System.out.println("What would you like your bio to be?");
        String bio = scanner.nextLine();

        System.out.println("Type in a jpg for your profile picture:");
        String profilePicture = scanner.nextLine();

        userDatabase.createNewUser(newUser, age, email, password, bio, profilePicture);
        //test for "addFriend"
        System.out.println("Give a name of a friend: ");
        String friendName = scanner.nextLine();
        Profile friend = new Profile(friendName, 18, "", "",
                "","");
        firstUser.addFriend(friend, "friend.txt");
        System.out.println("This is your friend: ");
        System.out.println(firstUser.getFriendsUsernames().get(0).getUsername());
        //test for "viewAllUsers"
        ArrayList<Profile> allUser =  userDatabase.viewAllUsers();
        System.out.print("All users: ");
        for (Profile user : allUser) {
            System.out.print(user.getUsername() + " ");
        }
        System.out.println();
        //test for "searchUserByName"
        System.out.println("Search for Jam: ");
        ArrayList<Profile> targetUsers = userDatabase.searchUserByName("Jam");
        for (Profile user: targetUsers) {
            System.out.print("User searched: " + user.getUsername());
        }
        System.out.println();
        //test for "removeUser", and out put all users again
        System.out.println("Removed the first user: ");
        userDatabase.removeUser(firstUser);
        allUser =  userDatabase.viewAllUsers();
        System.out.print("All users: ");
        for (Profile user : allUser) {
            System.out.println(user.getUsername());
        }



        //start to test message and message database
        System.out.println("Give me content for the first message: ");
        String firstContent = scanner.nextLine();
        System.out.println("Give me content for the second message: ");
        String secondContent = scanner.nextLine();
        System.out.println("Give me content for the third message: ");
        String thirdContent = scanner.nextLine();
        MessageDatabase messageDatabase = new MessageDatabase();
        //test "sendMessage"
        Message firstMessage = new Message("", "", new Date(), firstContent, "", "");
        Message secondMessage = new Message("", "", new Date(), secondContent, "", "");
        Message thirdMessage = new Message("", "", new Date(), thirdContent, "", "");
        messageDatabase.sendMessage(firstMessage);
        messageDatabase.sendMessage(secondMessage);
        messageDatabase.sendMessage(thirdMessage);
        System.out.println("Here is all the message currently sent: ");
        for (int i = 0; i < messageDatabase.getMessages().size(); i++) {
            System.out.println(messageDatabase.getMessages().get(i).getContent());
        }
        //test "editMessage"
        System.out.println("Give me new content for the second message: ");
        String newSecondContent = scanner.nextLine();
        messageDatabase.editMessage(secondContent, newSecondContent);
        System.out.println("Here is all the message currently sent: ");
        for (int i = 0; i < messageDatabase.getMessages().size(); i++) {
            System.out.println(messageDatabase.getMessages().get(i).getContent());
        }
        //test "deleteMessage"
        System.out.println("Result of deleting first message: ");
        messageDatabase.deleteMessage(firstMessage);
        System.out.println("Here is all the message currently sent: ");
        for (int i = 0; i < messageDatabase.getMessages().size(); i++) {
            System.out.println(messageDatabase.getMessages().get(i).getContent());
        }


    }

}
*/
