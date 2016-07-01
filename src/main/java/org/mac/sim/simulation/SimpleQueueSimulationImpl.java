package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.List;

import org.mac.sim.thread.SimpleWorker;
import org.mac.sim.thread.WorkerTask;

class SimpleQueueSimulationImpl implements Simulation {

	private int periodsToRun = 0;
	private List<SimpleWorker> workers;
	private int tasksPerPeriod = 0;
	private List<WorkerTask> completedTasks;

	public SimpleQueueSimulationImpl(final int periodsToRun, final List<SimpleWorker> workers,
			final int tasksPerPeriod) {
		this.periodsToRun = periodsToRun;
		this.workers = workers;
		this.tasksPerPeriod = tasksPerPeriod;
	}

	public void execute() throws InterruptedException {

		ArrayList<WorkerTask> tasks = new ArrayList<WorkerTask>();
		completedTasks = new ArrayList<WorkerTask>();

		WorkerTask tempCompletedTask = null;
		for (int i = 0; i < periodsToRun; i++) {

			for (int j = 0; j < tasksPerPeriod; j++) {
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

	}

	public List<WorkerTask> getCompletedTasks() {
		// TODO Auto-generated method stub
		return completedTasks;
	}

}
