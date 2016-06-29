package org.mac.sim.simulation;

public interface Simulation {

	/**
	 * Runs the simulation logic. Should return some report after completion.
	 * 
	 * @throws InterruptedException
	 */
	public void execute() throws InterruptedException;

}
