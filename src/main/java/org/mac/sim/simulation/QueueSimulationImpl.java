package org.mac.sim.simulation;

import java.util.List;

import org.mac.sim.clock.ClockManager;
import org.mac.sim.domain.Worker;
import org.mac.sim.global.SimulationParameters;
import org.mac.sim.thread.ThreadWorker;
import org.mac.sim.thread.WorkQueueManager;
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
	QueueSimulationImpl(final List<Worker> workers, final QueueSimulationParameters inputParameters)
			throws InterruptedException {

		super(workers, inputParameters);
		// TODO must set proper sleep interval
	}

	/**
	 * Executes the simulation with the constructed clock manager, queue
	 * manager, and workers.
	 */
	public void execute(final SimulationParameters params) throws InterruptedException {

		QueueSimulationParameters inputParams = (QueueSimulationParameters) params;
		WorkQueueManager queueManager = inputParams.getQueueManager();
		ClockManager clockManager = inputParams.getClockManager();

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

		// TODO verify that all tasks/workers have finished

		// Stop each worker
		for (Worker worker : workers) {
			// TODO get completed tasks from each worker.
			((ThreadWorker) worker).doStop();
		}

		queueManager.getPeriodsSpentInLoop();
	}

	public List<WorkerTask> getCompletedTasks() {
		// TODO Auto-generated method stub
		return null;
	}

}
