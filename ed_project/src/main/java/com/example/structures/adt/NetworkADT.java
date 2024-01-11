package com.example.structures.adt;

import com.example.structures.implementation.Vertex;

public interface NetworkADT extends GraphADT {
	/**
	 * Inserts an edge between two vertices of this graph.
	 *
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @param weight  the weight
	 */
	void addEdge(Vertex vertex1, Vertex vertex2, double weight);

	/**
	 * Returns the weight of the shortest path in this network.
	 *
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @return the weight of the shortest path in this network
	 */
	double shortestPathWeight(Vertex vertex1, Vertex vertex2);
}
