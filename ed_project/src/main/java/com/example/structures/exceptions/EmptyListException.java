package com.example.structures.exceptions;

public class EmptyListException extends Exception {
	public EmptyListException() {
		super("Esta lista está vazia!");
	}

	public EmptyListException(String message) {
		super(message + "\nEsta lista está vazia!");
	}
}
