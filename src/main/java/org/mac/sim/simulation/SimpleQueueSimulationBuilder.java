package org.mac.sim.simulation;

import java.util.ArrayList;

import org.mac.sim.domain.SimulationConfigrations;
import org.mac.sim.domain.Worker;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.SimpleWorker;

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
	private int tasksPerPeriod;
	private int periodsToRun;
	private int defaultTaskLength;

	/**
	 * Constructor performs minimal initialization required for safety. The rest
	 * is left to the user to define values.
	 * 
	 * @param simulationConfig
	 */
	public SimpleQueueSimulationBuilder(SimulationConfigrations simulationConfig) {

		super(simulationConfig);

		// Simple only uses one configuration
		this.tasksPerPeriod = simulationConfig.getTaskConfigurations().get(0).getRate();
		this.periodsToRun = simulationConfig.getTotalPeriods();
		this.defaultTaskLength = simulationConfig.getTaskConfigurations().get(0).getLength();
	}

	/**
	 * Adds the given number of workers to the Worker pool. The workers will
	 * have values of 0 for their buffer periods and their time to complete a
	 * task.
	 * 
	 * @param numberOfWorkers
	 */
	public void addWorkers(final int numberOfWorkers) {

		if (workers == null) {
			workers = new ArrayList<Worker>();
		}

		for (int i = 0; i < numberOfWorkers; i++) {
			workers.add(new SimpleWorker());
		}
	}

	/**
	 * Adds the given number of workers to the Worker pool. The workers will be
	 * assigned the provided buffer period and time to complete task values.
	 * 
	 * @param numberOfWorkers
	 * @param periodsToCompleteTask
	 * @param bufferPeriods
	 */
	public void addWorkers(final int numberOfWorkers, final int periodsToCompleteTask, final int bufferPeriods) {

		if (workers == null) {
			workers = new ArrayList<Worker>();
		}

		for (int i = 0; i < numberOfWorkers; i++) {
			workers.add(new SimpleWorker(periodsToCompleteTask, bufferPeriods));
		}
	}

	/**
	 * 
	 * @return
	 * @throws TurnoverException
	 */
	public Simulation build() throws TurnoverException {

		Simulation queueSimulation = new SimpleQueueSimulationImpl(workers,
				new SimpleQueueSimulationParameters(periodsToRun, tasksPerPeriod, defaultTaskLength));

		return queueSimulation;
	}

	public int getDefaultTaskLength() {
		return defaultTaskLength;
	}

	public void setDefaultTaskLength(int defaultTaskLength) {
		this.defaultTaskLength = defaultTaskLength;
	}
}
