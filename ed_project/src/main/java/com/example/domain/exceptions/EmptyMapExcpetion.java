package com.example.domain.exceptions;

/**
 * Exceção disparada quando se tenta iniciar uma nova partida sem que um mapa de
 * jogo tenha sido atribuído.
 * 
 * @author Luís Costa [8200737]
 * 
 */
public class EmptyMapExcpetion extends Exception {
	/**
	 * Construtor que define a mensagem padrão para a exceção.
	 */
	public EmptyMapExcpetion() {
		super("Ainda não foi carregado nenhum mapa de jogo para dar inicio à partida");
	}
}
