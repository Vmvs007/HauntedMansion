/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.toIntExact;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class FileManager {

    /**
     * Método para ler um ficheiro JSON, incluindo os JSONObjects e JSONArrays
     * contidos no mesmo
     *
     * @param path
     * @return
     */
    public Game readFile(String path) {

        JSONParser parser = new JSONParser();
        Game gamenull = null;

        try {

            //Declaração de variáveis para armazenamento de JSONObjects (id, idade, views, nome e email)
            int pontos, fantasma;
            String nome, aposento;

            //Declaração do JSONObject que vai conter o ficheiro completo
            JSONObject jogo = (JSONObject) parser.parse(new FileReader(path));

            //Declaração do JSONArray que vai conter todos os Users (Grafo Social)
            JSONArray grafoMapa = (JSONArray) jogo.get("mapa");

            //Declaração de um array de Users com o tamanho igual ao número de Users no Grafo Social
            Map[] mapa = new Map[grafoMapa.size()];

            //Declaração de um contador de Users
            int contaAposentos = 0;

            nome = (String) ((JSONObject) jogo).get("nome"); //Guarda numa variável (String nome) o JSONObject (nome)
            pontos = toIntExact((Long) ((JSONObject) jogo).get("pontos")); //Guarda numa variável (Int idade) o JSONObject (idade)

            //Ciclo que corre para cada User no Grafo Social
            for (Object us : grafoMapa) {
                if (us instanceof JSONObject) {
                    aposento = (String) ((JSONObject) us).get("aposento"); //Guarda numa variável (String nome) o JSONObject (nome)
                    fantasma = toIntExact((Long) ((JSONObject) us).get("fantasma")); //Guarda numa variável (Int idade) o JSONObject (idade)

                    //Declaração de um JSONArray que guarda as Skills de um User
                    JSONArray ligacoesArray = (JSONArray) ((JSONObject) us).get("ligacoes");
                    //Declaração de um array de Strings com o tamanho igual ao número de Skills do User
                    String[] ligacoes = (new String[ligacoesArray.size()]);

                    int contaLigacoes;

                    //Ciclo que corre para cada Skill do User
                    for (contaLigacoes = 0; contaLigacoes < ligacoesArray.size(); contaLigacoes++) {
                        ligacoes[contaLigacoes] = ligacoesArray.get(contaLigacoes).toString();

                    }
                    
                    mapa[contaAposentos] = new Map(aposento, fantasma, ligacoes);
                    contaAposentos++;
                }
            }

            Game game = new Game(nome, pontos, mapa);

            return game;

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFound");
        } catch (IOException | ParseException e) {
            System.out.println("Exception");
        }
        //Retorno em caso de não haverem Users no Grafo Social
        return gamenull;
    }

}
