package com.example.structures.implementation.queue;

import com.example.structures.adt.QueueADT;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.LinearNode;

/**
 * A classe LinkedQueue<T> implementa a interface QueueADT<T> e representa
 * uma fila baseada numa lista ligada.
 * 
 * Pode armazenar elementos de tipo genérico e T e fornece operações típicas de
 * uma queue, como enqueue, dequeue, first, isEmpty e size.
 * 
 * Esta implementação utiliza a classe e LinearNode<T> para representar os
 * elementos da fila e controla o início e o fim da mesma através dos campos
 * frontNode e rearNode.
 * 
 * @author Luís Costa [8200737]
 * @param <T> O tipo de elemento que a fila pode armazenar.
 * @see com.example.structures.adt.QueueADT
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.LinearNode
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueADT<T> {
	private LinearNode<T> frontNode;
	private LinearNode<T> rearNode;
	private int count;

	/**
	 * Cria uma nova instância da LinkedQueue<T> inicializando a queue
	 * como vazia.
	 */
	public LinkedQueue() {
		this.count = 0;
		this.frontNode = null;
		this.rearNode = null;
	}

	/**
	 * Cria uma nova instância da LinkedQueue<T> inicializando a queue com um
	 * elemento especificado.
	 *
	 * @param element O elemento a ser adicionado à queue.
	 */
	public LinkedQueue(T element) {
		this.count = 1;
		this.frontNode = new LinearNode(element);
		// this.rearNode = new LinearNode(element);
	}

	/**
	 * Adiciona um elemento ao final da queue.
	 *
	 * @param element O elemento a ser adicionado à queue.
	 */
	public void enqueue(T element) {
		LinearNode<T> newElement = new LinearNode<T>(element);

		if (this.frontNode == null) {
			this.frontNode = newElement;
			this.rearNode = newElement;
		} else {
			this.rearNode.setNext(newElement);
			this.rearNode = newElement;
		}

		this.count++;
	}

	/**
	 * Remove e retorna o elemento no início da queue.
	 *
	 * @return O elemento removido do início da queue.
	 * @throws EmptyListException Se a queue estiver vazia.
	 */
	public T dequeue() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("LinkedQueue: ");
		}

		T tmp = this.frontNode.getElement();
		LinearNode<T> nextLinearNode = this.frontNode.getNext();

		if (this.frontNode == null) {
			this.rearNode = null;
		}

		this.frontNode = nextLinearNode;
		this.count--;

		return tmp;
	}

	/**
	 * Retorna o elemento no início da queue sem removê-lo.
	 *
	 * @return O elemento no início da queue.
	 * @throws EmptyListException Se a queue estiver vazia.
	 */
	public T first() {
		return this.frontNode.getElement();
	}

	/**
	 * Verifica se a queue está vazia.
	 *
	 * @return true se a queue estiver vazia, false caso contrário.
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Retorna o número de elementos na queue.
	 *
	 * @return O número de elementos na queue.
	 */
	public int size() {
		return count;
	}

	/**
	 * Retorna uma representação da queue com recurso a uma string.
	 *
	 * @return Uma string de representação daqueue.
	 */
	public String toString() {
		LinearNode<T> current = this.frontNode;
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
