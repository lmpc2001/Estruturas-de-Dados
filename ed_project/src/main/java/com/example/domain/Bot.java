package com.example.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Classe representativa de um bot no jogo, contendo informações sobre seu nome,
 * estratégia e localização atual.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.usecases.exceptions.EmptyMapException
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.list.UnorderedList
 * 
 */
public class Bot {
	private String botName;
	private String strategy;
	private int[] currentLocation = new int[2];

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
	 * @param position a posição inicial do bot
	 */
	public Bot(String botName, String strategy, int[] position) {
		this.botName = botName;
		this.strategy = strategy;
		this.currentLocation = position;
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
	public String getStrategy() {
		return strategy;
	}

	/**
	 * Define a estratégia do bot.
	 *
	 * @param strategy a nova estratégia do bot
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * Obtém a posição atual do bot.
	 *
	 * @return um array representando a posição atual do bot [x, y]
	 */
	public int[] getCurrentPosition() {
		return currentLocation;
	}

	/**
	 * Define a posição atual do bot.
	 *
	 * @param newPosition o novo array representando a posição atual do bot [x, y]
	 */
	public void setCurrentPosition(int[] newPosition) {
		this.currentLocation = newPosition;
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
		jsonBot.put("Strategy", this.strategy);

		botLocation.add(this.currentLocation[0]);
		botLocation.add(this.currentLocation[1]);

		jsonBot.put("Location", botLocation);

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
