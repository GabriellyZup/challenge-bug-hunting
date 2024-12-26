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

            int option = menuHandler.displayMenu(); // Exibe o menu e captura a opção
            menuHandler.getScanner().nextLine();


            if (option == 1) {
                // Adicionar vídeo
                VideoModel video = menuHandler.captureVideo(); // Captura os dados do vídeo
                if (video != null) {
                    videoService.addVideo(video);
                    System.out.println("Vídeo adicionado com sucesso!");
                }

            } else if (option == 3) {
                // Pesquisar vídeo por título
                System.out.print("Digite o título para busca: ");
                String query = menuHandler.getScanner().nextLine();
                List<VideoModel> results = searchStrategy.search(videoService.listVideos(), query);
                for (VideoModel video : results) {
                    System.out.println(video);
                }
            } else if (option == 4) {
                // Sair do sistema
                System.out.println("Saindo do sistema...");
                break;
            } else {
                // Opção inválida
                System.out.println("Opção inválida.");
            }
        }

        // Fecha o scanner ao sair do sistema
        menuHandler.closeScanner();
    }
}
