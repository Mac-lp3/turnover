package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
import org.mac.sim.clock.ClockBuilder;
import org.mac.sim.thread.Worker;
import org.mac.sim.thread.WorkerTask;

/**
 * Encapsulates all of the logic for construction of a Simulation. Ensures
 * proper size of queue is set and all workers, managers, etc are assigned the
 * same shared objects.
 * 
 * @author Home
 *
 */
public class QueueSimulationBuilder {

	private ArrayList<Worker> workers;
	private Clock clock;
	private BlockingQueue<WorkerTask> taskQueue;
	private int tasksPerPeriod;

	/**
	 * 
	 * @param clock
	 *            The clock to use for this simulation
	 * @param tasksPerPeriod
	 *            The number of tasks to be added to the queue each period
	 */
	public QueueSimulationBuilder(final Clock clock, final int tasksPerPeriod) {
		this.clock = clock;
		this.tasksPerPeriod = tasksPerPeriod;
		this.taskQueue = new ArrayBlockingQueue<WorkerTask>(tasksPerPeriod * clock.getTotalPeriods());
	}

	/**
	 * 
	 * @param totalPeriods
	 *            Total periods for the simulation to run. This will be used to
	 *            construct a clock internally.
	 * @param tasksPerPeriod
	 *            The number of tasks to be added to the queue each period
	 */
	public QueueSimulationBuilder(final int totalPeriods, final int tasksPerPeriod) {
		this.clock = ClockBuilder.build(totalPeriods);
		this.tasksPerPeriod = tasksPerPeriod;
		this.taskQueue = new ArrayBlockingQueue<WorkerTask>(tasksPerPeriod * totalPeriods);
	}

	/**
	 * 
	 * @return
	 */
	public Simulation build() {
		Simulation queueSimulation = new QueueSimulationImpl(clock, workers, taskQueue, tasksPerPeriod);
		return null;
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
			workers.add(new Worker(taskQueue, 0, 0, clock));
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
			workers.add(new Worker(taskQueue, periodsToCompleteTask, bufferPeriods, clock));
		}
	}

}
