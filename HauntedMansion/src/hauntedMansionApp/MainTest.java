/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import dataStructures.ArrayOrderedList;
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
        System.out.println("==================== HAUNTED MANSION STARTS ====================");

        //Declara variáveis
        int i = 0;
        Aposento[] mapa = new Aposento[9];
        LinkedMap teste;
        int opcao = 0;
        Game game = null;
        teste = new LinkedMap();
        ArrayOrderedList<Classificacao> classificacoes = new ArrayOrderedList<Classificacao>();

        /**
         * Insere mapa
         */
        System.out.println("\n\n==================== Inserir Mapa ====================");

        //Lê o path para o ficheiro
        System.out.println("\nInserir path do ficheiro (Exemplo: mapa.json): ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println("\nO path inserido foi " + path);

        FileManager fm = new FileManager();
        i = 0;

        game = fm.readFile(path);

        while (i < game.getMapa().length) {
            if (game.getMapa()[i].getLigacoes()[0].equals("entrada")) {
                game.setEntrada(i);
            }
            teste.addVertex(game.getMapa()[i]);

            //System.out.println(game.getMapa()[i]);
            i++;
        }

        i = 0;

        while (i < game.getMapa().length) {
            teste.addVertex2(game.getMapa()[i], teste);

            //Cada iteração que faz tenta encontrar uma solução mais eficaz
            if (game.getMapa()[i].getLigacoes()[0].equals("exterior")) {
                teste.dijkstraShortestPath(game.getMapa()[game.getEntrada()]);
            }

            i++;
        }

        i = 0;
        boolean canPlay = true;

        if (canPlay) {
            System.out.println("\nMapa válido!");
        } else {
            System.out.println("\nMapa inválido! Por favor use um mapa novo!");
        }

        //Acaba de carregar mapa
        //Menu da aplicacao
        do {
            System.out.println("\n\n====================== HAUNTED MANSION GAME ======================");
            System.out.println("\n                  ================================");
            System.out.println("                   |     1 - Matriz Adjacencias     |");
            System.out.println("                   |     2 - Jogar                  |");
            System.out.println("                   |     3 - Classificacoes         |");
            System.out.println("                   |     4 - Solucoes | Walktrough  |");
            System.out.println("                   |     0 - Sair                   |");
            System.out.println("                    ================================\n");
            System.out.print("\n");
            System.out.println("Insira uma opcao:");
            Scanner in = new Scanner(System.in);
            opcao = in.nextInt();

            switch (opcao) {
                case 1:
                    //Matriz Adj
                    Aposento a = teste.getVertex("hall");
                    System.out.println(a.getAposento());
                    System.out.println(teste.printADJMatrix());

                    break;
                case 2:
                    //Jogar
                    //Escolha do username
                    Scanner usernameInput = new Scanner(System.in);
                    System.out.println("\nInsira o username: ");
                    String username = usernameInput.nextLine();
                    //Nome do jogo
                    System.out.println(username + " vai jogar: " + game.getNome() + " com " + game.getPontos() + " pontos de vida!");
                    int vida = teste.playGame(game.getMapa()[game.getEntrada()], game.getPontos());

                    //Escreve no ficheiro de classificacoes
                    classificacoes.add(new Classificacao(username, vida));
                    break;
                case 3:
                    System.out.println("\n\n==================== Classificacoes ====================");

                    Iterator classificationsITR = classificacoes.iterator();

                    while (classificationsITR.hasNext()) {
                        System.out.println(classificationsITR.next().toString());
                    }

                    break;
                case 4:
                    System.out.println("\n\n==================== SOLUCOES | SHORTEST PATH ====================");
                    System.out.println("\nCaminhos mais curtos para cada saída: \n");

                    while (i < game.getMapa().length) {

                        if (game.getMapa()[i].getLigacoes()[0].equals("exterior")) {
                            double custo = teste.custoShortestPath(game.getMapa()[game.getEntrada()], game.getMapa()[i]);
                            if (custo > game.getPontos()) {
                                canPlay = false;
                            }
                        }

                        i++;
                    }

                    break;
                case 0:
                    JsonWrite.writeJson(game.getNome(), classificacoes);
                    System.out.println("\n\n==================== HAUNTED MANSION CLOSES ====================");
                    break;
                default:
                    System.out.println("\nOpção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}
