public interface CommentInterface {
    public void addLike();
    public void removeLike();
    public void editComment(String newMessage);
    public boolean isEquals(Comment comment);
    public String getMessage();
    public void setMessage();
    public int getLikes();


}
