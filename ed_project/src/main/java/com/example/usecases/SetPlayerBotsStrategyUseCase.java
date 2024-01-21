package com.example.usecases;

import com.example.configs.Properties;
import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.queue.LinkedQueue;
import com.example.utils.ScannersADT;

/**
 * Classe responsável por definir a estratégia de movimentação para os bots de
 * um jogador.
 * A estratégia é escolhida interativamente para cada bot.
 *
 * @author Luís Costa [8200737]
 * @see com.example.domain.Bot
 * @see com.example.domain.Game
 * @see com.example.domain.Player
 * @see com.example.domain.exceptions.InvalidStrategyException
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.queue.LinkedQueue
 * @see com.example.utils.ScannersADT
 */
public class SetPlayerBotsStrategyUseCase {
	private Game game;
	private ScannersADT scanner;

	/**
	 * Construtor da classe SetPlayerBotsStrategyUseCase.
	 * 
	 * @param game    o jogo do qual serão carregados os jogadores para definição
	 *                das estratégias dos bots
	 * @param scanner Libraria a utilizar para interagir com o
	 *                utilizador
	 */
	public SetPlayerBotsStrategyUseCase(Game game, ScannersADT scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	/**
	 * Executa o caso de uso responsável por definir a estratégia de movimentação
	 * para os bots
	 * de um jogador.
	 *
	 * @param player O jogador para o qual a estratégia dos bots será definida.
	 * @throws EmptyListException Se a lista de bots do jogador estiver vazia.
	 * 
	 */
	public void execute(Player player) throws EmptyListException {
		LinkedQueue<Bot> playerBots = player.getPlayerBots().copyLinkedQueue();

		do {
			Bot bot = playerBots.dequeue();

			int botStrategy = scanner
					.getInputInt("Escolha a estratégia a adotar pelo bot " + bot.getBotName()
							+ " [1- Shortest_Path, 2- Random, 3- Avoid_Obstacles]: ");

			while (!isStrategyValid(botStrategy - 1)) {
				System.out.println("A estratégia selecionada já foi adotada por outro bot!");
				botStrategy = scanner
						.getInputInt("Escolha a estratégia a adotar pelo bot " + bot.getBotName()
								+ " [1- Shortest_Path, 2- Random, 3- Avoid_Obstacles]: ");
			}

			bot.setStrategy(Bot.Strategy.values()[botStrategy - 1]);

		} while (!playerBots.isEmpty());
	}

	/**
	 * Verifica se a estratégia escolhida pelo utilizador pode ser atribuída ao bot
	 *
	 * @param strategyIndex O índice da estratégia a ser verificada.
	 * @return true se a estratégia for válida para atribuíção, false caso
	 *         contrário.
	 * @throws EmptyListException Se a lista estiver vazia.
	 */
	private boolean isStrategyValid(int strategyIndex) throws EmptyListException {
		if (strategyIndex < 0 || strategyIndex > Bot.Strategy.values().length - 1) {
			return false;
		}

		if (this.game.getPlayers().first().getPlayerBots().size()
				* Properties.MAX_PLAYERS > Bot.Strategy.values().length - 1) {
			return true;
		}

		for (Player playerInGame : this.game.getPlayers()) {
			LinkedQueue<Bot> botsWithStrategyToVerify = playerInGame.getPlayerBots().copyLinkedQueue();

			do {
				if (botsWithStrategyToVerify.dequeue().getStrategy() == Bot.Strategy.values()[strategyIndex]) {
					return false;
				}
			} while (!botsWithStrategyToVerify.isEmpty());
		}

		return true;
	}
}
