package com.example.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * A classe Randomness fornece métodos capazes de gerar números aleatórios com
 * ou sem exclusão de valores específicos.
 * 
 * @author Luís Costa [8200737
 * ]
 */
public class Randomness {
	static Random random = new Random();

	/**
	 * Gera um número aleatório dentro de um intervalo, excluindo valores
	 * especificados.
	 *
	 * @param min     O valor mínimo do intervalo.
	 * @param max     O valor máximo do intervalo.
	 * @param exclude Um array de valores a serem excluídos da geração aleatória.
	 * @return Um número aleatório dentro do intervalo, e que não pertença aos
	 *         valores excluídos.
	 * 
	 */
	public static int getRandomWithoutDuplicates(int min, int max, int[] exclude) {
		Arrays.sort(exclude);
		int randomNumber = min + random.nextInt(max - min);
		for (int ex : exclude) {
			if (randomNumber == ex) {
				getRandomWithoutDuplicates(min, max, exclude);
			}
		}
		return randomNumber;
	}

	/**
	 * Gera um número aleatório dentro de um intervalo.
	 *
	 * @param min O valor mínimo do intervalo.
	 * @param max O valor máximo do intervalo.
	 * @return Um número aleatório dentro do intervalo.
	 */
	public static int getRandomNumber(int min, int max) {
		int randomNumber = min + random.nextInt((max) - min);

		return randomNumber;
	}
}
