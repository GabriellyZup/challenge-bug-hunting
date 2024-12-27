//responsável apenas por representar os dados de um vídeo

package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoModel {

    public enum VideoCategory {
        VIDEO, SERIE, DOCUMENTARIO
    }
    private String title;
    private String description;
    private int duration;
    private String category;
    private VideoCategory category;
    private Date publicationDate;

    public VideoModel(String title, String description, int duration, VideoCategory category, /*Date*/ String publicationDate) {
        validateTitle(title);
        validateDescription(description);
        validateDuration (duration);

        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        //this.publicationDate = publicationDate;
        this.publicationDate = validateAndParseDate(publicationDate);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public VideoCategory getCategory() {
        return category;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    // Validation methods
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
    }

    private void validateDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive number.");
        }
    }

    private Date validateAndParseDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use dd/MM/yyyy.");
        }
    }

    // toString method for debugging or display purposes
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "VideoModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", category=" + category +
                ", publicationDate=" + dateFormat.format(publicationDate) +
                '}';
    }
}

//    @Override
//    public String toString() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        return title + ";" + description + ";" + duration + ";" + category + ";" + sdf.format(publicationDate);
//    }
//
//
//    public static VideoModel fromString(String linha) {
//        try {
//            String[] partes = linha.split(";");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            return new VideoModel(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
//        } catch (Exception e) {
//            return null; // Ignora erros de parsing
//        }
//    }

