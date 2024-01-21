package com.example.usecases;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.network.exceptions.InvalidValueException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.JSON;

public class LoadPreviousGameUseCaseTest {
	private Game game = new Game();
	private GameMap map = new GameMap(4, 0.5);
	private Player player1 = new Player("Player 1");
	private Player player2 = new Player("Player 2");
	private Bot bot1 = new Bot("Bot 1");
	private Bot bot2 = new Bot("Bot 2");

	@Before
	public void saveMap() throws ElementNotFoundException, IOException, EmptyMapException, EmptyListException {
		this.map.addVertex(1);
		this.map.addVertex(2);
		this.map.addVertex(3);
		this.map.addVertex(4);

		this.map.addEdge(1, 2, 2.0);
		this.map.addEdge(3, 1, 5.0);
		this.map.addEdge(1, 3, 5.0);
		this.map.addEdge(4, 2, 1.0);

		this.player1.setFlag(1);
		this.player2.setFlag(2);

		bot1.setCurrentPosition(1);
		bot2.setCurrentPosition(2);

		bot1.setStrategy(Bot.Strategy.Random);
		bot2.setStrategy(Bot.Strategy.Shortest_Path);

		this.player1.addBot(bot1);
		this.player2.addBot(bot2);

		this.game.setGameMap(map);
		this.game.addPlayer(player1);
		this.game.addPlayer(player2);

		JSON.saveGame(game);
	}

	@Test
	public void shouldLoadPreviousGameCorrectly() throws IOException, ParseException, ElementNotFoundException,
			InvalidStrategyException, EmptyMapException, InvalidValueException, EmptyListException {
		Game newLoadedGame = new Game();

		LoadPreviousGameUseCase loadPreviousGameUseCase = new LoadPreviousGameUseCase(newLoadedGame);

		loadPreviousGameUseCase.execute();

		this.player1.addBot(bot1);
		this.player2.addBot(bot2);

		assertEquals(game.getGameMap().getNumberOfLocations(), newLoadedGame.getGameMap().getNumberOfLocations());

		assertArrayEquals(game.getGameMap().downloadAdjencyMatrix(),
				newLoadedGame.getGameMap().downloadAdjencyMatrix());
		assertArrayEquals(game.getGameMap().downloadAdjencyMatrixWithWeights(),
				newLoadedGame.getGameMap().downloadAdjencyMatrixWithWeights());

		assertEquals(this.game.getPlayers().toString(), newLoadedGame.getPlayers().toString());
		assertEquals(this.game.getPlayers().toString(), newLoadedGame.getPlayers().toString());
		assertEquals(this.game.getPlayers().toString(), newLoadedGame.getPlayers().toString());
	}
}
