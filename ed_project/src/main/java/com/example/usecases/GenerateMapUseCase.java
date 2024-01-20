package com.example.usecases;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Pair;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.Randomness;
import com.example.utils.Scanners;

/**
 * A classe GenerateMapUseCase é responsável por correr todas as operações relacionadas com a criação do mapa do jogo.
 * Faz parte dos casos de uso no domínio da aplicação, tratando especificamente da criação do grafo associado ao mapa e das conexões entre as suas localizações.
 * 
 * O método principal execute(), conduz o processo de criação do mapa, permitindo ao utilizador definir o número de localizações, a bidirecionalidade dos caminhos,
 * e a densidade das arestas entre as localizações.
 * 
 * O método execute() interage com o utilizador para obter as configurações desejadas
 * Em seguida, gera o mapa conforme as especificações e associa-o ao jogo.
 * 
 *
 * @author Luís Costa [8200737]
 * @see com.example.domain.Game
 * @see com.example.domain.GameMap
 * @see com.example.domain.Pair
 * @see com.example.structures.exceptions.ElementNotFoundException
 * @see com.example.structures.implementation.list.UnorderedList
 * @see com.example.utils.Randomness
 * @see com.example.utils.Scanners
 * 
 */
public class GenerateMapUseCase {
	private Game game;

	/**
	 * Constrói uma nova instância de da classe GenerateMapUseCase.
	 *
	 * @param game O jogo ao qual o mapa deve ser associado.
	 */
	public GenerateMapUseCase(Game game) {
		this.game = game;
	}

	/**
	 * Executa o processo de criação do mapa para o jogo.
	 *
	 * @throws ElementNotFoundException Se um vértice não for encontrado no grafo
	 *                                  durante a execução.
	 */
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

			for (int i = 0; i < numberOfEdges; i++) {
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

	/**
	 * Verifica se um par de vértices já existe numa lista de pares de vertices
	 * fornecia.
	 *
	 * @param compare  O par a ser comparado.
	 * @param pairList A lista de pares a ser verificada.
	 * @return true se o par já existe na lista, false casocontrário.
	 * 
	 */
	private static boolean existPairVertex(Pair compare, UnorderedList<Pair> pairList) {
		for (Pair existPair : pairList) {
			if (existPair.compareTo(compare) == 1) {
				return true;
			}
		}

		return false;
	}
}
