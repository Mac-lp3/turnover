package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
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

	private volatile BlockingQueue<WorkerTask> queue;
	private volatile Clock clock;

	private int ratePerPeriod;
	private boolean stop = true;
	private long periodsSpentInLoop = 0;
	private int addActions = 0;

	/**
	 * 
	 * @param queue
	 *            The queue to add tasks to.
	 * @param ratePerPeriod
	 *            The amount of tasks to add each period.
	 * @param clock
	 *            The clock tracking the passage of periods.
	 */
	public QueueManager(final BlockingQueue<WorkerTask> queue, final int ratePerPeriod, final Clock clock) {
		this.queue = queue;
		this.ratePerPeriod = ratePerPeriod;
		this.clock = clock;
	}

	/**
	 * Will continually watch the system clock until the next "period" has been
	 * reached. Will then update the queue with the given number of new
	 * arrivals.
	 */
	public void run() {

		this.stop = false;
		this.periodsSpentInLoop = 0;
		int j = 0;
		int tempTotalPeriods = clock.getTotalPeriods();

		int currentPeriod = clock.getCurrentPeriod();
		while (currentPeriod < tempTotalPeriods) {

			// watch clock for a period change
			if (currentPeriod < clock.getCurrentPeriod()) {

				// Add the specified number of jobs to the queue
				for (j = 0; j < ratePerPeriod; j++) {
					queue.add(new WorkerTask(0));
					addActions++;
				}

				this.periodsSpentInLoop++;
				currentPeriod = clock.getCurrentPeriod();

			}
		}

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
