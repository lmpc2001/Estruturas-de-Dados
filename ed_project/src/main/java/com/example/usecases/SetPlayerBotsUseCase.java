package com.example.usecases;

import com.example.domain.Bot;
import com.example.domain.Player;
import com.example.utils.ScannersADT;

/**
 * A classe SetPlayerBotsUseCase é responsável por gerir as operações
 * relacionadas com a definição dos bots para cada jogador.
 * Faz parte dos casos de uso no domínio da aplicação, tratando especificamente
 * da definição de bots para os jogadores.
 * Esta classe fornece um método, {@code execute}, para executar as ações
 * necessárias na definição de bots para jogadores.
 * 
 * 
 * O método execute solicita ao utilizador que introduza os nomes para
 * os bots do jogador, atribui as suas posições atuais com base na bandeira do
 * jogador e adiciona-os à lista de bots do jogador.
 * 
 * 
 * @author Luís Costa [8200737]
 * @see com.example.domain.Player
 * @see com.example.domain.Bot
 * @see com.example.utils.ScannersADT
 * 
 */
public class SetPlayerBotsUseCase {
	private ScannersADT scanner;

	/**
	 * Constrói uma nova instância da classe SetPlayerBotsUseCase.
	 * 
	 * @param scanner Libraria a utilizar para interagir com o
	 *                utilizador
	 */
	public SetPlayerBotsUseCase(ScannersADT scanner) {
		this.scanner = scanner;
	}

	/**
	 * Executa o processo de definição de bots para jogadores.
	 *
	 * @param player             O jogador para o qual os bots estão a ser
	 *                           definidos.
	 * @param numberOfPlayerBots O número de bots a serem definidos para o jogador.
	 */
	public void execute(Player player, int numberOfPlayerBots) {
		for (int i = 0; i < numberOfPlayerBots; i++) {
			String botName = scanner
					.getInputString("Defina o nome do bot " + (i + 1) + " do player " + player.getPlayerName() + ":");

			Bot bot = new Bot(botName);
			bot.setCurrentPosition(player.getFlag());
			player.addBot(bot);
		}
	}
}
