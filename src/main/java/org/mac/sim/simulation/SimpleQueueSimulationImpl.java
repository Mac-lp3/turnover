package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.List;

import org.mac.sim.domain.Worker;
import org.mac.sim.thread.WorkerTask;

class SimpleQueueSimulationImpl extends Simulation {

	public SimpleQueueSimulationImpl(final int periodsToRun, final List<Worker> workers, final int tasksPerPeriod)
			throws InterruptedException {
		super(periodsToRun, workers, tasksPerPeriod);
	}

	@Override
	protected void execute() throws InterruptedException {

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
