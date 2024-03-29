package com.example.usecases;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Pair;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.RandomnessADT;
import com.example.utils.ScannersADT;

/**
 * A classe GenerateMapUseCase é responsável por correr todas as operações
 * relacionadas com a criação do mapa do jogo.
 * Faz parte dos casos de uso no domínio da aplicação, tratando especificamente
 * da criação do grafo associado ao mapa e das conexões entre as suas
 * localizações.
 * 
 * O método principal execute(), conduz o processo de criação do mapa,
 * permitindo ao utilizador definir o número de localizações, a
 * bidirecionalidade dos caminhos,
 * e a densidade das arestas entre as localizações.
 * 
 * O método execute() interage com o utilizador para obter as configurações
 * desejadas
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
	private RandomnessADT randomLib;
	private ScannersADT scanner;

	/**
	 * Constrói uma nova instância de da classe GenerateMapUseCase.
	 *
	 * @param game      O jogo ao qual o mapa deve ser associado.
	 * @param randomLib Libraria a utilizar para gerar valores aleatórios
	 * @param scanner   Libraria a utilizar para interagir com o
	 *                  utilizador
	 */
	public GenerateMapUseCase(Game game, RandomnessADT randomLib, ScannersADT scanner) {
		this.game = game;
		this.randomLib = randomLib;
		this.scanner = scanner;
	}

	/**
	 * Executa o processo de criação do mapa para o jogo.
	 *
	 * @throws ElementNotFoundException Se um vértice não for encontrado no grafo
	 *                                  durante a execução.
	 */
	public void execute() throws ElementNotFoundException {
		int[] excludedNumbers;
		int numberOfLocations = scanner.getInputInt("| Insira o nº de localizações que deseja para o Mapa : ");
		excludedNumbers = new int[numberOfLocations];

		String isBiDirectional = scanner.getInputString("| Caminhos bidirecionais (Y/N) : ");

		do {
			if (!isBiDirectional.equalsIgnoreCase("Y") && !isBiDirectional.equalsIgnoreCase("N")) {
				System.out.println("Indique Y/N");
				isBiDirectional = scanner.getInputString("| Caminhos bidirecionais (Y/N) : ");

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
				int randomNeighborIndex = randomLib.getRandomWithoutDuplicates(0, numberOfLocations, excludedNumbers);
				excludedNumbers[i] = randomNeighborIndex;

				while (randomNeighborIndex == i) {
					randomNeighborIndex = randomLib.getRandomWithoutDuplicates(0, numberOfLocations, excludedNumbers);
					excludedNumbers[i] = randomNeighborIndex;
				}

				int randomDistanceBetweenNeighbors = randomLib.getRandomNumber(0, 16);

				map.addEdge(i, randomNeighborIndex, randomDistanceBetweenNeighbors);
				map.addEdge(randomNeighborIndex, i, randomDistanceBetweenNeighbors);
			}
		} else {
			UnorderedList<Pair> linkedVertex = new UnorderedList<Pair>();
			double edgeDensity = scanner.getInputDouble("| Insira a densidade entre as arestas [0 - 1] (eg. 0.5) : ");
			map.setEdgeDensity(edgeDensity);

			double numberOfEdges = (numberOfLocations * (numberOfLocations - 1)) * edgeDensity;

			for (int i = 0; i < numberOfEdges; i++) {
				int randomVertexIndex = randomLib.getRandomNumber(0, numberOfLocations);
				int randomNeighborIndex = randomLib.getRandomNumber(0, numberOfLocations);

				Pair<Integer> pair = new Pair<>(randomVertexIndex, randomNeighborIndex);

				while (existPairVertex(pair, linkedVertex) || randomNeighborIndex == randomVertexIndex) {
					randomNeighborIndex = randomLib.getRandomNumber(0, numberOfLocations);
					pair = new Pair<>(randomVertexIndex, randomNeighborIndex);
				}

				linkedVertex.addToRear(pair);

				int randomDistanceBetweenNeighbors = randomLib.getRandomNumber(1, 16);

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
