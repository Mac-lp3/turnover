package org.mac.sim.thread;

import java.util.List;

import org.mac.sim.domain.Worker;

public class SimpleWorker implements Worker {
	private int periodsToCompleteTask = 0;
	private int bufferPeriods = 0;
	private int nextActionablePeriod = 0;
	private int tasksCompleted = 0;

	public SimpleWorker() {

	}

	public SimpleWorker(int periodsToCompleteTask, int bufferPeriods) {
		this.periodsToCompleteTask = periodsToCompleteTask;
		this.bufferPeriods = bufferPeriods;
	}

	public WorkerTask action(final int currentPeriod, List<WorkerTask> taskQueue) {

		int tempTaskLength = 0;
		WorkerTask tempTask = null;

		if (currentPeriod >= nextActionablePeriod && !taskQueue.isEmpty()) {

			tempTask = taskQueue.remove(0);

			tempTaskLength = Integer.valueOf(Long.toString(tempTask.getServiceTimeRequired()));

			nextActionablePeriod = currentPeriod + tempTaskLength + bufferPeriods + periodsToCompleteTask;

			tasksCompleted++;
		}

		return tempTask;

	}

	public int getTasksCompleted() {
		return tasksCompleted;
	}

	public int getPeriodsToCompleteTask() {
		return periodsToCompleteTask;
	}

	public void setPeriodsToCompleteTask(int periodsToCompleteTask) {
		this.periodsToCompleteTask = periodsToCompleteTask;
	}

	public int getBufferPeriods() {
		return bufferPeriods;
	}

	public void setBufferPeriods(int bufferPeriods) {
		this.bufferPeriods = bufferPeriods;
	}
}
