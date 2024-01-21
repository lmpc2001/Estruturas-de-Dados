package com.example.utils;

/**
 * A interface RandomnessADT define os métodos a ser implementados nas
 * librarias de geração de números aleatórios que serão utilizadas no projeto.
 * 
 * @author Luís Costa [8200737]
 */
public interface RandomnessADT {
	/**
	 * Gera um número aleatório exclusivo dentro de um intervalo especifico,
	 * excluindo os valores fornecidos.
	 *
	 * @param min     o valor mínimo do intervalo
	 * @param max     o valor máximo do intervalo
	 * @param exclude array de valores a serem excluídos do processo
	 * @return um número aleatório dentro do intervalo especificado
	 */
	public int getRandomWithoutDuplicates(int min, int max, int[] exclude);

	/**
	 * Gera um número aleatório dentro de um intervalo especificado.
	 *
	 * @param min o valor mínimo do intervalo
	 * @param max o valor máximo do intervalo
	 * @return um número aleatório dentro do intervalo especificado
	 */
	public int getRandomNumber(int min, int max);
}
