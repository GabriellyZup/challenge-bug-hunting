package main;

import model.VideoModel;
import UserInterface.FileHandler;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoManager;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        VideoService videoService = new VideoManager(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {
//            System.out.println("\n=== Gerenciador de Vídeos ===");
//            System.out.println("1. Adicionar vídeo");
//            System.out.println("2. Listar vídeos");
//            System.out.println("3. Pesquisar vídeo por título");
//            System.out.println("4. Editar vídeo");
//            System.out.println("5. Excluir vídeo");
//            System.out.println("6. Filtrar vídeos por categoria");
//            System.out.println("7. Ordenar vídeos por data de publicação");
//            System.out.println("8. Exibir relatório de estatísticas");
//            System.out.println("9. Sair");
//            System.out.print("Escolha uma opção: ");
//
//            int option = fileHandler.getScanner().nextInt();
//            fileHandler.getScanner().nextLine();

            int option = fileHandler.displayMenu();

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
                if (results.isEmpty()) {
                    System.out.println("Nenhum vídeo encontrado com o título: " + query);
                } else {
                    results.forEach(System.out::println);
                }
//            }
//
//            else if (option == 4) {
//                System.out.print("Digite o título do vídeo a ser editado: ");
//                String title = fileHandler.getScanner().nextLine();
//                VideoModel video = fileHandler.captureVideo();
//                if (video != null) {
//                    // Implementar lógica de edição no VideoService
//                    System.out.println("Vídeo editado com sucesso!");
//                }
//            } else if (option == 5) {
//                System.out.print("Digite o título do vídeo a ser excluído: ");
//                String title = fileHandler.getScanner().nextLine();
//                // Implementar lógica de exclusão no VideoService
//                System.out.println("Vídeo excluído com sucesso!");
            }else if (option == 4) {
                System.out.print("Digite o título do vídeo a ser editado: ");
                String title = fileHandler.getScanner().nextLine();
                VideoModel updatedVideo = fileHandler.captureVideo();
                if (updatedVideo != null) {
                    if (videoService.editVideo(title, updatedVideo)) {
                        System.out.println("Vídeo editado com sucesso!");
                    } else {
                        System.out.println("Vídeo não encontrado.");
                    }
                }
            } else if (option == 5) {
                System.out.print("Digite o título do vídeo a ser excluído: ");
                String title = fileHandler.getScanner().nextLine();
                if (videoService.deleteVideo(title)) {
                    System.out.println("Vídeo excluído com sucesso!");
                } else {
                    System.out.println("Vídeo não encontrado.");
                }
            }


            else if (option == 6) {
                System.out.print("Digite a categoria para filtrar: ");
                String category = fileHandler.getScanner().nextLine();
                List<VideoModel> filteredVideos = videoService.filterVideosByCategory(category);
                if (filteredVideos.isEmpty()) {
                    System.out.println("Nenhum vídeo encontrado para a categoria: " + category);
                } else {
                    System.out.println("=== Vídeos na categoria: " + category + " ===");
                    filteredVideos.forEach(System.out::println);
                }
            } else if (option == 7) {
                System.out.println("=== Ordenar vídeos por data de publicação ===");
                System.out.print("Deseja ordenar em ordem reversa? (sim/não): ");
                String reverseInput = fileHandler.getScanner().nextLine();
                boolean reverse = reverseInput.equalsIgnoreCase("sim");
                List<VideoModel> sortedVideos = videoService.sortVideosByPublicationDate(reverse);
                if (sortedVideos.isEmpty()) {
                    System.out.println("Nenhum vídeo encontrado.");
                } else {
                    sortedVideos.forEach(System.out::println);
                }
            } else if (option == 8) {
                System.out.println("=== Relatório de Estatísticas ===");
                Map<String, Object> stats = videoService.generateStatistics();
                System.out.println("Total de vídeos: " + stats.get("Total de videos"));
                System.out.println("Duração total: " + stats.get("Duração total") + " minutos");
                System.out.println("Vídeos por categoria:");
                Map<String, Long> videosPorCategoria = (Map<String, Long>) stats.get("Videos por categoria");
                for (Map.Entry<String, Long> entry : videosPorCategoria.entrySet()) {
                    System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " vídeo(s)");
                }
            } else if (option == 9) {
                System.out.println("Saindo do sistema...");
                fileHandler.closeScanner();
                return;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        //fileHandler.closeScanner();
    }
}
