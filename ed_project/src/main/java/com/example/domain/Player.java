package com.example.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.queue.LinkedQueue;

public class Player {
	private String playerName;
	private LinkedQueue<Bot> playerBots;
	private int[] flag = new int[2];

	public Player(String playerName) {
		this.playerName = playerName;
		this.playerBots = new LinkedQueue<>();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public LinkedQueue<Bot> getPlayerBots() {
		return playerBots;
	}

	public void addBot(Bot playerBots) {
		this.playerBots.enqueue(playerBots);
	}

	public int[] getFlag() {
		return this.flag;
	}

	public void setFlag(int[] flagPosition) {
		this.flag = flagPosition;
	}

	public Bot getBotToPlay() throws EmptyListException{
		Bot botToMove = this.playerBots.dequeue();
		this.playerBots.enqueue(botToMove);

		return botToMove;
	}

	public JSONObject parseToJson() throws EmptyListException {
		JSONObject jsonPlayer = new JSONObject();
		JSONArray playerBotsJsonArray = new JSONArray();
		JSONArray playerLocation = new JSONArray();

		do {
			playerBotsJsonArray.add(playerBots.dequeue().parseToJson());
		} while (!playerBots.isEmpty());

		System.out.println("Lenght da Queue principal de bots do player" + playerBots.size());

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
