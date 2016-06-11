package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

import org.mac.sim.domain.WorkerTask;

public class QueueManager extends Thread {

	private BlockingQueue queue;
	private int ratePerPeriod;
	private int totalPeriods;
	private boolean stop;
	private long periodsSpentInLoop = 0;
	private int addActions = 0;

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
		int j = 0;
		long currentPeriod = 0;
		long nextPeriod = 0;
		long startTime = System.nanoTime();

		while (i < totalPeriods) {

			nextPeriod = Math.floorDiv((System.nanoTime() - startTime), 1000000);

			if (nextPeriod >= currentPeriod + 1) {

				// Next period reached. Add to the list.
				for (j = 0; j < ratePerPeriod; j++) {
					queue.add(new WorkerTask(0));
					addActions++;
				}

				i++;
				currentPeriod = nextPeriod;
			}
		}
		
		periodsSpentInLoop = currentPeriod;
	}
	
	public long getPeriodsSpentInLoop(){
		return periodsSpentInLoop;
	}

	public int getAddActions() {
		return addActions;
	}

}
