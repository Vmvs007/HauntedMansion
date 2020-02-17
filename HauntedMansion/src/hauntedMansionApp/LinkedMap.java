/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedMansionApp;

import dataStructures.Graph;
import interfaces.MapADT;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class LinkedMap<T> extends Graph<T> implements MapADT<T> {

    public LinkedMap() {
        super();
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getVertexIndex(vertex1), getVertexIndex(vertex2), weight);
    }

    public void addEdge(int vertex1, int vertex2, double weight) {
        if (vertex1 == -1 || vertex2 == -1) {
            System.out.println("Vértices Inválidos!");
        } else {

            adjMatrix[vertex1][vertex2] = weight;

        }

    }

    /**
     * Método que calcula o peso de cada vértice
     */
    public void calculateWeigth() {
        int i = 0, j = 0;
        Aposento[] mapa = new Aposento[vertices.length];
        String[] ligacoes;

        while (i < vertices.length) {
            mapa[i] = (Aposento) vertices[i];
            i++;
        }

        i = 0;

        while (i < numVertices) {

            ligacoes = mapa[i].getLigacoes();

            while (j < ligacoes.length) {

                addEdge((T) mapa[i], (T) getVertex(ligacoes[j]), (1.0 / getVertex(ligacoes[j]).getFantasma()));
                j++;
            }
            j = 0;
            i++;
        }

    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
