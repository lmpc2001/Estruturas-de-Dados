package com.example.structures.implementation;

import java.util.Arrays;
import java.util.Iterator;

import com.example.structures.adt.NetworkADT;

public class Graph implements NetworkADT {
	Vertex[] coordinates;
	int size;

	public Graph(int size) {
		this.coordinates = new Vertex[size];
		this.size = size;
	}

	public void setVertexs(Vertex[] newCoordinates) {
		this.coordinates = newCoordinates;
	}

	@Override
	public void addVertex(Vertex vertex) {
		if (size == coordinates.length) {
			increaseCapacity();
		}
		Vertex newVertex = new Vertex("", false);
		this.coordinates[size] = newVertex;
	}

	@Override
	public void removeVertex(Vertex vertex) {
		for (int i = 0; i < coordinates.length; i++) {
			if (this.coordinates[i] == vertex) {
				this.coordinates[i] = null;
				size--;
			}
		}
	}

	@Override
	public void addEdge(Vertex vertex1, Vertex vertex2, double distancia) {
		vertex1.addNeighbor(vertex2, distancia);
		vertex2.addNeighbor(vertex1, distancia);
	}

	@Override
	public void removeEdge(Vertex vertex1, Vertex vertex2) {
		vertex1.removeNeighbor(vertex2);
		vertex2.removeNeighbor(vertex1);
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

	public void getGraphMatrix() {
		int n = coordinates.length;
		int[][] adjacencyMatrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjacencyMatrix[i][j] = 0; // Inicializamos com 0
			}
		}

		for (int i = 0; i < n; i++) {
			Vertex currentVertex = coordinates[i];
			Vertex[] neighbors = currentVertex.getNeighbors();
			double[] weights = currentVertex.getWeights();

			for (int j = 0; j < neighbors.length; j++) {
				Vertex neighbor = neighbors[j];
				double weight = weights[j];

				int row = Arrays.asList(coordinates).indexOf(currentVertex);
				int col = Arrays.asList(coordinates).indexOf(neighbor);
				System.out.println("Row: " + row + " Col: "+ col);
				adjacencyMatrix[row][col] = (int) weight; // Podes ajustar conforme necessário
			}
		}

		// Imprimir a matriz de adjacência
		System.out.println("Matriz de Adjacência:");

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}
			System.out.println();
		}
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
