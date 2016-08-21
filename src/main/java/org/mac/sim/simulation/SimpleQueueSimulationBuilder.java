package org.mac.sim.simulation;

import java.util.ArrayList;

import org.mac.sim.domain.SimulationConfigurations;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;

/**
 * The purpose of this class is to collect all of the simulation parameters and
 * construct any specific classes and set any required internal values
 * accordingly. It uses reasonable defaults for any required but not specified
 * functionality.
 * 
 * @author Mac-LP3
 *
 */
public class SimpleQueueSimulationBuilder extends SimulationBuilder {

	private ArrayList<Worker> workers;

	/**
	 * Constructor performs minimal initialization required for safety. The rest
	 * is left to the user to define values.
	 * 
	 * @param simulationConfig
	 */
	public SimpleQueueSimulationBuilder(SimulationConfigurations simulationConfig) {

		super(simulationConfig);

		this.simulationParams = new SimpleQueueSimulationParameters();
		simulationParams.setPeriodUnits(simulationConfig.getClockUnits());
		simulationParams.setTotalPeriods(simulationConfig.getTotalPeriods());

		if (simulationConfig.getTaskConfigurations() != null && simulationConfig.getTaskConfigurations().size() > 0) {

			// SimpleSim only uses one task configuration for rate and length
			((SimpleQueueSimulationParameters) simulationParams)
					.setTaskConfiguration(simulationConfig.getTaskConfigurations().get(0));
		}

		if (simulationConfig.getWorkerConfigurations() != null
				&& simulationConfig.getWorkerConfigurations().size() > 0) {

			// SimpleSim only uses one worker configuration
			((SimpleQueueSimulationParameters) simulationParams)
					.setWorkerConfiguration(simulationConfig.getWorkerConfigurations().get(0));
		}

	}

	/**
	 * Adds the given number of workers to the Worker pool. The workers will
	 * have values of 0 for their buffer periods and their time to complete a
	 * task.
	 * 
	 * @param numberOfWorkers
	 */
	public void addWorkers(final int numberOfWorkers) {

		WorkerConfiguration wc = new WorkerConfiguration();
		wc.setTotal(numberOfWorkers);
		wc.setRestTime(0);
		wc.setAdditionalTime(0);
		wc.setArrivalPeriod(0);

		((SimpleQueueSimulationParameters) simulationParams).setWorkerConfiguration(wc);
	}

	/**
	 * Adds the given number of workers to the Worker pool. The workers will be
	 * assigned the provided buffer period and time to complete task values.
	 * 
	 * @param numberOfWorkers
	 * @param periodsToCompleteTask
	 * @param bufferPeriods
	 */
	public void addWorkers(final int numberOfWorkers, final int restPeriods) {

		WorkerConfiguration wc = new WorkerConfiguration();
		wc.setTotal(numberOfWorkers);
		wc.setRestTime(restPeriods);
		wc.setAdditionalTime(0);
		wc.setArrivalPeriod(0);

		((SimpleQueueSimulationParameters) simulationParams).setWorkerConfiguration(wc);
	}

	/**
	 * 
	 * @return
	 * @throws TurnoverException
	 */
	public Simulation build() throws TurnoverException {

		Simulation queueSimulation = new SimpleQueueSimulationImpl(workers,
				(SimpleQueueSimulationParameters) simulationParams);

		return queueSimulation;
	}

}
