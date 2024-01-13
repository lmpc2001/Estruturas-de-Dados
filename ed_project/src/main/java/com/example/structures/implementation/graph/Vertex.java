package com.example.structures.implementation.graph;

public class Vertex<T> {
	T element;
	Vertex[] neighbors;
	double[] weights;

	int size = 0;

	public Vertex() {
		this.element = element;
		this.neighbors = new Vertex[1];
		this.weights = new double[1];
		this.size = 0;
	}
	
	public Vertex(T element) {
		this.element = element;
		this.neighbors = new Vertex[1];
		this.weights = new double[1];
		this.size = 0;
	}

	public T getElement(){
		return this.element;
	}

	public Vertex[] getNeighbors() {
		return neighbors;
	}

	public double[] getWeights() {
		return weights;
	}

	public void addNeighbor(Vertex neighbor, double weight) {
		if (size == neighbors.length) {
			increaseCapacity();
		}

		neighbors[size] = neighbor;
		weights[size] = weight;
		size++;
	}

	public void removeNeighbor(Vertex neighbor) {
		for (int i = 0; i < neighbors.length; i++) {
			if (this.neighbors[i] == neighbor) {
				this.neighbors[i] = null;
				this.weights[i] = 0.0;
				size--;
				return;
			}
		}
	}

	private void increaseCapacity() {
		int newCapacity = neighbors.length + 1;
		neighbors = copyNeighborsArray(neighbors, newCapacity);
		weights = copyWeightsArray(weights, newCapacity);
	}

	private Vertex[] copyNeighborsArray(Vertex[] array, int newCapacity) {
		Vertex[] newArray = new Vertex[newCapacity];
		System.arraycopy(array, 0, newArray, 0, size);
		return newArray;
	}

	private double[] copyWeightsArray(double[] array, int newCapacity) {
		double[] newArray = new double[newCapacity];
		System.arraycopy(array, 0, newArray, 0, size);
		return newArray;
	}
}
