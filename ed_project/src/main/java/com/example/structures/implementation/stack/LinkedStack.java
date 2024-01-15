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
		return this.topNode != null;
	}

	public int size() {
		return count;
	}

	public String toString() {
		LinearNode<T> current = this.topNode;
		String s = "LinkedStack:\n";
		while (current != null) {
			s += current.getElement().toString() + "\n";
			current = current.getNext();
		}
		return s;
	}
}
