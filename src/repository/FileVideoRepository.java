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
                //addHeaderToFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Falha ao incializar o  repositório: " + e.getMessage(), e);
        }
    }

    public void save(VideoModel video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString() +";");
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar video: " + e.getMessage(), e);
        }
    }

    public void saveInvalid(String error, VideoModel video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(video.toString() + "; ERRO - " + error);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar video invalido: " + e.getMessage(), e);
        }
    }

    public List<VideoModel> findAll() {
        List<VideoModel> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                try{
                    if (line.endsWith(";")) {
                        videos.add(VideoModel.fromString(line));
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao processar a linha: " + e.getMessage(), e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler videos: " + e.getMessage(), e);
        }
        return videos;
    }

    public void saveAll(List<VideoModel> videos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (VideoModel video : videos) {
                bw.write(video.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar lista de videos: " + e.getMessage(), e);
        }
    }

    public void deletedByTitle(String title) {
        List<VideoModel> videos = findAll();
        List<VideoModel> updatedVideos = videos.stream()
                .filter(video -> !video.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());

        saveAll(updatedVideos);
    }
}
