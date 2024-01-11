package com.example.structures.implementation;

import java.util.Iterator;

import com.example.structures.adt.NetworkADT;

public class Graph implements NetworkADT {
	Vertex[] coordinates;
	int size;

	public Graph(int size) {
		this.coordinates = new Vertex[size]; // Podes ajustar conforme necessário
		this.size = size;
	}

	@Override
	public void addVertex(Vertex vertex) {
		if (size == coordinates.length) {
			increaseCapacity();
		}
		Vertex newVertex = new Vertex("");
		this.coordinates[size] = newVertex;
	}

	@Override
	public void removeVertex(Vertex vertex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeVertex'");
	}

	@Override
	public void addEdge(Vertex vertex1, Vertex vertex2, double distancia) {
		vertex1.addNeighbor(vertex2, distancia);
		vertex2.addNeighbor(vertex1, distancia);
	}

	@Override
	public void removeEdge(Vertex vertex1, Vertex vertex2) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeEdge'");
	}

	@Override
	public boolean isEmpty() {
		return this.size > 0;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isConnected'");
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public double shortestPathWeight(Vertex vertex1, Vertex vertex2) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'shortestPathWeight'");
	}

	@Override
	public Iterator iteratorBFS(Vertex startVertex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iteratorBFS'");
	}

	@Override
	public Iterator iteratorDFS(Vertex startVertex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iteratorDFS'");
	}

	@Override
	public Iterator iteratorShortestPath(Vertex startVertex, Vertex targetVertex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iteratorShortestPath'");
	}

	private void increaseCapacity() {
		int newCapacity = coordinates.length * 2;
		coordinates = copyArray(coordinates, newCapacity);
	}

	private Vertex[] copyArray(Vertex[] array, int newCapacity) {
		Vertex[] newArray = new Vertex[newCapacity];
		System.arraycopy(array, 0, newArray, 0, size);
		return newArray;
	}
}
