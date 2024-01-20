package com.example.domain.exceptions;

/**
 * Exceção disparada quando é escolhida uma estratégia inválida para um bot..
 * 
 * @author Luís Costa [8200737]
 * 
 */
public class InvalidStrategyException extends Exception {

	/**
	 * Construtor que define a mensagem padrão para a exceção.
	 */
	public InvalidStrategyException() {
		super("A estratégia que escolheu não pode ser associada a este bot");
	}

	/**
	 * Construtor adicional que permite especificar a estratégia carregada e
	 * informar
	 * que a mesma não existe.
	 *
	 * @param strategy A estratégia que se tentou associar ao bot.
	 */
	public InvalidStrategyException(String strategy) {
		super("A estratégia " + strategy + " não existe!");
	}
}
