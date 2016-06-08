package org.mac.sim.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Separate creation into a builder. Eventually.
 * 
 * @author Home
 *
 */
public class LineSimRunner {

	private static volatile List QUEUE = new ArrayList();
	private int ratePerPeriod; 
	private int totalPeriods;

	public LineSimRunner(int ratePerPeriod, int totalPeriods) {

	}

}
