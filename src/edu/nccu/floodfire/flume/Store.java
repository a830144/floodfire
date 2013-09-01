package edu.nccu.floodfire.flume;

import java.util.LinkedList;

public class Store {
	private LinkedList<Object> products = new LinkedList<Object>();

	public synchronized void add(Object product) {
		while (products.size() >= 100) {
			// 容量限制為 2
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		products.addLast(product);
		System.out.println("目前容量::"+products.size());
		notifyAll();
	}

	public synchronized Object get() {
		while (products.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Object product = products.removeFirst();
		notifyAll();
		return product;
	}

}
