package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.List;

import org.mac.sim.thread.SimpleWorker;
import org.mac.sim.thread.WorkerTask;

public class SimpleQueueSimulationImpl implements Simulation {

	private int periodsToRun = 0;
	private List<SimpleWorker> workers;

	public void execute() throws InterruptedException {

		ArrayList<SimpleWorker> workers = new ArrayList<SimpleWorker>();
		ArrayList<WorkerTask> tasks = new ArrayList<WorkerTask>();
		ArrayList<WorkerTask> completedTasks = new ArrayList<WorkerTask>();

		for (int i = 0; i < 10; i++) {
			workers.add(new SimpleWorker());
		}

		WorkerTask tempCompletedTask = null;
		for (int i = 0; i < periodsToRun; i++) {

			for (int j = 0; j < 5; j++) {
				tasks.add(new WorkerTask(j + 1));
			}

			for (SimpleWorker worker : workers) {
				tempCompletedTask = worker.action(i, tasks);
				if (tempCompletedTask != null) {
					completedTasks.add(tempCompletedTask);
				}
			}

			for (WorkerTask task : tasks) {
				task.incrementPeriodsInQueue();
			}
		}

		for (SimpleWorker worker : workers) {
			System.out.println(worker.getTasksCompleted());
		}

		System.out.println("~~~~~~~~");

		for (WorkerTask task : tasks) {
			System.out.println(task.getPeriodsInQueue());
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

		if (workers == null) {
			workers = new ArrayList<SimpleWorker>();
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
			workers = new ArrayList<SimpleWorker>();
		}

		for (int i = 0; i < numberOfWorkers; i++) {
			workers.add(new SimpleWorker(periodsToCompleteTask, bufferPeriods));
		}
	}

}
