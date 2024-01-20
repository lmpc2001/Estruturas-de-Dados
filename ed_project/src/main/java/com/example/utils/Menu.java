package com.example.utils;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.Game;
import com.example.domain.Player;
import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.GenerateKickOffPlayerUseCase;
import com.example.usecases.GenerateMapUseCase;
import com.example.usecases.LoadPreviousGameUseCase;
import com.example.usecases.MoveBotUseCase;
import com.example.usecases.SetFlagLocationUseCase;
import com.example.usecases.SetPlayerBotsStrategyUseCase;
import com.example.usecases.SetPlayerBotsUseCase;
import com.example.usecases.SetPlayersUseCase;
import com.example.usecases.StartGameUseCase;
import com.example.usecases.exceptions.EmptyMapException;

/**
 * A classe Menu é responsável por apresentar e gerir as opções do menu do jogo.
 * Utiliza os casos de uso e interage com o jogador para realizar a configuração
 * e execução do jogo.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.usecases.MoveBotUseCase
 * @see com.example.usecases.StartGameUseCase
 * @see com.example.usecases.SetPlayersUseCase
 * @see com.example.usecases.GenerateMapUseCase
 * @see com.example.usecases.SetPlayerBotsUseCase
 * @see com.example.usecases.SetFlagLocationUseCase
 * @see com.example.usecases.LoadPreviousGameUseCase
 * @see com.example.usecases.GenerateKickOffPlayerUseCase
 * @see com.example.usecases.SetPlayerBotsStrategyUseCase
 * 
 */
public class Menu {
	private Game game;

	private MoveBotUseCase moveBotUseCase;
	private StartGameUseCase startGameUseCase;
	private SetPlayersUseCase setPlayersUseCase;
	private GenerateMapUseCase generateMapUseCase;
	private SetPlayerBotsUseCase setPlayerBotsUseCase;
	private SetFlagLocationUseCase setFlagLocationUseCase;
	private LoadPreviousGameUseCase loadPreviousGameUseCase;
	private GenerateKickOffPlayerUseCase generateKickOffPlayerUseCase;
	private SetPlayerBotsStrategyUseCase setPlayerBotsStrategyUseCase;

	/**
	 * Construtor da classe Menu utilizado para criar uma nova instância do menu que
	 * possibilite a interação com o jogo
	 */
	public Menu() {
		this.game = new Game();
		this.moveBotUseCase = new MoveBotUseCase(game);
		this.setPlayerBotsUseCase = new SetPlayerBotsUseCase();
		this.generateMapUseCase = new GenerateMapUseCase(game);
		this.setFlagLocationUseCase = new SetFlagLocationUseCase(game);
		this.loadPreviousGameUseCase = new LoadPreviousGameUseCase(game);
		this.setPlayerBotsStrategyUseCase = new SetPlayerBotsStrategyUseCase(game);
		this.generateKickOffPlayerUseCase = new GenerateKickOffPlayerUseCase(game);
		this.startGameUseCase = new StartGameUseCase(game, generateKickOffPlayerUseCase, moveBotUseCase);
		this.setPlayersUseCase = new SetPlayersUseCase(game, setFlagLocationUseCase, setPlayerBotsUseCase,
				setPlayerBotsStrategyUseCase);
	}

	/**
	 * Apresenta o menu principal do jogo, permitindo ao jogador escolher entre
	 * diferentes opções.
	 *
	 * @throws IOException              Se ocorrer um erro de leitura/escrita no
	 *                                  ficheiro JSON.
	 * @throws EmptyMapException        Se o mapa estiver vazio.
	 * @throws ElementNotFoundException Se um elemento não for encontrado.
	 * @throws EmptyListException       Se a lista estiver vazia.
	 * @throws ParseException           Se ocorrer um erro de análise de JSON.
	 * @throws InvalidStrategyException Se a estratégia for inválida.
	 */
	public void showMainMenu()
			throws IOException, EmptyMapException, ElementNotFoundException, EmptyListException, ParseException,
			InvalidStrategyException {
		boolean showMenu = true;

		while (showMenu) {
			System.out.println("\n*************** Menu *************** \n"
					+ "| 1. Novo Jogo \n"
					+ "| 2. Carregar Jogo \n"
					+ "| 3. Configurações do Jogo \n"
					+ "| 4. Save Game \n"
					+ "| 5. Sair \n"
					+ "************************************");

			switch (Scanners.getInputInt("\n| Opção : ")) {
				case 1: {
					generateMapUseCase.execute();
					setPlayersUseCase.execute();
					startGameUseCase.execute();
					break;
				}
				case 2: {
					loadPreviousGameUseCase.execute();
					startGameUseCase.execute();
					break;
				}
				case 3: {
					showGameConfigurationMenu();
					break;
				}
				case 4: {
					JSON.saveGame(game);
					break;
				}
				default: {
					showMenu = false;
					return;
				}
			}
			showMenu = true;
		}
	}

	/**
	 * Apresenta o menu de configurações do jogo, permitindo ao jogador visualizar
	 * informações específicas do jogo em curso.
	 *
	 * @throws EmptyMapException  Se o mapa estiver vazio.
	 * @throws EmptyListException Se a lista estiver vazia.
	 */
	private void showGameConfigurationMenu() throws EmptyMapException, EmptyListException {
		boolean showMenu = true;

		while (showMenu) {
			System.out.println("\n*************** Configurações de Jogo ***************\n"
					+ "| 1. Ver posição da bandeira \n"
					+ "| 2. Ver nome do player \n"
					+ "| 3. Ver mapa \n"
					+ "| 4. Ver ordem dos jogadores \n"
					+ "| 5. Sair \n"
					+ "******************************************************");

			switch (Scanners.getInputInt("| Opção : ")) {
				case 1: {
					Player actualPlayer = this.game.getPlayers().first();

					System.out.println("[" + actualPlayer.getPlayerName() + "] A tua bandeira está na posição: "
							+ actualPlayer.getFlag());
					break;
				}
				case 2: {
					this.game.getGameMap().printAdjencyMatrixWithWeights();
					break;
				}
				case 3: {
					System.out.println(this.game.getPlayers().toString());
					break;
				}
				default: {
					showMenu = false;
					return;
				}
			}
			showMenu = true;
		}
	}
}
