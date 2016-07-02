package org.mac.sim.simulation;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
import org.mac.sim.clock.ClockManager;
import org.mac.sim.domain.Worker;
import org.mac.sim.thread.WorkQueueManager;
import org.mac.sim.thread.WorkerTask;

/**
 * Executes the simulation logic and provides access to workers/tasks for
 * analysis.
 * 
 * @author Home
 *
 */
public abstract class Simulation {

	protected List<WorkerTask> completedTasks;
	protected List<Worker> workers;
	protected int tasksPerPeriod;
	protected int periodsToRun;
	protected ClockManager clockManager;
	protected BlockingQueue<WorkerTask> taskQueue;
	protected WorkQueueManager queueManager;

	/**
	 * Constructor enforces that the simulation logic is executed before the
	 * user can access any report information.
	 * 
	 * @throws InterruptedException
	 */
	protected Simulation(final int periodsToRun, final List<Worker> workers, final int tasksPerPeriod)
			throws InterruptedException {
		this.periodsToRun = periodsToRun;
		this.tasksPerPeriod = tasksPerPeriod;
		this.workers = workers;
		execute();
	}
	
	protected Simulation(final Clock clock, final List<Worker> workers, final BlockingQueue<WorkerTask> taskQueue,
			final int ratePerPeriod) throws InterruptedException {

		this.clockManager = new ClockManager(clock);
		this.workers = workers;
		this.taskQueue = taskQueue;
		this.queueManager = new WorkQueueManager(taskQueue, ratePerPeriod, clock);
		execute();
	}

	/**
	 * Holds the simulation logic. Must populate the completedTasksList for use
	 * in reporting.
	 * 
	 * @throws InterruptedException
	 */
	protected abstract void execute() throws InterruptedException;

	public List<WorkerTask> getCompletedTasks() {
		return completedTasks;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

}
