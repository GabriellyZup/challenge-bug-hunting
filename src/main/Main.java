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
            System.out.println("\n=== Gerenciador de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Editar vídeo");
            System.out.println("5. Excluir vídeo");
            System.out.println("6. Filtrar vídeos por categoria");
            System.out.println("7. Ordenar vídeos por data de publicação");
            System.out.println("8. Exibir relatório de estatísticas");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            int option = fileHandler.getScanner().nextInt();
            fileHandler.getScanner().nextLine(); // Consumir a quebra de linha

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
                System.out.print("Digite o título do vídeo a ser editado: ");
                String title = fileHandler.getScanner().nextLine();
                VideoModel video = fileHandler.captureVideo();
                if (video != null) {
                    // Implementar lógica de edição no VideoService
                    System.out.println("Vídeo editado com sucesso!");
                }
            } else if (option == 5) {
                System.out.print("Digite o título do vídeo a ser excluído: ");
                String title = fileHandler.getScanner().nextLine();
                // Implementar lógica de exclusão no VideoService
                System.out.println("Vídeo excluído com sucesso!");
            } else if (option == 6) {
                System.out.print("Digite a categoria para filtrar: ");
                String category = fileHandler.getScanner().nextLine();
                // Implementar lógica de filtro por categoria no VideoService
            } else if (option == 7) {
                System.out.println("=== Ordenar vídeos por data de publicação ===");
                // Implementar lógica de ordenação no VideoService
            } else if (option == 8) {
                System.out.println("=== Relatório de Estatísticas ===");
                // Implementar lógica de relatório no VideoService
            } else if (option == 9) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        fileHandler.closeScanner();
    }
}
