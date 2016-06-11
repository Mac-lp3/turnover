package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

import org.mac.sim.domain.WorkerTask;

public class Worker extends Thread {

	private int periodsToCompleteTask = 0;
	private int bufferPeriods = 1;
	private BlockingQueue<WorkerTask> queue;
	private boolean runFlag = true;
	private int tasksCompleted = 0;

	public Worker(BlockingQueue<WorkerTask> queue, int periodsToCompleteTask, int bufferPeriods) {
		this.queue = queue;
		this.periodsToCompleteTask = periodsToCompleteTask;
		this.bufferPeriods = bufferPeriods;
	}

	public void run() {

		// get start time / set up
		long currentPeriod = 0;
		long nextPeriod = 0;
		long startTime = System.nanoTime();

		while (runFlag) {

			try {

				// method will until populated if it must
				queue.take();
				tasksCompleted++;

				// wait given service time + buffer time.
				sleep((periodsToCompleteTask + bufferPeriods));

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
