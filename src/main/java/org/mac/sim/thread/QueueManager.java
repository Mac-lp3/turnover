package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
import org.mac.sim.domain.WorkerTask;
import org.mac.sim.global.PeriodConversionConstants;

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
	private Clock clock;
	private int ratePerPeriod;
	// private int totalPeriods;
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
		long nanoTimePeriodLenth = PeriodConversionConstants.PERIOD_LENGTH_NANOS;
		long nextPeriod = 1;
		long periodToCheck = 0;
		long startTime = System.nanoTime();

		while (this.periodsSpentInLoop < tempTotalPeriods) {

			/*
			 * A period is defined by 10000000 nanoseconds. This number may be
			 * subject to change based on your CPU and the number of tasks to
			 * add each period. If the tests fail, try altering this number to
			 * better suit your hardware/model requirements.
			 */
			periodToCheck = Math.floorDiv((System.nanoTime() - startTime), nanoTimePeriodLenth);

			if (periodToCheck >= nextPeriod) {

				// Next period reached. Add required tasks to the list.
				for (j = 0; j < ratePerPeriod; j++) {
					queue.add(new WorkerTask(0));
					addActions++;
				}

				this.periodsSpentInLoop++;
				nextPeriod = periodToCheck + 1;
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
