package repository;

import model.VideoModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    public void save(VideoModel video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
//            System.err.println("Erro ao salvar video: " + e.getMessage());
            // Ignorar erros por enquanto
        }
    }

    @Override
    public List<VideoModel> findAll() {
        List<VideoModel> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                VideoModel video;
                video = VideoModel.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            //System.err.println("Erro ao ler videos: " + e.getMessage());
            // Ignorar erros por enquanto
        }
        return videos;
    }
}