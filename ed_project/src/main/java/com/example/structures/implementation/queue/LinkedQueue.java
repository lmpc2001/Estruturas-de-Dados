package com.example.structures.implementation.queue;

import com.example.structures.adt.QueueADT;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.LinearNode;

public class LinkedQueue<T> implements QueueADT<T> {
	private LinearNode<T> frontNode;
	private LinearNode<T> rearNode;
	private int count;

	public LinkedQueue() {
		this.count = 0;
		this.frontNode = null;
		this.rearNode = null;
	}

	public LinkedQueue(T element) {
		this.count = 1;
		this.frontNode = new LinearNode(element);
		// this.rearNode = new LinearNode(element);
	}

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

	public T first() {
		return this.frontNode.getElement();
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int size() {
		return count;
	}

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
