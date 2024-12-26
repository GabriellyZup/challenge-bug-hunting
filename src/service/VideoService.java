package service;

import model.VideoModel;

import java.util.List;

public interface VideoService {
    void addVideo(VideoModel video);
    List<VideoModel> listVideos();
}