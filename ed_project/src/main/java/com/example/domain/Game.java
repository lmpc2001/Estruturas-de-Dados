package com.example.domain;

import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.usecases.exceptions.EmptyMapException;

public class Game {
	private GameMap map;
	// private LinkedQueue<Player> players;
	private UnorderedList<Player> players;

	public Game() {
		this.map = null;
		this.players = new UnorderedList<>();
	}

	public Game(GameMap map) {
		this.map = map;
		this.players = new UnorderedList<>();
	}

	public void addPlayer(Player newPlayer) {
		this.players.addToRear(newPlayer);
	}

	public GameMap getGameMap() throws EmptyMapException {
		if (this.map == null) {
			throw new EmptyMapException();
		}
		return map;
	}

	public void setGameMap(GameMap newGameMap) {
		this.map = newGameMap;
	}

	public Player getPlayerTurn() throws EmptyListException {
		Player tmpPlayer = this.players.removeFirst();
		this.players.addToRear(tmpPlayer);

		return tmpPlayer;
	}

	public UnorderedList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(UnorderedList<Player> players) {
		this.players = players;
	}

	public boolean checkWin(Bot bot) throws EmptyListException {
		int[] botPosition = bot.getCurrentPosition();
		int[] oppositePlayerFlagPosition = players.first().getFlag();

		if (botPosition[0] == oppositePlayerFlagPosition[0] && botPosition[1] == oppositePlayerFlagPosition[1]) {
			return true;
		}

		return false;
	}
}
