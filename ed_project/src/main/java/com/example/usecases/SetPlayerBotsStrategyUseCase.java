package com.example.usecases;

import com.example.configs.Properties;
import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.queue.LinkedQueue;
import com.example.utils.Scanners;

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
 * @see com.example.utils.Scanners
 */
public class SetPlayerBotsStrategyUseCase {
	private Game game;

	/**
	 * Construtor da classe SetPlayerBotsStrategyUseCase.
	 */
	public SetPlayerBotsStrategyUseCase(Game game) {

	}

	/**
	 * Executa o caso de uso responsável por definir a estratégia de movimentação
	 * para os bots
	 * de um jogador.
	 *
	 * @param player O jogador para o qual a estratégia dos bots será definida.
	 * @throws EmptyListException       Se a lista de bots do jogador estiver vazia.
	 * @throws InvalidStrategyException Se a estratégia escolhida for inválida.
	 */
	public void execute(Player player) throws EmptyListException, InvalidStrategyException {
		LinkedQueue<Bot> playerBots = new LinkedQueue<>(player.getPlayerBots().first());

		do {
			Bot bot = playerBots.dequeue();

			int botStrategy = Scanners
					.getInputInt("Escolha a estratégia a adotar pelo bot " + bot.getBotName()
							+ " [1- Shortest_Path, 2- Random, 3- Objective_Weighted]: ");

			while (!isStrategyValid(botStrategy - 1)) {
				System.out.println("A estratégia selecionada já foi adotada por outro bot!");
				botStrategy = Scanners
						.getInputInt("Escolha a estratégia a adotar pelo bot " + bot.getBotName()
								+ " [1- Shortest_Path, 2- Random, 3- Objective_Weighted]: ");
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
		if (strategyIndex < Bot.Strategy.values().length - 1 || strategyIndex > Bot.Strategy.values().length - 1) {
			return false;
		}

		if (this.game.getPlayers().first().getPlayerBots().size()
				* Properties.MAX_PLAYERS > Bot.Strategy.values().length - 1) {
			return true;
		}

		for (Player playerInGame : this.game.getPlayers()) {
			LinkedQueue<Bot> botsWithStrategyToVerify = new LinkedQueue<>(playerInGame.getPlayerBots().first());

			do {
				if (botsWithStrategyToVerify.dequeue().getStrategy() == Bot.Strategy.values()[strategyIndex - 1]) {
					return false;
				}
			} while (!botsWithStrategyToVerify.isEmpty());
		}

		return true;
	}
}
