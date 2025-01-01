package service;

import model.VideoModel;
import repository.VideoRepository;

import java.text.SimpleDateFormat;
import java.text.ParseException;
//import java.util.List;
import java.util.*;
//import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

public class VideoManager implements VideoService {
    private final VideoRepository repository;
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public VideoManager(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(VideoModel video) {
        validateAndSetPublicationDate(video);
        repository.save(video);
    }

    @Override
    public List<VideoModel> listVideos() {
        return repository.findAll();
    }

    @Override
//    public List<VideoModel> searchByTitle(String title) {
//        List<VideoModel> allVideos = repository.findAll();
//        List<VideoModel> matchingVideos = new ArrayList<>();
//
//        for (VideoModel video : allVideos) {
//            if (video.getTitle().equalsIgnoreCase(title)) {
//                matchingVideos.add(video);
//            }
//        }
//
//        return matchingVideos;

    public List<VideoModel> searchByTitle(String title) {
        return repository.findAll().stream()
                .filter(video -> video.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    /**
     * @param title
     * @param updatedVideo
     * @return
     */
    public boolean editVideo(String title, VideoModel updatedVideo) {
        List<VideoModel> allVideos = repository.findAll();
        for (VideoModel video : allVideos) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                video.setTitle(updatedVideo.getTitle());
                video.setDescription(updatedVideo.getDescription());
                video.setDuration(updatedVideo.getDuration());
                video.setCategory(updatedVideo.getCategory());
                //video.setDate(updatedVideo.getDate());
                //repository.save(video); // Atualiza repo
                video.setPublicationDate /*(new SimpleDateFormat("dd/MM/yyyy").format*/(updatedVideo.getPublicationDate());
                repository.saveAll(allVideos);
                return true;
            }
        }
        return false; // Retorna false se o vídeo não for encontrado
    }

    public boolean deleteVideo(String title) {
        List<VideoModel> allVideos = repository.findAll();
        VideoModel videoToDelete = allVideos.stream()
                .filter(video -> video.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (videoToDelete != null) {
            allVideos.remove(videoToDelete);
            repository.saveAll(allVideos); // Atualiza repo com vídeos restantes
            return true;
        }
        return false; // Retorna false se o vídeo não for encontrado
    }


    public List<VideoModel> filterVideosByCategory(String category) {
        return repository.findAll().stream()
                .filter(video -> video.getCategory().name().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<VideoModel> sortVideosByPublicationDate(boolean reverse){
        return repository.findAll().stream()
                .sorted(Comparator.comparing(VideoModel::getPublicationDate, reverse ? Comparator.reverseOrder() : Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }


    public Map<String, Object> generateStatistics() {
        List<VideoModel> allVideos = repository.findAll();

        Map<String, Object> stats = new HashMap<>();
        stats.put("Total de videos", allVideos.size());
        stats.put("Duração total", allVideos.stream().mapToInt(VideoModel::getDuration).sum()); // Corrigido
        stats.put("Videos por categoria", allVideos.stream()
                .collect(Collectors.groupingBy(video -> video.getCategory().name(), Collectors.counting()))); // Corrigido
        return stats;
    }

    // Ordenar vídeos por uma chave específica
    public List<VideoModel> sortVideosBy(String key, boolean reverse) {
        Comparator<VideoModel> comparator;

        switch (key.toLowerCase()) {
            case "titulo":
                comparator = Comparator.comparing(VideoModel::getTitle);
                break;
            case "categoria":
                comparator = Comparator.comparing(VideoModel::getCategory);
                break;
            case "data":
                comparator = Comparator.comparing(VideoModel::getPublicationDate);
                break;
            default:
                throw new IllegalArgumentException("Critério de busca inválido, tente titulo, categoria ou data: " + key);
        }

        if (reverse) {
            comparator = comparator.reversed();
        }


        return repository.findAll().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    // Método
    private void validateAndSetPublicationDate(VideoModel video) {

            if (video.getPublicationDate() == null) {
                throw new IllegalArgumentException("A data de publicação não pode ser nula.");
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                sdf.setLenient(false); // Validação rigorosa
                Date validatedDate = sdf.parse(sdf.format(video.getPublicationDate()));
                video.setPublicationDate(validatedDate);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Data de publicação inválida. Use o formato " + DATE_FORMAT + ".");
            }

    }
}

