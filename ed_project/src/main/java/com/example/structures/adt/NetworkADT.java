package com.example.structures.adt;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;

public interface NetworkADT<T> extends GraphADT<T> {
	/**
	 * Inserts an edge between two vertices of this graph.
	 *
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @param weight  the weight
	 */
	void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException;

	/**
	 * Returns the weight of the shortest path in this network.
	 *
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @return the weight of the shortest path in this network
	 */
	public UnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws ElementNotFoundException;
}
