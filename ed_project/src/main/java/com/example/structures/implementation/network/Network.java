package com.example.structures.implementation.network;


import java.util.Arrays;

import com.example.structures.adt.NetworkADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.network.exceptions.InvalidValueException;

/**
 * A classe Network<T> implementa a interface NetworkADT<T> e faz extend à
 * classe Graph<T> de modo a representar um grafo ponderado com vértices do tipo
 * T.
 * 
 * Este grafo pode armazenar elementos de tipo genérico T e fornece todas as
 * operações típicas de um grafo, moldando apenas métodos especificos para
 * suprimir as necessidades de um grafo ponderado, como o caso do método addEdge
 * que se torna capaz de receber o peso, ou distancia, entre dois vértices
 *
 * @author Luís Costa [8200737]
 * @param <T> o tipo de elementos armazenados na rede
 * @see com.example.structures.adt.NetworkADT
 * @see com.example.structures.implementation.list.UnorderedList
 * @see com.example.structures.exceptions.ElementNotFoundException
 * @see com.example.structures.implementation.network.exceptions.InvalidValueException
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {
	private double[][] adjacencyMatrix;

	/**
	 * Cria uma intencia da classe Network com uma lista vertices de tamanho igual
	 * ao tamanho fornecido.
	 *
	 * @param size o tamanho inicial da rede
	 */
	public Network(int size) {
		super(size);
		this.adjacencyMatrix = new double[size][size];
	}

	/**
	 * Adiciona um vértice ao grafo. Se a capacidade atual for atingida, aumenta a
	 * sua
	 * capacidade.
	 *
	 * @param vertex1 o vértice a ser adicionado
	 */
	@Override
	public void addVertex(T vertex1) {
		if (numOfVertices == adjacencyMatrix.length) {
			increaseCapacity();
		}
		super.addVertex(vertex1);
	}

	/**
	 * Adiciona uma aresta ponderada entre dois vértices no grafo.
	 *
	 * @param vertex1 O vértice de origem
	 * @param vertex2 O vértice de destino
	 * @param weight  O peso/distância entre os dois vértices
	 * @throws ElementNotFoundException Se algum dos vértices não for encontrado na
	 *                                  grafo
	 * @throws InvalidValueException    Se o peso for negativo
	 */
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

	/**
	 * Encontra o caminho mais curto entre dois vértices tendo em consideração o
	 * peso das arestas no grafo.
	 *
	 * @param vertex1 O vértice da origem
	 * @param vertex2 O vértice de destino
	 * @return Uma lista não ordenada representando o caminho mais curto
	 * @throws ElementNotFoundException Se algum dos vértices não for encontrado no
	 *                                  grafo
	 */
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

	/**
	 * Imprime a matriz de adjacência com pesos no console.
	 */
	public void printAdjencyMatrixWithWeights() {
		String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		for (int i = 0; i < numOfVertices; i++) {
			if (i == 0) {
				System.out.print("\n\t");
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

	/**
	 * Retorna a matriz de adjacência com pesos.
	 *
	 * @return a matriz de adjacência com pesos
	 */
	public double[][] downloadAdjencyMatrixWithWeights() {
		return this.adjacencyMatrix;
	}

	/**
	 * Encontra os vizinhos de um determinado vértice.
	 *
	 * @param currentVertex O vértice para encontrar os vizinhos
	 * @return Uma lista não ordenada com os índices dos vértices vizinhos
	 */
	public UnorderedList<Integer> getNeighbors(int currentVertex) {
		UnorderedList<Integer> neighbors = new UnorderedList<>();

		for (int i = 0; i < numOfVertices; i++) {
			if (adjMatrix[currentVertex][i]) {
				neighbors.addToRear(i);
			}
		}

		return neighbors;
	}

	/**
	 * Encontra o vértice com distância mínima que ainda não tenha sido visitado.
	 *
	 * @param distances O array de distâncias
	 * @param visited   O array indicando vértices visitados
	 * @return O índice do vértice com a distância mínima
	 */
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

	/**
	 * Aumenta a capacidade da matriz de adjacência.
	 */
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