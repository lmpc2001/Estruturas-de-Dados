package com.example.domain;

import com.example.structures.implementation.Graph;

public class GameMap {
	private Graph map;
	private double edgeDensity;

	public GameMap(int numberOfLocations, int edgeDensity) {
		this.map = new Graph(numberOfLocations);
		this.edgeDensity = edgeDensity;
	}

	public int getNumberOfLocations() {
		return this.map.size();
	}

	public double getEdgeDensity() {
		return this.edgeDensity;
	}

	public void setEdgeDensity(double newEdgeDensity) {
		this.edgeDensity = newEdgeDensity;
	}

}
