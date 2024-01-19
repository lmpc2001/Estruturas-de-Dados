package com.example.domain;

import com.example.structures.implementation.graph.Vertex;

public class Local {
	private Vertex<Local> vertex;
	private int[][] coordinates;

	public Local() {
		this.vertex = new Vertex<Local>(this);
	}

	public Vertex<Local> getVertex() {
		return this.vertex;
	}
}
