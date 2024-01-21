package com.example.usecases;

import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.structures.implementation.network.exceptions.InvalidValueException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.ScannersADT;

/**
 * A classe MoveBotUseCase é responsável por executar as operações relacionadas
 * com o movimento dos bots no jogo.
 * Faz parte dos casos de uso no domínio da aplicação, tratando especificamente
 * da movimentação estratégica dos bots pelos jogadores.
 * 
 * 
 * Esta classe requer uma instância da classe Game para funcionar corretamente.
 * O método principal, execute(),
 * guia o processo de movimentação dos bots durante o jogo, interagindo com os
 * jogadores para definir novas posições.
 * 
 * 
 * 
 * 
 * O método execute() solicita ao jogador a posição desejada para onde mover o
 * bot, evitando posições já ocupadas pelos adversários ou pelos seus bots
 * restantes.
 * O loop continua até que um jogador capture a bandeira do adversário, momento
 * em que a vitória é declarada.
 * 
 *
 * 
 * @author Luís Costa [8200737]
 * @see com.example.domain.Bot
 * @see com.example.domain.Game
 * @see com.example.domain.Player
 * @see com.example.utils.ScannersADT
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.list.UnorderedList
 * @see com.example.structures.implementation.queue.LinkedQueue
 * 
 */
public class MoveBotUseCase {
	private Game game;
	private ScannersADT scanner;

	/**
	 * Constrói uma nova instância de {@code MoveBotUseCase}.
	 *
	 * @param game    O jogo no qual os bots serão movidos.
	 * @param scanner Libraria a utilizar para interagir com o
	 *                utilizador
	 */
	public MoveBotUseCase(Game game, ScannersADT scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	/**
	 * Executa o processo de movimentação dos bots no jogo.
	 *
	 * @throws EmptyListException    Se a queue de bots estiver vazia.
	 * @throws InvalidValueException Se o valor inserido for inválido enquanto
	 *                               index da lista
	 * @throws EmptyMapException     Se o mapa para o jogo não estiver definido
	 * 
	 */
	public void execute() throws EmptyListException, InvalidValueException, EmptyMapException {
		boolean play = true;
		Player playerToPlay;

		do {
			playerToPlay = game.getPlayerTurn();

			int newLocation = scanner.getInputInt(
					"[" + playerToPlay.getPlayerName()
							+ "]: Escolha o vértice para onde deseja mover o bot (Digite -1 para sair): ");

			if (newLocation == -1) {
				break;
			}

			UnorderedList<Player> gamePlayers = game.getPlayers();

			while (game.getGameMap().isPositionOccupied(newLocation, gamePlayers)) {
				System.out.println("A posição escolhida já se encontra ocupada pelo seu adversário");
				newLocation = scanner.getInputInt(
						"[" + playerToPlay.getPlayerName() + "]: Escolha o vértice para onde deseja mover o bot: ");
			}

			Bot botToMove = playerToPlay.getBotToPlay();

			// switch (botToMove.getStrategy().toString()) {
			// case "Shortest_Path":
			// int nextPosition;

			// do {
			// nextPosition = Scanners.getInputInt("[" + playerToPlay.getPlayerName()
			// + "]: Insira a nova linha para onde deseja mover o bot: ");
			// } while (game.getGameMap().isValidPosition(nextPosition));

			// botToMove.moveByShortestPath(game.getGameMap(), newLocation);
			// break;
			// case "Random":
			// botToMove.moveRandomly(game.getGameMap());
			// break;
			// case "Objective_Weighted":
			// botToMove.moveRandomly(game.getGameMap());
			// break;
			// }

			botToMove.setCurrentPosition(newLocation);

			play = !game.checkWin(botToMove);
		} while (play);

		System.out.println(
				"Parabéns " + playerToPlay.getPlayerName() + "! Conseguiste capturar a bandeira do teu adversário!");
	}
}
