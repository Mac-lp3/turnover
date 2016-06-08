package org.mac.sim.thread;

import java.util.List;

public class QueueManager extends Thread {

	private List queue;
	private int ratePerPeriod;
	private int totalPeriods;

	public QueueManager(List queue, int ratePerPeriod, int totalPeriods) {
		this.queue = queue;
		this.ratePerPeriod = ratePerPeriod;
		this.totalPeriods = totalPeriods;
	}

	public void run() {

		int i = 0;
		long curr = 0;
		long test = 0;
		long startTime = System.nanoTime();

		while (i < 9) {

			test = Math.floorDiv((System.nanoTime() - startTime), 10000000);

			if (test >= curr + 1) {
				System.out.println(test);
				i++;
				curr = test;
			}
		}
	}

}
