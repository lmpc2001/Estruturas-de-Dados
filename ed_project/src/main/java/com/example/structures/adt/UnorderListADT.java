package com.example.structures.adt;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

/**
 * Interface onde se encontram definidos os métodos básicos a implementar numa
 * lista não
 * ordenada.
 * 
 * @author Luís Costa [8200737]
 * @param <T> Tipo dos elementos armazenados na lista.
 */
public interface UnorderListADT<T> extends ListADT<T> {
    /**
     * Adiciona o elemento especificado no início da lista.
     *
     * @param element o elemento a ser adicionado a esta lista.
     */
    public void addToFront(T element);

    /**
     * Adiciona o elemento especificado no final da lista.
     *
     * @param element o elemento a ser adicionado a esta lista.
     */
    public void addToRear(T element);

    /**
     * Adiciona o elemento especificado após um elemento já presente na lista.
     *
     * @param element o elemento a ser adicionado a esta lista.
     * @param target  o elemento alvo já presente na lista.
     * @throws EmptyListException       Se a lista estiver vazia.
     * @throws ElementNotFoundException Se o elemento alvo não for encontrado na
     *                                  lista.
     */
    public void addAfter(T element, T target) throws EmptyListException, ElementNotFoundException;
}
