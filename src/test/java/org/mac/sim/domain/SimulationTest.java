package org.mac.sim.domain;

import org.junit.Test;

public class SimulationTest {

	@Test
	public void buildAndRunTest() {

		// TODO actual test conditions...
		SimulationBuilder sb = new SimulationBuilder();
		sb.setMonthsToPeakProductivity(12);
		sb.setTotalEmployees(100);
		sb.setPercentYearlyTurnOver(.06);
		sb.setYearsToSimulate(2);
		Simulation sim = sb.build();
		sim.execute();
	}
}
