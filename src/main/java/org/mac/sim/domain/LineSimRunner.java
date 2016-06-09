package org.mac.sim.domain;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Separate creation into a builder. Eventually.
 * 
 * @author Home
 *
 */
public class LineSimRunner {

	private BlockingQueue queue;
	private int ratePerPeriod;
	private int totalPeriods;

	public LineSimRunner(int ratePerPeriod, int totalPeriods) {

		this.ratePerPeriod = ratePerPeriod;
		this.totalPeriods = totalPeriods;

		// Absolute maximum
		queue = new ArrayBlockingQueue(ratePerPeriod * totalPeriods);
	}

	public void execute() {

	}

}
