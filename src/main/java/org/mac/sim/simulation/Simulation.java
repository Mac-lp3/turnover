package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.List;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.WorkerTask;

/**
 * Implementations of this class will maintain the actual simulation logic. This
 * class provides access methods to the worker and completed task lists for
 * analysis later.
 * 
 * @author Home
 *
 */
public abstract class Simulation {

	protected List<WorkerTask> completedTasks;
	protected List<WorkerTask> tasks;
	protected List<Worker> workers;
	protected SimulationParameters simulationParameters;
	protected List<WorkerConfiguration> workerConfigurations;
	protected List<TaskConfiguration> taskConfigurations;

	/**
	 * Constructor enforces that the simulation logic is executed before the
	 * user can access any report information.
	 * 
	 * @throws InterruptedException
	 */
	protected Simulation(final List<Worker> workers, final SimulationParameters simulationParameters)
			throws TurnoverException {

		this.workers = workers;
		if (this.workers == null) {
			this.workers = new ArrayList<Worker>();
		}

		this.tasks = new ArrayList<WorkerTask>();
		this.simulationParameters = simulationParameters;
		this.completedTasks = new ArrayList<WorkerTask>();
		execute(simulationParameters);

	}

	/**
	 * Holds the simulation logic. Must populate the completedTasksList for use
	 * in reporting.
	 * 
	 * @throws InterruptedException
	 */
	protected abstract void execute(final SimulationParameters params) throws TurnoverException;

	/**
	 * When adding tasks to the queue, a length must be specified. This method
	 * should provide some logical default value or generate a value according
	 * to the user's request.
	 * 
	 * @return
	 */
	protected abstract int getTaskLength();

	protected abstract void addWorkers(final int currentPeriod);

	protected abstract void addTasks(final int currentPeriod);

	public List<WorkerTask> getCompletedTasks() {
		return completedTasks;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public List<WorkerConfiguration> getWorkerConfigurations() {
		return workerConfigurations;
	}

	public List<TaskConfiguration> getTaskConfigurations() {
		return taskConfigurations;
	}

}
