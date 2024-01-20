package com.example.structures.implementation.network.exceptions;

public class InvalidValueException extends IllegalArgumentException{
	public InvalidValueException() {
		super("O valor que forneceu é inválido para este caso");
	}

	public InvalidValueException(String message) {
		super(message);
	}
}
