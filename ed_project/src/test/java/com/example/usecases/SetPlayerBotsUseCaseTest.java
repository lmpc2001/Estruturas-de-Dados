package com.example.usecases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.domain.Bot;
import com.example.domain.Player;
import com.example.structures.exceptions.EmptyListException;
import com.example.utils.TestScanners;

public class SetPlayerBotsUseCaseTest {

	@Test
	public void shouldSetGamePlayersCorrectly() throws EmptyListException {
		TestScanners scanner = new TestScanners();
		SetPlayerBotsUseCase setPlayerBotsUseCase = new SetPlayerBotsUseCase(scanner);

		Player player1 = new Player("Novo Jogador 1");

		player1.setFlag(2);

		assertEquals(0, player1.getPlayerBots().size());

		scanner.setInputString("Bot do player 1"); //Nome do bot para o jogador 1
		scanner.setInputString("Bot 2 do player 1"); //Nome do bot para o jogador 1
		setPlayerBotsUseCase.execute(player1, 2);

		assertEquals(2, player1.getPlayerBots().size());
		
		Bot bot1 = player1.getPlayerBots().dequeue();
		Bot bot2 = player1.getPlayerBots().dequeue();
		
		assertEquals("Bot do player 1", bot1.getBotName());
		assertEquals("Bot 2 do player 1", bot2.getBotName());

		assertEquals(2, bot1.getCurrentPosition());
		assertEquals(2, bot2.getCurrentPosition());
	}
}
