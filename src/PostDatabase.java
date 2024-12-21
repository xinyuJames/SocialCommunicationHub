import java.util.ArrayList;

/**
 * Team Project -- PostDatabase
 * This class represents a database for posts.
 *
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Saturday 4:37pm
 * @version Apr 13, 2024
 */

public class PostDatabase implements PostDatabaseInterface{
    private ArrayList<Post> posts;

    public PostDatabase() {
        this.posts = new ArrayList<Post>();
    }

    public synchronized void createPost(Post post) {
        posts.add(post);
    }

    public synchronized void deletePost(Post post) {
        for (int i =  0; i < posts.size(); i++) {
                if (posts.get(i).equals(post)) {
                    posts.remove(i);
                }
        }
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }
}
