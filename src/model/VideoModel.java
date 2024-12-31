//responsável apenas por representar os dados de um vídeo

package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class VideoModel {



    private String title;
    private String description;
    private int duration;
    private VideoCategory category;
    private Date publicationDate;

    private static final String DATE_FORMAT = "dd/MM/yyyy"; // Formato de data padrão
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    static {
        DATE_FORMATTER.setLenient(false); // Validação rigorosa de datas
    }

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

    public VideoModel create (String title, String description, int duration, String category, String publicationDate) {
        this(
                title,
                description,
                duration,
                VideoCategory.fromString(category), // Converte a categoria
                validateAndParseDate(publicationDate) // Valida e converte a data
        );
    }

    public void setPublicationDate(Date publicationDate) {
        if (publicationDate == null) {
            throw new IllegalArgumentException(" A data de publicação precisa ser preenchida.");
        }
        //this.publicationDate = validateAndParseDate(publicationDate);
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public void setDuration(int duration) {
        validateDuration(duration);
        this.duration = duration;
    }

    public void setCategory(VideoCategory category) {
        this.category = category;
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

    // Validações
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("O titula não pode estar vazio");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode esta vazio.");
        }
    }

    private void validateDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("A duração deve ser um número inteiro.");
        }
    }

    private Date validateAndParseDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválida. Use dd/MM/yyyy.");
        }
    }

    // toString para parse
//    @Override
//    public String toString() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        return "VideoModel{" +
//                "title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", duration=" + duration +
//                ", category=" + category +
//                ", publicationDate=" + dateFormat.format(publicationDate) +
//                '}';
//    }
//}

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return title + ";" + description + ";" + duration + ";" + category + ";" + sdf.format(publicationDate);
    }

    public static VideoModel fromString(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Preencha todos os campos para continuar.");
        }

        try {
            String[] parts = line.split(";");
            if (parts.length != 5) {
                throw new IllegalArgumentException("Formato de dados invalido. São esperados 5 campos separados por ';'.");
            }

            String title = parts[0];
            String description = parts[1];
            int duration = Integer.parseInt(parts[2]);
            VideoCategory category = VideoCategory.fromString(parts[3]);
            String publicationDate = parts[4];

            return new VideoModel(title, description, duration, category, publicationDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao processar a linha de entrada: " + e.getMessage(), e);
        }
    }
    public enum VideoCategory {
        VIDEO, SERIE, DOCUMENTARIO;

        public static VideoCategory fromString(String category) {
            try{
                return VideoCategory.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Categoria invelida. Use FILME, SERIE ou DOCUMENTARIO.");
            }
            //return false;
        }
    }
}
//
//    public static VideoModel fromString(String linha) {
//        try {
//            String[] parts = linha.split(";");
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            return new VideoModel(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
//        } catch (Exception e) {
//            return null; // Ignora erros de parsing
//        }
//    }

