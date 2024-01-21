package com.example.usecases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.TestScanners;

public class SetPlayersUseCaseTest {

	@Test
	public void shouldSetGamePlayersCorrectly()
			throws EmptyMapException, EmptyListException, InvalidStrategyException, ElementNotFoundException {

		Game game = new Game();
		GameMap map = new GameMap(4, 0.5);

		map.addVertex(1); // index 0
		map.addVertex(2); // index 1
		map.addVertex(3); // index 2
		map.addVertex(4); // index 3

		map.addEdge(1, 2, 2.0);
		map.addEdge(3, 1, 5.0);
		map.addEdge(1, 3, 5.0);
		map.addEdge(4, 2, 1.0);

		game.setGameMap(map);

		TestScanners scanner = new TestScanners();
		SetPlayerBotsUseCase setPlayerBotsUseCase = new SetPlayerBotsUseCase(scanner);
		SetFlagLocationUseCase setFlagLocationUseCase = new SetFlagLocationUseCase(game, scanner);
		SetPlayerBotsStrategyUseCase setPlayerBotsStrategyUseCase = new SetPlayerBotsStrategyUseCase(game, scanner);
		SetPlayersUseCase setPlayersUseCase = new SetPlayersUseCase(game, setFlagLocationUseCase, setPlayerBotsUseCase,
				setPlayerBotsStrategyUseCase, scanner);

		assertEquals(0, game.getPlayers().size());

		scanner.setInputInt(2); // Nº de bots por jogador

		/************************* Player 1 **************************/
		scanner.setInputString("Player 1"); 	// Nome do jogador
		scanner.setInputInt(2); 				// Posição onde deseja guardar a bandeira
		scanner.setInputString("Bot 1"); 		// Nome do bot
		scanner.setInputString("Bot 2"); 		// Nome do bot
		scanner.setInputInt(2); 				// estratégia a adotar pelo bot 2 -- Player 1
		scanner.setInputInt(3); 				// estratégia a adotar pelo bot 3 -- Player 1
		
		// /************************* Player 2 **************************/
		scanner.setInputString("Player 2"); 	// Nome do jogador
		scanner.setInputInt(1); 				// Posição onde deseja guardar a bandeira
		scanner.setInputString("Bot 3"); 		// Nome do bot
		scanner.setInputString("Bot 4"); 		// Nome do bot
		scanner.setInputInt(1); 				// estratégia a adotar pelo bot 1 -- Player 2
		scanner.setInputInt(3); 				// estratégia a adotar pelo bot 4 -- -- Player 2
		
		setPlayersUseCase.execute();

		assertEquals(2, game.getPlayers().size());

		Player player1 = game.getPlayers().first();
		Player player2 = game.getPlayers().last();

		assertEquals("Player 1", player1.getPlayerName());
		assertEquals("Player 2", player2.getPlayerName());

		assertEquals(1, player1.getFlag());
		assertEquals(0, player2.getFlag());

		assertEquals(2, player1.getPlayerBots().size());
		assertEquals(2, player2.getPlayerBots().size());

		assertEquals("Bot 1", player1.getPlayerBots().first().getBotName());
		assertEquals("Random", player1.getPlayerBots().dequeue().getStrategy().toString());

		assertEquals("Bot 2", player1.getPlayerBots().first().getBotName());
		assertEquals("Avoid_Obstacles", player1.getPlayerBots().dequeue().getStrategy().toString());

		assertEquals("Bot 3", player2.getPlayerBots().first().getBotName());
		assertEquals("Shortest_Path" , player2.getPlayerBots().dequeue().getStrategy().toString());

		assertEquals("Bot 4", player2.getPlayerBots().first().getBotName());
		assertEquals("Avoid_Obstacles", player2.getPlayerBots().dequeue().getStrategy().toString());
		
		UnorderedList<Player> players = new UnorderedList<>();
		players.addToRear(player1);
		players.addToRear(player2);

		assertEquals(game.getPlayers().toString(), players.toString());
	}
}
