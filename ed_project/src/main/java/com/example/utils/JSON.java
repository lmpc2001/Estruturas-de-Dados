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
import com.example.domain.exceptions.InvalidStrategyException;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.usecases.exceptions.EmptyMapException;

/**
 * Classe responsável pelos métodos de interação com ficheiros JSON
 * 
 * @author Luís Costa [8200737]
 */
public class JSON {

	/**
	 * Método utilizado para guardar o jogo atual, escrevendo os seus detalhes num
	 * ficheiro JSON
	 * 
	 * @param game instancia do objeto Game criado no inicio de cada novo jogo
	 * @throws IOException
	 * @throws EmptyMapException Se o mapa ainda não tiver sido definido
	 * @throws EmptyListException Se a lista estiver vazia 
	 */
	public static void saveGame(Game game) throws IOException, EmptyMapException, EmptyListException {
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

		FileWriter file = new FileWriter(Properties.GAME_FILE_PATH);
		file.write(jsonObject.toJSONString());
		file.flush();

		System.out.println("Jogo Guardado com sucesso");
	}

	/**
	 * Método responsável pelo processo de carregamento do mapa do último jogo
	 * guardado
	 * 
	 * @return o mapa de jogo carregado
	 * @throws ElementNotFoundException Se o elemento a procurar não existir no conjunto
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException Se o ficheiro a utilizar não for encontrado
	 */
	public static GameMap loadMap()
			throws ElementNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(Properties.GAME_FILE_PATH));

		JSONObject jsonObj = (JSONObject) object;
		JSONArray mapMatrix = (JSONArray) jsonObj.get("map");

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

		return map;
	}

	/**
	 * Método responsável pelo processo de carregamento dos players do último jogo
	 * guardado
	 * 
	 * @return a lista de jogadores do jogo guardado
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InvalidStrategyException 
	 * 
	 */
	public static UnorderedList<Player> loadPlayers() throws FileNotFoundException, IOException, ParseException, InvalidStrategyException {
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(Properties.GAME_FILE_PATH));

		JSONObject jsonObj = (JSONObject) object;
		JSONArray jsonArrayPlayers = (JSONArray) jsonObj.get("Players");

		UnorderedList<Player> resumedGamePlayers = new UnorderedList<>(jsonArrayPlayers.size());

		for (Object player : jsonArrayPlayers) {
			JSONObject jsonPlayer = (JSONObject) player;

			String playerName = (String) jsonPlayer.get("name");
			String flag = String.valueOf(jsonPlayer.get("flag"));

			Player loadedPlayer = new Player(playerName);

			loadedPlayer.setFlag(Integer.valueOf(flag));

			JSONArray jsonArrayPlayerBots = (JSONArray) jsonPlayer.get("bots");
			loadPlayerBots(loadedPlayer, jsonArrayPlayerBots);

			resumedGamePlayers.addToRear(loadedPlayer);
		}

		return resumedGamePlayers;
	}

	/**
	 * Método responsável pelo processo de carregamento dos Bots de cada jogador do
	 * último jogo guardado
	 * 
	 * @param loadedPlayer        instância da classe Player referente ao jogador
	 *                            carregado utilizada para atribuir os bots ao
	 *                            jogador correto
	 * 
	 * @param jsonArrayPlayerBots Array com a informação carregada relativa aos
	 *                            bots do Player do último jogo guardado
	 * @throws InvalidStrategyException 
	 */
	private static void loadPlayerBots(Player loadedPlayer, JSONArray jsonArrayPlayerBots) throws InvalidStrategyException {
		for (Object playerBot : jsonArrayPlayerBots) {
			JSONObject jsonPlayerBot = (JSONObject) playerBot;

			String botName = (String) jsonPlayerBot.get("Name");
			String botStrategy = (String) jsonPlayerBot.get("Strategy");
			String botLocation = String.valueOf(jsonPlayerBot.get("Location"));

			Bot loadedBot;

			switch (botStrategy) {
				case "Shortest_Path":
					loadedBot = new Bot(botName, Bot.Strategy.Shortest_Path, Integer.valueOf(botLocation));
					break;

				case "Random":
					loadedBot = new Bot(botName, Bot.Strategy.Random, Integer.valueOf(botLocation));
					break;

				case "Objective_Weighted":
					loadedBot = new Bot(botName, Bot.Strategy.Objective_Weighted, Integer.valueOf(botLocation));
					break;

				default:
					throw new InvalidStrategyException(botStrategy);
			}

			loadedPlayer.addBot(loadedBot);
		}
	}
}