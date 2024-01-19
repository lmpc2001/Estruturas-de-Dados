package com.example.usecases;

import com.example.domain.Game;
import com.example.structures.exceptions.EmptyListException;
import com.example.usecases.exceptions.EmptyMapException;

/**
 * A classe StartGameUseCase é responsável por gerir todas as operações
 * relacionadas com o iniciar e decorrer do jogo.
 * Faz parte dos casos de uso no domínio da aplicação, tratando especificamente
 * de realizar todas as operações necessários para jogar o jogo.
 * 
 * O método principal, execute(), executa todas as ações necessárias para eleger
 * o jogador que irá começar
 * e permitir a cada jogador mover cada um dos seus bots pelo mapa
 * 
 * 
 *
 * @author Luís Costa [8200737]
 * @version 1.0
 * @see com.example.domain.Game
 * @see com.example.usecases.exceptions.EmptyMapException;
 * @see com.example.structures.exceptions.EmptyListException;
 */
public class StartGameUseCase {
	private Game game;
	private GenerateKickOffPlayerUseCase generateKickOffPlayerUseCase;
	private MoveBotUseCase moveBotUseCase;

	/**
	 * Constrói uma nova instância da classe StartGameUseCase.
	 *
	 * @param game                         Instancia da classe jogo à qual os
	 *                                     jogadores
	 *                                     serão adicionados.
	 * @param generateKickOffPlayerUseCase Caso de uso para eleição do
	 *                                     jogador que irá iniciar o jogo.
	 * 
	 * @param moveBotUseCase               Caso de uso para movimentação dos bots de
	 *                                     cada jogador ao longo do mapa
	 */
	public StartGameUseCase(Game game,
			GenerateKickOffPlayerUseCase generateKickOffPlayerUseCase,
			MoveBotUseCase moveBotUseCase) {
		this.game = game;
		this.generateKickOffPlayerUseCase = generateKickOffPlayerUseCase;
		this.moveBotUseCase = moveBotUseCase;
	}

	/**
	 * Executa o processo de inicialização do jogo.
	 *
	 * @throws EmptyMapException Se o mapa estiver vazio.
	 * @throws EmptyListException Se a lista de bots do jogador estiver vazia.
	 * 
	 */
	public void execute() throws EmptyMapException, EmptyListException {
		this.game.getGameMap().seeMap();

		this.generateKickOffPlayerUseCase.execute();

		moveBotUseCase.execute();
	}
}
