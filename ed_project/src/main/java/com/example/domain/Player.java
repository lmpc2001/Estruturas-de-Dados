package com.example.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.structures.implementation.list.UnorderedList;

public class Player {
	private String playerName;
	private UnorderedList<Bot> playerBots;
	private int[] flag = new int[2];

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

	public int[] getFlag() {
		return this.flag;
	}

	public void setFlag(int[] flagPosition) {
		this.flag = flagPosition;
	}

	public JSONObject parseToJson() {
		JSONObject jsonPlayer = new JSONObject();
		JSONArray playerBotsJsonArray = new JSONArray();
		JSONArray playerLocation = new JSONArray();

		for(Bot playerBot : playerBots) {
			playerBotsJsonArray.add(playerBot.parseToJson());
		}

		playerLocation.add(this.flag[0]);
		playerLocation.add(this.flag[1]);

		jsonPlayer.put("name", this.playerName);
		jsonPlayer.put("bots", playerBotsJsonArray);
		jsonPlayer.put("flag", playerLocation);

		return jsonPlayer;
	}

	public String toString() {
		return "Player {" +
				"Name: " + this.playerName + "\n" +
				"Bots: " + this.playerBots + "\n" +
				"}";
	}
}
