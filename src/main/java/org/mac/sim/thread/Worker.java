package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
import org.mac.sim.domain.WorkerTask;

public class Worker extends Thread {

	private int periodsToCompleteTask = 0;
	private int bufferPeriods = 0;
	private volatile BlockingQueue<WorkerTask> queue;
	private volatile Clock clock;
	private boolean runFlag = true;
	private int tasksCompleted = 0;

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
	public Worker(BlockingQueue<WorkerTask> queue, int periodsToCompleteTask, int bufferPeriods, Clock clock) {
		this.queue = queue;
		this.periodsToCompleteTask = periodsToCompleteTask;
		this.bufferPeriods = bufferPeriods;
		this.clock = clock;
	}

	public void run() {

		// get start time / set up
		long taskLength = 0;
		int waitStartPeriod = 0;
		int periodsToWait = periodsToCompleteTask + bufferPeriods;

		runFlag = true;

		while (runFlag) {

			try {

				// take() will automatically wait until populated if it must
				if (!queue.isEmpty()) {

					taskLength = queue.take().getServiceTimeRequired();
					tasksCompleted++;

					waitStartPeriod = clock.getCurrentPeriod();

					// while (waitStartPeriod + periodsToWait + taskLength <
					// clock.getCurrentPeriod()) {

					// wait in loop for this time
					Thread.sleep(((waitStartPeriod + periodsToWait + taskLength) - clock.getCurrentPeriod()) / 2);
					// }
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
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

}
