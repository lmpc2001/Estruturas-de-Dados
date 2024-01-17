package com.example.utils;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.Game;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.GenerateKickOffPlayerUseCase;
import com.example.usecases.GenerateMapUseCase;
import com.example.usecases.LoadPreviousGameUseCase;
import com.example.usecases.MoveBotUseCase;
import com.example.usecases.SetFlagLocationUseCase;
import com.example.usecases.SetPlayerBotsUseCase;
import com.example.usecases.SetPlayersUseCase;
import com.example.usecases.StartGameUseCase;
import com.example.usecases.exceptions.EmptyMapException;

public class Menu {
	private Game game;

	private StartGameUseCase startGameUseCase;
	private GenerateKickOffPlayerUseCase generateKickOffPlayerUseCase;
	private LoadPreviousGameUseCase loadPreviousGameUseCase;
	private GenerateMapUseCase generateMapUseCase;
	private SetFlagLocationUseCase setFlagLocationUseCase;
	private SetPlayersUseCase setPlayersUseCase;
	private SetPlayerBotsUseCase setPlayerBotsUseCase;
	private MoveBotUseCase moveBotUseCase;

	public Menu() {
		this.game = new Game();
		this.moveBotUseCase = new MoveBotUseCase(game);
		this.setPlayerBotsUseCase = new SetPlayerBotsUseCase();
		this.generateMapUseCase = new GenerateMapUseCase(game);
		this.setFlagLocationUseCase = new SetFlagLocationUseCase(game);
		this.loadPreviousGameUseCase = new LoadPreviousGameUseCase(game);
		this.generateKickOffPlayerUseCase = new GenerateKickOffPlayerUseCase(game);
		this.startGameUseCase = new StartGameUseCase(game, generateKickOffPlayerUseCase,moveBotUseCase);
		this.setPlayersUseCase = new SetPlayersUseCase(game, setFlagLocationUseCase, setPlayerBotsUseCase);
	}

	public void showMainMenu()
			throws IOException, EmptyMapException, ElementNotFoundException, EmptyListException, ParseException {
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

						System.out.println("[" + actualPlayer.getPlayerName() +"] A tua bandeira está na posição: " + actualPlayer.getFlag());
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
