/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import interfaces.GraphADT;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Ricardo Pereira - 8170495 | Vitor Santos - 8170312
 */

public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 5;
    protected int numVertices; // number of vertices in the graph
    protected double[][] adjMatrix; // adjacency matrix
    protected T[] vertices; //Value of Vertices

    public Graph() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Método que retorna o index de um vértice
     *
     * @param vertex
     * @return index corresponde ao vertice ou -1 caso o vértice não exista na
     * nossa lista de vértices
     */
    public int getVertexIndex(T vertex) {
        int i = 0;
        while (i < vertices.length) {
            if (vertex.equals(vertices[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Método que adiciona vertice
     *
     * @param vertex
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacityArray();
            expandCapacityMatriz();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = 0.;
            adjMatrix[i][numVertices] = 0;
        }
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        //Implementado em NetworkGraph
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        int v1, v2;

        v1 = getVertexIndex(vertex1);
        v2 = getVertexIndex(vertex2);

        adjMatrix[v1][v2] = 0;
        adjMatrix[v2][v1] = 0;
    }

    public boolean indexIsValid(int index) {
        if (vertices[index] != null) {
            return true;
        } else {
            return false;
        }

    }

    public Iterator adjVertex(T vertex) {
        Integer x, startVertexaux, j = 0;
        startVertexaux = getVertexIndex(vertex);

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (startVertexaux == -1) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startVertexaux));
        visited[startVertexaux] = true;

        x = 0;
        while (j < 2) {
            while (!traversalQueue.isEmpty()) {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x.intValue()]);
            }

            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            if (j < 1) {
                for (int i = 0; i < numVertices; i++) {
                    if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                        traversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            }
            j++;
        }

        return resultList.iterator();

    }

    @Override
    public Iterator iteratorBFS(T startVertex) {
        Integer x, startVertexaux;
        startVertexaux = getVertexIndex(startVertex);

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (startVertexaux == -1) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startVertexaux));
        visited[startVertexaux] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);
            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator iteratorDFS(T startVertex) {
        Integer x, startVertexaux;
        boolean found;
        startVertexaux = getVertexIndex(startVertex);

        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startVertexaux)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startVertexaux));
        resultList.addToRear(vertices[startVertexaux]);
        visited[startVertexaux] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;
            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[x.intValue()][i] > 0 && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        Integer x, startVertexaux, targetVertexaux, aux;
        startVertexaux = getVertexIndex(startVertex);
        targetVertexaux = getVertexIndex(targetVertex);

        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<Double>();
        ArrayUnorderedList resultList = new ArrayUnorderedList();
        LinkedStack<Integer> stack = new LinkedStack<Integer>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startVertexaux) || !indexIsValid(targetVertexaux)
                || (startVertexaux == targetVertexaux) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startVertexaux] = 0;
        predecessor[startVertexaux] = 0;
        visited[startVertexaux] = true;
        weight = 0;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && adjMatrix[startVertexaux][i] > 0) {
                pathWeight[i] = pathWeight[startVertexaux] + adjMatrix[startVertexaux][i];
                predecessor[i] = startVertexaux;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }
        if (!traversalMinHeap.isEmpty()) {
            do {
                weight = (traversalMinHeap.removeMin());
                while (!traversalMinHeap.isEmpty()) {
                    traversalMinHeap.removeMin();
                }
                if (weight == Double.POSITIVE_INFINITY) {
                    return resultList.iterator();
                } else {
                    index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                    visited[index] = true;
                }

                for (int i = 0; i < numVertices; i++) {
                    if (!visited[i]) {
                        if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY) && (adjMatrix[index][i] > 0) && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                            pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                            predecessor[i] = index;
                        }
                        traversalMinHeap.addElement(pathWeight[i]);
                    }
                }
            } while (!traversalMinHeap.isEmpty() && !visited[targetVertexaux]);

            index = targetVertexaux;
            stack.push(index);
            do {
                index = predecessor[index];
                stack.push(index);
            } while (index != startVertexaux);

            while (!stack.isEmpty()) {
                aux = stack.pop();

                resultList.addToRear(vertices[aux]);

            }
        }

        return resultList.iterator();
    }

    public int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] < Double.POSITIVE_INFINITY)
                            && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (vertices.length == 0);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        Iterator itr;
        int contador = 0, i = 0;
        boolean re, entrou = false;

        while (i < numVertices) {
            entrou = true;
            itr = iteratorBFS(vertices[i]);

            while (itr.hasNext()) {
                itr.next();
                contador++;
            }
            if (contador < numVertices) {
                return false;
            }
            contador = 0;
            i++;
        }
        if (entrou == true) {
            return true;
        } else {
            return false;
        }

    }

    public Iterator reachableUsers(T vertex) {
        Iterator itr;

        itr = iteratorBFS(vertex);

        itr.next();

        return itr;

    }

    @Override
    public int size() {
        int i = 0;

        while (i < vertices.length && vertices[i] != null) {
            i++;
        }
        return i;
    }

    protected void expandCapacityArray() {
        T[] larger = (T[]) (new Object[numVertices * 2]);

        for (int scan = 0; scan < numVertices; scan++) {
            larger[scan] = vertices[scan];
        }

        vertices = larger;
    }

    protected void expandCapacityMatriz() {
        double[][] larger = (new double[numVertices * 2][numVertices * 2]);

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                larger[i][j] = adjMatrix[i][j];
            }
        }
        adjMatrix = larger;
    }

    public String printADJMatrix() {
        int i = 0, j = 0;
        String matriz = "";
        while (i < adjMatrix.length) {
            while (j < adjMatrix.length) {
                matriz = matriz + adjMatrix[i][j] + " |";
                j++;
            }
            j = 0;
            i++;
            matriz = matriz + "\n";
        }
        return matriz;
    }
}