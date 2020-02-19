/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

import hauntedMansionApp.*;
import interfaces.GraphADT;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_CAPACITY = 5;
    protected int numVertices; // number of vertices in the graph
    protected double[][] adjMatrix; // adjacency matrix
    protected T[] vertices; //Value of Vertices

    int[] verticesTabela;
    Double[] distanciaMaisCurtaDoStartVertex;
    int[] verticeAnterior;

    public int[] getVerticesTabela() {
        return verticesTabela;
    }

    public void setVerticesTabela(int[] verticesTabela) {
        this.verticesTabela = verticesTabela;
    }

    public Double[] getDistanciaMaisCurtaDoStartVertex() {
        return distanciaMaisCurtaDoStartVertex;
    }

    public void setDistanciaMaisCurtaDoStartVertex(Double[] distanciaMaisCurtaDoStartVertex) {
        this.distanciaMaisCurtaDoStartVertex = distanciaMaisCurtaDoStartVertex;
    }

    public int[] getVerticeAnterior() {
        return verticeAnterior;
    }

    public void setVerticeAnterior(int[] verticeAnterior) {
        this.verticeAnterior = verticeAnterior;
    }

    public Graph() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public void setVertex(T vertex) {
        Aposento[] map = new Aposento[numVertices];
        Aposento aposento = (Aposento) vertex;
        int i = 0;

        while (i < numVertices) {
            map[i] = (Aposento) vertices[i];
            i++;
        }

        i = 0;

        while (i < numVertices) {
            if (aposento.getAposento() == map[i].getAposento()) {
                vertices[i] = (T) aposento;
            }
            i++;
        }
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
        Aposento ver = (Aposento) vertex;

        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = -1;
            adjMatrix[i][numVertices] = -1;
        }
        numVertices++;
    }

    public void addVertex2(T vertex, LinkedMap map) {
        if (numVertices == vertices.length) {
            expandCapacityArray();
            expandCapacityMatriz();
        }
        Aposento ver = (Aposento) vertex;

        //Peso de si próprio
        //adjMatrix[ver.getIndex()][ver.getIndex()] = ver.getFantasma();
        for (int i = 0; i < ver.getLigacoes().length; i++) {
            //System.out.println(ver.getLigacoes()[i]);
            if (!(ver.getLigacoes()[i].equals("entrada")) && !(ver.getLigacoes()[i].equals("exterior"))) {
                //System.out.println("OLA " + ver.getLigacoes()[i]);
                Aposento aux = map.getVertex(String.valueOf(ver.getLigacoes()[i]));
                // System.out.println(aux.getAposento());
                adjMatrix[ver.getIndex()][aux.getIndex()] = aux.getFantasma();
                //System.out.println(aux.getFantasma());
            }

        }

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

    /**
     * Método que retorna um aposento
     *
     * @param aposento
     * @return
     */
    public Aposento getVertex(String aposento) {
        int i = 0;
        Aposento[] map = new Aposento[vertices.length];

        while (vertices[i] != null) {
            map[i] = (Aposento) vertices[i];
            if (map[i].getAposento().equals(aposento)) {
                return map[i];
            }
            i++;

        }

        return null;
    }

    /**
     * Método que retorna um aposento
     *
     * @param index
     * @return
     */
    public Aposento getVertexByIndex(int index) {
        int i = 0;
        Aposento[] map = new Aposento[vertices.length];

        while (vertices[i] != null) {
            map[i] = (Aposento) vertices[i];
            if (map[i].getIndex() == index) {
                return map[i];
            }
            i++;

        }

        return null;
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
                    if (adjMatrix[x.intValue()][i] >= 0 && !visited[i]) {
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

    public Iterator gameSimulation(T vertex, String ligacao) {
        Iterator connectedAposentos;
        Iterator[] shortPath;
        Aposento aux;
        Aposento[] map = new Aposento[numVertices];
        LinkedHeap teste = new LinkedHeap();
        ArrayUnorderedList aposentosLigados = new ArrayUnorderedList();
        ArrayUnorderedList aposentos1 = new ArrayUnorderedList();
        ArrayUnorderedList aposentos2 = new ArrayUnorderedList();
        String[] ligacoes;
        double[][] indexWeight = new double[numVertices][2];
        double auxD;
        T auxT;
        int auxI;

        int i = 0, j = 0, z = 0;

        connectedAposentos = iteratorBFS(vertex);
        while (connectedAposentos.hasNext()) {
            map[i] = (Aposento) connectedAposentos.next();
            i++;
        }

        while (j < i) {
            ligacoes = map[j].getLigacoes();
            while (z < ligacoes.length) {
                if (ligacoes[z].equals(ligacao)) {
                    aposentosLigados.addToRear(map[j]);
                }
                z++;
            }
            z = 0;
            j++;
        }

        j = 0;
        shortPath = new Iterator[aposentosLigados.size()];

        while (!aposentosLigados.isEmpty()) {
            auxT = (T) aposentosLigados.removeFirst();
            shortPath[j] = iteratorShortestPath(vertex, auxT);

            indexWeight[j][1] = calculatePathWeigth(shortPath[j]);
            teste.addElement(indexWeight[j][1]);

            shortPath[j] = iteratorShortestPath(vertex, auxT);

            while (shortPath[j].hasNext()) {
                aux = (Aposento) shortPath[j].next();
                indexWeight[j][0] = aux.getFantasma();
            }
            j++;
        }
        i = 0;

        while (!teste.isEmpty()) {
            auxD = (double) teste.removeMin();
            while (i < j) {
                if (indexWeight[i][1] == auxD) {
                    aposentos1.addToRear(indexWeight[i][0]);

                }
                i++;
            }
            i = 0;
        }

        while (!aposentos1.isEmpty()) {

            auxD = (double) aposentos1.removeFirst();
            auxI = (int) auxD;
            //aposentos2.addToRear(getVertex(auxI));
        }

        return aposentos2.iterator();
    }

    public void orderIndexWeight(double[][] indexWeight, int size) {
        int i = 0;

    }

    public double calculatePathWeigth(Iterator path) {
        Aposento[] map = new Aposento[numVertices];
        Iterator pathAux;
        pathAux = path;
        int i = 0, j = 0;
        double weight = 0;

        while (pathAux.hasNext()) {
            map[i] = (Aposento) pathAux.next();
            i++;
        }

        i--;
        while (j < i) {
            weight = weight + (1.0 / map[j + 1].getFantasma());
            j++;
        }

        System.out.println(weight);
        return weight;
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

    public Iterator reachableAposentos(T vertex) {
        Iterator itr;

        itr = iteratorBFS(vertex);

        itr.next();

        return itr;

    }

    public Iterator unreachableAposentos(T vertex) {
        Iterator itr;
        Aposento[] mapAux = new Aposento[size()];
        ArrayUnorderedList lista = new ArrayUnorderedList();
        ArrayUnorderedList resultList = new ArrayUnorderedList();

        int i = 0, j = 0;

        itr = iteratorBFS(vertex);

        while (itr.hasNext()) {
            lista.addToFront((Aposento) itr.next());
        }

        i = 0;

        while (i < size()) {
            if (!lista.contains(vertices[i])) {
                resultList.addToRear((Aposento) vertices[i]);
            }
            i++;
        }

        return resultList.iterator();

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
        while (i < this.numVertices) {
            while (j < this.numVertices) {
                matriz = matriz + adjMatrix[i][j] + " |";
                j++;
            }
            matriz += " - " + this.vertices[i];
            j = 0;
            i++;
            matriz = matriz + "\n";
        }
        return matriz;
    }

    /**
     *
     * @param startVertex
     */
    public void dijkstraShortestPath(T startVertex) {

        Aposento start = (Aposento) startVertex;
        //Aposento finish = (Aposento) targetVertex;

        ArrayList<Integer> visitados = new ArrayList<Integer>();
        ArrayList<String> porVisitar = new ArrayList<String>();

        //Tabela de decisão
        int[] verticesTabela = new int[vertices.length];
        Double[] distanciaMaisCurtaDoStartVertex = new Double[vertices.length];
        int[] verticeAnterior = new int[vertices.length];

        //Inicializar dados no porVisitar
        int i = 0;
        Aposento[] map = new Aposento[vertices.length];
        while (vertices[i] != null) {
            map[i] = (Aposento) vertices[i];
            porVisitar.add(String.valueOf(map[i].getIndex()));

            verticesTabela[i] = map[i].getIndex();
            distanciaMaisCurtaDoStartVertex[i] = Double.POSITIVE_INFINITY;
            verticeAnterior[i] = -1;
            i++;
        }

        //__________________ShortestPath______________________
        //Passo 1 --> Verificar peso entre o startVertex e ele próprio
        distanciaMaisCurtaDoStartVertex[start.getIndex()] = 0.0;

        //Passo 2 --> Enquanto porVisitar tiver conteudo
        while (!porVisitar.isEmpty()) {
            //Passo 3 --> Selecionar a distancia mais curta
            double menorValor = Double.POSITIVE_INFINITY;
            int indexMenorValor = 0;
            for (int j = 0; j < porVisitar.size(); j++) {
                if (distanciaMaisCurtaDoStartVertex[Integer.parseInt(porVisitar.get(j))] < menorValor) {
                    menorValor = distanciaMaisCurtaDoStartVertex[Integer.parseInt(porVisitar.get(j))];
                    indexMenorValor = Integer.parseInt(porVisitar.get(j));
                }
            }
            //Passo 4 --> Selecionar vizinhos do vertice com menor custo por visitar
            Aposento visitar = this.getVertexByIndex(indexMenorValor);
            double peso = 0.0;

            for (int f = 0; f < visitar.getLigacoes().length; f++) {
                if (!(visitar.getLigacoes()[f].equals("entrada")) && !(visitar.getLigacoes()[f].equals("exterior"))) {
                    Aposento aux = this.getVertex(String.valueOf(visitar.getLigacoes()[f]));

                    if (adjMatrix[indexMenorValor][aux.getIndex()] >= 0.0) {
                        peso = menorValor + adjMatrix[indexMenorValor][aux.getIndex()];
                    } else {
                        peso = menorValor;
                    }

                    //Atualizar a distancia e o vertice anterior se o custo for menor do que existe na tabela
                    if (peso < distanciaMaisCurtaDoStartVertex[aux.getIndex()]) {
                        distanciaMaisCurtaDoStartVertex[aux.getIndex()] = peso;
                        verticeAnterior[aux.getIndex()] = indexMenorValor;
                    }
                }

            }

            visitados.add(indexMenorValor);
            porVisitar.remove(String.valueOf(indexMenorValor));

            /*for (int j = 0; j < porVisitar.size(); j++) {
                System.out.println(porVisitar.get(j));
            }
            System.out.println("\n");*/
        }

        //Passo 5 --> Caminho com custo menor 
        /*System.out.print("Custo: " + distanciaMaisCurtaDoStartVertex[finish.getIndex()] + "-->");
        System.out.print(finish.getAposento());
        int caminho = verticeAnterior[finish.getIndex()];
        while(caminho != start.getIndex()){
            System.out.print("-->" + this.getVertexByIndex(caminho).getAposento());
            caminho = verticeAnterior[caminho];
        }
        System.out.print("-->" + this.getVertexByIndex(caminho).getAposento());

        System.out.println("");*/

 /*for (int k = 0; k < verticeAnterior.length; k++) {
            System.out.println(distanciaMaisCurtaDoStartVertex[k]);
        }*/
        this.setDistanciaMaisCurtaDoStartVertex(distanciaMaisCurtaDoStartVertex);
        this.setVerticeAnterior(verticeAnterior);
        this.setVerticesTabela(verticesTabela);
    }

    public double custoShortestPath(T startVertex, T targetVertex) {
        Aposento start = (Aposento) startVertex;

        Aposento finish = (Aposento) targetVertex;

        System.out.print("Custo: " + distanciaMaisCurtaDoStartVertex[finish.getIndex()] + "-->");
        System.out.print(finish.getAposento());
        int caminho = verticeAnterior[finish.getIndex()];
        while (caminho != start.getIndex()) {
            System.out.print("<--" + this.getVertexByIndex(caminho).getAposento());
            caminho = verticeAnterior[caminho];
        }
        System.out.print("<--" + this.getVertexByIndex(caminho).getAposento());

        System.out.println("");

        /*Iterator itr = this.adjVertex(startVertex);
        
        while(itr.hasNext()) {
         Object element = itr.next();
         System.out.print(element + " ");
      }*/
        return distanciaMaisCurtaDoStartVertex[finish.getIndex()];
    }

    /**
     *
     * @param startVertex
     * @param pontos
     */
    public void playGame(T startVertex, int pontos) {
        
        int shieldAposento = generateShield();

        //Declara variáveis
        Aposento start = (Aposento) startVertex;

        Scanner input = new Scanner(System.in);
        String opcao;

        //Passo 1 --> Imprimir vertice inicial
        int vida = pontos;
        ArrayList<String> aposentoJogar = new ArrayList<String>();

        while (vida > 0) {

            Iterator itr = this.adjVertex(startVertex);
            itr.next(); //Para não listar ele proprio
            System.out.println("\n\n========== Escolher uma divisão ==========\n");
            if (start.getLigacoes()[0].equals("exterior")) {
                aposentoJogar.add("exterior");
                System.out.println("exterior");
            }
            while (itr.hasNext()) {
                Object element = itr.next();
                Aposento aposento = (Aposento) element;
                System.out.println(aposento.getAposento() + " ");
                aposentoJogar.add(aposento.getAposento());
            }

            //Passo 2 --> escolher aposento para jogar
            System.out.print("\nOpção: ");
            opcao = input.nextLine();
            while (!aposentoJogar.contains(opcao)) {
                System.out.print("\nOpção: ");
                opcao = input.nextLine();

            }

            if (opcao.equals("exterior")) {
                System.out.println("\n\n==================== PARABÉNS! GANHOU! ====================");
                System.out.println("\nAcabou o jogo com " + vida + " pontos de vida!");
                Aposento resetShield = this.getVertexByIndex(shieldAposento);
                resetShield.setShield(0);
                break;
            }

            //Passo 3 --> Atualizar vida e verificar escudo
            
            
            startVertex = (T) this.getVertex(opcao);
            start = (Aposento) startVertex;
            
            
            vida = vida - start.getFantasma() + start.getShield();
            
            if (start.getShield()>0) {
                start.setShield(0);
            }
            
            System.out.println("\nVida Atual: " + vida + " pontos");

            if (vida <= 0) {
                System.out.println("\n\n========== OOPS! PERDEU! ==========");
            }
            aposentoJogar.removeAll(aposentoJogar);
        }
    }
    
    /**
     * 
     * @return 
     */
    public int generateShield(){
        
        int min= 999999;
        int max = -1;
       
        int i = 0;
        
        
        
        Aposento[] map = new Aposento[vertices.length];

        while (vertices[i] != null) {
            map[i] = (Aposento) vertices[i];
            
            if (map[i].getFantasma() > max) {
                max = map[i].getFantasma();
            }
            else if(map[i].getFantasma() < max){
                min = map[i].getFantasma();
            }
            
            i++;
        }
        
        int randomShield = ThreadLocalRandom.current().nextInt(min, max + 1);    
        int randomAposento = ThreadLocalRandom.current().nextInt(0, numVertices + 1);;
        
        
        Aposento shieldAposento = (Aposento) vertices[randomAposento];
        shieldAposento.setShield(randomShield);
        
        System.out.println("o shield que tem aposento: " + shieldAposento.getAposento());
        
        return randomAposento;
        
    }
}
