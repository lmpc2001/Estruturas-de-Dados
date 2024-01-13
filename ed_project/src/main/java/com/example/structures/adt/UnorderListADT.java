package com.example.structures.adt;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;

public interface UnorderListADT<T> extends ListADT<T>{
	/**
     * Adds the specified element to the front of the list
     *
     * @param element the element to be added to this list
     */
    public void addToFront(T element);

    /**
     * Adds the specified element to the rear of the list
     *
     * @param element the element to be added to this list
     */
    public void addToRear(T element);

    /**
     * Adds the specified element after a particular element already in the list
     *
     * @param element the element to be added to this list
     * @param target the target element already in the list
     * @throws EmptyListException
     */
    public void addAfter(T element, T target) throws EmptyListException, ElementNotFoundException;
}
