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

            //Declaração de variáveis para armazenamento de JSONObjects (nome, aposento, pontos, fantasma)
            int pontos, fantasma;
            String nome, aposento;

            //Declaração do JSONObject que vai conter o ficheiro completo
            JSONObject jogo = (JSONObject) parser.parse(new FileReader(path));

            //Declaração do JSONArray que vai conter todos os Aposentos (Grafo Mapa)
            JSONArray grafoMapa = (JSONArray) jogo.get("mapa");

            //Declaração de um array de Map com o tamanho igual ao número de Aposentos no Grafo Mapa
            Aposento[] mapa = new Aposento[grafoMapa.size()];

            //Declaração de um contador de Aposentos
            int contaAposentos = 0;

            nome = (String) ((JSONObject) jogo).get("nome"); //Guarda numa variável (String nome) o JSONObject (nome)
            pontos = toIntExact((Long) ((JSONObject) jogo).get("pontos")); //Guarda numa variável (Int pontos) o JSONObject (pontos)

            //Ciclo que corre para cada User no Grafo Social
            for (Object us : grafoMapa) {
                if (us instanceof JSONObject) {
                    aposento = (String) ((JSONObject) us).get("aposento"); //Guarda numa variável (String aposento) o JSONObject (aposento)
                    fantasma = toIntExact((Long) ((JSONObject) us).get("fantasma")); //Guarda numa variável (Int fantasma) o JSONObject (fantasma)

                    //Declaração de um JSONArray que guarda as Skills de um User
                    JSONArray ligacoesArray = (JSONArray) ((JSONObject) us).get("ligacoes");
                    //Declaração de um array de Strings com o tamanho igual ao número de ligacoes do aposento
                    String[] ligacoes = (new String[ligacoesArray.size()]);

                    int contaLigacoes;

                    //Ciclo que corre para cada Ligacao do Aposento
                    for (contaLigacoes = 0; contaLigacoes < ligacoesArray.size(); contaLigacoes++) {
                        ligacoes[contaLigacoes] = ligacoesArray.get(contaLigacoes).toString();

                    }
                    
                    mapa[contaAposentos] = new Aposento(aposento, fantasma, ligacoes);
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
