package com.example.structures.exceptions;

/**
 * Exceção disparada quando não é encontrado um determinado valor no conjunto de
 * opções.
 * 
 * @author Luís Costa [8200737]
 * 
 */
public class ElementNotFoundException extends Exception {

	/**
	 * Construtor adicional que permite especificar o elemento a procurar e
	 * que não foi encontrado na estrutura em questão.
	 *
	 * @param element o elemento que se tentou procurar.
	 */
	public ElementNotFoundException(String element) {
		super("O elemento " + element + " não se encontra nesta lista");
	}

	/**
	 * Construtor que define a mensagem padrão para a exceção.
	 */
	public ElementNotFoundException() {
		super("The element that you are looking for does not exist on this list");
	}
}
