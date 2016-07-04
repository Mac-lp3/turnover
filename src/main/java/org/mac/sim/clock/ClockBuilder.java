package org.mac.sim.clock;

/**
 * Grants other packages access to a clock implementation.
 * 
 * @author Home
 *
 */
public class ClockBuilder {

	/**
	 * Creates a new implementation of Clock.
	 * 
	 * @param totalPeriods
	 *            The total periods to run this simulation
	 * @return A Clock implementation
	 */
	public static Clock build(final int totalPeriods) {

		return new ClockImpl(totalPeriods);
	}

}
