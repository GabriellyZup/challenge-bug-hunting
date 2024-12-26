package UserInterface;

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

    // Fecha o scanner ao sair do programa
    public void closeScanner() {
        scanner.close();
    }

    // Retorna o scanner para capturar entradas adicionais, se necessário
    public Scanner getScanner() {
        return scanner;
    }
}


