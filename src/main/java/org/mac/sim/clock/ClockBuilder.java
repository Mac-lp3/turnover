package org.mac.sim.clock;

public class ClockBuilder {

	public static Clock build(final int totalPeriods) {

		return new ClockImpl(totalPeriods);
	}

}
