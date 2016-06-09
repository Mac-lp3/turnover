package org.mac.sim.thread;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {

	private int periodsToCompleteTask;
	private int bufferPeriods;
	private BlockingQueue queue;
	private boolean loop = true;

	public void run() {

		// get start time / set up
		long currentPeriod = 0;
		long nextPeriod = 0;
		long startTime = System.nanoTime();

		while (loop) {

			try {
				
				// check queue. This method will wait if it has to.
				queue.take();
				// wait given service time + buffer time.
				// TODO this is in millis... need to convert somehow
				this.sleep((periodsToCompleteTask + bufferPeriods) * 1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// repeat until kill command
		}
	}

	public synchronized void doStop() {
		loop = true;
		this.interrupt(); // break pool thread out of dequeue() call.
	}

	public synchronized boolean isStopped() {
		return loop;
	}

}
