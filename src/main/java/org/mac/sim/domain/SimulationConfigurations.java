package org.mac.sim.domain;

import java.util.List;

public class SimulationConfigurations {

	private String clockUnits;
	private int totalPeriods;
	private List<WorkerConfiguration> workerConfigurations;
	private List<TaskConfiguration> taskConfigurations;

	public String getClockUnits() {
		return clockUnits;
	}

	public void setClockUnits(String clockUnits) {
		this.clockUnits = clockUnits;
	}

	public int getTotalPeriods() {
		return totalPeriods;
	}

	public void setTotalPeriods(int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public List<WorkerConfiguration> getWorkerConfigurations() {
		return workerConfigurations;
	}

	public void setWorkerConfigurations(List<WorkerConfiguration> workerConfigurations) {
		this.workerConfigurations = workerConfigurations;
	}

	public List<TaskConfiguration> getTaskConfigurations() {
		return taskConfigurations;
	}

	public void setTaskConfigurations(List<TaskConfiguration> taskConfigurations) {
		this.taskConfigurations = taskConfigurations;
	}

}
