package com.example.domain;

import org.json.simple.JSONObject;

import com.example.structures.implementation.graph.Vertex;

public class Bot {
	private String botName;
	private String strategy;
	private Vertex<Local> currentLocation;

	public Bot(String botName) {
		this.botName = botName;
	}

	public Bot(String botName, String strategy, Vertex position) {
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

	public Vertex<Local> getCurrentPosition() {
		return currentLocation;
	}

	public void setCurrentPosition(Vertex<Local> newPosition) {
		this.currentLocation = newPosition;
	}

	public JSONObject parseToJson() {
		JSONObject jsonBot = new JSONObject();

		jsonBot.put("Name", this.botName);
		jsonBot.put("Strategy", this.strategy);
		jsonBot.put("Location", this.currentLocation);

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
