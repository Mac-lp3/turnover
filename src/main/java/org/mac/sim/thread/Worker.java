package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {

	private int periodsToCompleteTask = 0;
	private int bufferPeriods = 1;
	private BlockingQueue queue;
	private boolean runFlag = true;
	
	public Worker(BlockingQueue queue) {
		this.queue = queue;
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
				
				// wait given service time + buffer time.
				sleep((periodsToCompleteTask + bufferPeriods) * 10);

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

}
