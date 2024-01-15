package com.example.utils;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.GenerateKickOffPlayerUseCase;
import com.example.usecases.GenerateMapUseCase;
import com.example.usecases.LoadPreviousGame;
import com.example.usecases.SetFlagLocationUseCase;
import com.example.usecases.SetPlayerBotsUseCase;
import com.example.usecases.StartGameUseCase;
import com.example.usecases.exceptions.EmptyMapException;

public class Menu {
	static Game game;
	static GameMap map;

	private StartGameUseCase startGameUseCase;

	public Menu() {
		this.startGameUseCase = new StartGameUseCase();
	}

	public void showMainMenu()
			throws IOException, EmptyMapException, ParseException, ElementNotFoundException, EmptyListException {
		boolean showMenu = true;

		while (showMenu) {
			System.out.println("\n+ Menu + \n"
					+ "| 1. Novo Jogo \n"
					+ "| 2. Carregar Jogo \n"
					+ "| 3. Configurações do Jogo \n"
					+ "| 4. Save Game \n"
					+ "| 5. Sair");

			switch (Scanners.getInputInt("| Opção : ")) {
				case 1: {
					game = startGameUseCase.execute();
					break;
				}
				case 2: {
					// game = LoadPreviousGame.execute();

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

	private static void showGameConfigurationMenu() {
		boolean showMenu = true;

		while (showMenu) {
			System.out.println("\n+ Configurações de Jogo + \n"
					+ "| 1. Ver posição da bandeira \n"
					+ "| 2. Ver nome do player \n"
					+ "| 3. Ver mapa \n"
					+ "| 4. Ver ordem dos jogadores \n"
					+ "| 5. Sair");

			switch (Scanners.getInputInt("| Opção : ")) {
				case 1: {

					break;
				}
				case 2: {
					break;
				}
				case 3: {
					map.printAdjencyMatrixWithWeights();
					break;
				}
				case 4: {
					System.out.println(game.getPlayers().toString());
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
