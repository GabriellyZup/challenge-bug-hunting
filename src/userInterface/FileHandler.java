package userInterface;

import model.VideoModel;
import model.VideoParser;
import service.VideoManager;
import service.VideoService;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private final Scanner scanner;
    private final VideoManager videoManager;


    //private String publicationDataStr;

    public FileHandler(VideoManager videoManager) /*throws ParseException */{
        this.scanner = new Scanner(System.in);
        this.videoManager = videoManager;
    }


    public int displayMenu() {
        while (true) {
            try{
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
                //String input = scanner.nextLine().trim();
                int option = Integer.parseInt(scanner.nextLine().trim());
                if (option >= 1 && option <= 9) {
                    return option;
                } else {
                    System.out.println("Opção inválida. Por favor, escolha um número entre 1 e 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
    }

    public void handleAddVideo(){
        VideoModel video = captureVideo();
        if (video != null){
            videoManager.addVideo(video);
            System.out.println("Video adicionado com sucesso!");
        }
    }

    public void handleListVideos(){
        List<VideoModel> videos = videoManager.listVideos();
        if (videos.isEmpty()){
            System.out.println("Nenhum video encontrado.");
        } else {
            System.out.println("\n=== Lista de Videos ===");
            videos.forEach(System.out::println);
        }
    }

    public void handleSearchByTitle(){
        String query = prompt("Digite o título para busca: ");
        List<VideoModel> results = videoManager.searchByTitle(query);
        if (results.isEmpty()) {
            System.out.println("Nenhum video encontrado com o título: " + query);
        } else {
            System.out.println("\n=== Resultados da Busca ===");
            results.forEach(System.out::println);
        }
    }

    public void handleEditVideo(){
        String title = prompt("Digite o titulo do video a ser editado: ");
        VideoModel updatedVideo = captureVideo();
        if (updatedVideo != null) {
            boolean success = videoManager.editVideo(title, updatedVideo);
            System.out.println(success ? "Video editado com sucesso!" : "Video não encontrado.");
        }
    }

    public void handleDeleteVideo(){
        String title = prompt("Digite o titulo do video a ser excluido: ");
        boolean success = videoManager.deleteVideo(title);
        System.out.println(success ? "Video excluido com sucesso" : "Video não encontrado.");
    }

    public void handleFilterByCategory() {
        String category = prompt("Digite a categoria para filtrar: ");
        List<VideoModel> filteredVideos = videoManager.filterVideosByCategory(category);
        if (filteredVideos.isEmpty()){
            System.out.println("Nenhum video encontrado para a categoria: " + category);
        } else {
            System.out.println("=== Videos na categoria: " + category);
            filteredVideos.forEach(System.out::println);
        }
    }

    public void handledSortByPublicationDate(){
        boolean reverse = confirm("Deseja ordenar em ordem reversa? (sim/não): ");
        List<VideoModel> sortedVideos = videoManager.sortVideosByPublicationDate(reverse);
        if (sortedVideos.isEmpty()){
            System.out.println("Nenhum video encontrado para ser organizado.");
        } else {
            System.out.println("\n=== Videos Ordenados por Data ===");
            sortedVideos.forEach(System.out::println);
        }
    }


    public void handleStatisticReport() {
        System.out.println(("\n=== Relatório de Estatísticas ==="));
        var statistics = videoManager.generateStatistics();
        System.out.println("Total de videos: " + statistics.get("totalVideos"));
        System.out.println("Duração total: " + statistics.get("totalDuration") + " minutos");

        System.out.println("Videos por categoria: ");
        var categoryCount = (java.util.Map<String, Long>) statistics.get("videosByCategory");
        categoryCount.forEach((category, count) ->
                System.out.println("- " + category + ": " + count + "video(s)"));
    }

    public void closeScanner() {
        scanner.close();
    }


    public String prompt(String message){
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public boolean confirm(String message) {
        System.out.print(message);
        String response = scanner.nextLine().trim();
        return response.equalsIgnoreCase("sim");
    }

    // Captura os dados de um vídeo e retorna um objeto VideoModel
    public VideoModel captureVideo() {

        //while (true){
            try{
            System.out.println("\n=== Incluir Vídeo ===");
            String title = prompt("Digite o título do vídeo: ");
//            if (title.isEmpty()) {
//                throw new IllegalArgumentException("Erro: O título não pode estar vazio. ");
//            }

            String description = prompt("Digite a descrição do vídeo: ");
//            if (description.isEmpty()){
//                throw new IllegalArgumentException("Erro: A descrição não pode estar vazias. ");
//            }

            int duration = Integer.parseInt(prompt("Digite a duração do vídeo em minutos: "));
//            if (durationInput != null && durationInput.matches("\\d+")){
//                int duration = Integer.parseInt(durationInput);
//                if (duration <= 0) {
//                    throw new IllegalArgumentException("Erro: Digite a duração em minutos (numeros inteiros e positivos). ");
//                }
//            }
//            int duration = Integer.parseInt(durationInput);
//            if (duration <= 0) {
//                throw new IllegalArgumentException("Erro: A duração deve ser maior que zero. ");
//                }

            String categoryInput = prompt("Digite a categoria do video (FILME, SERIE, DOCUMENTARIO: )");
            VideoModel.VideoCategory category = VideoParser.validateAndParseCategory(categoryInput.toUpperCase().trim());

            String publicationDate = prompt("Digite a data de publicação (dd/mm/yyyy): ");
         //   VideoParser.parseDate(publicationDate);

            return new VideoModel(title, description, duration, category, publicationDate);
            } catch (Exception e) {
                        System.out.println("Erro ao carregar os dados do vídeo: " + e.getMessage());
                        System.out.println("Por favor, tente novamente.");
            }           return null;
        }
    }

//    public void closeScanner(){
//        scanner.close();
//    }


        // esse \\d+ é para segurar em somente numeros inteiros.





//        VideoModel videoModel = null;
//        scanner.nextLine();
        
//
//             //String title = scanner.nextLine().trim()
//            String description = prompt("Digite a descrição do vídeo: ");
//            int duration = Integer.parseInt(prompt("Digite a duração do vídeo (em minutos): "));
//            String categoryInput = prompt("Digite a categoria do vídeo (FILME, SERIE, DOCUMENTARIO): ");
//            VideoModel.VideoCategory category = VideoParser.validateAndParseCategory(categoryInput);
//             String publicationDate = prompt("Digite a data de publicação (dd/mm/yyyy): ");
//            return new VideoModel(title, description, duration, category , publicationDate);
//        } catch (Exception e) {
//            System.out.println("Erro ao capturar os dados do video: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public void closeScanner() {
//        scanner.close();
//    }
//
//         = scanner.nextLine().trim();
//        System.out.print("Digite a duração do vídeo (em minutos): ");
//        int duration /*= scanner.nextInt()*/;
//        try {
//            duration = Integer.parseInt(scanner.nextLine().trim());
//            if (duration <= 0) {
//                throw new IllegalArgumentException("A duração deve ser maior que zero.");
//            }
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("A duração deve ser um número inteiro válido.");
//        }
//
//
//        //scanner.nextLine();
//        System.out.println("Digite a categoria do video (FILME, SERIE, DOCUMENTÁRIO): ");
//        String category = VideoParser.validateCategory(scanner.nextLine().trim());
//        //String category = scanner.nextLine().trim().toUpperCase();
//        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
//        Date publicationDate = VideoParser.parseDate(scanner.nextLine().trim());
//
//        //String publicationDate = scanner.nextLine().trim();
//
//        //return new VideoModel(title, description, duration, category, publicationDate);
//        //}
//
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            //Date publicationDate = sdf.parse(publicationDataStr);
//            //return new VideoModel(title, description, duration, category, publicationDate);
//        } catch (Exception e) {
//            System.out.println("Data de publicação inválida. Use o formato dd/mm/aaaa: ");
//
//            //return null;

    
    
    


