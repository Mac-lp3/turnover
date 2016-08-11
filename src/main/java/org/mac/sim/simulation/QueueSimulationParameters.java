package org.mac.sim.simulation;

import org.mac.sim.clock.ClockManager;
import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.thread.TaskQueueManager;

class QueueSimulationParameters implements SimulationParameters {

	private ClockManager clockManager;
	private TaskQueueManager queueManager;

	/**
	 * 
	 * @param clockManager
	 * @param queueManager
	 */
	QueueSimulationParameters(final ClockManager clockManager, final TaskQueueManager queueManager) {
		this.clockManager = clockManager;
		this.queueManager = queueManager;
	}

	public ClockManager getClockManager() {
		return clockManager;
	}

	public TaskQueueManager getQueueManager() {
		return queueManager;
	}

}
