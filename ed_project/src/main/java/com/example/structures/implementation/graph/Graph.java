package com.example.structures.implementation.graph;

import java.util.Arrays;
import java.util.Iterator;

import com.example.configs.Properties;
import com.example.structures.adt.NetworkADT;
import com.example.structures.implementation.UnorderedList;

public class Graph<T> implements NetworkADT<T> {
	Vertex[] coordinates;
	int size;

	public Graph(int size) {
		this.coordinates = new Vertex[size];
		this.size = size;
	}

	public void setVertexes(Vertex[] newCoordinates) {
		this.coordinates = newCoordinates;
	}

	public Vertex[] getVertexes() {
		return this.coordinates;
	}

	public Vertex getVertex(int vertexIndex) {
		return this.coordinates[vertexIndex];
	}

	@Override
	public void addVertex(Vertex newVertex) {
		if (size == coordinates.length) {
			increaseCapacity();
		}
		this.coordinates[size] = newVertex;
	}

	@Override
	public void removeVertex(Vertex vertex) {
		for (int i = 0; i < coordinates.length; i++) {
			if (this.coordinates[i] == vertex) {
				this.coordinates[i] = null;
				size--;
			}
		}
	}

	@Override
	public void addEdge(Vertex vertex1, Vertex vertex2, double distancia) {
		vertex1.addNeighbor(vertex2, distancia);
		vertex2.addNeighbor(vertex1, distancia);
	}

	@Override
	public void removeEdge(Vertex vertex1, Vertex vertex2) {
		vertex1.removeNeighbor(vertex2);
		vertex2.removeNeighbor(vertex1);
	}

	@Override
	public boolean isEmpty() {
		return this.size <= 0;
	}

	@Override
	public boolean isConnected() {
		throw new UnsupportedOperationException("Unimplemented method 'isConnected'");
	}

	@Override
	public int size() {
		return this.size;
	}

	public void showGraphMatrix() {
		int n = coordinates.length;
		int[][] adjacencyMatrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjacencyMatrix[i][j] = 0; // Inicializamos com 0
			}
		}

		for (int i = 0; i < n; i++) {
			Vertex currentVertex = coordinates[i];
			Vertex[] neighbors = currentVertex.getNeighbors();
			double[] weights = currentVertex.getWeights();

			for (int j = 0; j < neighbors.length; j++) {
				Vertex neighbor = neighbors[j];
				double weight = weights[j];

				int row = Arrays.asList(coordinates).indexOf(currentVertex);
				int col = Arrays.asList(coordinates).indexOf(neighbor);
				adjacencyMatrix[row][col] = (int) weight; // Podes ajustar conforme necessário
			}
		}

		// Imprimir a matriz de adjacência
		System.out.println("Matriz de Adjacência:");

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[][] getGraphMatrix() {
		int n = coordinates.length;
		int[][] adjacencyMatrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjacencyMatrix[i][j] = 0; // Inicializamos com 0
			}
		}

		for (int i = 0; i < n; i++) {
			Vertex currentVertex = coordinates[i];
			Vertex[] neighbors = currentVertex.getNeighbors();
			double[] weights = currentVertex.getWeights();

			for (int j = 0; j < neighbors.length; j++) {
				Vertex neighbor = neighbors[j];
				double weight = weights[j];

				int row = Arrays.asList(coordinates).indexOf(currentVertex);
				int col = Arrays.asList(coordinates).indexOf(neighbor);
				adjacencyMatrix[row][col] = (int) weight; // Podes ajustar conforme necessário
			}
		}

		return adjacencyMatrix;
	}

	@Override
	public UnorderedList<T> shortestPathWeight(Vertex vertex1, Vertex vertex2) {
		// Inicializar distâncias e predecessor
		double[] distances = new double[size];
		int[] predecessors = new int[size];
		boolean[] visited = new boolean[size];

		// Inicializar distâncias como infinito e predecessores como -1
		Arrays.fill(distances, Double.POSITIVE_INFINITY);
		Arrays.fill(predecessors, -1);

		// Índice do vértice de partida
		int startIndex = Arrays.asList(coordinates).indexOf(vertex1);

		// A distância até o próprio vértice é 0
		distances[startIndex] = 0;

		// Executar o algoritmo de Dijkstra
		for (int i = 0; i < size - 1; i++) {
			int u = getMinDistanceVertex(distances, visited);

			visited[u] = true;

			Vertex currentVertex = coordinates[u];
			Vertex[] neighbors = currentVertex.getNeighbors();
			double[] weights = currentVertex.getWeights();

			for (int j = 0; j < neighbors.length; j++) {
				Vertex neighbor = neighbors[j];
				int v = Arrays.asList(coordinates).indexOf(neighbor);

				if (!visited[v] && distances[u] + weights[j] < distances[v]) {
					distances[v] = distances[u] + weights[j];
					predecessors[v] = u;
				}
			}
		}

		// Construir o caminho mais curto a partir dos predecessores
		UnorderedList<T> shortestPath = new UnorderedList<>();
		int endIndex = Arrays.asList(coordinates).indexOf(vertex2);
		buildPath(predecessors, endIndex, shortestPath);

		return shortestPath;
	}

	@Override
	public Iterator<T> iteratorShortestPath(Vertex startVertex, Vertex targetVertex) {
		return shortestPathWeight(startVertex, targetVertex).iterator();
	}

	@Override
	public Iterator iteratorBFS(Vertex startVertex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iteratorBFS'");
	}

	@Override
	public Iterator iteratorDFS(Vertex startVertex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iteratorDFS'");
	}

	private void increaseCapacity() {
		int newCapacity = coordinates.length * 2;
		coordinates = copyArray(coordinates, newCapacity);
	}

	private Vertex[] copyArray(Vertex[] array, int newCapacity) {
		Vertex[] newArray = new Vertex[newCapacity];
		System.arraycopy(array, 0, newArray, 0, size);
		return newArray;
	}

	// Auxiliar para obter o índice do vértice com a distância mínima
	private int getMinDistanceVertex(double[] distances, boolean[] visited) {
		double minDistance = Properties.MAX_DISTANCE_BETWEEN_VERTEXES + 1;
		int minIndex = -1;

		for (int i = 0; i < size; i++) {
			if (!visited[i] && distances[i] < minDistance) {
				minDistance = distances[i];
				minIndex = i;
			}
		}

		return minIndex;
	}

	// Auxiliar para construir o caminho mais curto a partir dos predecessores
	private void buildPath(int[] predecessors, int endIndex, UnorderedList<T> shortestPath) {
		if (endIndex != -1) {
			buildPath(predecessors, predecessors[endIndex], shortestPath);
			shortestPath.addToRear((T) coordinates[endIndex].getElement());
		}
	}
}
