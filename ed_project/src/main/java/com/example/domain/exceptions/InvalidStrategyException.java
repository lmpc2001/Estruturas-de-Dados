package com.example.domain.exceptions;

public class InvalidStrategyException extends Exception {
	public InvalidStrategyException() {
		super("A estratégia que escolheu não pode ser associada a este bot");
	}

	public InvalidStrategyException(String strategy) {
		super("A estratégia " + strategy + " não existe!");
	}
}
