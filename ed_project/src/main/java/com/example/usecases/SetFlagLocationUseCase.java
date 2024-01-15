package com.example.usecases;

import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Scanners;

public class SetFlagLocationUseCase {
	public static void execute(GameMap map, Player player)
			throws EmptyMapException {
				
		if (map.isEmpty()) {
			throw new EmptyMapException();
		}

		map.seeMap();

		int vertexLine = Scanners.getInputInt("Selecione a linha do vertice onde desejar guardar a bandeira: ");
		int vertexCol = Scanners.getInputInt("Selecione a coluna do vertice onde desejar guardar a bandeira: ");

		int[] flagVertex = {vertexLine, vertexCol};

		player.setFlag(flagVertex);
	}
}
