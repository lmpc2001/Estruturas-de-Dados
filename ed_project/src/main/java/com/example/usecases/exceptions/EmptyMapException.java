package com.example.usecases.exceptions;

public class EmptyMapException extends Exception {
	public EmptyMapException() {
		super("O mapa ainda não tem posições definidas!\nPor favor crie o mapa antes de posicionar a bandeira");
	}
}
