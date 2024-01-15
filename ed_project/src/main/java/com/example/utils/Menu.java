package com.example.utils;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.usecases.GenerateMapUseCase;
import com.example.usecases.SetFlagLocationUseCase;
import com.example.usecases.SetPlayerBotsUseCase;
import com.example.usecases.exceptions.EmptyMapException;

public class Menu {
	static Game game;
	static GameMap map;

	public static void showMainMenu() throws IOException, EmptyMapException, ParseException, ElementNotFoundException {
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
					map = GenerateMapUseCase.execute();
					game = new Game(map);

					map.seeMap();

					int numberOfPlayerBots = Scanners.getInputInt("| Nº de bots por jogador: ");

					String namePlayer1 = Scanners.getInputString("| Nome do jogador 1: ");
					Player player1 = new Player(namePlayer1);
					SetFlagLocationUseCase.execute(map, player1);

					String namePlayer2 = Scanners.getInputString("| Nome do jogador 2: ");
					Player player2 = new Player(namePlayer2);
					SetFlagLocationUseCase.execute(map, player2);

					SetPlayerBotsUseCase.execute(player1, numberOfPlayerBots);
					SetPlayerBotsUseCase.execute(player2, numberOfPlayerBots);

					game.addPlayer(player1);
					game.addPlayer(player2);

					map.seeMap();
					break;
				}
				case 2: {
					// GameMap map = LoadPreviousGame.execute();
					// game = new Game(map);

					JSON.uploadGameMap();

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
					+ "| 3. Sair");

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
				default: {
					showMenu = false;
					return;
				}
			}
			showMenu = true;
		}
	}
}
