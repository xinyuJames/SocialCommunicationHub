import java.util.*;
public interface PictureInterface 
{
    public String getFilePath();
    public int getFileSize();
    public String getFileFormat();
    public Date getTimePictureTaken();
    public String getLocation();
    public String getDescription();
    public void setDescription(String description);
}
