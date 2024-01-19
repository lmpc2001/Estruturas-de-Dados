package com.example.usecases;

import com.example.configs.Properties;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.Randomness;

/**
 * A classe GenerateKickOffPlayerUseCase é responsável por realizar as operações
 * relacionadas com a escolha do jogador que iniciará a partida.
 * Faz parte dos casos de uso no domínio da aplicação, tratando da seleção
 * aleatória de um jogador para começar a partida.
 * 
 * O método principal, execute(), guia o processo de seleção aleatória de um
 * jogador para iniciar a partida.
 * 
 * O método execute() obtém a lista de jogadores do jogo, seleciona
 * aleatoriamente um jogador para começar, e reorganiza a lista de jogadores
 * colocando o jogador escolhido no início da lista
 * 
 * @author Luís Costa [8200737]
 * @version 1.0
 * @see com.example.domain.Game
 * @see com.example.domain.Player
 * @see com.example.utils.Randomness
 * @see com.example.configs.Properties
 * @see com.example.structures.implementation.list.UnorderedList
 */
public class GenerateKickOffPlayerUseCase {
	private Game game;

	/**
	 * Constrói uma nova instância da classe GenerateKickOffPlayerUseCase.
	 *
	 * @param game O jogo no qual a escolha do pontapé inicial será realizada.
	 */
	public GenerateKickOffPlayerUseCase(Game game) {
		this.game = game;
	}

	/**
	 * Executa o processo de seleção aleatória do jogador para iniciar a partida
	 *
	 */
	public void execute() {
		UnorderedList<Player> gamePlayers = game.getPlayers();
		UnorderedList<Player> orderedPlayers = new UnorderedList<>(Properties.MAX_PLAYERS);

		int playerIndex = Randomness.getRandomNumber(0, Properties.MAX_PLAYERS);
		int count = 0;

		for (Player player : gamePlayers) {
			if (count == playerIndex) {
				orderedPlayers.addToFront(player);
			} else {
				orderedPlayers.addToRear(player);
			}
			count++;
		}

		game.setPlayers(orderedPlayers);
	}
}
