package com.example.structures.implementation.stack;

import com.example.structures.adt.StackADT;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.LinearNode;

/**
 * A classe LinkedStack<T> implementa a interface StackADT<T> e representa uma
 * pilha baseada numa lista ligada.
 * 
 * Esta stack pode armazenar elementos de tipo genérico T e fornece operações
 * típicas de uma stack, como push, pop, peek, isEmpty e size.
 * 
 * Esta implementação utiliza a classe LinearNode<T> para representar os
 * elementos da stack e controla o topo da mesma através do campo topNode.
 * 
 * @author Luís Costa [8200737]
 * @param <T> O tipo de elemento que a stack pode armazenar.
 * @see com.example.structures.adt.StackADT
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.LinearNode
 * 
 */
public class LinkedStack<T> implements StackADT<T> {
	private LinearNode<T> topNode;
	private int count;

	/**
	 * Cria uma nova instância da LinkedStack<T> inicializando a pilha como
	 * vazia.
	 */
	public LinkedStack() {
		this.count = 0;
		this.topNode = null;
	}

	/**
	 * Cria uma nova instância de LinkedStack<T> inicializando a stack
	 * com um elemento especificado.
	 *
	 * @param element O elemento a ser adicionado à stack.
	 */
	public LinkedStack(T element) {
		this.count = 0;
		this.topNode = new LinearNode<T>(element);
	}

	/**
	 * Adiciona um elemento ao topo da stack.
	 *
	 * @param element O elemento a ser adicionado à stack.
	 */
	public void push(T element) {
		LinearNode<T> tmp = this.topNode;
		LinearNode<T> newElement = new LinearNode<>(element);

		this.topNode = newElement;
		this.topNode.setNext(tmp);
		this.count++;
	}

	/**
	 * Remove e retorna o elemento no topo da stack.
	 *
	 * @return O elemento removido do topo da stack.
	 * @throws EmptyListException Se a stack estiver vazia.
	 */
	public T pop() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		T element = this.topNode.getElement();
		LinearNode<T> nextLinearNode = this.topNode.getNext();

		if (nextLinearNode == null) {
			this.topNode = null;
			return element;
		}

		this.topNode = nextLinearNode;
		this.count--;

		return element;
	}

	/**
	 * Retorna o elemento no topo da stack sem removê-lo.
	 *
	 * @return O elemento no topo da stack.
	 * @throws EmptyListException Se a stack estiver vazia.
	 */
	public T peek() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		return this.topNode.getElement();
	}

	/**
	 * Verifica se a stack está vazia.
	 *
	 * @return true se a stack estiver vazia, false caso contrário.
	 */
	public boolean isEmpty() {
		return this.topNode == null;
	}

	/**
	 * Retorna o número de elementos na stack.
	 *
	 * @return O número de elementos na stack.
	 */
	public int size() {
		return count;
	}

	/**
	 * Retorna uma representação da stack com recurso a uma string.
	 *
	 * @return Uma string de representação da stack.
	 */
	public String toString() {
		LinearNode<T> current = this.topNode;
		StringBuilder sb = new StringBuilder();
		int counter = 0;

		sb.append("[");
		while (current != null) {
			sb.append(current.getElement().toString());
			current = current.getNext();

			// Adiciona vírgula apenas se não for o último elemento
			if (counter < this.size() - 1) {
				sb.append(", ");
			}
			counter++;
		}
		sb.append("]");

		return sb.toString();
	}
}
