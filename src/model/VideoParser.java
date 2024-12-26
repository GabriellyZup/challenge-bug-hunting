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
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    parts[3],
                    sdf.parse(parts[4])
            );
        } catch (Exception e) {
            return null;
        }
    }
}