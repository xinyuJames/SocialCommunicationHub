import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Team Project -- Profile
 * This class represents a user profile in a social networking context.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 11, 2024
 */
public class Profile implements ProfileInterface {
    private String username;
    private int age;
    private String email;
    protected String password;
    private ArrayList<String> friends = new ArrayList<String>();
    private final ArrayList<String> blocks = new ArrayList<>();
    private String bio;
    private File profilePicture;
    private ArrayList<Post> posts;

    public Profile(String username, int age, String email, String password,
                   String bio) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
        this.bio = bio;
        //setProfilePicture(profilePicture);
    }

    public Profile(String data) throws BadDataException {
        String[] elements = data.split(",");
        //Format: username, age, email, password, profilePicture, bio
        //Store username
        if (elements[0].isEmpty()) {
            throw new BadDataException("No username provided.");
        } else {
            this.username = elements[0];
        }

        //Store age
        if (elements[1].isEmpty()) {
            throw new BadDataException("No age provided.");
        } else {
            try {
                this.age = Integer.parseInt(elements[1]);
            } catch (NumberFormatException e) {
                throw new BadDataException("Age format Wrong.");
            }

        }

        //Store email
        if (elements[2].isEmpty()) {
            throw new BadDataException("No email provided");
        } else {
            if (elements[2].contains("@")) {
                this.email = elements[2];
            } else {
                throw new BadDataException("Email format Wrong.");
            }
        }

        //Store password
        if (elements[3].isEmpty()) {
            throw new BadDataException("No password provided.");
        } else {
            this.password = elements[3];
        }

        //Store profile picture
       /*if (elements[4].isEmpty()) {
            this.profilePicture = null;
        } else {
            this.profilePicture = new File(elements[4]);
        } */

        //Store bio
        StringBuilder sb = new StringBuilder();
        int commaAdded = elements.length - 6;
        for (int i = 4; i < elements.length; i++) {
            sb.append(elements[i]);
            if (commaAdded > 0) {
                sb.append(",");
                commaAdded--;
            }
        }
        this.bio = sb.toString();

    }

    public Profile() {
        this.username = null;
        this.age = -1;
        this.email = null;
        this.password = null;
        this.friends = null;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void resetPassword(String password) {
        boolean emailConfirmError = false;
        do {
            String resetPWEmail = JOptionPane.showInputDialog(null, "Please enter the email that is associated with the acount!", "Reset Password", JOptionPane.QUESTION_MESSAGE);
            if (resetPWEmail.equals(email)) {
                boolean passwordConfirmError = true;
                do {

                    String resetPW1 = JOptionPane.showInputDialog(null, "Enter the new password you would like to set!", "Reset Password", JOptionPane.QUESTION_MESSAGE);
                    String resetPW2 = JOptionPane.showInputDialog(null, "Confirm your password!", "Reset Password", JOptionPane.QUESTION_MESSAGE);
                    if (resetPW1.equals(resetPW2)) {
                        this.password = password;
                        JOptionPane.showMessageDialog(null, "Congratulations! Your password has been reset!", "Reset Password", JOptionPane.INFORMATION_MESSAGE);
                        passwordConfirmError = false;
                    }
                } while (passwordConfirmError);

            }

        } while (emailConfirmError);


    }

    public boolean readFriends(String filename) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(filename));
            String line = bfr.readLine();
            while (line != null && !line.isEmpty()) {
                String[] parts = line.split(";");
                if (username.contentEquals(parts[0])) {
                    if (parts[1].contains(",")) {
                        friends.clear();
                        String[] friendsArray = parts[1].split(",");
                        Collections.addAll(friends, friendsArray);
                        return true;
                    } else if (!parts[1].isEmpty()) {
                        friends.clear();
                        friends.add(parts[1]);
                        return true;
                    }
                    break;
                }
                line = bfr.readLine();
            }
            return false;
        } catch (IOException e) {
            System.out.println("Unable to read friends, file error.");
            return false;
        }
    }

    public boolean addFriend(String friend, String filename) {
        readFriends(filename);
        // add friend's username to friend list
        for (String block: blocks) {
            if (block.equalsIgnoreCase(friend)) {
                return false;
            }
        }


        friends.add(friend);

        // Prepare to read and write all user's friends back to file.
        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> allUserFriend = new ArrayList<>();
            String line;

            while ((line = bfr.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(";");
                if (!parts[0].equals(username)) {
                    allUserFriend.add(line);
                }
            }

            //write all back to the file
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(filename, false))) {
                for (String userLine : allUserFriend) {
                    pw.println(userLine);
                }

                // Write the current user's friends
                pw.print(username + ";");
                for (int i = 0; i < friends.size(); i++) {
                    pw.print(friends.get(i));
                    if (i < friends.size() - 1) {
                        pw.print(",");
                    }
                }
                pw.println();
            }
        } catch (IOException e) {
            System.out.println("Failed to update friends list: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean removeFriend(String friendUsername, String filename) {
        readFriends(filename);
        //remove friend's username from friends list
        friends.remove(friendUsername);
        //rewrite all user's friends back to file.
        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> allUserFriend = new ArrayList<>();
            String line;

            while ((line = bfr.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(";");
                if (!parts[0].equals(username)) {
                    allUserFriend.add(line);
                }
            }

            //write all back to the file
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(filename, false))) {
                for (String userLine : allUserFriend) {
                    pw.println(userLine);
                }

                // Write the current user's friends
                if (!friends.isEmpty()) {
                    pw.print(username + ";");
                    for (int i = 0; i < friends.size(); i++) {
                        pw.print(friends.get(i));
                        if (i < friends.size() - 1) {
                            pw.print(",");
                        }
                    }
                }
                pw.println();
            }
        } catch (IOException e) {
            System.out.println("Failed to update friends list: " + e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public ArrayList<String> getFriends() {
        return friends;
    }


    public boolean readBlocks(String filename) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(filename));
            String line = bfr.readLine();
            while (line != null && !line.isEmpty()) {
                String[] parts = line.split(";");
                if (username.contentEquals(parts[0])) {
                    if (parts[1].contains(",")) {
                        blocks.clear();
                        String[] friendsArray = parts[1].split(",");
                        Collections.addAll(blocks, friendsArray);
                        return true;
                    } else if (!parts[1].isEmpty()) {
                        blocks.clear();
                        blocks.add(parts[1]);
                        return true;
                    }
                    break;
                }
                line = bfr.readLine();
            }
            return false;
        } catch (IOException e) {
            System.out.println("Unable to read blocks, file error.");
            return false;
        }
    }

    public boolean addBlock(String blockUsername, String filename) {
        readBlocks(filename);
        //add block's username to friend list
        blocks.add(blockUsername);
        friends.remove(blockUsername);

        removeFriend(blockUsername, "FriendList.txt");
        //rewrite all user's blocks back to file.
        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> allUserBlock = new ArrayList<>();
            String line;

            while ((line = bfr.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(";");
                if (!parts[0].equals(username)) {
                    allUserBlock.add(line);
                }
            }

            //write all back to the file
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(filename, false))) {
                for (String userLine : allUserBlock) {
                    pw.println(userLine);
                }

                // Write the current user's friends
                pw.print(username + ";");
                for (int i = 0; i < blocks.size(); i++) {
                    pw.print(blocks.get(i));
                    if (i < blocks.size() - 1) {
                        pw.print(",");
                    }
                }
                pw.println();
            }
        } catch (IOException e) {
            System.out.println("Failed to update blocks list: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean removeBlock(String blockUsername, String filename) {
        readBlocks(filename);
        //remove block's username from friends list
        blocks.remove(blockUsername);
        //rewrite all user's blocks back to file.
        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> allUserBlock = new ArrayList<>();
            String line;

            while ((line = bfr.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(";");
                if (!parts[0].equals(username)) {
                    allUserBlock.add(line);
                }
            }

            //write all back to the file
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(filename, false))) {
                for (String userLine : allUserBlock) {
                    pw.println(userLine);
                }

                // Write the current user's friends
                if (!blocks.isEmpty()) {
                    pw.print(username + ";");
                    for (int i = 0; i < blocks.size(); i++) {
                        pw.print(blocks.get(i));
                        if (i < blocks.size() - 1) {
                            pw.print(",");
                        }
                    }
                }
                pw.println();
            }
        } catch (IOException e) {
            System.out.println("Failed to update friends list: " + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<String> getBlocks() {
        return blocks;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public File getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(profilePicture));

            File output = new File("profile_picture.jpg");
            ImageIO.write(image, "jpg", output);

            this.profilePicture = output;

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void createPost(String caption, Picture[] pictures, Date timestamp, ArrayList<Profile> tags,
                           boolean publicPost) {
        Post newPost = new Post(this, caption, pictures, tags, publicPost);
        posts.add(newPost);
    }

    public void deletePost(Post post) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).equals(post)) {
                posts.remove(i);
            }
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    /*public ArrayList<Post> displayFriendsPosts() {
        ArrayList<Post> friendsPosts = new ArrayList<Post>();
        for (Profile friend: friendsProfiles) {
            friendsPosts.addAll(friend.getPosts());
        }
        return friendsPosts;
    }*/

    public String toString() {
        return "Name: " + this.username + "\nBio: " + this.bio;
    }

    public boolean equals(Profile user) {
        return this.email.equals(user.getEmail());
    }
}
