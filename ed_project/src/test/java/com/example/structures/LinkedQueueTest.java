package com.example.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.structures.exceptions.ElementNotFoundException;
import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.queue.LinkedQueue;

public class LinkedQueueTest {
	@Test
	public void shouldHaveTheCorrectSize() throws ElementNotFoundException {
		LinkedQueue<String> queue = new LinkedQueue<>();

		assertTrue(queue.size() == 0);

		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		assertTrue(queue.size() == 3);
	}

	@Test
	public void shouldEnqueueCorrectly() throws ElementNotFoundException {
		LinkedQueue<String> queue = new LinkedQueue<>();

		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		assertEquals("[A, B, C]" , queue.toString());
	}

	@Test
	public void shouldDequeueCorrectly() throws ElementNotFoundException, EmptyListException {
		LinkedQueue<String> queue = new LinkedQueue<>();

		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		assertEquals("A" , queue.dequeue());
		assertEquals(2 , queue.size());
		assertEquals("[B, C]" , queue.toString());

		assertEquals("B", queue.first());
	}

	@Test
	public void shouldReturnFirstElementCorrectly() throws ElementNotFoundException, EmptyListException {
		LinkedQueue<String> queue = new LinkedQueue<>();

		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		assertEquals("A" , queue.first());
	}

	@Test
	public void shouldNotBeEmpty() throws ElementNotFoundException, EmptyListException {
		LinkedQueue<String> queue = new LinkedQueue<>();

		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");

		assertEquals(false , queue.isEmpty());
	}

	@Test
	public void shouldBeEmpty() throws EmptyListException {
		LinkedQueue<String> queue = new LinkedQueue<>();

		assertEquals(true , queue.isEmpty());
	}

	@Test(expected = EmptyListException.class)
	public void shouldThrowAEmptyListExceptionWhenDequeueAEmptyQueue() throws EmptyListException {
		LinkedQueue<String> queue = new LinkedQueue<>();

		assertEquals("Should Throw A EmptyListException When Dequeue A Empty Queue", true, queue.dequeue());
	}
}
