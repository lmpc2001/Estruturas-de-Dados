package com.example.domain;

import com.example.structures.adt.GraphADT;
import com.example.structures.implementation.Graph;
import com.example.structures.implementation.Vertex;

public class GameMap extends Graph{
	private Graph map;
	private double edgeDensity;

	public GameMap(int numberOfLocations, double edgeDensity) {
		super(numberOfLocations);
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

	public void addVertex(Vertex Vertex) {
		this.map.addVertex(Vertex);
	}

	public void setCoordinates(Vertex[] newCoordinates) {
		this.map.setVertexes(newCoordinates);
	}

	public void getMatrix() {
		this.map.getGraphMatrix();
	}
}
