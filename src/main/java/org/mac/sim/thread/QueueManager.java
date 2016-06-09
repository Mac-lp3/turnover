package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

public class QueueManager extends Thread {

	private BlockingQueue queue;
	private int ratePerPeriod;
	private int totalPeriods;

	public QueueManager(BlockingQueue queue, int ratePerPeriod, int totalPeriods) {
		this.queue = queue;
		this.ratePerPeriod = ratePerPeriod;
		this.totalPeriods = totalPeriods;
	}

	/**
	 * Will continually watch the system clock until the next "period" has been
	 * reached. Will then update the queue with the given number of new
	 * arrivals.
	 */
	public void run() {

		int i = 0;
		long currentPeriod = 0;
		long nextPeriod = 0;
		long startTime = System.nanoTime();

		while (i < 9) {

			nextPeriod = Math.floorDiv((System.nanoTime() - startTime), 10000000);

			if (nextPeriod > currentPeriod + 1) {
				// Next period reached. Add to the list.
				queue.add(null);
				i++;
				currentPeriod = nextPeriod;
			}
		}
	}

}
