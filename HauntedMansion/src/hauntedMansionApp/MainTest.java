/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author vmvs0
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Lê o path para o ficheiro JSON
        String path = ("mapa.json");

        Game game;
        int i = 0;

        FileManager fm = new FileManager();
        game = fm.readFile(path);

        System.out.println(game.toString());
        System.out.println("");
        i++;
    }
}

