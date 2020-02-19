/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import dataStructures.ArrayOrderedList;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.parser.JSONParser;

/**
 * @author Crunchify.com How to write JSON object to File in Java?
 */
public class JsonWrite {

    private static FileWriter file;

    @SuppressWarnings("unchecked")
    public static String writeJson(String nome, ArrayOrderedList classificacoes) {

        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONObject obj_username = new JSONObject();
        Iterator classificationsITR = classificacoes.iterator();

        while (classificationsITR.hasNext()) {
            Classificacao atual = (Classificacao) classificationsITR.next();
            obj_username.put(atual.getUsername(), atual.getVida());
        }

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(nome + ".txt", true);

            file.write(obj_username.toJSONString());
            Log("Successfully Copied JSON Object to File...");
            Log("\nJSON Object: " + obj_username);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "Sucess write";
    }

    /**
     * 
     * @param path
     * @return 
     */
    /*
    @SuppressWarnings("unchecked")
    public static ArrayOrderedList readJson(String path) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject classificacoes = (JSONObject) parser.parse(new FileReader(path));
            
           
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

    
                    JSONArray ligacoesArray = (JSONArray) ((JSONObject) us).get("ligacoes");
                    //Declaração de um array de Strings com o tamanho igual ao número de ligacoes do aposento
                    String[] ligacoes = (new String[ligacoesArray.size()]);

                    int contaLigacoes;

                    //Ciclo que corre para cada Ligacao do Aposento
                    for (contaLigacoes = 0; contaLigacoes < ligacoesArray.size(); contaLigacoes++) {
                        ligacoes[contaLigacoes] = ligacoesArray.get(contaLigacoes).toString();

                    }

                    mapa[contaAposentos] = new Aposento(aposento, fantasma, ligacoes, contaAposentos);
                    contaAposentos++;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFound");
        } catch (IOException | ParseException e) {
            System.out.println("Exception");
        }
    }
*/


static public void Log(String str) {
        System.out.println("str");
    }

    
}
