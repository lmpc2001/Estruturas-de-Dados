package com.example.structures.exceptions;

/**
 * Exceção disparada quando é realizada uma operação com uma lista vazia.
 * 
 * @author Luís Costa [8200737]
 * 
 */
public class EmptyListException extends Exception {
	/**
	 * Construtor que define a mensagem padrão para a exceção.
	 */
	public EmptyListException() {
		super("Esta lista está vazia!");
	}

	/**
	 * Construtor adicional que permite especificar uma mensagem personalizada para
	 * a exceção.
	 *
	 * @param message Mensagem personalizada a ser exibida na exceção.
	 */
	public EmptyListException(String message) {
		super(message + "\nEsta lista está vazia!");
	}
}
