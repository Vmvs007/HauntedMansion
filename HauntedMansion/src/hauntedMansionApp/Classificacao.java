/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

/**
 *
 * @author vmvs0
 */
public class Classificacao implements Comparable<Classificacao>{

    private String username;
    private int vida;

    public Classificacao(String username, int vida) {
        this.username = username;
        this.vida = vida;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    @Override
    public int compareTo(Classificacao comparClassification) {
       return this.vida - comparClassification.vida;
    }

    @Override
    public String toString() {
        return "Classificacao{" + "username=" + username + ", vida=" + vida + '}';
    }
    

}
