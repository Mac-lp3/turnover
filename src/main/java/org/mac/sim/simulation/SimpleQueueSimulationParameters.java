package org.mac.sim.simulation;

import org.mac.sim.domain.SimulationParameters;

class SimpleQueueSimulationParameters implements SimulationParameters {

	private int periodsToRun;
	private int defaultTaskLength;
	private int tasksPerPeriod;

	/**
	 * 
	 * @param periodsToRun
	 * @param tasksPerPeriod
	 */
	SimpleQueueSimulationParameters(final int periodsToRun, final int tasksPerPeriod) {
		this.periodsToRun = periodsToRun;
		this.tasksPerPeriod = tasksPerPeriod;
	}
	
	SimpleQueueSimulationParameters(final int periodsToRun, final int tasksPerPeriod, final int defaultTaskLength) {
		this.periodsToRun = periodsToRun;
		this.tasksPerPeriod = tasksPerPeriod;
		this.defaultTaskLength = defaultTaskLength;
	}

	public int getPeriodsToRun() {
		return periodsToRun;
	}

	public int getTasksPerPeriod() {
		return tasksPerPeriod;
	}

	public int getDefaultTaskLength() {
		return defaultTaskLength;
	}

	public void setDefaultTaskLength(int defaultTaskLength) {
		this.defaultTaskLength = defaultTaskLength;
	}

}
