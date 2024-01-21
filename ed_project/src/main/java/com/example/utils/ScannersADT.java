package com.example.utils;

/**
 * A interface ScannersADT indica quais os métodos a implementar para interagir
 * com o utilizador e obter informação por ele fornecida.
 * 
 * @author Luís Costa [87200737]
 */
public interface ScannersADT {

	/**
	 * Obtém um valor inteiro fornecido pelo utilizador.
	 * 
	 * @param mensagem A mensagem a ser apresentada ao utilizador.
	 * @return O valor inteiro fornecido pelo utilizador.
	 */
	public int getInputInt(String mensagem);

	/**
	 * Obtém o valor decimal fornecido pelo utilizador.
	 * 
	 * @param mensagem A mensagem a ser apresentada ao utilizador.
	 * @return O valor decimal fornecido pelo utilizador.
	 */
	public double getInputDouble(String mensagem);

	/**
	 * Obtém a string fornecida pelo utilizador.
	 * 
	 * @param mensagem A mensagem a ser apresentada ao utilizador.
	 * @return A string fornecida pelo utilizador.
	 */
	public String getInputString(String mensagem);

}