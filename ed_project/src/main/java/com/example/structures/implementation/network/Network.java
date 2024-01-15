package com.example.structures.implementation.network;

import java.util.Arrays;

import com.example.structures.adt.NetworkADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.network.exceptions.InvalidValueException;

public class Network<T> extends Graph<T> implements NetworkADT<T> {
	private double[][] adjacencyMatrix;

	public Network(int size) {
		super(size);
		this.adjacencyMatrix = new double[size][size];
	}

	@Override
	public void addVertex(T vertex1) {
		if (numOfVertices == adjacencyMatrix.length) {
			increaseCapacity();
		}
		super.addVertex(vertex1);
	}

	@Override
	public void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException {
		super.addEdge(vertex1, vertex2);

		if (weight < 0.0) {
			throw new InvalidValueException();
		}

		int indexVertex1 = findVertexIndex(vertex1);
		int indexVertex2 = findVertexIndex(vertex2);

		this.adjacencyMatrix[indexVertex1][indexVertex2] = weight;
	}

	@Override
	public UnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws ElementNotFoundException {
		int indexVertex1 = findVertexIndex(vertex1);
		int indexVertex2 = findVertexIndex(vertex2);

		if (indexVertex1 < 0 || indexVertex2 < 0) {
			throw new ElementNotFoundException();
		}

		double[] distances = new double[numOfVertices];
		int[] predecessors = new int[numOfVertices];
		boolean[] visited = new boolean[numOfVertices];

		// Inicializa distâncias e predecessores
		Arrays.fill(distances, Double.POSITIVE_INFINITY);
		Arrays.fill(predecessors, -1);

		distances[indexVertex1] = 0.0;

		for (int i = 0; i < numOfVertices; i++) {
			int currentVertex = getMinimumDistanceVertex(distances, visited);
			visited[currentVertex] = true;

			for (int neighbor : getNeighbors(currentVertex)) {
				if (!visited[neighbor]) {
					double alternativeDistance = distances[currentVertex] + adjacencyMatrix[currentVertex][neighbor];

					if (alternativeDistance < distances[neighbor]) {
						distances[neighbor] = alternativeDistance;
						predecessors[neighbor] = currentVertex;
					}
				}
			}
		}

		// Constrói o caminho mais curto
		UnorderedList<T> shortestPath = new UnorderedList<>();
		int current = indexVertex2;

		while (current != -1) {
			shortestPath.addToFront(vertex[(current)]);
			current = predecessors[current];
		}

		return shortestPath;
	}

	public void printAdjencyMatrixWithWeights() {
		String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		for (int i = 0; i < numOfVertices; i++) {
			if (i == 0) {
				System.out.print("\t");
			}
			System.out.print(alphabet[i] + "\t");
		}

		System.out.println();

		for (int i = 0; i < numOfVertices; i++) {
			System.out.print(alphabet[i] + "\t");
			for (int j = 0; j < numOfVertices; j++) {
				System.out.print(adjacencyMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public double[][] downloadAdjencyMatrixWithWeights() {
		return this.adjacencyMatrix;
	}

	private UnorderedList<Integer> getNeighbors(int currentVertex) {
		UnorderedList<Integer> neighbors = new UnorderedList<>();

		for (int i = 0; i < numOfVertices; i++) {
			if (adjMatrix[currentVertex][i]) {
				neighbors.addToRear(i);
			}
		}

		return neighbors;
	}

	private int getMinimumDistanceVertex(double[] distances, boolean[] visited) {
		double minDistance = Double.POSITIVE_INFINITY;
		int minVertex = -1;

		for (int vertex = 0; vertex < numOfVertices; vertex++) {
			if (!visited[vertex] && distances[vertex] < minDistance) {
				minDistance = distances[vertex];
				minVertex = vertex;
			}
		}

		return minVertex;
	}

	private void increaseCapacity() {
		double[][] newMatrix = new double[this.numOfVertices][this.numOfVertices];

		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				newMatrix[i][j] = this.adjacencyMatrix[i][j];
			}
		}

		this.adjacencyMatrix = newMatrix;
	}
}