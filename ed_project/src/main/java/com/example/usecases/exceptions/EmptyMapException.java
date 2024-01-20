package com.example.usecases.exceptions;

/**
 * Exceção disparada quando uma operação é realizada num
 * mapa que ainda não possui vértices definidos.
 * 
 * @author Luís Costa [8200737]
 * 
 */
public class EmptyMapException extends Exception {
	/**
	 * Construtor que define a mensagem padrão para a exceção.
	 */
	public EmptyMapException() {
		super("O mapa ainda não tem posições definidas!\nPor favor crie o mapa antes de posicionar a bandeira");
	}
}
