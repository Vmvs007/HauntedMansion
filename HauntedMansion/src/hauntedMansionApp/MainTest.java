/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author vmvs0
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Programa começa
        System.out.println("==================== HAUNTED MANSION STARTS HERE ====================");

       
        //Declara variáveis
        int i = 0;
        Aposento[] mapa= new Aposento[9];
        LinkedMap teste;
        int opcao = 0;
        Game game = null;
        teste = new LinkedMap();
        
        //Menu da aplicacao
        do {
            System.out.println("\n\n==================== HAUNTED MANSION GAME ====================");
            System.out.println("\n                  =========================");
            System.out.println("                  |     1 - Inserir Mapa               |");
            System.out.println("                  |     2 - Ver mapa com a sua coluna  |");
            System.out.println("                  |     3 - Jogar                      |");
            System.out.println("                  |     4 - Classificacoes             |");
            System.out.println("                  |     5 - Opcao 5                    |");
            System.out.println("                  |     6 - Opcao 6                    |");
            System.out.println("                  |     0 - Sair                       |");
            System.out.println("                  =========================\n");

            System.out.print("\n");
            System.out.println("Insira uma opcao:");
            Scanner in = new Scanner(System.in);
            opcao = in.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n\n==================== Inserir Mapa ====================");

                    //Lê o path para o ficheiro
                    System.out.println("\nInserir path do ficheiro: ");
                    Scanner scanner = new Scanner(System.in);
                    String path = scanner.nextLine();
                    System.out.println("\nO path inserido foi " + path);

                    FileManager fm = new FileManager();
                    i= 0;

                    game = fm.readFile(path);
                    
                    while (i < game.getMapa().length) {
                        if (game.getMapa()[i].getLigacoes()[0].equals("entrada"))
                        {
                            game.setEntrada(i);
                        }
                        teste.addVertex(game.getMapa()[i]);
                        
                        //System.out.println(game.getMapa()[i]);
                        i++;
                    }
                    
                    i=0;
                    
                    while (i < game.getMapa().length) {
                        teste.addVertex2(game.getMapa()[i],teste);
                        
                        //Cada iteração que faz tenta encontrar uma solução mais eficaz
                        if (game.getMapa()[i].getLigacoes()[0].equals("exterior"))
                        {
                          teste.dijkstraShortestPath(game.getMapa()[game.getEntrada()]);
                        }
                        
                        i++;
                    }
                    
                    i=0;
                    boolean canPlay=true;
                    
                    System.out.println("\nCaminhos mais curtos para cada saída: \n");

                    while (i < game.getMapa().length) {
                        
                        if (game.getMapa()[i].getLigacoes()[0].equals("exterior"))
                        {
                          double custo = teste.custoShortestPath(game.getMapa()[game.getEntrada()],game.getMapa()[i]);
                            if (custo > game.getPontos()) {
                                canPlay = false;
                            }
                        }
                        
                        i++;
                    }
                    
                    if (canPlay) {
                        System.out.println("\nMapa válido!");
                    }
                    else System.out.println("\nMapa inválido! Por favor use um mapa novo!");
                    
                    
                    
                    break;
                case 2:
                    Aposento a = teste.getVertex("hall");
                    //System.out.println(a.getAposento());
                    System.out.println(teste.printADJMatrix());
                    
                    break;
                case 3:
                    System.out.println(game.getNome());
                    teste.playGame(game.getMapa()[game.getEntrada()], game.getPontos());
                    break;
                case 4:
                    System.out.println("Case 2");
                    break;
                case 5:
                    System.out.println("Case 5");
                    break;
                case 6:
                    System.out.println("Case 5");
                    break;
                case 0:
                    System.out.println("\n\n==================== HAUNTED MANSION CLOSES ====================");
                    break;
                default:
                    System.out.println("\nOpção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}
