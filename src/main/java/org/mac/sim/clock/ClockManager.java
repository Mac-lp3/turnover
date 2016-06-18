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

	private ClockImpl clock;
	private boolean runFlag = false;

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

			// Find how many nanos have passed since the start and divide by
			// period length. Floor the result.
			periodToCheck = Math.floorDiv((System.nanoTime() - startTime),
					PeriodConversionConstants.PERIOD_LENGTH_NANOS);

			if (periodToCheck >= nextPeriod) {

				tempCurrentPeriod++;
				clock.setCurrentPeriod(tempCurrentPeriod);
				nextPeriod = periodToCheck + 1;
			}
		}

	}

	public void doStop() {
		runFlag = false;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

}
