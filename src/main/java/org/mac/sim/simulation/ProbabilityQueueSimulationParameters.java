package org.mac.sim.simulation;

import java.util.List;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.WorkerConfiguration;

public class ProbabilityQueueSimulationParameters implements SimulationParameters {

	private int totalPeriods;
	private String periodUnits;
	private List<TaskConfiguration> taskConfigurations;
	private List<WorkerConfiguration> workerConfigurations;

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
