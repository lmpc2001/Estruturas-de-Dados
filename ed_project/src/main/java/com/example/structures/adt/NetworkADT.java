package com.example.structures.adt;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;

/**
 * Interface NetworkADT faz extend à interface GraphADT herdando a os métodos
 * básicos de um grafo e define os métodos caracteristicos de uma rede (network).
 * 
 * @author Luís Costa [8200737]
 * @param <T> Tipo dos elementos armazenados na rede.
 */
public interface NetworkADT<T> extends GraphADT<T> {
    /**
     * Insere uma aresta entre dois vértices na rede.
     *
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @param weight  o peso da aresta
     * @throws ElementNotFoundException se um dos vértices não for encontrado na rede.
     */
	void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException;

    /**
     * Retorna uma lista com os vértices que formam o caminho mais curto na rede.
     *
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @return o caminho mais curto na rede.
     * @throws ElementNotFoundException se um dos vértices não for encontrado na rede.
     */
	public UnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws ElementNotFoundException;
}
