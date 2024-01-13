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
import com.example.structures.implementation.UnorderedList;
import com.example.structures.implementation.graph.Vertex;

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

		int[][] mapMatrix = map.getMap();

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

	public static Game uploadGameMap() throws FileNotFoundException, IOException, ParseException {
		Game resumeGame;

		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(Properties.UPLOAD_GAMEMAP_FILE_PATH));

		JSONObject jsonObj = (JSONObject) object;
		JSONArray resumedMapMatrix = (JSONArray) jsonObj.get("map");

		JSONArray jsonArrayPlayers = (JSONArray) jsonObj.get("Players");

		loadPlayers(jsonArrayPlayers);

	}

	private static void loadPlayers(Game resumeGame, JSONArray jsonArrayPlayers) {
		for (Object player : jsonArrayPlayers) {
			JSONObject jsonPlayer = (JSONObject) player;

			String playerName = (String) jsonPlayer.get("name");
			Vertex flag = (Vertex) jsonPlayer.get("flag");

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
			String botLocation = (String) jsonPlayerBot.get("Location");

			Vertex vertex = new Vertex<>();

			Bot loadedBot = new Bot(botName, botStrategy, vertex);

			loadedPlayer.addBot(loadedBot);
		}
	}
}
