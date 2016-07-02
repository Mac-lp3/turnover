package org.mac.sim.simulation;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
import org.mac.sim.domain.Worker;
import org.mac.sim.thread.ThreadWorker;
import org.mac.sim.thread.WorkerTask;

class QueueSimulationImpl extends Simulation {

	/**
	 * Constructs required managers.
	 * 
	 * @param clock
	 * @param workers
	 * @param taskQueue
	 * @param ratePerPeriod
	 * @throws InterruptedException
	 */
	QueueSimulationImpl(final Clock clock, final List<Worker> workers, final BlockingQueue<WorkerTask> taskQueue,
			final int ratePerPeriod) throws InterruptedException {

		super(clock, workers, taskQueue, ratePerPeriod);
		// TODO must set proper sleep interval
	}

	/**
	 * Executes the simulation with the constructed clock manager, queue
	 * manager, and workers.
	 */
	public void execute() throws InterruptedException {

		// Start the queue manager
		queueManager.start();

		// Start each worker
		for (Worker worker : workers) {
			((ThreadWorker) worker).start();
		}

		// Start the clock
		clockManager.start();

		// Wait for clockManager to complete
		clockManager.join();
		queueManager.join();

		// Stop each worker
		int totalTasksCompleted = 0;
		for (Worker worker : workers) {
			((ThreadWorker) worker).doStop();
			totalTasksCompleted += worker.getTasksCompleted();
		}

		queueManager.getPeriodsSpentInLoop();
	}

	public List<WorkerTask> getCompletedTasks() {
		// TODO Auto-generated method stub
		return null;
	}

}
