package com.example.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.TestScanners;

public class SetFlagLocationUseCaseTest {
	
	@Test
	public void shouldSetPlayerFlagCorrectly() throws ElementNotFoundException, EmptyMapException {
		Game game = new Game();
		GameMap map = new GameMap(5, 0);

		map.addVertex(1); // index 0
		map.addVertex(2); // index 1
		map.addVertex(3); // index 2
		map.addVertex(4); // index 3
		map.addVertex(5); // index 4

		map.addEdge(1, 2, 5.0);
		map.addEdge(2, 4, 13.0);
		map.addEdge(3, 1, 1.0);
		map.addEdge(3, 4, 6.0);
		map.addEdge(2, 5, 2.0);
		map.addEdge(1, 5, 14.0);

		game.setGameMap(map);

		TestScanners scanner = new TestScanners();
		SetFlagLocationUseCase setFlagLocationUseCase = new SetFlagLocationUseCase(game, scanner);
		Player player1 = new Player("Player 1");

		scanner.setInputInt(4);
		setFlagLocationUseCase.execute(player1);

		assertNotNull(player1.getFlag());
		assertEquals(3, player1.getFlag());
	}
	
	@Test(expected = EmptyMapException.class)
	public void shouldThrowAnExceptionIfTheMapIsNotSet() throws ElementNotFoundException, EmptyMapException {
		Game game = new Game();

		TestScanners scanner = new TestScanners();
		SetFlagLocationUseCase setFlagLocationUseCase = new SetFlagLocationUseCase(game, scanner);
		Player player1 = new Player("Player 1");

		scanner.setInputInt(5);
		setFlagLocationUseCase.execute(player1);
	}
}
