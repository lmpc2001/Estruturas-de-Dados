package com.example.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.network.Network;

public class NetworkTest {

	@Test
	public void shouldAnswerWithTrue() throws ElementNotFoundException {
		Network<Character> network = new Network<>(3);

		// Adiciona vértices
		network.addVertex('A');
		network.addVertex('B');
		network.addVertex('C');

		// Adiciona arestas com pesos
		network.addEdge('A', 'B', 2.0);
		network.addEdge('A', 'C', 5.0);
		network.addEdge('B', 'C', 1.0);

		// Testa o método shortestPathWeight
		UnorderedList<Character> shortestPath = network.shortestPathWeight('A', 'C');
		
		assertEquals("Algortimo de Dijkstra", "[A, B, C]", shortestPath.toString());
	}
}
