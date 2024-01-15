package com.example.domain.exceptions;

public class EmptyMapExcpetion extends Exception{
	public EmptyMapExcpetion() {
		super("Ainda não foi carregado nenhum mapa de jogo para dar inicio à partida");
	}
}
