package org.mac.sim.thread;

import java.util.Collection;
import java.util.List;

import org.mac.sim.domain.Worker;

public class SimpleWorker implements Worker {
	
	private int periodsToCompleteTask = 0;
	private int bufferPeriods = 0;
	private int nextActionablePeriod = 0;
	private int tasksCompleted = 0;
	private int totalPeriodsBuffering = 0;
	private int workerConfigHash = 0; // used to track stoppage time
	private boolean isActive = true;

	public SimpleWorker() {

	}

	public SimpleWorker(int periodsToCompleteTask, int bufferPeriods) {
		this.periodsToCompleteTask = periodsToCompleteTask;
		this.bufferPeriods = bufferPeriods;
	}

	public WorkerTask action(final int currentPeriod, Collection<WorkerTask> taskQueue) {

		int tempTaskLength = 0;
		WorkerTask tempTask = null;

		if (isActive) {
			if (currentPeriod >= nextActionablePeriod && !taskQueue.isEmpty()) {

				tempTask = ((List<WorkerTask>) taskQueue).remove(0);

				tempTaskLength = Integer.valueOf(Long.toString(tempTask.getServiceTimeRequired()));

				nextActionablePeriod = currentPeriod + tempTaskLength + bufferPeriods + periodsToCompleteTask;

				setTotalPeriodsBuffering(getTotalPeriodsBuffering() + bufferPeriods);
				tasksCompleted++;
			}
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getWorkerConfigHash() {
		return workerConfigHash;
	}

	public void setWorkerConfigHash(int workerConfigHash) {
		this.workerConfigHash = workerConfigHash;
	}

	public int getTotalPeriodsBuffering() {
		return totalPeriodsBuffering;
	}

	public void setTotalPeriodsBuffering(int totalPeriodsBuffering) {
		this.totalPeriodsBuffering = totalPeriodsBuffering;
	}

}
