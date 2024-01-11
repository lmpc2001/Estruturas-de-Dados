package com.example.domain;

public class Bot {
	private String botName;
	private String strategy;
	private int[] currentLocationCoordinates;

	public Bot(String botName, String strategy, int[] currentLocationCoordinates) {
		this.botName = botName;
		this.strategy = strategy;
		this.currentLocationCoordinates = currentLocationCoordinates;
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

	public int[] getCurrentLocationCoordinates() {
		return currentLocationCoordinates;
	}

	public void setCurrentLocationCoordinates(int[] currentLocationCoordinates) {
		this.currentLocationCoordinates = currentLocationCoordinates;
	}

	public String toString() {
		return "Bot {" +
				"Name: " + this.botName + "\n" +
				"Strategy: " + this.strategy + "\n" +
				"Current Location: " + this.currentLocationCoordinates + "\n" +
				"}";
	}
}
