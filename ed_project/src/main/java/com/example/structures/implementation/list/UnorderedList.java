package com.example.structures.implementation.list;

import com.example.structures.adt.UnorderListADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

public class UnorderedList<T> extends ArrayList<T> implements UnorderListADT<T> {

	public UnorderedList() {
		super();
	}

	public UnorderedList(int size) {
		super(size);
	}

	@Override
	public void addToFront(T element) {
		T[] tmpList = list;

		if (this.rear == this.list.length) {
			increaseListCapacity();
		}

		for (int i = rear; i > front; i--) {
			tmpList[i] = this.list[i - 1];
		}

		tmpList[front] = element;
		this.rear++;
		this.modCount++;
	}

	@Override
	public void addToRear(T element) {
		if (isEmpty()) {
			this.list[front] = element;
		}

		if (this.rear == this.list.length) {
			increaseListCapacity();
		}

		this.list[rear] = element;
		this.rear++;
		this.modCount++;
	}

	@Override
	public void addAfter(T element, T target) throws EmptyListException, ElementNotFoundException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		if (!listContains(element)) {
			throw new ElementNotFoundException();
		}

		if (this.list.length - 1 == rear) {
			increaseListCapacity();
		}

		int targetIndex = 0;

		for (int i = 0; i < this.rear; i++) {
			if (this.list[i].equals(target)) {
				targetIndex = i;
			}
		}

		for (int i = rear; i > targetIndex; i--) {
			this.list[i] = this.list[i - 1];
		}

		this.list[targetIndex + 1] = element;
		this.rear++;
		this.modCount++;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		for (int i = 0; i < rear; i++) {
			sb.append(list[i]);

			// Adiciona vírgula apenas se não for o último elemento
			if (i < rear - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");

		return sb.toString();
	}
}
