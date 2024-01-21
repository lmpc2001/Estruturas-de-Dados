package com.example.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.network.Graph;

public class GraphTest {
	public void testAddVertex() {
		Graph<String> graph = new Graph<>(5);

		// Testa adição de vértices
		graph.addVertex("A");
		graph.addVertex("B");

		assertEquals(2, graph.size());
	}

	@Test
	public void testRemoveVertex() throws ElementNotFoundException {
		Graph<String> graph = new Graph<>(5);

		// Testa remoção de vértices
		graph.addVertex("A");
		graph.addVertex("B");
		graph.removeVertex("A");

		assertEquals(1, graph.size());
	}

	@Test
	public void testAddEdge() throws ElementNotFoundException {
		Graph<String> graph = new Graph<>(5);

		// Testa adição de arestas
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addEdge("A", "B");

		assertTrue(graph.downloadAdjencyMatrix()[0][1]);
		assertTrue(graph.downloadAdjencyMatrix()[1][0]);
	}

	@Test
	public void testRemoveEdge() throws ElementNotFoundException {
		Graph<String> graph = new Graph<>(5);

		// Testa remoção de arestas
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addEdge("A", "B");
		graph.removeEdge("A", "B");

		assertFalse(graph.downloadAdjencyMatrix()[0][1]);
		assertFalse(graph.downloadAdjencyMatrix()[1][0]);
	}

	@Test
	public void testIteratorBFS() throws EmptyListException, ElementNotFoundException {
		Graph<String> graph = new Graph<>(5);

		// Testa o iterador de caminho mais curto entre índices
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("C", "D");

		Iterator<String> iterator = graph.iteratorBFS("A");

		assertTrue(iterator.hasNext());
		assertEquals("A", iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals("B", iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals("C", iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals("D", iterator.next());

		assertFalse(iterator.hasNext());
	}

	@Test
	public void testIteratorShortestPath() throws EmptyListException, ElementNotFoundException {
		Graph<String> graph = new Graph<>(5);

		// Testa o iterador de caminho mais curto entre índices
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("C", "D");

		Iterator<String> iterator = graph.iteratorShortestPath(0, 3);

		assertTrue(iterator.hasNext());
		assertEquals("A", iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals("B", iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals("C", iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals("D", iterator.next());

		assertFalse(iterator.hasNext());
	}
}
