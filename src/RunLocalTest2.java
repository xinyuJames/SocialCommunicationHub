/*
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class RunLocalTest2 {
    private Profile profile;
    private MessageDatabase messageDatabase;
    private Message message;
    private PostDatabase postDatabase;
    private Post post;

    @Before
    public void setUp() {
        // Profile setup
        profile = new Profile("testUser", 25, "testUser@example.com", "password123", "This is a test bio", "path/to/picture.jpg");
        
        // MessageDatabase setup
        messageDatabase = new MessageDatabase();
        message = new Message("user1", "user2", new Date(), "Hello, World!", "Sent", "text");
        messageDatabase.sendMessage(message);
        
        // PostDatabase setup
        postDatabase = new PostDatabase();
        post = new Post(profile, "Test Post", new Picture[0], true);
        postDatabase.createPost(post);
    }

    // Profile tests
    @Test
    public void testSetBio() {
        profile.setBio("Updated bio");
        assertEquals("Bio should be updated", "Updated bio", profile.getBio());
    }

    @Test
    public void testGetBio() {
        assertEquals("Should return the initial bio", "This is a test bio", profile.getBio());
    }

    @Test
    public void testAddFriend() {
        profile.addFriend("friendUser", "friends.txt"); 
        assertTrue("Friend should be added", profile.getFriends().contains("friendUser"));
    }

    @Test
    public void testRemoveFriend() {
        profile.addFriend("friendUser", "friends.txt"); 
        profile.removeFriend("friendUser", "friends.txt"); 
        assertFalse("Friend should be removed", profile.getFriends().contains("friendUser"));
    }

    @Test
    public void testAddFriendNotExists() {
        assertFalse("Should return false if friend does not exist", profile.addFriend("nonExistingUser", "friends.txt"));
    }

    @Test
    public void testRemoveFriendNotExists() {
        assertFalse("Should return false if trying to remove a non-existing friend", profile.removeFriend("nonExistingUser", "friends.txt"));
    }

    @Test
    public void testAddBlock() {
        profile.addBlock("blockedUser", "blocks.txt");
        assertTrue("User should be blocked", profile.getBlocks().contains("blockedUser"));
    }

    @Test
    public void testRemoveBlock() {
        profile.removeBlock("blockedUser", "blocks.txt");
        assertFalse("User should be unblocked", profile.getBlocks().contains("blockedUser"));
    }

    @Test
    public void testResetPassword() {
        profile.resetPassword("newPassword123");
        assertEquals("Password should be updated", "newPassword123", profile.getPassword());
    }

    @Test
    public void testSetAndGetProfilePicture() {
        String newProfilePicturePath = "path/to/newPicture.jpg";
        profile.setProfilePicture(newProfilePicturePath);
        assertEquals("Profile picture should be updated", new File(newProfilePicturePath), profile.getProfilePicture());
    }

    @Test
    public void testProfileEquals() {
        Profile profile1 = new Profile("user1", 25, "user1@example.com", "pass1", "bio1", "pic1.jpg");
        Profile profile2 = new Profile("user1", 25, "user1@example.com", "pass1", "bio1", "pic1.jpg");

        assertTrue("Profiles with the same email should be considered equal", profile1.equals(profile2));
    }
    
    // MessageDatabase tests
    @Test
    public void testSendMessage() {
        assertTrue("Message should be present in the database after sending", messageDatabase.getMessages().contains(message));
    }

    @Test
    public void testDeleteMessage() {
        messageDatabase.deleteMessage(message); 
        assertFalse("Message should be removed from the database", messageDatabase.getMessages().contains(message));
    }

    @Test
    public void testEditMessage() {
        messageDatabase.editMessage("Hello, World!", "Edited Message");
        assertTrue("Edited message should be in the database", messageDatabase.getMessages().stream().anyMatch(m -> "Edited Message".equals(m.getContent())));
    }
    
    @Test
    public void testSendAndDeleteAttachment() {
        String attachmentPath = "path/to/attachment.jpg";
        messageDatabase.sendMessage(message);
        messageDatabase.sendAttachment(attachmentPath);
        assertTrue("Attachment should be added", messageDatabase.getPictures().contains(attachmentPath));

        messageDatabase.deleteAttachment(attachmentPath);
        assertFalse("Attachment should be deleted", messageDatabase.getPictures().contains(attachmentPath));
    }

    @Test
    public void testMessageTimestamps() {
        Date beforeSend = new Date();
        messageDatabase.sendMessage(new Message("user1", "user2", new Date(), "Test Message", "Sent", "text"));
        Date afterSend = new Date();

        ArrayList<Date> times = messageDatabase.getTimes();
        assertFalse("Times list should not be empty after sending a message", times.isEmpty());
        Date messageTime = times.get(times.size() - 1); 

        assertTrue("Message timestamp should be after beforeSend and before afterSend",
            messageTime.after(beforeSend) && messageTime.before(afterSend));
    }
}
*/
