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

	QueueSimulationImpl(final Clock clock, final List<Worker> workers, final BlockingQueue<WorkerTask> taskQueue) {

	}

	public void execute() {
		// TODO Auto-generated method stub

	}

}
