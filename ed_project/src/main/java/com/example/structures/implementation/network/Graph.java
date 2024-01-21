package com.example.structures.implementation.network;

import java.util.Iterator;

import com.example.structures.adt.GraphADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.queue.LinkedQueue;
import com.example.structures.implementation.stack.LinkedStack;

/**
 * A classe Graph<T> implementa a interface GraphADT<T> e representa um
 * grafo através de uma matriz de adjacência.
 * 
 * Este grafo pode armazenar elementos de tipo genérico T e fornece operações
 * típicas de um grafo, tais como: adicionar e remover vértices, adicionar e
 * remover arestas, obter o tamanho do grafo, criar iteradores para travessias
 * BFS, DFS e encontrar o caminho mais curto entre dois vértices.
 *
 * @author Luís Costa [8200737]
 * @param <T> O tipo de dados a ser armazenado nos vértices do grafo.
 * @see com.example.structures.adt.GraphADT
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.stack.LinkedStack
 * @see com.example.structures.implementation.queue.LinkedQueue
 * @see com.example.structures.implementation.list.UnorderedList
 * @see com.example.structures.exceptions.ElementNotFoundException
 * 
 */
public class Graph<T> implements GraphADT<T> {
    protected T[] vertex;
    protected boolean[][] adjMatrix; // adjacency matrix

    int numOfVertices;

    /**
     * Cria uma nova instância de um grafo<T> inicializando a sua lista de vértices
     * com o tamanho enviado pelo utilizador.
     * 
     * @param size tamanho inicial do grafo
     */
    public Graph(int size) {
        this.vertex = (T[]) (new Object[size]);
        this.adjMatrix = new boolean[size][size];
        this.numOfVertices = 0;
    }

    /**
     * Adiciona um novo vértice ao grafo.
     *
     * @param newVertex O vértice a ser adicionado ao grafo.
     */
    @Override
    public void addVertex(T newVertex) {
        if (numOfVertices == vertex.length) {
            increaseCapacity();
        }

        this.vertex[numOfVertices] = newVertex;

        for (int i = 0; i < adjMatrix.length; i++) {
            adjMatrix[numOfVertices][i] = false;
            adjMatrix[i][numOfVertices] = false;
        }
        numOfVertices++;
    }

    /**
     * Remove um vértice específico do grafo, bem como todas as arestas conectadas
     * ao mesmo.
     *
     * @param removeVertex O vértice a ser removido do grafo.
     * @throws ElementNotFoundException Se o vértice a ser removido não for
     *                                  encontrado no grafo.
     */
    @Override
    public void removeVertex(T removeVertex) throws ElementNotFoundException {
        int removeVertexIndex = findVertexIndex(removeVertex);

        if (removeVertexIndex > -1) {
            numOfVertices--;

            for (int i = 0; i < numOfVertices; i++) {
                vertex[i] = vertex[i + 1];
            }

            for (int i = removeVertexIndex; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    this.adjMatrix[i][j] = this.adjMatrix[i + 1][j];
                }
            }

            for (int i = removeVertexIndex; i < numOfVertices; i++) {
                for (int j = 0; j < numOfVertices; j++) {
                    this.adjMatrix[i][j] = this.adjMatrix[i][j + 1];
                }
            }
        }
    }

    /**
     * Adiciona uma aresta entre dois vértices no grafo.
     *
     * @param vertex1 O vertice de origem.
     * @param vertex2 O vertice de chegada.
     * @throws ElementNotFoundException Se um dos vértices não for encontrado no
     *                                  grafo.
     */
    @Override
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException {
        int indexVertex1 = findVertexIndex(vertex1);
        int indexVertex2 = findVertexIndex(vertex2);

        if (indexVertex1 >= 0 && indexVertex2 >= 0) {
            adjMatrix[indexVertex1][indexVertex2] = true;
            // adjMatrix[indexVertex2][indexVertex1] = true;
        }
    }

    /**
     * Remove a aresta entre dois vértices no grafo.
     *
     * @param vertex1 O vertice de origem.
     * @param vertex2 O vertice de chegada.
     * @throws ElementNotFoundException Se um dos vértices não for encontrado no
     *                                  grafo.
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException {
        int indexVertex1 = findVertexIndex(vertex1);
        int indexVertex2 = findVertexIndex(vertex2);

        if (indexVertex1 >= 0 && indexVertex2 >= 0) {
            adjMatrix[indexVertex1][indexVertex2] = false;
            adjMatrix[indexVertex2][indexVertex1] = false;
        }
    }

    /**
     * Verifica se o grafo está vazio.
     *
     * @return true se o grafo estiver vazio, false caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return this.numOfVertices <= 0;
    }

    /**
     * Verifica se o grafo é um grafo conectado.
     *
     * @return true se o grafo for conectado, false caso contrário.
     * @throws UnsupportedOperationException Este método ainda não foi implementado.
     */
    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Unimplemented method 'isConnected'");
    }

    /**
     * Retorna o número de vértices no grafo.
     *
     * @return O número de vértices no grafo.
     */
    @Override
    public int size() {
        return this.numOfVertices;
    }

    /**
     * Retorna um iterador para a travessia do grafo em largura (BFS) a partir de um
     * vértice inicial.
     *
     * @param startVertex O vértice de partida para a travessia.
     * @return Um iterador para a travessia BFS.
     * @throws ElementNotFoundException      se o elemento a procurar não for
     *                                       encontrado
     * @throws EmptyListException            Se a fila estiver vazia
     * @throws UnsupportedOperationException Este método ainda não foi implementado.
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) throws ElementNotFoundException, EmptyListException {
        int startVertexIndex = findVertexIndex(startVertex);
        boolean[] visited = new boolean[numOfVertices];

        LinkedQueue<Integer> path = new LinkedQueue<>();
        UnorderedList<T> result = new UnorderedList<>();

        visited[startVertexIndex] = true;
        path.enqueue(startVertexIndex);

        while (!path.isEmpty()) {
            int currentVertexIndex = path.dequeue();
            result.addToRear(vertex[currentVertexIndex]);

            for (int i = 0; i < numOfVertices; i++) {
                if (adjMatrix[currentVertexIndex][i] && !visited[i]) {
                    visited[i] = true;
                    path.enqueue(i);
                }
            }
        }

        return result.iterator();
    }

    /**
     * Retorna um iterador para a travessia do grafo em profundidade (DFS) a partir
     * de um vértice inicial.
     *
     * @param startVertex O vértice de partida para a travessia.
     * @return Um iterador para a travessia DFS.
     * @throws EmptyListException            Se a lista estiver vazia
     * @throws UnsupportedOperationException Este método ainda não foi implementado.
     * @throws ElementNotFoundException      Se o elemento a procurar não existir na
     *                                       lista em questão
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) throws ElementNotFoundException, EmptyListException {
        int startVertexIndex = findVertexIndex(startVertex);
        boolean[] visited = new boolean[numOfVertices];

        LinkedStack<Integer> path = new LinkedStack<>();
        UnorderedList<T> result = new UnorderedList<>();

        path.push(startVertexIndex);

        while (!path.isEmpty()) {
            int currentVertexIndex = path.pop();
            if (!visited[currentVertexIndex]) {
                result.addToRear(vertex[currentVertexIndex]);
                visited[currentVertexIndex] = true;

                for (int i = numOfVertices - 1; i >= 0; i--) {
                    if (adjMatrix[currentVertexIndex][i] && !visited[i]) {
                        path.push(i);
                    }
                }
            }
        }

        return result.iterator();
    }

    /**
     * Retorna um iterador para o caminho mais curto entre dois vértices no grafo.
     *
     * @param startVertex  O vértice de partida.
     * @param targetVertex O vértice de destino.
     * @return Um iterador para o caminho mais curto entre os vértices.
     * @throws EmptyListException       Se a lista estiver vazia.
     * @throws ElementNotFoundException Se um dos vértices não for encontrado no
     *                                  grafo.
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
            throws EmptyListException, ElementNotFoundException {
        int startVertexIndex = findVertexIndex(startVertex);
        int targetVertexIndex = findVertexIndex(targetVertex);

        return iteratorShortestPath(startVertexIndex, targetVertexIndex);
    }

    /**
     * Retorna um iterador para o caminho mais curto entre dois vértices no grafo.
     *
     * @param startVertexIndex  O index do vértice de partida.
     * @param targetVertexIndex O index do vértice de destino.
     * @return Um iterador para o caminho mais curto entre os vértices.
     * @throws EmptyListException Se a lista estiver vazia.
     * 
     */
    public Iterator<T> iteratorShortestPath(int startVertexIndex, int targetVertexIndex) throws EmptyListException {
        UnorderedList<T> resultPath = new UnorderedList<>();
        if (startVertexIndex < 0 || targetVertexIndex < 0) {
            return resultPath.iterator();
        }

        Iterator<Integer> i;

        i = iteratorShortestPathIndices(startVertexIndex, targetVertexIndex);
        while (i.hasNext()) {
            resultPath.addToRear(vertex[i.next()]);
        }

        return resultPath.iterator();
    }

    /**
     * Retorna um iterador referente aos indices dos vértices que constituem o
     * caminho mais curto entre dois vértices no grafo.
     *
     * @param startIndex  O index do vértice de partida.
     * @param targetIndex O index do vértice de destino.
     * @return Um iterador para o caminho mais curto entre os vértices.
     * @throws EmptyListException Se a lista estiver vazia.
     * 
     */
    private Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyListException {
        int currentVertex = startIndex;
        UnorderedList<Integer> shortestPath = new UnorderedList<>();

        boolean[] visited = new boolean[numOfVertices];
        int[] predecessors = new int[numOfVertices];

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!queue.isEmpty()) {
            currentVertex = queue.dequeue();

            for (int i = 0; i < numOfVertices; i++) {
                if (adjMatrix[currentVertex][i] && !visited[i]) {
                    queue.enqueue(i);
                    visited[i] = true;
                    predecessors[i] = currentVertex;
                }
            }
        }

        LinkedStack<Integer> tmpStack = new LinkedStack<>();
        tmpStack.push(currentVertex);

        do {
            currentVertex = predecessors[currentVertex];
            tmpStack.push(currentVertex);
        } while (currentVertex != startIndex);

        while (!tmpStack.isEmpty()) {
            shortestPath.addToRear(tmpStack.pop());
        }

        return shortestPath.iterator();

    }

    /**
     * Imprime a matriz de adjacência do grafo na consola.
     */
    public void printAdjencyMatrix() {
        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                System.out.print((adjMatrix[i][j] ? "1" : "0") + " ");
            }
            System.out.println();
        }
    }

    /**
     * Retorna a matriz de adjacência do grafo.
     *
     * @return A matriz de adjacência do grafo.
     */
    public boolean[][] downloadAdjencyMatrix() {
        return this.adjMatrix;
    }

    /**
     * Retorna a lista de vértices do grafo.
     *
     * @return A lista de vértices do grafo.
     */
    public T[] getVertex() {
        return this.vertex;
    }

    /**
     * Encontra o índice de um vértice no array
     * 
     * @param vertex o vertice a procurar no grafo
     * @return index do vértice caso exista
     * @throws ElementNotFoundException Se o elemento não existir no grafo
     */
    protected int findVertexIndex(T vertex) throws ElementNotFoundException {
        for (int i = 0; i < numOfVertices; i++) {
            if (this.vertex[i].equals(vertex)) {
                return i;
            }
        }

        throw new ElementNotFoundException(vertex.toString());
    }

    /**
     * Verifica se existe um caminho que vá do vértice 1 ao vértice 2
     * 
     * @param vertex1 vertice de partida
     * @param vertex2 vertice de destino
     * @return true se existir um caminho entre os dois vértices, false caso
     *         contrário
     * @throws ElementNotFoundException Se algum dos vertices não existir no grafo
     */
    public boolean hasEdge(T vertex1, T vertex2) throws ElementNotFoundException {
        int vertex1Index = findVertexIndex(vertex1);
        int vertex2Index = findVertexIndex(vertex2);

        return adjMatrix[vertex1Index][vertex2Index];
    }

    /**
     * Obtém o número de caminhos existentes no grafo
     * 
     * @return número de caminhos no grafo
     */
    public int numberOfEdges() {
        int numOfEdges = 0;
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j]) {
                    numOfEdges++;
                }
            }
        }

        return numOfEdges;
    }

    private void increaseCapacity() {
        int vertexNewCapacity = vertex.length * 2;

        T[] newArray = (T[]) (new Object[vertexNewCapacity]);
        boolean[][] newMatrix = new boolean[vertexNewCapacity][vertexNewCapacity];

        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                newMatrix[i][j] = this.adjMatrix[i][j];
            }
            newArray[i] = this.vertex[i];
        }

        vertex = newArray;
        adjMatrix = newMatrix;
    }
}