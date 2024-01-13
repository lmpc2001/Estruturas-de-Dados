package com.example.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scanners {
	public static int getInputInt(String mensagem) {
		int input = 0;
		boolean erro = false;

		do {
			try {
				Scanner scanner = new Scanner(System.in);
				System.out.print(mensagem);
				input = scanner.nextInt();
				erro = false;
			} catch (InputMismatchException e) {
				System.out.println("| Input invalido, volte a tentar");
				erro = true;
			}
		} while (erro);

		return input;
	}

	public static double getInputDouble(String mensagem) {
		double input = 0;
		boolean erro = false;

		do {
			try {
				Scanner scanner = new Scanner(System.in);
				System.out.print(mensagem);
				input = scanner.nextDouble();
				erro = false;
			} catch (InputMismatchException e) {
				System.out.println("| Input invalido, volte a tentar");
				erro = true;
			}
		} while (erro);

		return input;
	}

	public static String getInputString(String mensagem) {
		String input = "";
		boolean erro = false;

		do {
			try {
				Scanner scanner = new Scanner(System.in);
				System.out.print(mensagem);
				input = scanner.nextLine();
				erro = false;
			} catch (InputMismatchException e) {
				System.out.println("| Input invalido, volte a tentar");
				erro = true;
			}
		} while (erro);

		return input;
	}
}
