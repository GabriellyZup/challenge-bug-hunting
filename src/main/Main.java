package main;

import model.VideoModel;
import UserInterface.MenuHandler;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MenuHandler menuHandler = new MenuHandler();
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {

            int option = menuHandler.displayMenu();
            menuHandler.getScanner().nextLine();


            if (option == 1) {
                VideoModel video = menuHandler.captureVideo();
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
                String query = menuHandler.getScanner().nextLine();
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

        menuHandler.closeScanner();
    }
}
