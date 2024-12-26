package model;

import java.text.SimpleDateFormat;

public class VideoParser {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static String toString(VideoModel video) {
        return video.getTitle() + ";" + video.getDescription() + ";" + video.getDuration() + ";" + video.getCategory() + ";" + sdf.format(video.getPublicationDate());
    }

    public static VideoModel fromString(String line) {
        try {
            String[] parts = line.split(";");
            return new VideoModel(
                    parts[0], //title
                    parts[1], //descript
                    Integer.parseInt(parts[2]), //duration
                    parts[3], //category
                    sdf.parse(parts[4]) //day publication
            );
        } catch (Exception e) {
            return null;
        }
    }
}