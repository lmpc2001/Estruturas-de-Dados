package com.example.structures.implementation.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import com.example.configs.Properties;
import com.example.structures.adt.ListADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

/**
 * A classe abstrata ArrayList<T> implementa a interface ListADT<T> e fornece
 * uma implementação básica de uma lista utilizando arrays.
 * 
 * Pode armazenar elementos de tipo genérico T e fornece operações
 * base de uma lista, como remoção, acesso ao primeiro e último elemento
 * e iteração.
 * 
 * @author Luís Costa [8200737]
 * @param <T> O tipo de elemento que a lista pode armazenar.
 * @see com.example.structures.adt.ListADT
 * @see com.example.structures.exceptions.ElementNotFoundException
 * @see com.example.structures.exceptions.EmptyListException
 * @version 1.0
 */
public abstract class ArrayList<T> implements ListADT<T> {
	private final int DEFAULT_SIZE = Properties.LIST_DEFAULT_SIZE;

	protected T[] list;
	protected int front;
	protected int rear;
	protected int modCount;

	/**
	 * Inicializa uma nova lista com um tamanho padrão.
	 */
	protected ArrayList() {
		this.list = (T[]) (new Object[DEFAULT_SIZE]);
		this.front = 0;
		this.rear = 0;
		this.modCount = 0;
	}

	/**
	 * Inicializa uma nova lista com um tamanho fornecido.
	 * 
	 * @param size O tamanho inicial da lista.
	 */
	protected ArrayList(int size) {
		this.list = (T[]) (new Object[size]);
		this.front = 0;
		this.rear = 0;
		this.modCount = 0;
	}

	/**
	 * Remove e retorna o primeiro elemento da lista.
	 *
	 * @return O primeiro elemento removido da lista.
	 * @throws EmptyListException Se a lista estiver vazia.
	 */
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

	/**
	 * Remove e retorna o último elemento da lista.
	 *
	 * @return O último elemento removido da lista.
	 * @throws EmptyListException Se a lista estiver vazia.
	 */
	@Override
	public T removeLast() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		T element = this.list[rear - 1];
		this.list[rear - 1] = null;

		this.rear--;
		this.modCount++;

		return element;
	}

	/**
	 * Remove e retorna a primeira ocorrência do elemento especificado na lista.
	 *
	 * @param element O elemento a ser removido da lista.
	 * @return O elemento removido da lista.
	 * @throws EmptyListException       Se a lista estiver vazia.
	 * @throws ElementNotFoundException Se o elemento especificado não for
	 *                                  encontrado na lista.
	 */
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

	/**
	 * Retorna o primeiro elemento da lista sem removê-lo.
	 *
	 * @return O primeiro elemento da lista.
	 * @throws EmptyListException Se a lista estiver vazia.
	 */
	@Override
	public T first() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		return this.list[front];
	}

	/**
	 * Retorna o último elemento da lista sem removê-lo.
	 *
	 * @return O último elemento da lista.
	 * @throws EmptyListException Se a lista estiver vazia.
	 */
	@Override
	public T last() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		return this.list[rear - 1];
	}

	/**
	 * Verifica se a lista está vazia.
	 *
	 * @return true se a lista estiver vazia, false caso contrário.
	 */
	@Override
	public boolean isEmpty() {
		return this.rear == 0;
	}

	/**
	 * Retorna o número de elementos na lista.
	 *
	 * @return O número de elementos na lista.
	 */
	@Override
	public int size() {
		return this.rear;
	}

	/**
	 * Retorna um iterador sobre os elementos da lista.
	 *
	 * @return Um iterador sobre os elementos da lista.
	 */
	@Override
	public Iterator<T> iterator() {
		return new BaseIterator<>();
	}

	/**
	 * Reorganiza os elementos da lista após a remoção do primeiro elemento.
	 * Move cada elemento uma posição para a frente no array.
	 * Atualiza os índices que o atecedem e os que o precedem.
	 */
	private void reorderArray() {
		for (int i = 0; i < this.rear; i++) {
			this.list[i] = this.list[i + 1];
		}
	}

	/**
	 * Reorganiza os elementos da lista após a remoção de um elemento de uma posição
	 * específica.
	 * Move cada elemento uma posição para a frente no array a partir da posição
	 * especificada.
	 * Atualiza os índices que o atecedem.
	 *
	 * @param afterPosition A posição após a qual a reorganização deve ocorrer.
	 */
	private void reorderArray(int afterPosition) {
		for (int i = afterPosition; i < this.rear; i++) {
			this.list[i] = this.list[i + 1];
		}
	}

	/**
	 * Verifica se a lista contém o elemento especificado.
	 *
	 * @param element O elemento a ser verificado na lista.
	 * @return true se a lista contiver o elemento, false caso contrário.
	 */
	protected boolean listContains(T element) {
		boolean contains = false;

		for (T el : this.list) {
			System.out.println(el);
			if (el.equals(element)) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	/**
	 * Aumenta a capacidade da lista, criando um novo array com tamanho incrementado
	 * e copiando os elementos existentes.
	 * Atualiza a referência do array da lista para o novo array com capacidade
	 * aumentada.
	 */
	protected void increaseListCapacity() {
		T[] tmpList = (T[]) (new Object[this.list.length + 1]);
		System.arraycopy(this.list, 0, tmpList, 0, rear);
		this.list = tmpList;
	}

	/**
	 * Classe interna que implementa a interface Iterator<T> para iteração sobre os
	 * elementos da lista.
	 *
	 * @param <T> O tipo de elemento da lista.
	 */
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
