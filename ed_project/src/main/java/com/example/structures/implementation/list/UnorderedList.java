package com.example.structures.implementation.list;

import com.example.structures.adt.UnorderListADT;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.network.exceptions.InvalidValueException;

/**
 * A classe UnorderedList<T> faz extende à classe ArrayList<T> e implementa a
 * interface UnorderListADT<T> herdando os métodos base de interação com uma
 * lista da classe ArrayList<T> e especifica os métodos de adição de novos
 * elementos com base nos métodos definidos pela interface UnorderListADT<T>
 * 
 * Pode armazenar elementos de tipo genérico T e fornece as operações
 * tradicionais de uma lista não ordenada, como adição no inicio, adição no fim,
 * adição após um elemento específico bem como as restantes operações
 * tradicionais de uma lista.
 * 
 * @author Luís Costa [8200737]
 * @param <T> O tipo de elemento que a lista pode armazenar.
 * @see com.example.structures.adt.UnorderListADT
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.exceptions.ElementNotFoundException
 * @see com.example.structures.implementation.network.exceptions.InvalidValueException;
 * 
 */
public class UnorderedList<T> extends ArrayList<T> implements UnorderListADT<T> {

	/**
	 * Cria uma nova lista não ordenada com o tamanho padrão
	 * 
	 */
	public UnorderedList() {
		super();
	}

	/**
	 * Cria uma nova lista não ordenada com um tamanho especificado
	 * 
	 * @param size tamanho da lista
	 */
	public UnorderedList(int size) {
		super(size);
	}

	/**
	 * Adiciona o elemento especificado ao início da lista.
	 * Se a lista estiver cheia, aumenta a capacidade antes de adicionar o elemento.
	 *
	 * @param element O elemento a ser adicionado ao início da lista.
	 */
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

	/**
	 * Adiciona o elemento especificado ao final da lista.
	 * Se a lista estiver cheia, aumenta a capacidade antes de adicionar o elemento.
	 *
	 * @param element O elemento a ser adicionado ao final da lista.
	 */
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

	/**
	 * Adiciona o elemento especificado após o elemento identificado na lista.
	 * Se a lista estiver cheia, aumenta a capacidade antes de adicionar o elemento.
	 *
	 * @param element O elemento a ser adicionado na lista.
	 * @param target  O elemento após o qual o novo elemento será adicionado.
	 * @throws EmptyListException       Se a lista estiver vazia.
	 * @throws ElementNotFoundException Se o elemento especificado não for
	 *                                  encontrado na
	 *                                  lista.
	 */
	@Override
	public void addAfter(T element, T target) throws EmptyListException, ElementNotFoundException {
		if (isEmpty()) {
			throw new EmptyListException();
		}
		if (!listContains(target)) {
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

	/**
	 * Procura e retorna um elemento especifico da lista
	 *
	 * @return elemento encontrado
	 * @throws EmptyListException       se a lista estiver vazia
	 * @throws ElementNotFoundException se o elemento a procurar não existir na
	 *                                  lista
	 */
	@Override
	public T getElement(T element) throws EmptyListException, ElementNotFoundException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		for (T t : list) {
			if (element.equals(t)) {
				return t;
			}
		}

		throw new ElementNotFoundException();
	}

	/**
	 * Procura e retorna um elemento especifico da lista
	 *
	 * @param elementIndex o index do elemento a retornar
	 * @return elemento encontrado
	 * @throws EmptyListException    se a lista estiver vazia
	 * @throws InvalidValueException se o index fornecido for inválido
	 */
	public T getElement(int elementIndex) throws EmptyListException, InvalidValueException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		if (elementIndex < 0 || elementIndex > size()) {
			throw new InvalidValueException("O index " + elementIndex + " é inválido para esta lista");
		}

		return this.list[elementIndex];
	}

	/**
	 * Retorna uma representação da lista na forma de String.
	 *
	 * @return Uma String que representa a lista no formato [elemento1, elemento2,
	 *         ...]
	 */
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
