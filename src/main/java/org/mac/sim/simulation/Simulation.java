package org.mac.sim.simulation;

import java.util.List;

import org.mac.sim.thread.WorkerTask;

public interface Simulation {

	/**
	 * Runs the simulation logic. Should return some report after completion.
	 * 
	 * @throws InterruptedException
	 */
	public void execute() throws InterruptedException;

	public List<WorkerTask> getCompletedTasks();

}
