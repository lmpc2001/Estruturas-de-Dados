package com.example.structures.implementation;

public class Vertex {
	boolean flag;
	Vertex[] neighbors;
	double[] weights;

	int size = 0;

	public Vertex(boolean flag) {
		this.flag = flag;
		this.neighbors = new Vertex[0];
		this.weights = new double[0];
		this.size = 0;
	}

	public boolean isFlag() {
		return this.flag;
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

	private void increaseCapacity() {
		int newCapacity = neighbors.length * 2;
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