package com.example.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.example.domain.Game;
import com.example.domain.GameMap;
import com.example.structures.exceptions.ElementNotFoundException;
import com.example.usecases.exceptions.EmptyMapException;
import com.example.utils.Randomness;
import com.example.utils.TestScanners;

public class GenerateMapUseCaseTest {

	@Test
	public void shouldGenerateAMapWithBidirectionPaths() throws ElementNotFoundException, EmptyMapException {
		Game game = new Game();

		Randomness randomLib = new Randomness();
		TestScanners scanner = new TestScanners();

		GenerateMapUseCase generateMapUseCase = new GenerateMapUseCase(game, randomLib, scanner);

		scanner.setInputInt(6); // número de vertices a criar no mapa
		scanner.setInputString("y"); // caminhos bidirecionais

		generateMapUseCase.execute();

		GameMap map = game.getGameMap();

		assertNotNull(map);

		assertEquals(6, map.getNumberOfLocations());
	}

	@Test
	public void shouldGenerateAMapWithUniDirectionalPaths() throws ElementNotFoundException, EmptyMapException {
		Game game = new Game();
		int numOfVertices = 6;
		double edgeDensity = 0.5;
		double expectedNumberOfEdges = (numOfVertices * (numOfVertices -1)) * edgeDensity;

		Randomness randomLib = new Randomness();
		TestScanners scanner = new TestScanners();

		GenerateMapUseCase generateMapUseCase = new GenerateMapUseCase(game, randomLib, scanner);

		scanner.setInputInt(numOfVertices); // número de vertices a criar no mapa
		scanner.setInputString("n"); // caminhos bidirecionais
		scanner.setInputDouble(edgeDensity); // densidade das arestas

		generateMapUseCase.execute();

		GameMap map = game.getGameMap();

		assertNotNull(map);

		assertEquals(6, map.getNumberOfLocations());
		assertEquals((int) expectedNumberOfEdges, map.numberOfEdges());
	}
}
