package com.example.structures.implementation;

import com.example.structures.adt.QueueADT;

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
		this.count = 0;
		this.frontNode = new LinearNode(element);
		this.rearNode = new LinearNode(element);
	}

	public void enqueue(T element) {
		LinearNode<T> newElement = new LinearNode<T>(element);

		this.frontNode.setNext(newElement);
		this.rearNode = newElement;
	}

	public T dequeue() {
		T tmp = this.frontNode.getElement();
		LinearNode<T> nextLinearNode = this.frontNode.getNext();

		this.frontNode.setNext(nextLinearNode);

		return tmp;
	}

	public T first() {
		return this.frontNode.getElement();
	}

	public boolean isEmpty() {
		if (this.frontNode.getNext() != null) {
			return false;
		}

		return true;
	}

	public int size() {
		return count;
	}

}
