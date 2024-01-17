package com.example.structures.implementation.stack;

import com.example.structures.adt.StackADT;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.LinearNode;

public class LinkedStack<T> implements StackADT<T> {
	private LinearNode<T> topNode;
	private int count;

	public LinkedStack() {
		this.count = 0;
		this.topNode = null;
	}

	public LinkedStack(T element) {
		this.count = 0;
		this.topNode = new LinearNode<T>(element);
	}

	public void push(T element) {
		LinearNode<T> tmp = this.topNode;
		LinearNode<T> newElement = new LinearNode<>(element);

		this.topNode = newElement;
		this.topNode.setNext(tmp);
		this.count++;
	}

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

	public T peek() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		return this.topNode.getElement();
	}

	public boolean isEmpty() {
		return this.topNode == null;
	}

	public int size() {
		return count;
	}

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
