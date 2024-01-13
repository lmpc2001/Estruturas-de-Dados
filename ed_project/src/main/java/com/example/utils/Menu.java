package com.example.utils;

import java.io.IOException;

import com.example.domain.GameMap;
import com.example.usecases.GenerateMapUseCase;
import com.example.usecases.LoadPreviousGame;

public class Menu {
	static GameMap map;

	public static void showMainMenu() throws IOException {
		boolean showMenu = true;

		while (showMenu) {
			System.out.println("\n+ Menu + \n"
					+ "| 1. Novo Jogo \n"
					+ "| 2. Carregar Jogo \n"
					+ "| 3 Sair");

			switch (Scanners.getInputInt("| Opção : ")) {
				case 1: {
					map = GenerateMapUseCase.execute();
					map.getMatrix();
					break;
				}
				case 2: {
					map = LoadPreviousGame.execute();
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

	// private static boolean newGameMenu() throws IOException {
	// switch (Scanners.getInputInt("| Opção : ")) {
	// case 1: {
	// GenerateMapUseCase.execute();
	// break;
	// }
	// case 2: {
	// System.out.println("\n+ Listar vertives +");

	// break;
	// }
	// case 3: {
	// System.out.println("\n+ Calcular trajeto para um vendedor +");

	// break;
	// }
	// case 4: {
	// return false;
	// }
	// }

	// return true;
	// }

	// private static boolean uploadGameMenu() throws IOException {

	// switch (Scanners.getInputInt("| Opção : ")) {
	// case 1: {
	// int numberOfLocations = Scanners.getInputInt("| Insira o nº de localizações
	// que deseja para o Mapa : ");
	// double edgeDensity = Scanners.getInputDouble("| Insira a densidade entre as
	// arestas (eg. 0.5) : ");

	// GameMap map = new GameMap(numberOfLocations, edgeDensity);

	// break;
	// }
	// case 2: {
	// System.out.println("\n+ Listar vertives +");

	// break;
	// }
	// case 3: {
	// System.out.println("\n+ Calcular trajeto para um vendedor +");

	// break;
	// }
	// case 4: {
	// return false;
	// }
	// }

	// return true;

	// }
}
