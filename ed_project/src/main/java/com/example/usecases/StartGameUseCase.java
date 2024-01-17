package com.example.usecases;

import com.example.domain.Game;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.exceptions.EmptyMapException;

public class StartGameUseCase {
	private Game game;
	private GenerateKickOffPlayerUseCase generateKickOffPlayerUseCase;
	private MoveBotUseCase moveBotUseCase;

	public StartGameUseCase(Game game,
			GenerateKickOffPlayerUseCase generateKickOffPlayerUseCase,
			MoveBotUseCase moveBotUseCase) {
		this.game = game;
		this.generateKickOffPlayerUseCase = generateKickOffPlayerUseCase;
		this.moveBotUseCase = moveBotUseCase;
	}

	public void execute() throws EmptyMapException, EmptyListException {
		this.game.getGameMap().seeMap();

		this.generateKickOffPlayerUseCase.execute();

		moveBotUseCase.execute();
	}
}
