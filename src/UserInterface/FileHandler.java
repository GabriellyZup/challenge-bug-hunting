package UserInterface;

import model.VideoModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileHandler {
    private final Scanner scanner;

    public FileHandler(){
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Pesquisar vídeo por título");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    //fecha scanner
    public void closeScanner() {
        scanner.close();
    }

    //retorna scanner para capturar entradas adic, se neces
    public Scanner getScanner() {
        return scanner;
    }

    // Captura os dados de um vídeo e retorna um objeto VideoModel
    public VideoModel captureVideo() {
        VideoModel videoModel = null;
        scanner.nextLine();
        
        System.out.println("\n=== Capturar Vídeo ===");
        System.out.print("Digite o título do vídeo: ");
        String title = scanner.nextLine();
        System.out.print("Digite a descrição do vídeo: ");
        String description = scanner.nextLine();
        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite a categoria do video: ");
        String category = scanner.nextLine();
        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);
            videoModel = new VideoModel(title, description, duration, category, dataPublicacao);
            
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo.");
        }
        scanner.nextLine();

       
        return videoModel;
    }
}


