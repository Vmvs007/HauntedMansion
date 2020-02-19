/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import java.util.Arrays;

/**
 *
 * @author vmvs0
 */
public class Game {

    private String nome;
    private int pontos;
    private Aposento[] mapa;
    private int entrada;

    public int getEntrada() {
        return entrada;
    }

    public Game(String nome, int pontos, Aposento[] mapa) {
        this.nome = nome;
        this.pontos = pontos;
        this.mapa = mapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Aposento[] getMapa() {
        return mapa;
    }

    public void setMapa(Aposento[] mapa) {
        this.mapa = mapa;
    }

    public void setEntrada(int index) {
        this.entrada = index;
    }

    @Override
    public String toString() {
        return "\nGame :" + "\n"
                + "Nome = " + nome + "\n"
                + "Pontos = " + pontos + "\n"
                + "Mapa = " + Arrays.toString(mapa) + "\n";
    }
    /*
    // toString para jLabel
    @Override
    public String toString() {
        return "<html>"
                + "Game {" + "<br>"
                + "Nome = " + nome + "<br>"
                + "Pontos = " + pontos + "<br>"
                + "Mapa = " + Arrays.toString(mapa) + "<br>"
                + "}" + "<br>"
                + "</html>";
    }
     */
}
