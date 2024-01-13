package com.example.usecases;

import java.util.Random;

import com.example.domain.GameMap;
import com.example.structures.implementation.Vertex;
import com.example.utils.Randomness;
import com.example.utils.Scanners;

public class GenerateMapUseCase {
	static Vertex[] locations;
	static int[] excludedNumbers;

	public static GameMap execute() {

		int numberOfLocations = Scanners.getInputInt("| Insira o nº de localizações que deseja para o Mapa : ");
		locations = new Vertex[numberOfLocations];
		excludedNumbers = new int[numberOfLocations];

		System.out.println(locations.length);

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
			locations[i] = new Vertex("" + i, false);
		}

		for (int i = 0; i < locations.length; i++) {
			int randomNeighborIndex = Randomness.getRandomWithoutDuplicates(0, numberOfLocations, excludedNumbers);
			excludedNumbers[i] = randomNeighborIndex;

			int randomDistanceBetweenNeighbors = Randomness.getRandomNumber(0, 15);
			System.out.println(randomDistanceBetweenNeighbors);

			Vertex vertex = locations[i];
			System.out.println("Random Neighbor index: " + randomNeighborIndex);

			Vertex randomNeighborVertex = locations[randomNeighborIndex];
			vertex.addNeighbor(randomNeighborVertex, randomDistanceBetweenNeighbors);

			if (isBiDirectional.equalsIgnoreCase("y")) {
				randomNeighborVertex.addNeighbor(vertex, randomDistanceBetweenNeighbors);
			}
		}

		GameMap map = new GameMap(numberOfLocations, edgeDensity);
		map.setCoordinates(locations);

		return map;
	}
}
