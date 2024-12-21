public class Comment {
    private Profile commenter;
    private String message;
    private int likes;

    public Comment(Profile commenter, String message) {
        this.commenter = commenter;
        this.message = message;
        this.likes = 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return likes;
    }

    public Profile getCommenter() {
        return commenter;
    }

    public void addLike() {
        this.likes++;
    }

    public void removeLike() {
        this.likes--;
    }

    public void editComment(String newMessage) {
        this.message = newMessage;
    }

    public boolean isEquals(Comment comment) {
        return this.message.equals(comment.getMessage());
    }

}
