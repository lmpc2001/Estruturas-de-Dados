package com.example.domain;

import com.example.structures.implementation.network.Network;

public class GameMap extends Network {
	private double edgeDensity;

	public GameMap(int numberOfLocations, double edgeDensity) {
		super(numberOfLocations);
		this.edgeDensity = edgeDensity;
	}

	public int getNumberOfLocations() {
		return this.size();
	}

	public double getEdgeDensity() {
		return this.edgeDensity;
	}

	public void setEdgeDensity(double newEdgeDensity) {
		this.edgeDensity = newEdgeDensity;
	}

	public void seeMap() {
		this.printAdjencyMatrixWithWeights();
	}

	public boolean checkWin(Player oppositePlayer, Bot bot) {
		int[] botPosition = bot.getCurrentPosition();
		int[] oppositePlayerFlagPosition = oppositePlayer.getFlag();

		if (botPosition[0] == oppositePlayerFlagPosition[0] && botPosition[1] == oppositePlayerFlagPosition[1]) {
			return true;
		}

		return false;
	}
}
