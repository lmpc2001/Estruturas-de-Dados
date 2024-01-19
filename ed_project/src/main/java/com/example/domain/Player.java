package com.example.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.queue.LinkedQueue;

/**
 * Classe que representa um jogador no jogo.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.structures.exceptions.EmptyListException;
 * @see com.example.structures.implementation.queue.LinkedQueue;
 * 
 */
public class Player {
	private String playerName;
	private LinkedQueue<Bot> playerBots;
	private int[] flag = new int[2];

	/**
	 * Cria um novo jogador com o nome fornecido.
	 *
	 * @param playerName o nome do jogador
	 */
	public Player(String playerName) {
		this.playerName = playerName;
		this.playerBots = new LinkedQueue<>();
	}

	/**
	 * Obtém o nome do jogador.
	 *
	 * @return o nome do jogador
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Define o nome do jogador.
	 *
	 * @param playerName o novo nome do jogador
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Obtém a fila de bots pertencentes ao jogador.
	 *
	 * @return a fila de bots
	 */
	public LinkedQueue<Bot> getPlayerBots() {
		return playerBots;
	}

	/**
	 * Adiciona um bot à fila de bots do jogador.
	 *
	 * @param playerBots o bot a ser adicionado
	 */
	public void addBot(Bot playerBots) {
		this.playerBots.enqueue(playerBots);
	}

	/**
	 * Obtém a posição atual da bandeira do jogador.
	 *
	 * @return uma array contendo a posição da bandeira [x, y]
	 */
	public int[] getFlag() {
		return this.flag;
	}

	/**
	 * Define a posição da bandeira do jogador.
	 *
	 * @param flagPosition um array contendo a posição da bandeira [x, y]
	 */
	public void setFlag(int[] flagPosition) {
		this.flag = flagPosition;
	}

	/**
	 * Obtém o próximo bot para jogar.
	 *
	 * @return o bot a ser jogado
	 * @throws EmptyListException se a fila de bots do jogador estiver vazia
	 */
	public Bot getBotToPlay() throws EmptyListException {
		Bot botToMove = this.playerBots.dequeue();
		this.playerBots.enqueue(botToMove);

		return botToMove;
	}

	/**
	 * Converte as informações do jogador para um objeto JSON preparando-as para
	 * serem escritas no ficheiro JSON.
	 *
	 * @return um objeto JSON com a informação do jogador
	 * @throws EmptyListException se a fila de bots do jogador estiver vazia
	 */
	public JSONObject parseToJson() throws EmptyListException {
		JSONObject jsonPlayer = new JSONObject();
		JSONArray playerBotsJsonArray = new JSONArray();
		JSONArray playerLocation = new JSONArray();

		do {
			playerBotsJsonArray.add(playerBots.dequeue().parseToJson());
		} while (!playerBots.isEmpty());

		playerLocation.add(this.flag[0]);
		playerLocation.add(this.flag[1]);

		jsonPlayer.put("name", this.playerName);
		jsonPlayer.put("bots", playerBotsJsonArray);
		jsonPlayer.put("flag", playerLocation);

		return jsonPlayer;
	}

	public String toString() {
		return "Player {\n" +
				"Name: " + this.playerName + "\n" +
				"Bots: " + this.playerBots + "\n" +
				"}";
	}
}
