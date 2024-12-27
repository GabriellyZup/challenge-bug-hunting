package service;

import model.VideoModel;
import repository.VideoRepository;

import java.util.List;
import java.util.ArrayList;

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
    public List<VideoModel> searchByTitle(String title) {
        List<VideoModel> allVideos = repository.findAll();
        List<VideoModel> matchingVideos = new ArrayList<>();

        for (VideoModel video : allVideos) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                matchingVideos.add(video);
            }
        }

        return matchingVideos;
    }
}
