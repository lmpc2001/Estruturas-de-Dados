package com.example.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;

public class ArrayListTest {
	@Test
	public void shouldHaveTheCorrectSize() throws ElementNotFoundException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		assertTrue(unorderedList.size() == 0);

		unorderedList.addToRear("A");
		unorderedList.addToRear("B");
		unorderedList.addToRear("C");

		assertEquals(unorderedList.size(), 3);
	}

	@Test
	public void shouldRemoveFirstCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		unorderedList.addToFront("A");
		unorderedList.addToRear("B");
		unorderedList.addToRear("C");

		assertEquals(unorderedList.removeFirst(), "A");
		assertEquals(unorderedList.size(), 2);
		assertEquals(unorderedList.toString(), "[B, C]");

	}

	@Test
	public void shouldRemoveCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		unorderedList.addToFront("A");
		unorderedList.addToRear("B");
		unorderedList.addToRear("C");

		assertEquals(unorderedList.remove("B"), "B");
		assertEquals(unorderedList.size(), 2);
		assertEquals(unorderedList.toString(), "[A, C]");
	}

	@Test
	public void shouldRemoveLastCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		unorderedList.addToFront("A");
		unorderedList.addToRear("B");
		unorderedList.addToRear("C");

		assertEquals(unorderedList.removeLast(), "C");
		assertEquals(unorderedList.size(), 2);
		assertEquals(unorderedList.toString(), "[A, B]");
	}

	@Test
	public void shouldBeEmpty() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		assertEquals(unorderedList.isEmpty(), true);
	}

	@Test
	public void shouldNotBeEmpty() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();
		
		unorderedList.addToFront("A");

		assertEquals(unorderedList.isEmpty(), false);
	}

	@Test
	public void shouldReturnFirstElementCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();
		
		unorderedList.addToFront("A");
		unorderedList.addToFront("B");
		unorderedList.addToFront("C");

		assertEquals(unorderedList.first(), "C");
	}

	@Test
	public void shouldReturnLastElementCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();
		
		unorderedList.addToRear("A");
		unorderedList.addToRear("B");
		unorderedList.addToRear("C");

		assertEquals(unorderedList.last(), "C");
	}

	@Test(expected = EmptyListException.class)
	public void shouldThrowAEmptyListException() throws EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		unorderedList.last();		
	}
}
