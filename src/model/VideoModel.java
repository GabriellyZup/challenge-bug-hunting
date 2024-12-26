//responsável apenas por representar os dados de um vídeo

package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoModel {
    private String title;
    private String description;
    private int duration; // in minutes
    private String category;
    private Date publicationDate;

    public VideoModel(String title, String description, int duration, String category, Date publicationDate) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.publicationDate = publicationDate;
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

    public String getCategory() {
        return category;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return title + ";" + description + ";" + duration + ";" + category + ";" + sdf.format(publicationDate);
    }
}

    /**

    public static VideoModel fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return new VideoModel(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }
     **/
