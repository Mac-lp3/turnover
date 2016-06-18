package org.mac.sim.clock;

class ClockImpl implements Clock {

	private int totalPeriods = 0;
	private int currentPeriod = 0;

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

}
