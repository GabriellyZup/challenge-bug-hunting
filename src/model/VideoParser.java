package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class VideoParser {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private VideoParser() {
        // Construtor privado para evitar instâncias
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            throw new IllegalArgumentException("A data não pode ser nula ou vazia.");
        }

//        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
//        dateFormatter.setLenient(false); // Garante validação rigorosa
        sdf.setLenient(false);
        try {
           // return dateFormatter.parse(dateStr);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato " + DATE_FORMAT + ".");
        }
    }

    private static final Set<String> VALID_CATEGORIES = Set.of("FILME", "SERIE", "DOCUMENTARIO");
    //public static String validateCategory(String category) {
    public static VideoModel.VideoCategory validateAndParseCategory(String categoryInput) {
        //if (category == null || category.isEmpty()) {
        if (categoryInput == null || categoryInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: A categoria não pode estar vazia.");
        }
        try {
            return VideoModel.VideoCategory.valueOf(categoryInput.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: " + VALID_CATEGORIES);
        }

        //String upperCategory = category.trim().toUpperCase();
        //if (!VALID_CATEGORIES.contains(upperCategory)) {
            //throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: " + VALID_CATEGORIES);
        //}
        //return upperCategory;
    }

//    public static VideoModel.VideoCategory validateAndParseCategory(String categoryInput) {
//        return null;
//    }
}





//package model;
//
//public enum VideoCategory {
//    MOVIE,
//    SERIES,
//    DOCUMENTARY
//}


//package model;
//
//import java.text.SimpleDateFormat;
//
//public class VideoParser {
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//    public static String toString(VideoModel video) {
//        return video.getTitle() + ";" + video.getDescription() + ";" + video.getDuration() + ";" + video.getCategory() + ";" + sdf.format(video.getPublicationDate());
//    }
//
//    public static VideoModel fromString(String line) {
//        try {
//            String[] parts = line.split(";");
//            return new VideoModel(
//                    parts[0], //title
//                    parts[1], //descript
//                    Integer.parseInt(parts[2]), //duration
//                    parts[3], //category
//                    sdf.parse(parts[4]) //day publication
//            );
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}