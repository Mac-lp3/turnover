package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.List;

import org.mac.sim.domain.Worker;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.global.SimulationParameters;
import org.mac.sim.thread.WorkerTask;

/**
 * Simulations how a pool of workers accomplish a live queue of tasks by using a
 * for loop.
 * 
 * @author Home
 *
 */
class SimpleQueueSimulationImpl extends Simulation {

	public SimpleQueueSimulationImpl(final List<Worker> workers, final SimpleQueueSimulationParameters params)
			throws TurnoverException {

		super(workers, params);
	}

	protected void execute(final SimulationParameters params) throws TurnoverException {

		// Retrieve input details
		SimpleQueueSimulationParameters inputParams = (SimpleQueueSimulationParameters) params;
		int periodsToRun = inputParams.getPeriodsToRun();
		int tasksPerPeriod = inputParams.getTasksPerPeriod();

		ArrayList<WorkerTask> tasks = new ArrayList<WorkerTask>();
		completedTasks = new ArrayList<WorkerTask>();

		WorkerTask tempCompletedTask = null;
		WorkerTask tempNewTask = null;
		for (int i = 0; i < periodsToRun; i++) {

			// Add required tasks per period
			for (int j = 0; j < tasksPerPeriod; j++) {
				tempNewTask = new WorkerTask(j + 1);
				tempNewTask.setArrivalPeriod(i);
				tasks.add(tempNewTask);
			}

			// Allow each worker to act (or wait)
			for (Worker worker : workers) {

				// If a task was completed, add it to the completed list
				tempCompletedTask = worker.action(i, tasks);
				if (tempCompletedTask != null) {
					completedTasks.add(tempCompletedTask);
				}
			}

			// Update remaining tasks
			for (WorkerTask task : tasks) {
				task.incrementPeriodsInQueue();
			}
		}

	}

	public List<WorkerTask> getCompletedTasks() {
		// TODO Auto-generated method stub
		return completedTasks;
	}

}
