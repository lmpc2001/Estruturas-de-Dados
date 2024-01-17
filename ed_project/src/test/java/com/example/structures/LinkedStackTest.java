package com.example.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.stack.LinkedStack;

public class LinkedStackTest {
	@Test
	public void shouldHaveTheCorrectSize() throws ElementNotFoundException {
		LinkedStack<String> stack = new LinkedStack<>();

		assertTrue(stack.size() == 0);

		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertTrue(stack.size() == 3);
	}

	@Test
	public void shouldPushCorrectly() throws ElementNotFoundException {
		LinkedStack<String> stack = new LinkedStack<>();

		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertEquals("[C, B, A]" , stack.toString());
	}

	@Test
	public void shouldPopCorrectly() throws ElementNotFoundException, EmptyListException {
		LinkedStack<String> stack = new LinkedStack<>();

		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertEquals("C" , stack.pop());
		assertEquals(2 , stack.size());
		assertEquals("[B, A]" , stack.toString());
	}

	@Test
	public void shouldReturnFirstElementCorrectly() throws ElementNotFoundException, EmptyListException {
		LinkedStack<String> stack = new LinkedStack<>();

		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertEquals("C" , stack.peek());
	}

	@Test
	public void shouldNotBeEmpty() throws ElementNotFoundException, EmptyListException {
		LinkedStack<String> stack = new LinkedStack<>();

		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertEquals(false , stack.isEmpty());
	}

	@Test
	public void shouldBeEmpty() throws EmptyListException {
		LinkedStack<String> stack = new LinkedStack<>();

		assertEquals(true , stack.isEmpty());
	}

	@Test(expected = EmptyListException.class)
	public void shouldThrowAEmptyListExceptionWhenPopAEmptyQueue() throws EmptyListException {
		LinkedStack<String> stack = new LinkedStack<>();

		stack.pop();
	}
}
