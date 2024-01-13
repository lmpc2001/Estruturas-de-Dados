package com.example.structures.exceptions;

public class ElementNotFoundException extends Exception {
	public ElementNotFoundException() {
		super("The element that you are looking for does not exist on this list");
	}
}
