package UserInterface;

import model.VideoModel;
import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner;

    public MenuHandler(){
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
        scanner.nextLine();
        System.out.println("\n=== Capturar Vídeo ===");
        System.out.print("Digite o título do vídeo: ");
        String title = scanner.nextLine();
        System.out.print("Digite a descrição do vídeo: ");
        String description = scanner.nextLine();
        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        // Retorna um novo objeto VideoModel com os dados capturados
        return new VideoModel(title, description, duration);
    }
}


