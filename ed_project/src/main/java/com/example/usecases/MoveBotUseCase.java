package com.example.usecases;

import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.queue.LinkedQueue;
import com.example.utils.Scanners;

public class MoveBotUseCase {
	public static void execute(Game game) throws EmptyListException {
		boolean play = true;
		Player playerToPlay;

		do {
			playerToPlay = game.getPlayerTurn();

			int linha = Scanners.getInputInt(
					"[" + playerToPlay.getPlayerName() + "]: Insira a linha para onde deseja mover o bot (Digite -1 para sair): ");
			int coluna = Scanners.getInputInt(
					"[" + playerToPlay.getPlayerName() + "]: Insira a linha para onde deseja mover o bot (Digite -1 para sair): ");

			if(linha == -1 || coluna == -1){
				break;
			}

			UnorderedList<Player> gamePlayers = game.getPlayers();

			for (Player player : gamePlayers) {

				LinkedQueue<Bot> bots = new LinkedQueue<>(player.getPlayerBots().first());

				System.out.println(bots.toString());

				do {
					int[] botCoordinates = bots.dequeue().getCurrentPosition();

					while (botCoordinates[0] == linha && botCoordinates[1] == coluna) {
						System.out.println("A posição escolhida já se encontra ocupada pelo seu adversário");
						linha = Scanners.getInputInt("[" + playerToPlay.getPlayerName() + "]: Insira a nova linha para onde deseja mover o bot: ");
						coluna = Scanners.getInputInt("[" + playerToPlay.getPlayerName() + "]: Insira a nova coluna para onde deseja mover o bot: ");
					}

				} while (!bots.isEmpty());
			}

			Bot botToMove = playerToPlay.getBotToPlay();

			int[] newCoordinates = { linha, coluna };

			botToMove.setCurrentPosition(newCoordinates);

			play = !game.checkWin(botToMove);
		} while (play);

		System.out.println("Parabéns " + playerToPlay.getPlayerName() + "! Conseguiste capturar a bandeira do teu adversário!");
	}
}
