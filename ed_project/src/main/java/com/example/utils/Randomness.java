package com.example.utils;

import java.util.Random;

public class Randomness {
	static Random random = new Random();

	public static int getRandomWithoutDuplicates(int min, int max, int[] exclude) {
		int randomNumber = min + random.nextInt(max);

		for (int ex : exclude) {
			if (randomNumber == ex) {
				randomNumber = min + random.nextInt(max);
			}
		}
		return randomNumber;
	}

	public static int getRandomNumber(int min, int max) {
		int randomNumber = min + random.nextInt(max - min);

		return randomNumber;
	}
}
