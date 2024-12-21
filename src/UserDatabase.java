import java.io.*;
import java.util.ArrayList;

/**
 * Team Project -- UserDatabase
 * <p>
 * A database that stored users information.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 10, 2024
 */
public class UserDatabase implements UserDatabaseInterface {
    private final String UserFile;

    private final ArrayList<Profile> users = new ArrayList<>();

    public UserDatabase(String userFile) {

        this.UserFile = userFile;
        readUsers();
    }

    public synchronized boolean readUsers() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(this.UserFile));
            String dataLine = bfr.readLine();
            while (dataLine != null && !dataLine.isEmpty()) {
                Profile user;
                try {
                    user = new Profile(dataLine);
                } catch (BadDataException e) {
                    user = new Profile();
                }
                users.add(user);

                dataLine = bfr.readLine();
            }
            bfr.close();

        } catch (IOException e) {
            return false;
        }
        return true;

    }

    public synchronized ArrayList<Profile> viewAllUsers() { //users are stored in "users" field
        return users;
    }

    public synchronized ArrayList<Profile> searchUserByName(String name) { //return the value of profile array with name in it.
        ArrayList<Profile> targetProfile = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().contains(name)) {
                targetProfile.add(users.get(i));
            }
        }

        return targetProfile;
    }

    public synchronized boolean removeUser(Profile user) {
        for (int i = 0; i < users.size(); i++) {
            if (user.equals(users.get(i))) {
                users.remove(i); //Remove target user from the list
            }
        }

        //Rewrite all the users back to UserFile
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(this.UserFile, false));

            for (int i = 0; i < users.size(); i++) {
                //Format: username, age, email, password, profilePicture, bio
                pw.print(users.get(i).getUsername() + ",");
                pw.print(users.get(i).getAge() + ",");
                pw.print(users.get(i).getEmail() + ",");
                pw.print(users.get(i).getPassword() + ",");
                //pw.print(users.get(i).getProfilePicture() + ",");
                pw.print(users.get(i).getBio() + "\n");
            }
            pw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public synchronized boolean createNewUser(String username, int age, String email,
                                              String password, String bio) {
        // Check if username already exists
        for (Profile user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Username already exists
            }
        }

        // Create new Profile object
        Profile newUser = new Profile(username, age, email, password, bio);
        users.add(newUser); // Add to the database
        //Rewrite all the users back to UserFile
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(this.UserFile, false));

            for (int i = 0; i < users.size(); i++) {
                //Format: username, age, email, password, profilePicture, bio
                pw.print(users.get(i).getUsername() + ",");
                pw.print(users.get(i).getAge() + ",");
                pw.print(users.get(i).getEmail() + ",");
                pw.print(users.get(i).getPassword() + ",");
                pw.print(users.get(i).getBio() + "\n");
            }
            pw.close();
            return true;
        } catch (IOException e) {
            return false;
        }


    }

    // Method to verify login credentials
    public synchronized Profile login(String username, String password) {
        String hashedPassword = hashPassword(password);
        for (Profile user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(hashedPassword)) {
                return user; // Login successful, return user profile
            }
        }
        return null; // Login failed
    }

    // Password protected login
    private synchronized String hashPassword(String password) {
        return Integer.toString(password.hashCode());
    }

}
