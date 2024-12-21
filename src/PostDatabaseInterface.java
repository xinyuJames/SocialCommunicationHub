import java.util.ArrayList;

/**
 * Team Project -- PostDatabaseInterface
 * This class represents an interface for the Post Database
 *
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Saturday 4:49pm
 * @version Apr 13, 2024
 */
public interface PostDatabaseInterface {
    public void createPost(Post post);
    public void deletePost(Post post);
    public ArrayList<Post> getPosts();

}
