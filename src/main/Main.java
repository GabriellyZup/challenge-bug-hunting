package main;

import model.VideoModel;
import UserInterface.MenuHandler;
import repository.FileVideoRepository;
import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;
//import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuHandler = menuHandler = new MenuHandler();

        //Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {

            /**
           System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
           System.out.println("1. Adicionar vídeo");
           System.out.println("2. Listar vídeos");
           System.out.println("3. Pesquisar vídeo por título");
           System.out.println("4. Sair");
           System.out.print("Escolha uma opção: ");
             **/

           int option = menuHandler.displayMenu(); // Exibe o menu e captura a opção
           menuHandler.getScanner().nextLine();


            if (option == 1) {
                VideoModel video = videoInputHandler.captureVideo();
                if (video != null) {
                    videoService.addVideo(video);
                    System.out.println("Vídeo adicionado com sucesso!");
                }


                /**
                System.out.println("Digite o título do vídeo: ");
                //String titulo = scanner.nextLine();
                System.out.print("Digite a descrição do vídeo: ");
                //String descricao = scanner.nextLine();
                System.out.print("Digite a duração do vídeo (em minutos): ");
                int duracao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                System.out.print("Digite a categoria do vídeo: ");
                String categoria = scanner.nextLine();
                System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
                String dataStr = scanner.nextLine();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataPublicacao = sdf.parse(dataStr);
                    Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
                    videoService.addVideo(video);
                    System.out.println("Vídeo adicionado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao adicionar vídeo.");
                }
                **/

            } else if (option == 2) {
                List<VideoModel> videos = videoService.listVideos();
                for (VideoModel video : videos) {
                    System.out.println(video);
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

        MenuHandler.closeScanner();
    }
}