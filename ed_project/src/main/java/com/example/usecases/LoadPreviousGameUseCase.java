package com.example.usecases;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.JSON;

public class LoadPreviousGameUseCase {
	private Game resumeGame;

	public LoadPreviousGameUseCase(Game game) {
		this.resumeGame = game;
	}

	public void execute() throws IOException, ParseException, ElementNotFoundException {
		GameMap map = JSON.loadMap();
		UnorderedList<Player> players = JSON.loadPlayers();

		resumeGame.setGameMap(map);

		for (Player player : players) {
			resumeGame.addPlayer(player);
		}
	}
}
