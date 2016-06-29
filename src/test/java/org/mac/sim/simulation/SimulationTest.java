package org.mac.sim.simulation;

import org.junit.Test;
import org.mac.sim.simulation.Simulation;
import org.mac.sim.simulation.SimulationBuilder;

public class SimulationTest {

	@Test
	public void buildAndRunTest() throws InterruptedException {

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