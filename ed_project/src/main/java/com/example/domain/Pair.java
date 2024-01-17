package com.example.domain;

public class Pair<T> implements Comparable<Pair<T>> {
	private T firstValue;
	private T secondValue;

	public Pair(T firstValue, T secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	public T getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(T firstValue) {
		this.firstValue = firstValue;
	}

	public T getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(T secondValue) {
		this.secondValue = secondValue;
	}

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
