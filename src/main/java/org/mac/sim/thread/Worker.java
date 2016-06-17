package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

import org.mac.sim.domain.WorkerTask;
import org.mac.sim.global.PeriodConversionConstants;

public class Worker extends Thread {

	private int periodsToCompleteTask = 0;
	private int bufferPeriods = 0;
	private BlockingQueue<WorkerTask> queue;
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
	public Worker(BlockingQueue<WorkerTask> queue, int periodsToCompleteTask, int bufferPeriods) {
		this.queue = queue;
		this.periodsToCompleteTask = periodsToCompleteTask;
		this.bufferPeriods = bufferPeriods;
	}

	public void run() {

		// get start time / set up
		long taskLength = 0;

		while (runFlag) {

			try {

				// take() will automatically wait until populated if it must
				taskLength = queue.take().getServiceTimeRequired();
				tasksCompleted++;

				// wait service time + buffer time + task time.
				sleep((periodsToCompleteTask + bufferPeriods + taskLength)
						* PeriodConversionConstants.MILLI_TO_PERIOD_MULTIPLIER);

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
