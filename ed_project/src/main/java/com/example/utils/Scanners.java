package com.example.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A classe Scanners fornece métodos para interagir com o utilizador e solicitar
 * um valor númerico, decimal ou uma cadeia de caracters.
 * 
 * @author Luís Costa [8200737]
 * 
 */
public class Scanners implements ScannersADT {

	/**
	 * Obtém um número inteiro selecionado pelo utilizador.
	 *
	 * @param mensagem A mensagem a ser exibida antes de receber o valor.
	 * @return O número inteiro fornecido pelo utilizador.
	 */
	public int getInputInt(String mensagem) {
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

	/**
	 * Obtém um número decimal selecionado pelo utilizador.
	 *
	 * @param mensagem A mensagem a ser exibida antes de receber o valor.
	 * @return O número decimal fornecido pelo utilizador.
	 */
	public double getInputDouble(String mensagem) {
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

	/**
	 * Obtém uma string escrita pelo utilizador.
	 *
	 * @param mensagem A mensagem a ser exibida antes de receber a string escrita
	 *                 pelo utilizador.
	 * @return A string fornecida pelo utilizador.
	 */
	public String getInputString(String mensagem) {
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
