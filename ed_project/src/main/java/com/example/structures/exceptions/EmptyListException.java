package com.example.structures.exceptions;

public class EmptyListException extends Exception {
	public EmptyListException(String message) {
		super("Erro: " + message);
	}
}
