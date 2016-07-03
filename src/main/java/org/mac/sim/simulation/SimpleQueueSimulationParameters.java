package org.mac.sim.simulation;

import org.mac.sim.global.SimulationParameters;

class SimpleQueueSimulationParameters implements SimulationParameters {

	private int periodsToRun;
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

	public int getPeriodsToRun() {
		return periodsToRun;
	}

	public int getTasksPerPeriod() {
		return tasksPerPeriod;
	}

}
