package com.example.usecases;

import com.example.domain.GameMap;
import com.example.domain.Local;
import com.example.structures.implementation.graph.Vertex;
import com.example.utils.Randomness;
import com.example.utils.Scanners;

public class GenerateMapUseCase {
	static Vertex<Local>[] locationsVertex;
	static int[] excludedNumbers;

	public static GameMap execute() {

		int numberOfLocations = Scanners.getInputInt("| Insira o nº de localizações que deseja para o Mapa : ");
		locationsVertex = new Vertex[numberOfLocations];
		excludedNumbers = new int[numberOfLocations];

		String isBiDirectional = Scanners.getInputString("| Caminhos bidirecionais (Y/N) : ");

		do {
			if (!isBiDirectional.equalsIgnoreCase("Y") && !isBiDirectional.equalsIgnoreCase("N")) {
				System.out.println("Indique Y/N");
				isBiDirectional = Scanners.getInputString("| Caminhos bidirecionais (Y/N) : ");

			} else {
				break;
			}
		} while (true);

		double edgeDensity = Scanners.getInputDouble("| Insira a densidade entre as arestas (eg. 0.5) : ");

		for (int i = 0; i < numberOfLocations; i++) {
			locationsVertex[i] = new Vertex();
		}

		for (int i = 0; i < locationsVertex.length; i++) {
			int randomNeighborIndex = Randomness.getRandomNumber(0, numberOfLocations);
			excludedNumbers[i] = randomNeighborIndex;

			while (randomNeighborIndex == i) {
				randomNeighborIndex = Randomness.getRandomNumber(0, numberOfLocations);
				excludedNumbers[i] = randomNeighborIndex;
			}

			int randomDistanceBetweenNeighbors = Randomness.getRandomNumber(0, 15);

			Vertex vertex = locationsVertex[i];

			Vertex randomNeighborVertex = locationsVertex[randomNeighborIndex];
			vertex.addNeighbor(randomNeighborVertex, randomDistanceBetweenNeighbors);

			if (isBiDirectional.equalsIgnoreCase("y")) {
				randomNeighborVertex.addNeighbor(vertex, randomDistanceBetweenNeighbors);
			}
		}

		GameMap map = new GameMap(numberOfLocations, edgeDensity);
		map.setVertexes(locationsVertex);

		return map;
	}
}
