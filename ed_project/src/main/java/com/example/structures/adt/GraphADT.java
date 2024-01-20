package com.example.structures.adt;

import java.util.Iterator;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

/**
 *
 * @author Luís Costa [8200737]
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
	 * Retorna um iterador de largura a começar no vértice especificado.
	 * 
	 * @param startVertex o vértice de início
	 * @return um iterador de largura a começar no
	 *         vértice especificado
	 * @throws ElementNotFoundException se o vértice não for encontrado
	 */

	Iterator<T> iteratorBFS(T startVertex) throws ElementNotFoundException, EmptyListException;

	/**
	 * Retorna um iterador de profundidade a começar no vértice especificado.
	 *
	 * @param startVertex o vértice de início
	 * @return um iterador de profundidade a começar no
	 *         vértice especificado
	 */

	Iterator<T> iteratorDFS(T startVertex) throws ElementNotFoundException, EmptyListException;

	/**
	 * Retorna um iterador que contém o caminho mais curto entre
	 * os dois vértices.
	 *
	 * @param startVertex  O vértice de origem
	 * @param targetVertex O vértice de destino
	 * @return um iterador que contém o caminho mais curto
	 *         entre os dois vértices
	 */

	Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyListException, ElementNotFoundException;

	/**
	 * Retorna true caso o grafo esteja vazio ou false caso contrário
	 *
	 * @return true ou false
	 */
	public boolean isEmpty();

	/**
	 * Retorna true caso o grafo seja conexo ou false caso contrário
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