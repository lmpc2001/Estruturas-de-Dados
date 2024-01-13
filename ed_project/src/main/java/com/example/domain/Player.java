package com.example.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.structures.implementation.UnorderedList;
import com.example.structures.implementation.graph.Vertex;

public class Player {
	private String playerName;
	private UnorderedList<Bot> playerBots;
	private Vertex<Local> flag;

	public Player(String playerName) {
		this.playerName = playerName;
		this.playerBots = new UnorderedList<>();
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public UnorderedList<Bot> getPlayerBots() {
		return playerBots;
	}

	public void addBot(Bot playerBots) {
		this.playerBots.addToRear(playerBots);
	}

	public Vertex<Local> getFlag() {
		return this.flag;
	}

	public void setFlag(Vertex<Local> flagPosition) {
		this.flag = flagPosition;
	}

	public JSONObject parseToJson() {
		JSONObject jsonPlayer = new JSONObject();
		JSONArray playerBotsJsonArray = new JSONArray();

		for(Bot playerBot : playerBots) {
			playerBotsJsonArray.add(playerBot.parseToJson());
		}

		jsonPlayer.put("name", this.playerName);
		jsonPlayer.put("bots", playerBotsJsonArray);
		jsonPlayer.put("flag", this.flag);

		return jsonPlayer;
	}

	public String toString() {
		return "Player {" +
				"Name: " + this.playerName + "\n" +
				"Bots: " + this.playerBots + "\n" +
				"}";
	}
}
