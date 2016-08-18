package org.mac.sim.simulation;

import java.util.ArrayList;

import org.mac.sim.domain.SimulationConfigrations;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;

public class LinearQueueSimulationBuilder extends SimulationBuilder {

	private LinearQueueSimulationParameters params;

	public LinearQueueSimulationBuilder(int totalPeriods, String units) {

		super(null);
		this.params = new LinearQueueSimulationParameters();
		params.setTotalPeriods(totalPeriods);
	}

	public LinearQueueSimulationBuilder(SimulationConfigrations simulationConfig) {

		super(simulationConfig);

		this.params = new LinearQueueSimulationParameters();

		if (simulationConfig != null) {
			params.setTaskConfigurations(simulationConfig.getTaskConfigurations());
			params.setWorkerConfigurations(simulationConfig.getWorkerConfigurations());
		}

	}

	public Simulation build() throws TurnoverException {

		Simulation queueSimulation = new LinearQueueSimulationImpl(null, params);

		return queueSimulation;
	}

	public void addWorkerConfig(WorkerConfiguration workerConfiguration) {

		if (params.getWorkerConfigurations() == null) {
			params.setWorkerConfigurations(new ArrayList<WorkerConfiguration>());
		}

		params.getWorkerConfigurations().add(workerConfiguration);
	}

	public void addTaskConfig(TaskConfiguration taskConfiguration) {

		if (params.getTaskConfigurations() == null) {
			params.setTaskConfigurations(new ArrayList<TaskConfiguration>());
		}

		params.getTaskConfigurations().add(taskConfiguration);
	}

}
