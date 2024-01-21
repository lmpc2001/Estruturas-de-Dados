package com.example.structures.implementation.network.exceptions;

/**
 * Exceção disparada sempre que é encontrado um valor inválido
 * 
 * @author Luís Costa [8200737]
 * 
 */
public class InvalidValueException extends IllegalArgumentException {

	/**
	 * Construtor que define a mensagem padrão para a exceção.
	 */
	public InvalidValueException() {
		super("O valor que forneceu é inválido para este caso");
	}

	/**
	 * Construtor adicional que permite a personalização da mensagem a apresentar
	 *
	 * @param message Mensagem personalizada a ser exibida na exceção.
	 */
	public InvalidValueException(String message) {
		super(message);
	}
}
