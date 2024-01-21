package com.example.utils;

import com.example.structures.exceptions.EmptyListException;
import com.example.structures.implementation.queue.LinkedQueue;

public class TestScanners implements ScannersADT {

	private LinkedQueue<Integer> inputIntResponse = new LinkedQueue<>();
	private LinkedQueue<Double> inputDoubleResponse = new LinkedQueue<>();
	private LinkedQueue<String> inputStringResponse = new LinkedQueue<>();

	@Override
	public int getInputInt(String value) {
		try {
			return inputIntResponse.dequeue();
		} catch (EmptyListException e) {
			return 0;
		}
	}

	@Override
	public double getInputDouble(String value) {
		try {
			return inputDoubleResponse.dequeue();
		} catch (EmptyListException e) {
			return 0.0;
		}
	}

	@Override
	public String getInputString(String mensagem) {
		try {
			return inputStringResponse.dequeue();
		} catch (Exception e) {
			return "";
		}
	}
	
	public void setInputInt(int value) {
		this.inputIntResponse.enqueue(value);
	}
	
	public void setInputDouble(double value) {
		this.inputDoubleResponse.enqueue(value);
	}
	
	public void setInputString(String value) {
		this.inputStringResponse.enqueue(value);
	}
}
