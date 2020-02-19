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
public class Aposento {

    private String aposento;
    private int fantasma;
    private String[] ligacoes;
    private int entrada;
    private int index;
    private int shield;

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
    

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Aposento(String aposento, int fantasma, String[] ligacoes, int index) {
        this.aposento = aposento;
        this.fantasma = fantasma;
        this.ligacoes = ligacoes;
        this.index = index;
    }

    public String getAposento() {
        return aposento;
    }

    public void setAposento(String aposento) {
        this.aposento = aposento;
    }

    public int getFantasma() {
        return fantasma;
    }

    public void setFantasma(int fantasma) {
        this.fantasma = fantasma;
    }

    public String[] getLigacoes() {
        return ligacoes;
    }

    public void setLigacoes(String[] ligacoes) {
        this.ligacoes = ligacoes;
    }

    @Override
    public String toString() {
        return "\nAposento = " + aposento + "\n"
                + "Fantasma = " + fantasma + "\n"
                + "Ligacoes = " + Arrays.toString(ligacoes) + "\n";
    }
    /*
    // toString para jLabel
    @Override
    public String toString() {
        return "<html>"
                + "Aposento = " + aposento + "<br>"
                + "Fantasma = " + fantasma + "<br>"
                + "Ligacoes = " + Arrays.toString(ligacoes) + "<br>"
                + "}" + "<br>"
                + "</html>";

    }
     */
}
