package com.example.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.list.UnorderedList;

public class UnorderedListTest{
	@Test
	public void shouldHaveTheCorrectSize() throws ElementNotFoundException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		assertTrue(unorderedList.size() == 0);

		unorderedList.addToRear("A");
		unorderedList.addToRear("B");
		unorderedList.addToRear("C");

		assertTrue(unorderedList.size() == 3);
	}

	@Test
	public void shouldAddToFrontCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		unorderedList.addToFront("A");
		unorderedList.addToRear("B");
		unorderedList.addToRear("C");

		assertTrue(unorderedList.first().equals("A"));
	}

	@Test
	public void shouldAddToRearCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		unorderedList.addToRear("A");
		unorderedList.addToRear("G");
		unorderedList.addToRear("H");
		unorderedList.addToRear("I");

		unorderedList.addToRear("J");
		unorderedList.addToRear("K");
		unorderedList.addToRear("L");

		assertTrue(unorderedList.first().equals("A"));
		assertTrue(unorderedList.last().equals("L"));
	}

	@Test
	public void shouldAddAfterCorrectly() throws ElementNotFoundException, EmptyListException {
		UnorderedList<String> unorderedList = new UnorderedList<>();

		unorderedList.addToFront("A");
		unorderedList.addToRear("G");
		unorderedList.addToRear("H");

		assertTrue(unorderedList.first().equals("A"));

		assertTrue(unorderedList.last().equals("H"));

		unorderedList.addAfter("L", "G");

		assertEquals(unorderedList.toString(), "[A, G, L, H]");

	}
}
