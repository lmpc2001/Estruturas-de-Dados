package com.example.structures.exceptions;

public class ElementNotFoundException extends Exception {
	public ElementNotFoundException(String element) {
		super("O elemento " + element + " não se encontra nesta lista");
	}

	public ElementNotFoundException() {
		super("The element that you are looking for does not exist on this list");
	}
}
