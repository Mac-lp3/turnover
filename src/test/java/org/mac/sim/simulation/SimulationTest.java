package org.mac.sim.simulation;

import org.junit.Test;
import org.mac.sim.domain.SimulationForm;
import org.mac.sim.domain.Worker;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.WorkerTask;

public class SimulationTest {

	@Test
	public void buildAndRunTest() throws InterruptedException {

		// TODO actual test conditions...
		// SimulationBuilder sb = new SimulationBuilder();
		// sb.setMonthsToPeakProductivity(12);
		// sb.setTotalEmployees(100);
		// sb.setPercentYearlyTurnOver(.06);
		// sb.setYearsToSimulate(2);
		// Simulation sim = sb.build();
		// sim.execute();
	}

	@Test
	public void simulationBuilderTest() throws TurnoverException {

		QueueSimulationBuilder qsb = new QueueSimulationBuilder(50, 10);
		qsb.addWorkers(5);
		Simulation sim = qsb.build();
	}

	@Test
	public void simpleQueueSimulationTest() throws TurnoverException {
		
		SimulationForm simpleSimulationForm = new SimulationForm();
		
		simpleSimulationForm.setQuickStartRate(8);
		simpleSimulationForm.setQuickStartPeriods(100);

		SimpleQueueSimulationBuilder qsb = new SimpleQueueSimulationBuilder(simpleSimulationForm);
		qsb.addWorkers(5, 10, 5);
		qsb.addWorkers(3, 7, 3);
		qsb.addWorkers(3, 5, 1);

		Simulation sim = qsb.build();

		for (WorkerTask task : sim.getCompletedTasks()) {
			System.out.println("Waited: " + task.getPeriodsInQueue() + " arrived: " + task.getArrivalPeriod());
		}

		System.out.println("`````````");

		for (Worker worker : sim.getWorkers()) {
			System.out.println(worker.getTasksCompleted());
		}
	}
}
