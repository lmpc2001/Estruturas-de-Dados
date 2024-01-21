package com.example.domain;

import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.network.Network;
import com.example.structures.implementation.queue.LinkedQueue;

/**
 * Classe representativa do mapa de jogo baseado numa rede de localizações.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.network.Network
 * @see com.example.structures.implementation.queue.LinkedQueue
 * @see com.example.structures.implementation.list.UnorderedList
 * 
 */
public class GameMap extends Network {
	private double edgeDensity;

	/**
	 * Cria uma instancia de um mapa de jogo com o número especificado de
	 * localizações e
	 * densidade de aresta.
	 *
	 * @param numberOfLocations o número de localizações no mapa
	 * @param edgeDensity       a densidade de aresta do mapa
	 */
	public GameMap(int numberOfLocations, double edgeDensity) {
		super(numberOfLocations);
		this.edgeDensity = edgeDensity;
	}

	/**
	 * Obtém o número de localizações no mapa.
	 *
	 * @return o número de localizações
	 */
	public int getNumberOfLocations() {
		return this.size();
	}

	/**
	 * Obtém a densidade das aresta do mapa.
	 *
	 * @return a densidade de aresta
	 */
	public double getEdgeDensity() {
		return this.edgeDensity;
	}

	/**
	 * Define uma nova densidade de aresta para o mapa.
	 *
	 * @param newEdgeDensity a nova densidade de aresta
	 */
	public void setEdgeDensity(double newEdgeDensity) {
		this.edgeDensity = newEdgeDensity;
	}

	/**
	 * Verifica se um vertice existe validando o indice recebido.
	 *
	 * @param vertexIndex o indice do vertice a validar
	 * @return true se o indice do vertice for válido ou flase caso contrário
	 */
	public boolean isValidPosition(int vertexIndex) {
		return vertexIndex >= 0 && vertexIndex < getNumberOfLocations();
	}

	/**
	 * Verifica se os indices dos vertices são válidos e se existe uma aresta que os
	 * ligue
	 *
	 * @param startVertexIndex  o indice do vertice inicial a validar
	 * @param targetVertexIndex o indice do vertice de destino a validar
	 * @return true se os indices dos vertices forem válidos e existir um caminho
	 *         entre eles ou false caso contrário
	 */
	public boolean isValidPositionWithEdge(int startVertexIndex, int targetVertexIndex) {
		return targetVertexIndex >= 0 && targetVertexIndex < getNumberOfLocations()
				&& adjMatrix[startVertexIndex][targetVertexIndex]; // Valida se a posição é válida e se existe um
																	// caminho entre os dois pontos
	}

	/**
	 * Exibe o mapa do jogo, apresentando a matriz de adjacência com os pesos
	 * das arestas.
	 */
	public void seeMap() {
		this.printAdjencyMatrixWithWeights();
	}

	/**
	 * Exibe o cconjunto de vértices/locais presentes no mapa
	 */
	public void seeVertex() {
		System.out.print("\n|Posições: ");
		for (int i = 0; i < this.getVertex().length; i++) {
			System.out.print(i + " ");
		}

		System.out.println();
	}
}
