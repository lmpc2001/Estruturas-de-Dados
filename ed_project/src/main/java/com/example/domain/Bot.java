package com.example.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Bot {
	private String botName;
	private String strategy;
	private int[] currentLocation = new int[2];

	public Bot(String botName) {
		this.botName = botName;
	}

	public Bot(String botName, String strategy, int[] position) {
		this.botName = botName;
		this.strategy = strategy;
		this.currentLocation = position;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public int[] getCurrentPosition() {
		return currentLocation;
	}

	public void setCurrentPosition(int[] newPosition) {
		this.currentLocation = newPosition;
	}

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

	public String toString() {
		return "Bot {" +
				"Name: " + this.botName + "\n" +
				"Strategy: " + this.strategy + "\n" +
				"Current Location: " + this.currentLocation + "\n" +
				"}";
	}
}
