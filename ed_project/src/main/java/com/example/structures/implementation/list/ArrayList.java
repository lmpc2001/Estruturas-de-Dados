package com.example.structures.implementation.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import com.example.configs.Properties;
import com.example.structures.adt.ListADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

public abstract class ArrayList<T> implements ListADT<T> {
	private final int DEFAULT_SIZE = Properties.LIST_DEFAULT_SIZE;

	protected T[] list;
	protected int front;
	protected int rear;
	protected int modCount;

	protected ArrayList() {
		this.list = (T[]) (new Object[DEFAULT_SIZE]);
		this.front = 0;
		this.rear = 0;
		this.modCount = 0;
	}

	protected ArrayList(int size) {
		this.list = (T[]) (new Object[size]);
		this.front = 0;
		this.rear = 0;
		this.modCount = 0;
	}

	@Override
	public T removeFirst() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		T element = list[front];
		this.list[front] = null;
		this.rear--;
		
		reorderArray();

		this.modCount++;

		return element;

	}

	@Override
	public T removeLast() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		T element = this.list[rear];
		this.list[rear - 1] = null;

		this.rear--;
		this.modCount++;

		return element;
	}

	@Override
	public T remove(T element) throws EmptyListException, ElementNotFoundException {
		int removedElementIndex = 0;
		T removedElement = null;

		if (isEmpty()) {
			throw new EmptyListException();
		}

		if (!listContains(element)) {
			throw new ElementNotFoundException();
		}

		for (T el : this.list) {
			if (el.equals((element))) {
				removedElement = el;
				break;
			}
			removedElementIndex++;
		}

		if (removedElement == null) {
			throw new ElementNotFoundException();
		}

		this.list[removedElementIndex] = null;
		reorderArray(removedElementIndex);

		this.rear--;
		this.modCount++;

		return removedElement;
	}

	@Override
	public T first() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		return this.list[front];
	}

	@Override
	public T last() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		return this.list[rear - 1];
	}

	@Override
	public boolean isEmpty() {
		return this.rear == 0;
	}

	@Override
	public int size() {
		return this.rear;
	}

	@Override
	public Iterator<T> iterator() {
		return new BaseIterator<>();
	}

	private void reorderArray() {
		for (int i = 0; i < this.rear; i++) {
			this.list[i] = this.list[i + 1];
		}
	}

	private void reorderArray(int afterPosition) {
		for (int i = afterPosition; i < this.rear; i++) {
			this.list[i] = this.list[i + 1];
		}
	}

	protected boolean listContains(T element) {
		boolean contains = false;

		for (T el : this.list) {
			if (el.equals(element)) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	protected void increaseListCapacity() {
		T[] tmpList = (T[]) (new Object[this.list.length + 1]);
		System.arraycopy(this.list, 0, tmpList, 0, rear);
		this.list = tmpList;
	}

	public class BaseIterator<T> implements Iterator<T> {
		private final T[] items;
		private int current;
		private int expectedModCount;

		public BaseIterator() {
			this.items = (T[]) ArrayList.this.list;
			this.current = 0;
			this.expectedModCount = ArrayList.this.modCount;
		}

		@Override
		public boolean hasNext() {
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException("Concorrência");
			}
			
			return this.current < this.items.length && this.items[this.current] != null;
		}

		@Override
		public T next() {
			T temp = items[this.current];
			if (hasNext()) {
				this.current++;
			}
			return temp;
		}

	}
}
