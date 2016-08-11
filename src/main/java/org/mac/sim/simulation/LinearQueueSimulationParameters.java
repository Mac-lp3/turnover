package org.mac.sim.simulation;

import java.util.List;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.WorkerConfiguration;

public class LinearQueueSimulationParameters implements SimulationParameters {

	private int totalPeriods;
	private List<TaskConfiguration> taskConfigurations;
	private List<WorkerConfiguration> workerConfigurations;

	public int getTotalPeriods() {
		return totalPeriods;
	}

	public void setTotalPeriods(int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public List<TaskConfiguration> getTaskConfigurations() {
		return taskConfigurations;
	}

	public void setTaskConfigurations(List<TaskConfiguration> taskConfigurations) {
		this.taskConfigurations = taskConfigurations;
	}

	public List<WorkerConfiguration> getWorkerConfigurations() {
		return workerConfigurations;
	}

	public void setWorkerConfigurations(List<WorkerConfiguration> workerConfigurations) {
		this.workerConfigurations = workerConfigurations;
	}

}
