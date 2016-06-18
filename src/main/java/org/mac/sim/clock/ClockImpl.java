package org.mac.sim.clock;

class ClockImpl implements Clock {

	private int totalPeriods = 0;
	private volatile int currentPeriod = 0;
	private int nextPeriod = 1;

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

	public void incrementPeriod() {
		this.currentPeriod++;
		this.nextPeriod = this.currentPeriod + 1;
	}

}
