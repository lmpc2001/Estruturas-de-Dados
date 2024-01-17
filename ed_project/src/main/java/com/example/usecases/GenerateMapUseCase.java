package com.example.usecases;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Pair;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.Randomness;
import com.example.utils.Scanners;

public class GenerateMapUseCase {
	private Game game;

	public GenerateMapUseCase(Game game) {
		this.game = game;
	}

	public void execute() throws ElementNotFoundException {
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

		System.out.println();

		GameMap map = new GameMap(numberOfLocations, 0);

		for (int i = 0; i < numberOfLocations; i++) {
			map.addVertex(i);
		}

		if (isBiDirectional.equalsIgnoreCase("y")) {
			for (int i = 0; i < numberOfLocations; i++) {
				int randomNeighborIndex = Randomness.getRandomWithoutDuplicates(0, numberOfLocations, excludedNumbers);
				excludedNumbers[i] = randomNeighborIndex;

				while (randomNeighborIndex == i) {
					randomNeighborIndex = Randomness.getRandomWithoutDuplicates(0, numberOfLocations, excludedNumbers);
					excludedNumbers[i] = randomNeighborIndex;
				}

				int randomDistanceBetweenNeighbors = Randomness.getRandomNumber(0, 16);

				System.out.println(i + " -> " + randomNeighborIndex + ": " + randomDistanceBetweenNeighbors);

				map.addEdge(i, randomNeighborIndex, randomDistanceBetweenNeighbors);
				map.addEdge(randomNeighborIndex, i, randomDistanceBetweenNeighbors);
			}
		} else {
			UnorderedList<Pair> linkedVertex = new UnorderedList<Pair>();
			double edgeDensity = Scanners.getInputDouble("| Insira a densidade entre as arestas [0 - 1] (eg. 0.5) : ");
			map.setEdgeDensity(edgeDensity);

			double numberOfEdges = (numberOfLocations * (numberOfLocations - 1)) * edgeDensity;

			System.out.println(numberOfEdges);

			for (int i = 0; i < numberOfEdges - 1; i++) {
				int randomVertexIndex = Randomness.getRandomNumber(0, numberOfLocations);
				int randomNeighborIndex = Randomness.getRandomNumber(0, numberOfLocations);

				Pair<Integer> pair = new Pair<>(randomVertexIndex, randomNeighborIndex);

				while (existPairVertex(pair, linkedVertex) || randomNeighborIndex == randomVertexIndex) {
					randomNeighborIndex = Randomness.getRandomNumber(0, numberOfLocations);
					pair = new Pair<>(randomVertexIndex, randomNeighborIndex);
				}

				linkedVertex.addToRear(pair);

				int randomDistanceBetweenNeighbors = Randomness.getRandomNumber(1, 16);

				map.addEdge(randomVertexIndex, randomNeighborIndex, randomDistanceBetweenNeighbors);
			}

		}

		game.setGameMap(map);
	}

	private static boolean existPairVertex(Pair compare, UnorderedList<Pair> pairList) {
		for (Pair existPair : pairList) {
			if (existPair.compareTo(compare) == 1) {
				return true;
			}
		}

		return false;
	}
}
