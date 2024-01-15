package com.example.usecases;

import com.example.configs.Properties;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.Randomness;

public class GenerateKickOffPlayerUseCase {

	public static void execute(Game game) throws EmptyListException {
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
