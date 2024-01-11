package com.example.structures.adt;

/**
 * Interface onde se encontram definidas todas as funções e seus tipos de dados
 * implementadas pela classe CircularArrayQueue
 *
 * @param <T>
 */
public interface QueueADT<T> {
	/**
	 * Função que tem como objetivo de adicionar um elemento ao rear da queue
	 *
	 * @param elemento elemento a adicionar á queue
	 */
	public void enqueue(T elemento);

	/**
	 * Função com a funcionalidade de remover o elemento da frente da queue
	 *
	 * @return elemento na prossição da frente da queue
	 */
	public T dequeue();

	/**
	 * Retorna, sem eliminar, o elemento da frente da queue
	 *
	 * @return elemento da frente da queue
	 */
	public T first();

	/**
	 * Informa se a queue se encontra vazia ou não
	 *
	 * @return true se a queue esta vazia
	 */
	public boolean isEmpty();

	/**
	 * Informa qual o numero de elementos presentes na queue
	 *
	 * @return numero de elementos na queue
	 */
	public int size();

	/**
	 * Retorna uma String que representa a queue
	 *
	 * @return string representativa da queue
	 */
	public String toString();
}