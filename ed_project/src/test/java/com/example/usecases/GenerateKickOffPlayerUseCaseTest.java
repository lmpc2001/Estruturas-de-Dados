package com.example.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.TestRandomness;

public class GenerateKickOffPlayerUseCaseTest {
	@Test
	public void shouldSelectRandomPlayerAndMoveToTheFront() throws EmptyListException {
		Game game = new Game();
		TestRandomness randomLib = new TestRandomness();

		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		
		game.addPlayer(player1);
		game.addPlayer(player2);

		GenerateKickOffPlayerUseCase generateKickOffPlayerUseCase = new GenerateKickOffPlayerUseCase(game, randomLib);
		generateKickOffPlayerUseCase.execute();

		UnorderedList<Player> players = game.getPlayers();
		assertEquals("Player2 should be moved to the front", player2, players.first());
		assertNotEquals("Player1 should not be at the front", player1, players.first());
	}
}
