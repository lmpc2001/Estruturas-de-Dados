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
}
