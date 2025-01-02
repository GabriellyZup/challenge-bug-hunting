package userInterface;

import model.VideoModel;
import model.VideoParser;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileHandler {
    private final Scanner scanner;
    
    //private String publicationDataStr;

    public FileHandler() /*throws ParseException */{
        this.scanner = new Scanner(System.in);
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

//    public void closeScanner() {
//        scanner.close();
//    }
//
//    //retorna scanner para capturar entradas adic, se neces
//    public Scanner getScanner() {
//        return scanner;
    
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

        while (true){
            try{
            System.out.println("\n=== Incluir Vídeo ===");
            String title = prompt("Digite o título do vídeo: ");
            if (title.isEmpty()) {
                throw new IllegalArgumentException("Erro: O título não pode estar vazio. ");
            }

            String description = prompt("Digite a descrição do vídeo: ");
            if (description.isEmpty()){
                throw new IllegalArgumentException("Erro: A descrição não pode estar vazias. ");
            }

            String durationInput = prompt("Digite a duração dos vídeos(em minutos): ");
            if (durationInput != null && durationInput.matches("\\d+")){
                int duration = Integer.parseInt(durationInput);
                if (duration <= 0) {
                    throw new IllegalArgumentException("Erro: Digite a duração em minutos (numeros inteiros e positivos). ");
                }
            }
            int duration = Integer.parseInt(durationInput);
            if (duration <= 0) {
                throw new IllegalArgumentException("Erro: A duração deve ser maior que zero. ");
                }

            String categoryInput = prompt("Digite a categoria do video (FILME, SERIE, DOCUMENTARIO: )");
            VideoModel.VideoCategory category = VideoParser.validateAndParseCategory(categoryInput);

            String publicationDate = prompt("Digite a data de publicação (dd/mm/yyyy): ");
            VideoParser.parseDate(publicationDate);

            return new VideoModel(title, description, duration, category, publicationDate);
            } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Por favor, tente novamente.");
            }
        }
    }

    public void closeScanner(){
        scanner.close();
    }


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
        }
    
    
    


