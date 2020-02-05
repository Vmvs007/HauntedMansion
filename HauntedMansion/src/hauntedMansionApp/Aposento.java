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

    public Aposento(String aposento, int fantasma, String[] ligacoes) {
        this.aposento = aposento;
        this.fantasma = fantasma;
        this.ligacoes = ligacoes;
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
        return "<html>"
                + "Aposento = " + aposento + "<br>"
                + "Fantasma = " + fantasma + "<br>"
                + "Ligacoes = " + Arrays.toString(ligacoes) + "<br>"
                + "}" + "<br>"
                + "</html>";

    }
}
