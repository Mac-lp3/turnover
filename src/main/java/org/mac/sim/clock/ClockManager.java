package org.mac.sim.clock;

import org.mac.sim.global.PeriodConversionConstants;

/**
 * Thread that manages the tracking of periods based on the provided nanosecond
 * interval. Stores the current period in the Clock passed into the constructor.
 * 
 * @author Home
 *
 */
public class ClockManager extends Thread {

	private volatile ClockImpl clock;
	private volatile boolean runFlag = false;

	public ClockManager(final Clock clock) {
		this.clock = (ClockImpl) clock;
	}

	public void run() {

		runFlag = true;

		int tempCurrentPeriod = clock.getCurrentPeriod();
		int tempTotalPeriods = clock.getTotalPeriods();
		long nextPeriod = 1;
		long periodToCheck = 0;
		long startTime = System.nanoTime();

		while (runFlag && tempCurrentPeriod < tempTotalPeriods) {

			// nanos that have passed divide by period length (rounded down).
			periodToCheck = Math.floorDiv((System.nanoTime() - startTime),
					PeriodConversionConstants.PERIOD_LENGTH_NANOS);

			// If value is larger than current, the period has changed
			if (periodToCheck >= nextPeriod) {

				// Increment the global period count
				clock.incrementPeriod();
				tempCurrentPeriod = clock.getCurrentPeriod();
				nextPeriod = tempCurrentPeriod + 1;

				// Sleep so other threads can perform their task
				try {

					Thread.sleep(60);
					// Reset start time
					startTime = System.nanoTime();

				} catch (Exception e) {
					// Should let the user know about this some how
					e.printStackTrace();
					doStop();
				}
			}
		}

		doStop();

	}

	public void doStop() {
		runFlag = false;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

	public boolean isStopped() {
		return !runFlag;
	}

}
