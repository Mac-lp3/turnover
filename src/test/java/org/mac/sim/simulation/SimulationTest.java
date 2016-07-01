package org.mac.sim.simulation;

import org.junit.Test;
import org.mac.sim.thread.WorkerTask;

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

	@Test
	public void simpleQueueSimulationTest() throws InterruptedException {

		SimpleQueueSimulationBuilder qsb = new SimpleQueueSimulationBuilder(100, 8);
		qsb.addWorkers(3, 10, 5);
		qsb.addWorkers(3, 7, 3);
		qsb.addWorkers(3, 5, 1);

		Simulation sim = qsb.build();
		sim.execute();

		System.out.println("`````````");
		for (WorkerTask task : sim.getCompletedTasks()) {
			System.out.println(task.getPeriodsInQueue());
		}
	}
}
