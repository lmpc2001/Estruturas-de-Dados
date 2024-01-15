package com.example.usecases;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Scanners;

public class StartGameUseCase {
	private Game game;
	private GameMap map;

	public StartGameUseCase() {
		this.game = null;
		this.map = null;
	}

	public Game execute() throws ElementNotFoundException, EmptyMapException, EmptyListException {
		map = GenerateMapUseCase.execute();
		game = new Game(map);

		map.seeMap();

		int numberOfPlayerBots = Scanners.getInputInt("| Nº de bots por jogador: ");

		String namePlayer1 = Scanners.getInputString("| Nome do jogador 1: ");
		Player player1 = new Player(namePlayer1);
		SetFlagLocationUseCase.execute(map, player1);

		String namePlayer2 = Scanners.getInputString("| Nome do jogador 2: ");
		Player player2 = new Player(namePlayer2);
		SetFlagLocationUseCase.execute(map, player2);

		SetPlayerBotsUseCase.execute(player1, numberOfPlayerBots);
		SetPlayerBotsUseCase.execute(player2, numberOfPlayerBots);

		game.addPlayer(player1);
		game.addPlayer(player2);

		map.seeMap();

		GenerateKickOffPlayerUseCase.execute(game);
		
		MoveBotUseCase.execute(game);
		return game;	
	}
}
