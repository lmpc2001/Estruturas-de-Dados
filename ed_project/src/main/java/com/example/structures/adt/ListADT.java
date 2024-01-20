package com.example.structures.adt;

import java.util.Iterator;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

/**
 * Interface onde se encontram definidos todas as funções e seus tipos de dados
 * implementadas pela classe ArrayUnorderedList
 *
 * @author Luís Costa [8200737]
 * @param <T>
 */
public interface ListADT<T> extends Iterable<T> {

    /**
     * Função responsável por remover e retornar o primeiro elemento da lista
     *
     * @return primeiro elemento da lista
     */
    public T removeFirst() throws EmptyListException;

    /**
     * Função responsável por remover e retornar o último elemento da lista
     *
     * @return último elemento da lista
     */
    public T removeLast() throws EmptyListException;

    /**
     * Função responsável por remover e retornar o elemento especificado da
     * lista
     *
     * @param element
     * @return o eleemento especifico da lista
     */
    public T remove(T element) throws EmptyListException, ElementNotFoundException;

    /**
     * Função responsável por retornar o primeiro elemento da lista
     *
     * @return primeiro elemento da lista
     */
    public T first() throws EmptyListException;

    /**
     * Função responsável por retornar o último elemento da lista
     *
     * @return último elemento da lista
     */
    public T last() throws EmptyListException;

    /**
     * Função responsável por procurar e retornar um elemento especifico da lista
     *
     * @return elemento encontrado
     * @throws EmptyListException se a lista estiver vazia
     * @throws ElementNotFoundException se o elemento a procurar não existir na lista
     */
    public T getElement(T element) throws EmptyListException, ElementNotFoundException;

    /**
     * Função responsável por verificar se a lista está ou não vazia
     *
     * @return true se a lista estiver vazia, caso contrário return false
     */
    public boolean isEmpty();

    /**
     * Função responsável por retornar o tamanho atual da lista
     *
     * @return tamanho atual da lista
     */
    public int size();

    /**
     *
     * @return
     */
    public Iterator<T> iterator();

    /**
     * Função responsável por retornar uma string que caracterize a lista
     *
     * @return string ilustrativa da lista
     */
    public String toString();
}