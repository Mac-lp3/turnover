package org.mac.sim.clock;

/**
 * Clock implementations will contain the period tracking logic. They also
 * expose methods to allow other classes to view what period the simulation
 * currently is on.
 * 
 * @author Home
 *
 */
public interface Clock {

	public int getTotalPeriods();

	public int getCurrentPeriod();

	public int getNextPeriod();

}
