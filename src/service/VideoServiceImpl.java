package service;

import model.VideoModel;
import repository.VideoRepository;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;

    public VideoServiceImpl(VideoRepository repository) {
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
}