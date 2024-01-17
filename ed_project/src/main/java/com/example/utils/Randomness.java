package com.example.utils;

import java.util.Arrays;
import java.util.Random;

public class Randomness {
	static Random random = new Random();

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

	public static int getRandomNumber(int min, int max) {
		int randomNumber = min + random.nextInt((max) - min);

		return randomNumber;
	}
}
