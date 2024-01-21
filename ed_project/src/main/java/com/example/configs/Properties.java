package com.example.configs;

/**
 * A classe Properties contém as configurações estáticas da aplicação.
 * Inclui informações como o caminho para o arquivo onde o jogo deve ser
 * guardado e a partir do qual deverá ser carregado, o tamanho padrão da lista, 
 * a distância máxima entre vértices e o número máximo de jogadores.
 * 
 * @author Luís Costa [8200737]
 */
public final class Properties {

	/**
	 * O caminho do arquivo de jogo salvo.
	 */
	public static final String GAME_FILE_PATH = "C:\\Users\\luism\\Desktop\\ESTG\\2_ano\\ED\\Estruturas-de-Dados\\Files\\savedGameMap.json";

	/**
	 * Tamanho padrão das listas.
	 */
	public static final int LIST_DEFAULT_SIZE = 10;

	/**
	 * Distância máxima entre vértices no mapa do jogo.
	 */
	public static final int MAX_DISTANCE_BETWEEN_VERTEXES = 15;

	/**
	 * Número máximo de jogadores permitidos no jogo.
	 */
	public static final int MAX_PLAYERS = 2;
}