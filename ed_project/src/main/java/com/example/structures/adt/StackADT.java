package com.example.structures.adt;

import com.example.structures.exceptions.EmptyListException;

/**
 * Interface onde se encontram definidas todas as funções e os tipos de dados a
 * implementar no desenvolvimento de uma pilha
 * 
 * @author Luís Costa [8200737]
 * @param <T> O tipo de dados dos elementos a armazenar na pilha
 */
public interface StackADT<T> {

	/**
	 * Adiciona um elemento ao topo da stack
	 *
	 * @param element o elemento a adicionar à pilha
	 */
	public void push(T element);

	/**
	 * Remove e retorna o elemento do topo da stack
	 *
	 * @return o elemento removido do topo da stack
	 * @throws EmptyListException se a pilha estiver vazia
	 */
	public T pop() throws EmptyListException;

	/**
	 * Retorna o elemento do topo da stack
	 *
	 * @return elemento T do topo da stack
	 * @throws EmptyListException se a pilha estiver vazia
	 */
	public T peek() throws EmptyListException;

	/**
	 * Valida se a stack está ou não vazio
	 *
	 * @return true se a stack estiver vazia, caso contrário return false
	 */
	public boolean isEmpty();

	/**
	 * Retorna o número de elementos presentes na stack
	 *
	 * @return número de elementos presentes na stack
	 */
	public int size();

	/**
	 * Retorna uma string representativa da stack
	 *
	 * @return string representativa da stack
	 */
	@Override
	public String toString();
}