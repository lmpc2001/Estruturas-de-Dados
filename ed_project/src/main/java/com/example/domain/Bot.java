package com.example.domain;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.network.exceptions.InvalidValueException;
import com.example.structures.implementation.queue.LinkedQueue;
import com.example.utils.Randomness;

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
	private int currentLocation;

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
	 * @param botName  o nome do bot
	 * @param strategy a estratégia do bot
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
		Shortest_Path, Random, Objective_Weighted
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
	 * @return um array representando a posição atual do bot [x, y]
	 */
	public int getCurrentPosition() {
		return currentLocation;
	}

	/**
	 * Define a posição atual do bot.
	 *
	 * @param newPosition o novo array representando a posição atual do bot [x, y]
	 */
	public void setCurrentPosition(int newPosition) {
		this.currentLocation = newPosition ;
	}

	/**
	 * Movimenta o bot pelo mapa, escolhendo a próxima posição de forma aleatória
	 * 
	 * @param gameMap mapa do jogo pelo qual o bot irá circular
	 * @throws EmptyListException se a lista estiver vazia
	 * 
	 */
	public void moveRandomly(GameMap gameMap) throws EmptyListException {
		UnorderedList<Integer> neighbors = gameMap.getNeighbors(this.getCurrentPosition());

		if (!neighbors.isEmpty()) {
			int neighborIndex = neighbors.getElement(Randomness.getRandomNumber(0, neighbors.size()));

			if (gameMap.isValidPositionWithEdge(this.getCurrentPosition(), neighborIndex)) {
				setCurrentPosition(neighborIndex);
			}
		}
	}

	/**
	 * Movimenta o bot pelo mapa, adoptando o caminho mais curto até uma determinada
	 * posição
	 * 
	 * @param gameMap mapa do jogo pelo qual o bot irá circular
	 * @throws EmptyListException se a lista estiver vazia
	 * @throws ElementNotFoundException 
	 * @throws InvalidValueException 
	 * 
	 */
	public void moveByShortestPath(GameMap gameMap, int targetLocation) throws EmptyListException, InvalidValueException, ElementNotFoundException {
		int currentLocation = this.getCurrentPosition();
		
		Iterator<Integer> shortestPathIterator = gameMap.iteratorShortestPath(currentLocation, targetLocation);

		LinkedQueue<Integer> path = new LinkedQueue<>();

		while (shortestPathIterator.hasNext()) {
			path.enqueue(shortestPathIterator.next());
		}

		while (!path.isEmpty()) {
			this.setCurrentPosition(path.dequeue());
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
