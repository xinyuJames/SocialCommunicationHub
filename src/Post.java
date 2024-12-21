import java.util.*;

/**
 * Team Project -- Post
 * This class Represents a social media post, including attributes such as author, caption, attached pictures,
 * time of posting, likes, comments.
 *
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 1, 2024
 */

public class Post {
    private Profile author;
    private String caption;
    private Picture[] pictures;
    private Date timeStamp;
    private int likes;
    private ArrayList<Comment> comments;
    private ArrayList<Profile> tags;
    private boolean publicPost;
    private static int postNum = 0;
    private int postId;

    public Post(Profile author, String caption, Picture[] pictures, ArrayList<Profile> tags, boolean publicPost) {
        this.author = author;
        this.caption = caption;
        this.pictures = pictures;
        this.timeStamp = new Date();
        this.likes = 0;
        this.comments = new ArrayList<Comment>();
        this.tags = tags;
        this.publicPost = publicPost;

        postNum++;
        postId = postNum;
    }

    public Post(Profile author, String caption, Picture[] pictures, boolean publicPost) {
        this.author = author;
        this.caption = caption;
        this.pictures = pictures;
        this.timeStamp = new Date();
        this.likes = 0;
        this.comments = new ArrayList<Comment>();
        this.tags = new ArrayList<Profile>();
        this.publicPost = publicPost;
    }

    public synchronized Profile getAuthor() {
        return author;
    }

    public synchronized String getCaption() {
        return caption;
    }

    public synchronized Picture[] getPictures() {
        return pictures;
    }

    public synchronized void setPictures(Picture[] pictures) {
        this.pictures = pictures;
    }

    public synchronized Date getTimestamp() {
        return timeStamp;
    }

    public synchronized int getLikes() {
        return likes;
    }

    public synchronized void addLike() {
        this.likes++;
    }

    public synchronized void downVote() {
        this.likes--;
    }

    public synchronized ArrayList<Comment> getComments() {
        return comments;
    }

    public synchronized void deleteComment(Comment comment, Profile user) {
        if (user.equals(author) || user.equals(comment.getCommenter())) {
            for (int i = 0; i < comments.size(); i++) {
                if (comments.get(i).equals(comment)) {
                    comments.remove(i);
                    break;
                }
            }
        }

    }

    public synchronized ArrayList<Profile> getTags() {
        return tags;
    }

    public synchronized void addTag(Profile user) {
        tags.add(user);
        System.out.println("Profile has been added as a tag to the post");
    }

    public synchronized void deleteTag(Profile user) {
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).equals(user)) {
                tags.remove(i);
                System.out.println("Tag removed successfully");
                break;
            }
        }

    }

    public synchronized boolean getPublic() {
        return publicPost;
    }

    public synchronized void changeVisibility() {
        if (publicPost) {
            publicPost = false;
        } else {
            publicPost = true;
        }
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void likeComment(Comment comment) {
        for (Comment comment1 : comments) {
            if (comment.isEquals(comment1)) {
                comment1.addLike();
            }
        }
    }

    public int getPostId() {
        return postId;
    }

    public void downvoteComment(Comment comment) {
        for (Comment comment1 : comments) {
            if (comment.isEquals(comment1)) {
                comment1.removeLike();
            }
        }
    }

    public boolean equals(Post post) {
        return this.postId == post.getPostId();
    }
}
