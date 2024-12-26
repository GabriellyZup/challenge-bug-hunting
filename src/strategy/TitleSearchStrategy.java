package strategy;

import model.VideoModel;

import java.util.List;
import java.util.stream.Collectors;

public class TitleSearchStrategy implements SearchStrategy {
    @Override
    public List<VideoModel> search(List<VideoModel> videos, String query) {
        return videos.stream()
                .filter(video -> video.getTitle().toLowerCase().contains(query.toLowerCase()))
                //ver se mantem ou se substitui em
                //.filter(video -> video.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
}