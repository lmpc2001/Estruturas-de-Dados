package com.example.domain;

import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;
import com.example.usecases.exceptions.EmptyMapException;

/**
 * A classe Game representa um novo jogo contendo os jogadores que o irão jogar
 * e todos os métodos necessários para controlar a sua jogabilidade.
 * 
 * @author Luís Costa [8200737]
 * @see com.example.usecases.exceptions.EmptyMapException
 * @see com.example.structures.exceptions.EmptyListException
 * @see com.example.structures.implementation.list.UnorderedList
 * 
 */
public class Game {
	private GameMap map;
	private UnorderedList<Player> players;

	/**
	 * Cria um novo jogo sem mapa inicial.
	 */
	public Game() {
		this.map = null;
		this.players = new UnorderedList<>();
	}

	/**
	 * Cria um novo jogo com um mapa inicial.
	 *
	 * @param map o mapa inicial do jogo
	 */
	public Game(GameMap map) {
		this.map = map;
		this.players = new UnorderedList<>();
	}

	/**
	 * Adiciona um novo jogador ao jogo.
	 *
	 * @param newPlayer o novo jogador a ser adicionado
	 */
	public void addPlayer(Player newPlayer) {
		this.players.addToRear(newPlayer);
	}

	/**
	 * Obtém o mapa do jogo.
	 *
	 * @return o mapa do jogo
	 * @throws EmptyMapException se o mapa estiver vazio
	 */
	public GameMap getGameMap() throws EmptyMapException {
		if (this.map == null) {
			throw new EmptyMapException();
		}
		return map;
	}

	/**
	 * Define um novo mapa para o jogo.
	 *
	 * @param newGameMap o novo mapa do jogo
	 */
	public void setGameMap(GameMap newGameMap) {
		this.map = newGameMap;
	}

	/**
	 * Obtém o jogador que irá jogar na proxima jogada.
	 *
	 * @return o jogador que irá jogar na proxima jogada
	 * @throws EmptyListException se a lista de jogadores estiver vazia
	 */
	public Player getPlayerTurn() throws EmptyListException {
		Player tmpPlayer = this.players.removeFirst();
		this.players.addToRear(tmpPlayer);

		return tmpPlayer;
	}

	/**
	 * Obtém a lista de jogadores no jogo.
	 *
	 * @return a lista de jogadores no jogo
	 */
	public UnorderedList<Player> getPlayers() {
		return players;
	}

	/**
	 * Define a lista de jogadores no jogo.
	 *
	 * @param players a nova lista de jogadores
	 */
	public void setPlayers(UnorderedList<Player> players) {
		this.players = players;
	}

	/**
	 * Verifica se o Bot atingiu a posição da bandeira do jogador oposto, indicando
	 * uma vitória.
	 *
	 * @param bot o bot cuja posição será verificada
	 * @return true se o bot atingir a posição da bandeira do jogador oposto, caso
	 *         contrário, false
	 * @throws EmptyListException se a lista de jogadores estiver vazia
	 */
	public boolean checkWin(Bot bot) throws EmptyListException {
		int[] botPosition = bot.getCurrentPosition();
		int[] oppositePlayerFlagPosition = players.first().getFlag();

		if (botPosition[0] == oppositePlayerFlagPosition[0] && botPosition[1] == oppositePlayerFlagPosition[1]) {
			return true;
		}

		return false;
	}
}
