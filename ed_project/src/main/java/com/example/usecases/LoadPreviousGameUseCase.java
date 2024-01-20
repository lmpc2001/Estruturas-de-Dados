package com.example.usecases;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.utils.JSON;

/**
 * A classe LoadPreviousGameUseCase é responsável por realizar todas as
 * operações relacionadas com o carregamento de um jogo anteriormente guardado.
 * Faz parte dos casos de uso no domínio da aplicação, tratando da restauração
 * do estado do jogo a partir de dados previamente armazenados e fornecidos a
 * partir de um ficheiro JSON.
 * 
 * Esta classe requer uma instância de Game para funcionar corretamente.
 * O método principal, execute(), guia o processo de carregamento do mapa e dos
 * jogadores a partir de um ficheiro JSON, restaurando o estado do jogo.
 *
 * O método execute() utiliza a classe utilitária JSON para carregar o mapa e a
 * lista de jogadores previamente salva.
 * Em seguida, atualiza o jogo com o mapa carregado e adiciona os jogadores ao
 * jogo restaurado.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.utils.JSON
 * @see com.example.domain.Game
 * @see com.example.domain.Player
 * @see com.example.domain.GameMap
 * @see com.example.domain.exceptions.InvalidStrategyException
 * @see com.example.structures.implementation.list.UnorderedList
 * @see com.example.structures.exceptions.ElementNotFoundException
 * 
 */
public class LoadPreviousGameUseCase {
	private Game resumeGame;

	/**
	 * Constrói uma nova instância da calsse LoadPreviousGameUseCase.
	 *
	 * @param game O jogo para o qual o estado será restaurado.
	 */
	public LoadPreviousGameUseCase(Game game) {
		this.resumeGame = game;
	}

	/**
	 * Executa o processo de carregamento de um jogo anteriormente salvo.
	 *
	 * @throws IOException              Se ocorrer um erro de leitura/escrita
	 *                                  durante
	 *                                  o carregamento a partir do ficheiro JSON.
	 * @throws ParseException           Se ocorrer um erro de análise durante o
	 *                                  carregamento do ficheiro JSON.
	 * @throws ElementNotFoundException Se um elemento não for encontrado durante o
	 *                                  carregamento.
	 * @throws InvalidStrategyException Se a estratégia escolhida não existir nas
	 *                                  opções fornecidas
	 */
	public void execute() throws IOException, ParseException, ElementNotFoundException, InvalidStrategyException {
		GameMap map = JSON.loadMap();
		UnorderedList<Player> players = JSON.loadPlayers();

		resumeGame.setGameMap(map);

		for (Player player : players) {
			resumeGame.addPlayer(player);
		}
	}
}
