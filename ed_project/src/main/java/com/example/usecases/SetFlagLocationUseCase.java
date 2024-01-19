package com.example.usecases;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Scanners;

/**
 * A classe SetFlagLocationUseCase é responsável pela definição da
 * localização das bandeiras de cada jogador.
 * Faz parte dos casos de uso no domínio da aplicação, tratando unicamente
 * das operações necessárias para a definição da localização de cada bandeira.
 * 
 * O método principal, execute(), executa todas as ações necessárias para
 * definir a posição da bandeira de cada jogador
 * 
 *
 * @author Luís Costa [8200737]
 * @version 1.0
 * @see com.example.domain.Game
 * @see com.example.domain.Player
 * @see com.example.domain.GameMap
 * @see com.example.utils.Scanners
 * @see com.example.usecases.exceptions.EmptyMapException;
 */
public class SetFlagLocationUseCase {
	private Game game;

	/**
	 * Constrói uma nova instância da classe StartGameUseCase.
	 *
	 * @param game Instancia da classe jogo referente ao jogo atual
	 *             
	 */
	public SetFlagLocationUseCase(Game game) {
		this.game = game;
	}

	/**
	 * Executa o processo de definição de bots para jogadores.
	 *
	 * @param player             O jogador que irá definir a localização da sua bandeira
	 * 
	 */
	public void execute(Player player) throws EmptyMapException {
		GameMap map = game.getGameMap();

		if (map.isEmpty()) {
			throw new EmptyMapException();
		}

		map.seeMap();

		int vertexLine = Scanners.getInputInt("Selecione a linha do vertice onde desejar guardar a bandeira: ");
		int vertexCol = Scanners.getInputInt("Selecione a coluna do vertice onde desejar guardar a bandeira: ");

		int[] flagVertex = { vertexLine, vertexCol };

		player.setFlag(flagVertex);
	}
}
