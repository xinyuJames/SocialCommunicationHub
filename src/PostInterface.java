import java.util.*;

public interface PostInterface 
{
    public String getAuthor();
    public String getCaption();
    public Picture[] getPictures();
    public void setPictures(Picture[] pictures);
    public Date getTimestamp();
    public int getLikes();
    public void addLike();
    public ArrayList<String> getComments();
    public void deleteComment(String comment);
    public ArrayList<Profile> getTags();
    public void addTag(Profile user);
    public void deleteTag(Profile user);
    public boolean getPublic();
    public void changeVisibility();
}
