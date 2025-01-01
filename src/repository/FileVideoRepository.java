package repository;

import model.VideoModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileVideoRepository implements VideoRepository {
    private final File file;

    public FileVideoRepository(String filePath) {
        this.file = new File(filePath);
        try{
            if (!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Falha ao incializar o  repositório: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletedByTitle(String title) {
        deletedByTitle(title);
    }

    @Override
    public void save(VideoModel video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar video: " + e.getMessage());
            // Ignorar erros por enquanto
        }
    }

    @Override
    public List<VideoModel> findAll() {
        List<VideoModel> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try{
                    VideoModel video = VideoModel.fromString(line);
                    if (video != null) {
                        videos.add(video);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao processaro a linha de entrada: " + e.getMessage(), e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler videos: " + e.getMessage(), e);
            // Ignorar erros por enquanto
        }
        return videos;
    }

    // Novo método saveAll para salvar uma lista de vídeos
    @Override
    public void saveAll(List<VideoModel> videos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (VideoModel video : videos) {
                bw.write(video.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar lista de videos: " + e.getMessage(), e);                  // Ignorar erros por enquanto
        }
    }
    //@Override
    public void deleteByTitle(String title) {
        List<VideoModel> videos = findAll(); // Load all videos
        List<VideoModel> updatedVideos = videos.stream()
                .filter(video -> !video.getTitle().equalsIgnoreCase(title)) // Exclude the video with the given title
                .collect(Collectors.toList());

        saveAll(updatedVideos); // Save the updated list back to the file
    }
}
