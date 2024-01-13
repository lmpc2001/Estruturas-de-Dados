package com.example.usecases;

import com.example.domain.Bot;
import com.example.domain.Player;
import com.example.utils.Scanners;

public class SetPlayerBotsUseCase {
	public static void execute(Player player, int numberOfPlayerBots) {
		for (int i = 0; i < numberOfPlayerBots; i++) {
			String botName = Scanners.getInputString("Defina o nome do bot " + i + " do player " + player.getPlayerName()+ ":");

			Bot bot = new Bot(botName);
			player.addBot(bot);
		}
	}
}
