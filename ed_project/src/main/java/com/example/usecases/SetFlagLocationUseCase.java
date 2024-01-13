package com.example.usecases;

import com.example.domain.GameMap;
import com.example.domain.Player;
import com.example.structures.implementation.graph.Vertex;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Scanners;

public class SetFlagLocationUseCase {
	public static void execute(GameMap map, Player player)
			throws EmptyMapException {
				
		if (map.isEmpty()) {
			throw new EmptyMapException();
		}

		map.showGraphMatrix();

		int vertexPosition = Scanners.getInputInt("Selecione a posição do vertice onde desejar guardar a bandeira: ");

		Vertex flagPosition = map.getVertex(vertexPosition);

		player.setFlag(flagPosition);
	}
}
