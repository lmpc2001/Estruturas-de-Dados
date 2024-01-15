package com.example.utils;

import java.util.Random;

public class Randomness {
	static Random random = new Random();
	
	public static int getRandomNumber(int min, int max) {
		int randomNumber = min + random.nextInt((max) - min);

		return randomNumber;
	}
}
