package org.mac.sim.simulation;

import org.junit.Test;

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

	@Test
	public void simulationBuilderTest() throws InterruptedException {

		QueueSimulationBuilder qsb = new QueueSimulationBuilder(50, 10);
		qsb.addWorkers(5);
		Simulation sim = qsb.build();
		sim.execute();
	}
}
