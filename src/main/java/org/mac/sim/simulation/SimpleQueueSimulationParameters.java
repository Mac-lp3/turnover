package org.mac.sim.simulation;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.WorkerConfiguration;

class SimpleQueueSimulationParameters implements SimulationParameters {

	private int defaultTaskLength;
	private int tasksPerPeriod;
	private int totalPeriods;
	private String periodUnits;

	// SimpleSim only uses one "flat" config for both
	private WorkerConfiguration workerConfiguration;
	private TaskConfiguration taskConfiguration;

	public SimpleQueueSimulationParameters() {

	}

	/**
	 * 
	 * @param periodsToRun
	 * @param tasksPerPeriod
	 */
	SimpleQueueSimulationParameters(final int periodsToRun, final int tasksPerPeriod) {
		this.totalPeriods = periodsToRun;
		this.tasksPerPeriod = tasksPerPeriod;
	}

	SimpleQueueSimulationParameters(final int periodsToRun, final int tasksPerPeriod, final int defaultTaskLength) {
		this.totalPeriods = periodsToRun;
		this.tasksPerPeriod = tasksPerPeriod;
		this.defaultTaskLength = defaultTaskLength;
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

	public int getTotalPeriods() {
		return totalPeriods;
	}

	public void setTotalPeriods(int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public String getPeriodUnits() {
		return periodUnits;
	}

	public void setPeriodUnits(String periodUnits) {
		this.periodUnits = periodUnits;
	}

	public void setTasksPerPeriod(int tasksPerPeriod) {
		this.tasksPerPeriod = tasksPerPeriod;
	}

	public WorkerConfiguration getWorkerConfiguration() {
		return workerConfiguration;
	}

	public void setWorkerConfiguration(WorkerConfiguration workerConfiguration) {
		this.workerConfiguration = workerConfiguration;
	}

	public TaskConfiguration getTaskConfiguration() {
		return taskConfiguration;
	}

	public void setTaskConfiguration(TaskConfiguration taskConfiguration) {
		this.taskConfiguration = taskConfiguration;
	}

}
