package org.mac.sim.simulation;

import org.mac.sim.clock.ClockManager;
import org.mac.sim.global.SimulationParameters;
import org.mac.sim.thread.WorkQueueManager;

class QueueSimulationParameters implements SimulationParameters {

	private ClockManager clockManager;
	private WorkQueueManager queueManager;

	/**
	 * 
	 * @param clockManager
	 * @param queueManager
	 */
	QueueSimulationParameters(final ClockManager clockManager, final WorkQueueManager queueManager) {
		this.clockManager = clockManager;
		this.queueManager = queueManager;
	}

	public ClockManager getClockManager() {
		return clockManager;
	}

	public WorkQueueManager getQueueManager() {
		return queueManager;
	}

}
