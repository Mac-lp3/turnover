package org.mac.sim.simulation;

import java.util.ArrayList;

import org.mac.sim.domain.SimulationConfigurations;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;

public class ProbabilityQueueSimulationBuilder extends SimulationBuilder {

	public ProbabilityQueueSimulationBuilder(SimulationConfigurations simulationConfig) {

		super(simulationConfig);

		this.simulationParams = new ProbabilityQueueSimulationParameters();
		this.simulationParams.setPeriodUnits(simulationConfig.getClockUnits());
		this.simulationParams.setTotalPeriods(simulationConfig.getTotalPeriods());

		// Empty list is fine. Initialize if not.
		if (simulationConfig.getTaskConfigurations() != null) {
			((ProbabilityQueueSimulationParameters) this.simulationParams)
					.setTaskConfigurations(simulationConfig.getTaskConfigurations());
		} else {
			((ProbabilityQueueSimulationParameters) this.simulationParams)
					.setTaskConfigurations(new ArrayList<TaskConfiguration>());
		}

		// Empty list is fine. Initialize if not.
		if (simulationConfig.getWorkerConfigurations() != null) {
			((ProbabilityQueueSimulationParameters) this.simulationParams)
					.setWorkerConfigurations(simulationConfig.getWorkerConfigurations());
		} else {
			((ProbabilityQueueSimulationParameters) this.simulationParams)
					.setWorkerConfigurations(new ArrayList<WorkerConfiguration>());
		}

	}

	public void addWorkers(WorkerConfiguration workerConfig) {
		((ProbabilityQueueSimulationParameters) this.simulationParams).getWorkerConfigurations().add(workerConfig);
	}

	@Override
	public Simulation build() throws TurnoverException {

		Simulation sim = new ProbabilityQueueSimulationImpl(null, simulationParams);

		return sim;
	}

}
