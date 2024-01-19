package com.example.usecases;

import com.example.configs.Properties;
import com.example.domain.Game;
import com.example.domain.Player;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Scanners;

/**
 * A classe SetPlayersUseCase é responsável por gerir todas as operações
 * relacionadas com a definição dos jogadores do jogo.
 * Faz parte dos casos de uso no domínio da aplicação, tratando especificamente
 * da configuração inicial dos jogadores.
 * 
 * O método principal, execute(), conduz o processo de definição dos jogadores,
 * da localização das bandeiras e dos bots associados.
 * 
 
 * Este método solicita ao utilizador o número de bots por jogador e, em
 * seguida, itera para cada jogador,
 * solicitando os seus nomes e definindo as suas localizações de bandeira e
 * configurando os bots associados.
 
 * 
 *
 * @author Luís Costa [8200737]
 * @version 1.0
 * @see com.example.domain.Game
 * @see com.example.domain.Player
 * @see com.example.usecases.exceptions.EmptyMapException
 * @see com.example.utils.Scanners
 */
public class SetPlayersUseCase {
	private Game game;
	private SetPlayerBotsUseCase setPlayerBotsUseCase;
	private SetFlagLocationUseCase setFlagLocationUseCase;

	/**
	 * Constrói uma nova instância da classe SetPlayersUseCase.
	 *
	 * @param game                   Instancia da classe jogo à qual os jogadores
	 *                               serão adicionados.
	 * @param setFlagLocationUseCase Caso de uso para configuração da localização
	 *                               das bandeiras de cada jogador.
	 * @param setPlayerBotsUseCase   Caso de uso para configuração dos bots de cada
	 *                               jogador.
	 */
	public SetPlayersUseCase(Game game,
			SetFlagLocationUseCase setFlagLocationUseCase,
			SetPlayerBotsUseCase setPlayerBotsUseCase) {
		this.game = game;
		this.setFlagLocationUseCase = setFlagLocationUseCase;
		this.setPlayerBotsUseCase = setPlayerBotsUseCase;
	}

	/**
	 * Executa o processo de definição de jogadores para o jogo.
	 *
	 * @throws EmptyMapException Se o mapa estiver vazio.
	 */
	public void execute() throws EmptyMapException {
		int numberOfPlayerBots = Scanners.getInputInt("| Nº de bots por jogador: ");

		for (int i = 0; i < Properties.MAX_PLAYERS; i++) {
			String namePlayer = Scanners.getInputString("| Nome do jogador " + i + 1 + ": ");
			Player player = new Player(namePlayer);

			this.setFlagLocationUseCase.execute(player);
			this.setPlayerBotsUseCase.execute(player, numberOfPlayerBots);

			game.addPlayer(player);
		}
	}
}
