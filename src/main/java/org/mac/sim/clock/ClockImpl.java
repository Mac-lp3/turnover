package org.mac.sim.clock;

/**
 * Private implementation of Clock. This is to ensure that other classes that
 * require a Clock for synchronization cannot change it's internal state.
 * 
 * @author Home
 *
 */
class ClockImpl implements Clock {

	private int totalPeriods = 0;
	private volatile int currentPeriod = 0;
	private int nextPeriod = 1;

	/**
	 * 
	 * @param totalPeriods
	 *            The total periods to run this simulation
	 */
	ClockImpl(final int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public int getTotalPeriods() {
		return totalPeriods;
	}

	public int getCurrentPeriod() {
		return currentPeriod;
	}

	public void setTotalPeriods(int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public void setCurrentPeriod(int currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public int getNextPeriod() {
		return nextPeriod;
	}

	public void setNextPeriod(int nextPeriod) {
		this.nextPeriod = nextPeriod;
	}

	/**
	 * Increments the current period by 1 and sets a proper next period value.
	 */
	public void incrementPeriod() {
		this.currentPeriod++;
		this.nextPeriod = this.currentPeriod + 1;
	}

}
