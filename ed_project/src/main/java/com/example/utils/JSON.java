package com.example.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.configs.Properties;
import com.example.domain.Bot;
import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.implementation.graph.Vertex;
import com.example.structures.implementation.list.UnorderedList;

public class JSON {
	public static void saveGame(Game game) throws IOException {
		GameMap map = game.getGameMap();
		UnorderedList<Player> players = game.getPlayers();
		JSONArray jsonPlayersArray = new JSONArray();
		JSONArray jsonMapArray = new JSONArray();

		for (Player player : players) {
			jsonPlayersArray.add(player.parseToJson());
		}

		JSONObject jsonObject = new JSONObject();

		double[][] mapMatrix = map.downloadAdjencyMatrixWithWeights();

		for (int i = 0; i < mapMatrix.length; i++) {
			JSONArray mapLineMatrix = new JSONArray();
			for (int j = 0; j < mapMatrix[i].length; j++) {
				mapLineMatrix.add(mapMatrix[i][j]);
			}
			jsonMapArray.add(mapLineMatrix);
		}

		jsonObject.put("map", jsonMapArray);
		jsonObject.put("Players", jsonPlayersArray);

		FileWriter file = new FileWriter(Properties.SAVE_GAMEMAP_FILE_PATH);
		file.write(jsonObject.toJSONString());
		file.flush();

		System.out.println("Jogo Guardado com sucesso");
	}

	public static Game uploadGameMap()
			throws FileNotFoundException, IOException, ParseException, ElementNotFoundException {
		Game resumeGame = null;

		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(Properties.SAVE_GAMEMAP_FILE_PATH));

		JSONObject jsonObj = (JSONObject) object;
		JSONArray resumedMapMatrix = (JSONArray) jsonObj.get("map");

		JSONArray jsonArrayPlayers = (JSONArray) jsonObj.get("Players");

		loadMap(resumeGame, resumedMapMatrix);
		loadPlayers(resumeGame, jsonArrayPlayers);

		return null;
	}

	private static void loadMap(Game resumeGame, JSONArray mapMatrix) throws ElementNotFoundException {
		Object[] lines = new Object[mapMatrix.size()];
		double[][] loadedMapMatrix = new double[mapMatrix.size()][mapMatrix.size()];
		int counter = 0;

		for (Object line : mapMatrix) {
			lines[counter] = line;
			counter++;
		}

		GameMap map = new GameMap(lines.length, 0);

		for (int i = 0; i < lines.length; i++) {
			String splitedLine[] = lines[i].toString().replace("[", "").replace("]", "").split(",");

			for (int j = 0; j < splitedLine.length; j++) {
				loadedMapMatrix[i][j] = Double.parseDouble(splitedLine[j]);
			}
		}

		for (int i = 0; i < loadedMapMatrix.length; i++) {
			map.addVertex(i);
		}

		for (int i = 0; i < mapMatrix.size(); i++) {
			for (int j = 0; j < mapMatrix.size(); j++) {
				if (loadedMapMatrix[i][j] != 0) {
					map.addEdge(i, j, loadedMapMatrix[i][j]);
				}
			}
		}

		map.printAdjencyMatrix();
		map.printAdjencyMatrixWithWeights();
	}

	private static void loadPlayers(Game resumeGame, JSONArray jsonArrayPlayers) {
		for (Object player : jsonArrayPlayers) {
			JSONObject jsonPlayer = (JSONObject) player;

			String playerName = (String) jsonPlayer.get("name");
			int[] flag = (int[]) jsonPlayer.get("flag");

			Player loadedPlayer = new Player(playerName);

			loadedPlayer.setFlag(flag);

			JSONArray jsonArrayPlayerBots = (JSONArray) jsonPlayer.get("bots");
			loadPlayerBots(loadedPlayer, jsonArrayPlayerBots);

			resumeGame.addPlayer(loadedPlayer);
		}
	}

	private static void loadPlayerBots(Player loadedPlayer, JSONArray jsonArrayPlayerBots) {
		for (Object playerBot : jsonArrayPlayerBots) {
			JSONObject jsonPlayerBot = (JSONObject) playerBot;

			String botName = (String) jsonPlayerBot.get("name");
			String botStrategy = (String) jsonPlayerBot.get("Strategy");
			int[] botLocation = (int[]) jsonPlayerBot.get("Location");

			Bot loadedBot = new Bot(botName, botStrategy, botLocation);

			loadedPlayer.addBot(loadedBot);
		}
	}
}
