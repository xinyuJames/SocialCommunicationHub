import java.util.*;

/**
 * Team Project -- Picture
 *
 * This class represents a picture file with properties such as file path, size, format, timestamp of when the picture was taken,
 * location where it was taken, and a description. 
 * @author Xinyu Liu, Aiyan Alam, Hyun-gun Park, Elliott Shi, Thursday 11:30am
 * @version Apr 1, 2024
 */

public class Picture implements PictureInterface
{
    private String filePath;
    private int fileSize;
    private String fileFormat;
    private Date timePictureTaken;
    private String location;
    private String description;

    public Picture(String filepath, int fileSize, String fileFormat, String location, String description)
    {
        this.filePath = filepath;
        this.fileFormat = fileFormat;
        this.fileFormat = fileFormat;
        this.timePictureTaken = new Date();
        this.location = location;
        this.description = description;
    }

    public Picture(String filepath, int fileSize, String fileFormat, String location)
    {
        this.filePath = filepath;
        this.fileFormat = fileFormat;
        this.fileFormat = fileFormat;
        this.timePictureTaken = new Date();
        this.location = location;
        this.description = "";
    }

    public synchronized String getFilePath()
    {
        return filePath;
    }

    public synchronized int getFileSize()
    {
        return fileSize;
    }

    public synchronized String getFileFormat()
    {
        return fileFormat;
    }

    public synchronized Date getTimePictureTaken()
    {
        return timePictureTaken;
    }

    public synchronized String getLocation()
    {
        return location;
    }

    public synchronized String getDescription()
    {
        return description;
    }

    public synchronized void setDescription(String description)
    {
        this.description = description;
    }
}
