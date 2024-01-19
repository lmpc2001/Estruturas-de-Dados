package com.example.domain;

import com.example.structures.implementation.network.Network;

/**
 * Classe representativa do mapa de jogo baseado numa rede de localizações.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.structures.implementation.network.Network
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
	 * Exibe o mapa do jogo, apresentando a matriz de adjacência com os pesos
	 * das arestas.
	 */
	public void seeMap() {
		this.printAdjencyMatrixWithWeights();
	}
}
