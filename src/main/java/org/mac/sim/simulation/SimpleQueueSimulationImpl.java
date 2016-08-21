package org.mac.sim.simulation;

import java.util.List;
import java.util.Random;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.SimpleWorker;
import org.mac.sim.thread.WorkerTask;

/**
 * Simulations how a pool of workers accomplish a live queue of tasks by using a
 * for loop.
 * 
 * @author Home
 *
 */
class SimpleQueueSimulationImpl extends Simulation {

	private WorkerConfiguration workerConfiguration;
	private TaskConfiguration taskConfiguration;

	// Used for task length generation
	private Random random;
	private int tasksPerPeriod = 0;

	public SimpleQueueSimulationImpl(final List<Worker> workers, final SimpleQueueSimulationParameters params)
			throws TurnoverException {

		super(null, params);

	}

	protected void execute(final SimulationParameters params) throws TurnoverException {

		this.workerConfiguration = ((SimpleQueueSimulationParameters) params).getWorkerConfiguration();
		this.taskConfiguration = ((SimpleQueueSimulationParameters) params).getTaskConfiguration();
		this.tasksPerPeriod = this.taskConfiguration.getArrivalRate();

		// Add the workers
		addWorkers(0);

		// Get total periods to run
		int totalPeriods = ((SimpleQueueSimulationParameters) params).getTotalPeriods();

		WorkerTask tempCompletedTask = null;
		for (int i = 0; i < totalPeriods; i++) {

			// Add required tasks per period
			addTasks(i);

			// Allow each worker to act (or wait)
			for (Worker worker : workers) {

				// If a task was completed, add it to the completed list
				tempCompletedTask = worker.action(i, tasks);
				if (tempCompletedTask != null) {
					tempCompletedTask.setPeriodServiced(i);
					completedTasks.add(tempCompletedTask);
				}
			}

			// Update remaining tasks
			for (WorkerTask task : tasks) {
				task.incrementPeriodsInQueue();
			}
		}

	}

	protected void addTasks(final int currentPeriod) {

		// Add required tasks per period
		WorkerTask tempNewTask;
		for (int j = 0; j < tasksPerPeriod; j++) {
			tempNewTask = new WorkerTask(getTaskLength());
			tempNewTask.setArrivalPeriod(currentPeriod);
			this.tasks.add(tempNewTask);
		}

	}

	/**
	 * Since only one set of workers is used through a simple simulation, this
	 * is called at start
	 * 
	 * @param currentPeriod
	 */
	protected void addWorkers(final int currentPeriod) {

		for (int i = 0; i < workerConfiguration.getTotal(); i++) {
			workers.add(new SimpleWorker(0, workerConfiguration.getRestTime()));
		}

	}

	public List<WorkerTask> getCompletedTasks() {

		return completedTasks;
	}

	@Override
	protected int getTaskLength() {

		random = new Random();

		if (taskConfiguration.getTaskLength() == 0) {
			return random.nextInt(10 - 1) + 1;
		}

		return ((SimpleQueueSimulationParameters) simulationParameters).getDefaultTaskLength();
	}

}
