package com.example.structures.adt;

import java.util.Iterator;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

/**
 *
 */
public interface GraphADT<T> {

	/**
	 * Adiciona um vertice ao grafo
	 *
	 * @param vertex vertice a ser adicionado
	 */
	public void addVertex(T vertex);

	/**
	 * Remove um vertice do grafo
	 *
	 * @param vertex vertice a remover
	 */
	public void removeVertex(T vertex) throws ElementNotFoundException;

	/**
	 * Adiciona um caminho entre dois vertices
	 *
	 * @param vertex1 vertice 1
	 * @param vertex2 vertice 2
	 */
	public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException;

	/**
	 * Remove um caminho entre dois vertices
	 *
	 * @param vertex1 vertice 1
	 * @param vertex2 vertice 2
	 */
	public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException;

	/**
	 * Returns a breadth first iterator starting with the given vertex.
	 * 
	 * @param startVertex the starting vertex
	 * @return a breadth first iterator beginning at
	 *         the given vertex
	 */
	Iterator<T> iteratorBFS(T startVertex);

	/**
	 * Returns a depth first iterator starting with the given vertex.
	 *
	 * @param startVertex the starting vertex
	 * @return a depth first iterator starting at the
	 *         given vertex
	 */
	Iterator<T> iteratorDFS(T startVertex);

	/** 
	 * Returns an iterator that contains the shortest path between
	 * the two vertices. 
	 *
	 * @param startVertex the starting vertex
	 * @param targetVertex the ending vertex
	 * @return an iterator that contains the shortest 
	 * path between the two vertices
	 */
	Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyListException, ElementNotFoundException;

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