package com.example.domain;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.queue.LinkedQueue;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.RandomnessADT;

/**
 * Classe representativa de um bot no jogo, contendo informações sobre seu nome,
 * estratégia e localização atual.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.utils.Randomness
 * @see com.example.usecases.exceptions.EmptyMapException
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.queue.LinkedQueue
 * @see com.example.structures.implementation.list.UnorderedList
 * @see com.example.structures.exceptions.ElementNotFoundException
 * @see com.example.structures.implementation.network.exceptions.InvalidValueException
 * 
 */
public class Bot {
	private String botName;
	private Strategy strategy;
	private int currentLocation; // indice do vertice onde se encontra o bot

	/**
	 * Cria um novo bot com o nome fornecido.
	 *
	 * @param botName o nome do bot
	 */
	public Bot(String botName) {
		this.botName = botName;
	}

	/**
	 * Cria um novo bot com o nome, estratégia e posição fornecidos.
	 *
	 * @param botName     o nome do bot
	 * @param strategy    a estratégia do bot
	 * @param vertexIndex a posição inicial do bot
	 */
	public Bot(String botName, Strategy strategy, int vertexIndex) {
		this.botName = botName;
		this.strategy = strategy;
		this.currentLocation = vertexIndex;
	}

	/**
	 * Estratégias de movimentação disponíveis para os bots
	 */
	public enum Strategy {
		Shortest_Path, Random, Avoid_Obstacles
	}

	/**
	 * Obtém o nome do bot.
	 *
	 * @return o nome do bot
	 */
	public String getBotName() {
		return botName;
	}

	/**
	 * Define o nome do bot.
	 *
	 * @param botName o novo nome do bot
	 */
	public void setBotName(String botName) {
		this.botName = botName;
	}

	/**
	 * Obtém a estratégia do bot.
	 *
	 * @return a estratégia do bot
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * Define a estratégia do bot.
	 *
	 * @param strategy a nova estratégia do bot
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Obtém a posição atual do bot.
	 *
	 * @return o indice do vertice onde se encontra o bot atualmente
	 */
	public int getCurrentPosition() {
		return currentLocation;
	}

	/**
	 * Define a posição atual do bot.
	 *
	 * @param newPosition o novo indice do vertice para onde se moveu o bot
	 */
	public void setCurrentPosition(int newPosition) {
		this.currentLocation = newPosition;
	}

	/**
	 * Movimenta o bot pelo mapa, escolhendo a próxima posição de forma aleatória
	 * 
	 * @param gameMap   mapa do jogo pelo qual o bot irá circular
	 * @param randomLib libraria a utilizar para escolher de forma aletória o
	 *                  proximo vertice para onde o bot se deverá deslocar
	 * @throws EmptyListException se a lista estiver vazia
	 * 
	 */
	public void moveRandomly(GameMap gameMap, RandomnessADT randomLib) throws EmptyListException {
		UnorderedList<Integer> neighbors = gameMap.getNeighbors(this.getCurrentPosition());

		if (!neighbors.isEmpty()) {
			int neighborIndex;

			do {
				neighborIndex = neighbors.getElement(randomLib.getRandomNumber(0, neighbors.size()));
			} while (gameMap.isValidPositionWithEdge(this.getCurrentPosition(), neighborIndex));

			setCurrentPosition(neighborIndex);
		}
	}

	/**
	 * Movimenta o bot pelo mapa, adoptando o caminho mais curto até uma determinada
	 * posição
	 * 
	 * @param gameMap        mapa do jogo pelo qual o bot irá circular
	 * @param targetLocation index do vertice de destino
	 * @throws EmptyListException se a lista estiver vazia
	 * 
	 */
	public void moveByShortestPath(GameMap gameMap, int targetLocation) throws EmptyListException {
		int botCurrentLocation = this.getCurrentPosition();

		Iterator<Integer> shortestPathIterator = gameMap.iteratorShortestPath(botCurrentLocation, targetLocation);

		LinkedQueue<Integer> path = new LinkedQueue<>();

		while (shortestPathIterator.hasNext()) {
			path.enqueue(shortestPathIterator.next());
		}

		if (!path.isEmpty()) {
			this.setCurrentPosition(path.dequeue());
		}
	}

	/**
	 * Movimenta o bot pelo mapa, evitando obstáculos até uma determinada posição
	 * 
	 * @param game a instancia do jogo atual
	 * @throws EmptyListException       Se a lista estiver vazia
	 * @throws ElementNotFoundException Se o vertice a procurar não existir
	 * @throws EmptyMapException        Se o mapa ainda não tiver sido definido
	 * 
	 */
	public void moveAvoidingObstacles(Game game)
			throws EmptyListException, ElementNotFoundException, EmptyMapException {
		GameMap gameMap = game.getGameMap();

		int botCurrentLocation = this.getCurrentPosition();
		LinkedQueue<Integer> path = new LinkedQueue<>();
		boolean[] visited = new boolean[gameMap.getNumberOfLocations()];

		Iterator<Integer> bfsTravess = gameMap.iteratorBFS(botCurrentLocation);

		while (bfsTravess.hasNext()) {
			path.enqueue(bfsTravess.next());
		}

		if (!path.isEmpty()) {
			int nextVertex = path.dequeue();
			UnorderedList<Integer> neighborIndexes = gameMap.getNeighbors(nextVertex);

			for (int neighborIndex : neighborIndexes) {
				if (gameMap.isValidPosition(neighborIndex) && !visited[neighborIndex]
						&& !game.isPositionTaken(neighborIndex)) {
					this.setCurrentPosition(path.dequeue());
					visited[neighborIndex] = true;
				}
			}
		}
	}

	/**
	 * Converte as informações do Bot para um objeto JSON preparando-as para
	 * serem escritas no ficheiro JSON.
	 *
	 * @return um objeto JSON representando as informações do bot
	 */
	public JSONObject parseToJson() {
		JSONObject jsonBot = new JSONObject();
		JSONArray botLocation = new JSONArray();

		jsonBot.put("Name", this.botName);
		jsonBot.put("Strategy", this.strategy.toString());
		jsonBot.put("Location", this.currentLocation);

		return jsonBot;
	}

	/**
	 * Converte as informações do bot para uma string formatada.
	 *
	 * @return uma string formatada representativa do bot
	 */
	public String toString() {
		return "Bot {\n" +
				"Name: " + this.botName + "\n" +
				"Strategy: " + this.strategy + "\n" +
				"Current Location: " + this.currentLocation + "\n" +
				"}";
	}
}
