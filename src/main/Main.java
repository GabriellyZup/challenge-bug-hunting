package main;

import model.VideoModel;
import UserInterface.FileHandler;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoManager;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        VideoService videoService = new VideoManager(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {

            int option = fileHandler.displayMenu();
            fileHandler.getScanner().nextLine();


            if (option == 1) {
                VideoModel video = fileHandler.captureVideo();
                if (video != null) {
                    videoService.addVideo(video);
                    System.out.println("Vídeo adicionado com sucesso!");
                }
            } else if (option == 2) {
                System.out.println("=== Lista de Vídeos ===");
                List<VideoModel> videos = videoService.listVideos();
                if (videos.isEmpty()) {
                    System.out.println("Nenhum vídeo encontrado.");
                } else {
                    for (VideoModel video : videos) {
                        System.out.println(video);
                    }
                }
            } else if (option == 3) {
                System.out.print("Digite o título para busca: ");
                String query = fileHandler.getScanner().nextLine();
                List<VideoModel> results = searchStrategy.search(videoService.listVideos(), query);
                for (VideoModel video : results) {
                    System.out.println(video);
                }
            } else if (option == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        fileHandler.closeScanner();
    }
}
