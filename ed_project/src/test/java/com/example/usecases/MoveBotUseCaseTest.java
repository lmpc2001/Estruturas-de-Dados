package com.example.usecases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.network.exceptions.InvalidValueException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.TestScanners;

public class MoveBotUseCaseTest {

	@Test
	public void shouldMoveBotToTheRightPosition() throws ElementNotFoundException, InvalidValueException, EmptyListException, EmptyMapException {
		TestScanners scanner = new TestScanners();

		Player player1 = new Player("Player 1");
		Bot bot1 = new Bot("Bot 1");

		Player player2 = new Player("Player 2");
		Bot bot2 = new Bot("Bot 2");

		
		Game game = new Game();
		GameMap map = new GameMap(4, 0.5);

		map.addVertex(1);
		map.addVertex(2);
		map.addVertex(3);
		map.addVertex(4);
		
		map.addEdge(1, 2, 2.0);
		map.addEdge(3, 1, 5.0);
		map.addEdge(1, 3, 5.0);
		map.addEdge(4, 2, 1.0);
		
		game.setGameMap(map);
		
		player1.setFlag(1);
		bot1.setCurrentPosition(1);
		player1.addBot(bot1);
		
		player2.setFlag(3);
		bot2.setCurrentPosition(3);
		player2.addBot(bot2);
		
		game.addPlayer(player1);
		game.addPlayer(player2);

		MoveBotUseCase moveBotUseCase = new MoveBotUseCase(game, scanner);

		scanner.setInputInt(0);
		moveBotUseCase.execute();

		assertEquals(0, player1.getPlayerBots().first().getCurrentPosition());
	}
}
