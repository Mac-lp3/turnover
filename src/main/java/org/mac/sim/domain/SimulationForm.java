package org.mac.sim.domain;

public class SimulationForm {

	private String quickStartUnits;
	private int quickStartPeriods;
	private int quickStartWorkers;
	private int quickStartRate;
	private int quickStartTaskTime;
	private int quickStartRestTime;

	public String getQuickStartUnits() {
		return quickStartUnits;
	}

	public void setQuickStartUnits(String quickStartUnits) {
		this.quickStartUnits = quickStartUnits;
	}

	public int getQuickStartPeriods() {
		return quickStartPeriods;
	}

	public void setQuickStartPeriods(int quickStartPeriods) {
		this.quickStartPeriods = quickStartPeriods;
	}

	public int getQuickStartWorkers() {
		return quickStartWorkers;
	}

	public void setQuickStartWorkers(int quickStartWorkers) {
		this.quickStartWorkers = quickStartWorkers;
	}

	public int getQuickStartRate() {
		return quickStartRate;
	}

	public void setQuickStartRate(int quickStartRate) {
		this.quickStartRate = quickStartRate;
	}

	public int getQuickStartRestTime() {
		return quickStartRestTime;
	}

	public void setQuickStartRestTime(int quickStartRestTime) {
		this.quickStartRestTime = quickStartRestTime;
	}

	public int getQuickStartTaskTime() {
		return quickStartTaskTime;
	}

	public void setQuickStartTaskTime(int quickStartTaskTime) {
		this.quickStartTaskTime = quickStartTaskTime;
	}

}
