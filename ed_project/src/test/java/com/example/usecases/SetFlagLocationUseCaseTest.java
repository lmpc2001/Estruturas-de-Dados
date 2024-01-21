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

		map.addVertex("A");  // index: 0
		map.addVertex("B");  // index: 1
		map.addVertex("C");  // index: 2
		map.addVertex("D");  // index: 3
		map.addVertex("E");  // index: 4

		map.addEdge("A", "B", 5.0);
		map.addEdge("B", "D", 13.0);
		map.addEdge("C", "A", 1.0);
		map.addEdge("C", "D", 6.0);
		map.addEdge("B", "E", 2.0);
		map.addEdge("A", "E", 14.0);

		game.setGameMap(map);

		TestScanners scanner = new TestScanners();
		SetFlagLocationUseCase setFlagLocationUseCase = new SetFlagLocationUseCase(game, scanner);
		Player player1 = new Player("Player 1");

		scanner.setInputInt(4);
		setFlagLocationUseCase.execute(player1);

		assertNotNull(player1.getFlag());
		assertEquals(4, player1.getFlag());
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
