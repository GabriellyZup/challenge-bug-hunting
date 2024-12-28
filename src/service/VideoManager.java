package service;

import model.VideoModel;
import repository.VideoRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

public class VideoManager implements VideoService {
    private final VideoRepository repository;

    public VideoManager(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(VideoModel video) {
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

    public boolean editVideo(int id, VideoModel updatedVideo) {
        List<VideoModel> allVideos = repository.findAll();
        for (VideoModel video : allVideos) {
            if (video.getId() == id) {
                video.setTitle(updatedVideo.getTitle());
                video.setCategory(updatedVideo.getCategory());
                video.setDate(updatedVideo.getDate());
                repository.save(video); // Atualiza repo
                return true;
            }
        }
        return false; // Retorna false se o vídeo não for encontrado
    }

    // Excluir vídeo
    public boolean deleteVideo(int id) {
        List<VideoModel> allVideos = repository.findAll();
        VideoModel videoToDelete = allVideos.stream()
                .filter(video -> video.getId() == id)
                .findFirst()
                .orElse(null);

        if (videoToDelete != null) {
            allVideos.remove(videoToDelete);
            repository.saveAll(allVideos); // Atualiza repo com vídeos restantes
            return true;
        }
        return false; // Retorna false se o vídeo não for encontrado
    }

    // Filtrar vídeos por categoria
    public List<VideoModel> filterVideosByCategory(String category) {
        return repository.findAll().stream()
                .filter(video -> video.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Ordenar vídeos por uma chave específica
    public List<VideoModel> sortVideosBy(String key, boolean reverse) {
        Comparator<VideoModel> comparator;

        switch (key.toLowerCase()) {
            case "title":
                comparator = Comparator.comparing(VideoModel::getTitle);
                break;
            case "category":
                comparator = Comparator.comparing(VideoModel::getCategory);
                break;
            case "date":
                comparator = Comparator.comparing(VideoModel::getDate);
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
}

