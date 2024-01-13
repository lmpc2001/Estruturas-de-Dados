package com.example.structures.adt;

import java.util.Iterator;

import com.example.structures.implementation.graph.Vertex;

/**
 *
 */
public interface GraphADT {

	/**
	 * Adiciona um vertice ao grafo
	 *
	 * @param vertex vertice a ser adicionado
	 */
	public void addVertex(Vertex vertex);

	/**
	 * Remove um vertice do grafo
	 *
	 * @param vertex vertice a remover
	 */
	public void removeVertex(Vertex vertex);

	/**
	 * Adiciona um caminho entre dois vertices
	 *
	 * @param vertex1 vertice 1
	 * @param vertex2 vertice 2
	 */
	public void addEdge(Vertex vertex1, Vertex vertex2, double weight);

	/**
	 * Remove um caminho entre dois vertices
	 *
	 * @param vertex1 vertice 1
	 * @param vertex2 vertice 2
	 */
	public void removeEdge(Vertex vertex1, Vertex vertex2);

	/**
	 * Returns a breadth first iterator starting with the given vertex.
	 * 
	 * @param startVertex the starting vertex
	 * @return a breadth first iterator beginning at
	 *         the given vertex
	 */
	Iterator iteratorBFS(Vertex startVertex);

	/**
	 * Returns a depth first iterator starting with the given vertex.
	 *
	 * @param startVertex the starting vertex
	 * @return a depth first iterator starting at the
	 *         given vertex
	 */
	Iterator iteratorDFS(Vertex startVertex);

	/** 
	 * Returns an iterator that contains the shortest path between
	 * the two vertices. 
	 *
	 * @param startVertex the starting vertex
	 * @param targetVertex the ending vertex
	 * @return an iterator that contains the shortest 
	 * path between the two vertices
	 */
	Iterator iteratorShortestPath(Vertex startVertex, Vertex targetVertex);

	/**
	 * Retorna true caso o grafo esteja vazio e false caso não esteja
	 *
	 * @return true ou false
	 */
	public boolean isEmpty();

	/**
	 * Retorna true caso o grafo seja conexo ou false caso nao seja
	 *
	 * @return true ou false
	 */
	public boolean isConnected();

	/**
	 * Retorna o tamanho do grafo
	 *
	 * @return numero de vertices do grafo
	 */
	public int size();

	/**
	 * Retorna uma representação da matriz de adjacencias
	 *
	 * @return uma string que representa a matriz de adjacencias
	 */
	public String toString();
}