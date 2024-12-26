package model;

import java.util.Date;

public class Video {
    private String title;
    private String description;
    private int duration; // in minutes
    private String category;
    private Date publicationDate;

    // Constructor
    public Video(String title, String description, int duration, String category, Date publicationDate) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.publicationDate = publicationDate;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}