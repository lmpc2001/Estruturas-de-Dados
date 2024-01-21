package com.example.utils;

public interface RandomnessADT {
	public int getRandomWithoutDuplicates(int min, int max, int[] exclude);

	public int getRandomNumber(int min, int max);
}
