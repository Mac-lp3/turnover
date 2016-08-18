package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mac.sim.domain.SimulationConfigrations;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
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
		
		SimulationConfigrations simpleSimulationConfiguration = new SimulationConfigrations();
		
		// set clock details
		simpleSimulationConfiguration.setTotalPeriods(100);
		simpleSimulationConfiguration.setClockUnits("Minutes");
		
		// set task config
		TaskConfiguration taskConfig = new TaskConfiguration();
		taskConfig.setRate(8);
		taskConfig.setStartPeriod(0);
		
		List<TaskConfiguration> taskConfigList = new ArrayList<TaskConfiguration>();
		taskConfigList.add(taskConfig);
		simpleSimulationConfiguration.setTaskConfigurations(taskConfigList);
		
		WorkerConfiguration workerConfig1 = new WorkerConfiguration();
		WorkerConfiguration workerConfig2 = new WorkerConfiguration();
		WorkerConfiguration workerConfig3 = new WorkerConfiguration();

		SimpleQueueSimulationBuilder qsb = new SimpleQueueSimulationBuilder(simpleSimulationConfiguration);
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
