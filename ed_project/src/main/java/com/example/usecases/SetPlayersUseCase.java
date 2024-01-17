package com.example.usecases;

import com.example.configs.Properties;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Scanners;

public class SetPlayersUseCase {
	private Game game;
	private SetFlagLocationUseCase setFlagLocationUseCase;
	private SetPlayerBotsUseCase setPlayerBotsUseCase;

	public SetPlayersUseCase(Game game,
			SetFlagLocationUseCase setFlagLocationUseCase,
			SetPlayerBotsUseCase setPlayerBotsUseCase) {
		this.game = game;
		this.setFlagLocationUseCase = setFlagLocationUseCase;
		this.setPlayerBotsUseCase = setPlayerBotsUseCase;
	}

	public void execute() throws EmptyMapException {
		int numberOfPlayerBots = Scanners.getInputInt("| Nº de bots por jogador: ");

		for (int i = 0; i < Properties.MAX_PLAYERS; i++) {
			String namePlayer = Scanners.getInputString("| Nome do jogador " + i + 1 + ": ");
			Player player = new Player(namePlayer);

			this.setFlagLocationUseCase.execute(player);
			this.setPlayerBotsUseCase.execute(player, numberOfPlayerBots);

			game.addPlayer(player);
		}
	}
}
