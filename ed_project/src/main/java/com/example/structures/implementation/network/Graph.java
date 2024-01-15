package com.example.structures.implementation.network;

import java.util.Iterator;

import com.example.structures.adt.GraphADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.queue.LinkedQueue;
import com.example.structures.implementation.stack.LinkedStack;

public class Graph<T> implements GraphADT<T> {
    protected T[] vertex;
    protected boolean[][] adjMatrix; // adjacency matrix

    int numOfVertices;

    public Graph(int size) {
        this.vertex = (T[]) (new Object[size]);
        this.adjMatrix = new boolean[size][size];
        this.numOfVertices = 0;
    }

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

    @Override
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException {
        int indexVertex1 = findVertexIndex(vertex1);
        int indexVertex2 = findVertexIndex(vertex2);

        if (indexVertex1 >= 0 && indexVertex2 >= 0) {
            adjMatrix[indexVertex1][indexVertex2] = true;
            adjMatrix[indexVertex2][indexVertex1] = true;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException {
        int indexVertex1 = findVertexIndex(vertex1);
        int indexVertex2 = findVertexIndex(vertex2);

        if (indexVertex1 >= 0 && indexVertex2 >= 0) {
            adjMatrix[indexVertex1][indexVertex2] = false;
            adjMatrix[indexVertex2][indexVertex1] = false;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.numOfVertices <= 0;
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("Unimplemented method 'isConnected'");
    }

    @Override
    public int size() {
        return this.numOfVertices;
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iteratorBFS'");
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iteratorDFS'");
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyListException, ElementNotFoundException {
        int startVertexIndex = findVertexIndex(startVertex);
        int targetVertexIndex = findVertexIndex(targetVertex);

        return iteratorShortestPath(startVertexIndex, targetVertexIndex);
    }

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

    public void printAdjencyMatrix() {
        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean[][] downloadAdjencyMatrix() {
		return this.adjMatrix;
	}

    protected int findVertexIndex(T vertex) throws ElementNotFoundException {
        for (int i = 0; i < numOfVertices; i++) {
            if (this.vertex[i].equals(vertex)) {
                return i;
            }
        }

        throw new ElementNotFoundException(vertex.toString());
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