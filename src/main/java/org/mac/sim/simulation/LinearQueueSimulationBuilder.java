package org.mac.sim.simulation;

import java.util.ArrayList;

import org.mac.sim.domain.SimulationConfigurations;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;

public class LinearQueueSimulationBuilder extends SimulationBuilder {

	public LinearQueueSimulationBuilder(int totalPeriods, String units) {

		super(null);
		this.simulationParams = new LinearQueueSimulationParameters();
		simulationParams.setTotalPeriods(totalPeriods);
		simulationParams.setPeriodUnits(units);
	}

	public LinearQueueSimulationBuilder(SimulationConfigurations simulationConfig) {

		super(simulationConfig);

		this.simulationParams = new LinearQueueSimulationParameters();

		if (simulationConfig != null) {
			
			((LinearQueueSimulationParameters) simulationParams)
					.setTaskConfigurations(simulationConfig.getTaskConfigurations());
			
			((LinearQueueSimulationParameters) simulationParams)
					.setWorkerConfigurations(simulationConfig.getWorkerConfigurations());
			
			((LinearQueueSimulationParameters) simulationParams).setTotalPeriods(simulationConfig.getTotalPeriods());
			
			((LinearQueueSimulationParameters) simulationParams).setPeriodUnits(simulationConfig.getClockUnits());
		}

	}

	public Simulation build() throws TurnoverException {

		Simulation queueSimulation = new LinearQueueSimulationImpl(null, simulationParams);

		return queueSimulation;
	}

	public void addWorkerConfig(WorkerConfiguration workerConfiguration) {

		if (((LinearQueueSimulationParameters) simulationParams).getWorkerConfigurations() == null) {
			((LinearQueueSimulationParameters) simulationParams)
					.setWorkerConfigurations(new ArrayList<WorkerConfiguration>());
		}

		((LinearQueueSimulationParameters) simulationParams).getWorkerConfigurations().add(workerConfiguration);
	}

	public void addTaskConfig(TaskConfiguration taskConfiguration) {

		if (((LinearQueueSimulationParameters) simulationParams).getTaskConfigurations() == null) {
			((LinearQueueSimulationParameters) simulationParams)
					.setTaskConfigurations(new ArrayList<TaskConfiguration>());
		}

		((LinearQueueSimulationParameters) simulationParams).getTaskConfigurations().add(taskConfiguration);
	}

}
