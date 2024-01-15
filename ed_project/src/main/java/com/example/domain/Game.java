package com.example.domain;

import com.example.configs.Properties;
import com.example.structures.implementation.list.UnorderedList;

public class Game {
	GameMap map;
	UnorderedList<Player> players;

	public Game(GameMap map) {
		this.map = map;
		this.players = new UnorderedList<>(Properties.MAX_PLAYERS);
	}

	public void addPlayer(Player newPlayer) {
		this.players.addToRear(newPlayer);
	}

	public GameMap getGameMap() {
		return map;
	}

	public UnorderedList<Player> getPlayers() {
		return players;
	}
}
