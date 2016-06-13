package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

import org.mac.sim.domain.WorkerTask;

/**
 * This class encapsulates the task of adding tasks to the queue at a specified
 * rate. It runs in it's own separate thread and adds a specified number of
 * tasks every interval, for a given number of intervals.
 * 
 * @author Mac-LP3
 *
 */
public class QueueManager extends Thread {

	private BlockingQueue<WorkerTask> queue;
	private int ratePerPeriod;
	private int totalPeriods;
	private boolean stop = true;
	private long periodsSpentInLoop = 0;
	private int addActions = 0;

	/**
	 * 
	 * @param queue
	 *            The queue to add tasks to.
	 * @param ratePerPeriod
	 *            The amount of tasks to add each period.
	 * @param totalPeriods
	 *            The number of periods to run.
	 */
	public QueueManager(BlockingQueue<WorkerTask> queue, int ratePerPeriod, int totalPeriods) {
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

		this.stop = false;

		int i = 0;
		int j = 0;
		long currentPeriod = 0;
		long nextPeriod = 0;
		long periodToCheck = 1;
		long startTime = System.nanoTime();

		while (i < totalPeriods) {

			nextPeriod = Math.floorDiv((System.nanoTime() - startTime), 10000000);

			if (nextPeriod >= periodToCheck) {

				// Next period reached. Add to the list.
				for (j = 0; j < ratePerPeriod; j++) {
					queue.add(new WorkerTask(0));
					addActions++;
				}

				i++;
				// currentPeriod = nextPeriod;
				periodToCheck = nextPeriod + 1;
			}
		}

		periodsSpentInLoop = nextPeriod;

	}

	public long getPeriodsSpentInLoop() {
		return periodsSpentInLoop;
	}

	public int getAddActions() {
		return addActions;
	}

	public synchronized void doStop() {
		stop = true;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

	public synchronized boolean isStopped() {
		return stop;
	}

}
