import java.util.ArrayList;
/**
 * Team Project -- UserDatabaseInterface
 *
 * Interface for UserDatabase class. This involved all the important methods in UserDatabase class.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 *
 * @version Apr 16, 2024
 *
 */
public interface UserDatabaseInterface {
    public ArrayList<Profile> viewAllUsers();

    public ArrayList<Profile> searchUserByName(String name);

    public boolean removeUser(Profile user);

    public boolean createNewUser(String username, int age, String email, String password, String bio);


}
