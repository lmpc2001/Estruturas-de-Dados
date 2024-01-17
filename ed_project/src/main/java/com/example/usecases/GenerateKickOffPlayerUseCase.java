package com.example.usecases;

import com.example.configs.Properties;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.Randomness;

public class GenerateKickOffPlayerUseCase {
	private Game game;

	public GenerateKickOffPlayerUseCase(Game game) {
		this.game = game;
	}

	public void execute() {
		UnorderedList<Player> gamePlayers = game.getPlayers();
		UnorderedList<Player> orderedPlayers = new UnorderedList<>(Properties.MAX_PLAYERS);

		int playerIndex = Randomness.getRandomNumber(0, Properties.MAX_PLAYERS);
		int count = 0;


		for (Player player : gamePlayers) {
			if (count == playerIndex) {
				orderedPlayers.addToFront(player);
			} else {
				orderedPlayers.addToRear(player);
			}
			count++;
		}

		game.setPlayers(orderedPlayers);
	}
}
