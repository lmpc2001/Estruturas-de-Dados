package com.example.usecases;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.ScannersADT;

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
 * @see com.example.domain.Game
 * @see com.example.domain.Player
 * @see com.example.domain.GameMap
 * @see com.example.utils.ScannersADT
 * @see com.example.usecases.exceptions.EmptyMapException;
 * 
 */
public class SetFlagLocationUseCase {
	private Game game;
	private ScannersADT scanner;

	/**
	 * Constrói uma nova instância da classe StartGameUseCase.
	 *
	 * @param game    Instancia da classe jogo referente ao jogo atual
	 * @param scanner Libraria a utilizar para interagir com o
	 *                utilizador
	 */
	public SetFlagLocationUseCase(Game game, ScannersADT scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	/**
	 * Executa o processo de definição de bots para jogadores.
	 *
	 * @param player O jogador que irá definir a localização da sua bandeira
	 * @throws EmptyMapException se o mapa estiver vazio
	 * @throws ElementNotFoundException  Se o elemento a procurar não existir
	 */
	public void execute(Player player) throws EmptyMapException, ElementNotFoundException {
		int vertex;
		int vertexIndex;
		GameMap map = game.getGameMap();

		if (map.isEmpty()) {
			throw new EmptyMapException();
		}

		map.seeVertex();

		do {
			vertex = scanner.getInputInt("Selecione a posição onde deseja guardar a bandeira: ");
			vertexIndex = game.getGameMap().findVertexIndex(vertex);
		} while (!this.game.getGameMap().isValidPosition(vertexIndex));

		player.setFlag(vertexIndex);
	}
}
