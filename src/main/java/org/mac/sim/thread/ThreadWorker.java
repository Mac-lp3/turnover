package org.mac.sim.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
import org.mac.sim.domain.Worker;

public class ThreadWorker extends Thread implements Worker {

	private int periodsToCompleteTask = 0;
	private int bufferPeriods = 0;
	private volatile BlockingQueue<WorkerTask> queue;
	private volatile Clock clock;
	private boolean runFlag = true;
	private int tasksCompleted = 0;
	private List<WorkerTask> completedTasks = new ArrayList<WorkerTask>();

	/**
	 * 
	 * @param queue
	 *            The queue to be operated upon
	 * @param periodsToCompleteTask
	 *            How many periods (in addition to the number of periods
	 *            specified by the task) this specific worker needs to complete
	 *            a task
	 * @param bufferPeriods
	 *            Number of periods this worker needs as "down time" before it
	 *            is ready to preform the next task.
	 */
	public ThreadWorker(BlockingQueue<WorkerTask> queue, int periodsToCompleteTask, int bufferPeriods, Clock clock) {
		this.queue = queue;
		this.periodsToCompleteTask = periodsToCompleteTask;
		this.bufferPeriods = bufferPeriods;
		this.clock = clock;
	}

	public void run() {

		// get start time / set up
		int taskLength = 0;
		int waitStartPeriod = 0;
		int periodsToWait = periodsToCompleteTask + bufferPeriods;
		WorkerTask tempTask = null;
		runFlag = true;

		while (runFlag) {

			if (!queue.isEmpty()) {

				waitStartPeriod = clock.getCurrentPeriod();
				tempTask = action(waitStartPeriod, queue);
				taskLength = tempTask.getServiceTimeRequired();

				try {
					// sleep for the calculated time
					Thread.sleep(((waitStartPeriod + periodsToWait + taskLength) - clock.getCurrentPeriod()) / 2);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void doStop() {
		runFlag = false;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

	public synchronized boolean isStopped() {
		return runFlag;
	}

	public int getTasksCompleted() {
		return tasksCompleted;
	}

	public WorkerTask action(int currentPeriod, Collection<WorkerTask> taskQueue) {

		WorkerTask tempTask = null;

		try {

			// take() will automatically wait until populated if it must
			tempTask = ((BlockingQueue<WorkerTask>) taskQueue).take();
			completedTasks.add(tempTask);
			tasksCompleted++;

		} catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempTask;

	}

	public List<WorkerTask> getCompletedTasks() {
		return completedTasks;
	}

}
