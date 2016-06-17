package org.mac.sim.thread;

import org.mac.sim.global.PeriodConversionConstants;

public class SimClock extends Thread {

	private int totalPeriods = 0;
	private int currentPeriod = 0;
	private boolean runFlag = false;

	public SimClock(final int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public void run() {

		runFlag = true;

		long nextPeriod = 0;
		long periodToCheck = 1;
		long startTime = System.nanoTime();

		while (runFlag && currentPeriod < totalPeriods) {

			nextPeriod = Math.floorDiv((System.nanoTime() - startTime), PeriodConversionConstants.PERIOD_LENGTH_NANOS);

			if (nextPeriod >= periodToCheck) {

				currentPeriod++;
				periodToCheck = nextPeriod + 1;
			}
		}

	}

	public void doStop() {
		runFlag = false;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

	public int getTotalPeriods() {
		return totalPeriods;
	}

	public void setTotalPeriods(int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public int getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(int currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

}
