package com.example.usecases;

import com.example.domain.Bot;
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
 * @see com.example.domain.Player
 * @see com.example.domain.Bot
 * @see com.example.domain.exceptions.InvalidStrategyException
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.queue.LinkedQueue
 * @see com.example.utils.Scanners
 */
public class SetPlayerBotsStrategyUseCase {
	/**
	 * Construtor da classe SetPlayerBotsStrategyUseCase.
	 */
	public SetPlayerBotsStrategyUseCase() {

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

			switch (botStrategy) {
				case 1:
					bot.setStrategy(Bot.Strategy.Shortest_Path);
					break;
				case 2:
					bot.setStrategy(Bot.Strategy.Random);
					break;
				case 3:
					bot.setStrategy(Bot.Strategy.Objective_Weighted);
					break;

				default:
					throw new InvalidStrategyException();
			}

		} while (!playerBots.isEmpty());
	}
}
