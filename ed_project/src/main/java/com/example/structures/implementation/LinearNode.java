package com.example.structures.implementation;

/**
 * A classe LinearNode<T> representa um nó numa estrutura de dados ligada, como
 * uma LinkedQueue ou uma LinkedStack.
 * Pode armazenar um elemento de tipo genérico <T> e possui uma referência para
 * o próximo nó na sequência.
 * 
 * Esta classe fornece construtores para criar um nó vazio ou um nó com um
 * elemento especificado.
 * 
 * Os métodos getNext() e setNext() permitem aceder e modificar a referência
 * para o próximo nó.
 * Os métodos getElement() e setElement() permitem acessar e modificar o
 * elemento armazenado no nó.
 * 
 * @author Luís Costa [8200737]
 * @param <T> O tipo de elemento que o nó pode armazenar.
 * 
 */
public class LinearNode<T> {
	private LinearNode<T> next;
	private T element;

	/**
	 * Cria um nó vazio.
	 */
	public LinearNode() {
		this.next = null;
		this.element = null;
	}

	/**
	 * Cria um nó com um elemento especificado.
	 *
	 * @param element O elemento a ser armazenado no nó.
	 */
	public LinearNode(T element) {
		this.next = null;
		this.element = element;
	}

	/**
	 * Obtém o próximo nó na lista.
	 *
	 * @return O próximo nó na list.
	 */
	public LinearNode<T> getNext() {
		return next;
	}

	/**
	 * Define o próximo nó na lista.
	 *
	 * @param next O próximo nó a ser adicionado à lista.
	 */
	public void setNext(LinearNode<T> next) {
		this.next = next;
	}

	/**
	 * Obtém o elemento armazenado no nó.
	 *
	 * @return O elemento armazenado no nó.
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Define o elemento a ser armazenado no nó.
	 *
	 * @param element O elemento a ser configurado.
	 */
	public void setElement(T element) {
		this.element = element;
	}

}
