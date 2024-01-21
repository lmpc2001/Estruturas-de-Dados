package com.example.usecases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.EmptyListException;
import com.example.utils.TestScanners;

public class SetPlayerBotsStrategyUseCaseTest {

	@Test
	public void shouldSetGamePlayersCorrectly() throws EmptyListException, InvalidStrategyException {
		Game game = new Game();

		TestScanners scanner = new TestScanners();
		SetPlayerBotsStrategyUseCase setPlayerBotsStrategyUseCase = new SetPlayerBotsStrategyUseCase(game, scanner);

		Bot bot1 = new Bot("Bot 1");
		Bot bot2 = new Bot("Bot 2");

		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");

		player1.addBot(bot1);
		player2.addBot(bot2);

		game.addPlayer(player1);
		game.addPlayer(player2);

		scanner.setInputInt(1);
		setPlayerBotsStrategyUseCase.execute(player1);

		scanner.setInputInt(2);
		setPlayerBotsStrategyUseCase.execute(player2);

		assertEquals("Shortest_Path", player1.getPlayerBots().first().getStrategy().toString());
		assertEquals("Random", player2.getPlayerBots().first().getStrategy().toString());
	}

	@Test(expected = EmptyListException.class)
	public void shouldThrowAnExceptionIfGameDoesNotHaveAnyPlayer() throws EmptyListException, InvalidStrategyException {
		Game game = new Game();

		TestScanners scanner = new TestScanners();
		SetPlayerBotsStrategyUseCase setPlayerBotsStrategyUseCase = new SetPlayerBotsStrategyUseCase(game, scanner);

		Bot bot1 = new Bot("Bot 1");

		Player player1 = new Player("Player 1");

		player1.addBot(bot1);


		scanner.setInputInt(1);
		setPlayerBotsStrategyUseCase.execute(player1);
	}
}
