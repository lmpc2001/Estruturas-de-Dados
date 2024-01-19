package com.example.domain;

/**
 * Uma classe que representa um par de valores genéricos.
 *
 * @author Luís Costa [8200737]
 * @param <T> o tipo dos valores no par
 */
public class Pair<T> implements Comparable<Pair<T>> {
	private T firstValue;
	private T secondValue;

	/**
	 * Cria um par com os valores fornecidos.
	 *
	 * @param firstValue  o primeiro valor do par
	 * @param secondValue o segundo valor do par
	 */
	public Pair(T firstValue, T secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	/**
     * Obtém o primeiro valor do par.
     *
     * @return o primeiro valor do par
     */
	public T getFirstValue() {
		return firstValue;
	}

	/**
     * Define o primeiro valor do par.
     *
     * @param firstValue o primeiro valor do par
     */
	public void setFirstValue(T firstValue) {
		this.firstValue = firstValue;
	}

	/**
     * Obtém o segundo valor do par.
     *
     * @return o segundo valor do par
     */
	public T getSecondValue() {
		return secondValue;
	}

	/**
     * Define o segundo valor do par.
     *
     * @param secondValue o segundo valor do par
     */
	public void setSecondValue(T secondValue) {
		this.secondValue = secondValue;
	}

	/**
     * Compara este par com outro par para determinar a igualdade.
     *
     * @param comparablePair o par a ser comparado
     * @return 1 se os pares são iguais, 0 caso contrário
     */
	@Override
	public int compareTo(Pair<T> comparablePair) {
		if (this.equals(comparablePair)) {
			return 1;
		}

		if (firstValue == comparablePair.firstValue && this.secondValue == comparablePair.secondValue) {
			return 1;
		}

		return 0;
	}
}
