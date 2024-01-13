package com.example.domain;

import com.example.structures.implementation.graph.Graph;
import com.example.structures.implementation.graph.Vertex;

public class GameMap extends Graph {
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
		this.showGraphMatrix();
	}

	public int[][] getMap() {
		return this.getGraphMatrix();
	}

	public boolean checkWin(Player oppositePlayer, Bot bot) {
		Vertex botPosition = bot.getCurrentPosition();
		Vertex oppositePlayerFlagPosition = oppositePlayer.getFlag();

		return botPosition.equals(oppositePlayerFlagPosition);
	}
}
