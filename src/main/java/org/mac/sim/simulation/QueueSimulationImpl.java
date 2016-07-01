package org.mac.sim.simulation;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.mac.sim.clock.Clock;
import org.mac.sim.clock.ClockManager;
import org.mac.sim.thread.WorkQueueManager;
import org.mac.sim.thread.Worker;
import org.mac.sim.thread.WorkerTask;

class QueueSimulationImpl implements Simulation {

	private ClockManager clockManager;
	private WorkQueueManager queueManager;
	private List<Worker> workers;
	private BlockingQueue<WorkerTask> taskQueue;

	/**
	 * Constructs required managers.
	 * 
	 * @param clock
	 * @param workers
	 * @param taskQueue
	 * @param ratePerPeriod
	 */
	QueueSimulationImpl(final Clock clock, final List<Worker> workers, final BlockingQueue<WorkerTask> taskQueue,
			final int ratePerPeriod) {

		// TODO must set proper sleep interval
		this.clockManager = new ClockManager(clock);
		this.workers = workers;
		this.taskQueue = taskQueue;
		this.queueManager = new WorkQueueManager(taskQueue, ratePerPeriod, clock);
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
			worker.start();
		}

		// Start the clock
		clockManager.start();

		// Wait for clockManager to complete
		clockManager.join();
		queueManager.join();

		// Stop each worker
		int totalTasksCompleted = 0;
		for (Worker worker : workers) {
			worker.doStop();
			totalTasksCompleted += worker.getTasksCompleted();
		}

		queueManager.getPeriodsSpentInLoop();
	}

	public List<WorkerTask> getCompletedTasks() {
		// TODO Auto-generated method stub
		return null;
	}

}
