package com.example.usecases;

import com.example.domain.GameMap;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.utils.Randomness;
import com.example.utils.Scanners;

public class GenerateMapUseCase {
	public static GameMap execute() throws ElementNotFoundException {
		int[] excludedNumbers;
		int numberOfLocations = Scanners.getInputInt("| Insira o nº de localizações que deseja para o Mapa : ");
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

		System.out.println();

		GameMap map = new GameMap(numberOfLocations, edgeDensity);

		for (int i = 0; i < numberOfLocations; i++) {
			map.addVertex(i);
		}

		for (int i = 0; i < numberOfLocations; i++) {
			int randomNeighborIndex = Randomness.getRandomNumber(0, numberOfLocations);
			excludedNumbers[i] = randomNeighborIndex;

			while (randomNeighborIndex == i) {
				randomNeighborIndex = Randomness.getRandomNumber(0, numberOfLocations);
				excludedNumbers[i] = randomNeighborIndex;
			}

			int randomDistanceBetweenNeighbors = Randomness.getRandomNumber(0, 15);

			map.addEdge(i, randomNeighborIndex, randomDistanceBetweenNeighbors);

			if (isBiDirectional.equalsIgnoreCase("y")) {
				map.addEdge(randomNeighborIndex, i, randomDistanceBetweenNeighbors);
			}
		}

		return map;
	}
}
