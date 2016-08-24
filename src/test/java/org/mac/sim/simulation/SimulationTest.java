package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mac.sim.domain.SimulationConfigurations;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.WorkerTask;

public class SimulationTest {

	@Before
	public void setUpSimConfig() {

	}

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

		SimulationConfigurations simpleSimulationConfiguration = new SimulationConfigurations();

		// set clock details
		simpleSimulationConfiguration.setTotalPeriods(100);
		simpleSimulationConfiguration.setClockUnits("Minutes");

		// set task config
		TaskConfiguration taskConfig = new TaskConfiguration();
		taskConfig.setArrivalRate(8);
		taskConfig.setStartPeriod(0);

		List<TaskConfiguration> taskConfigList = new ArrayList<TaskConfiguration>();
		taskConfigList.add(taskConfig);
		simpleSimulationConfiguration.setTaskConfigurations(taskConfigList);

		SimpleQueueSimulationBuilder qsb = new SimpleQueueSimulationBuilder(simpleSimulationConfiguration);
		qsb.addWorkers(5, 5);
		qsb.addWorkers(3, 3);
		qsb.addWorkers(3, 1);

		Simulation sim = qsb.build();

		for (WorkerTask task : sim.getCompletedTasks()) {
			System.out.println("Waited: " + task.getPeriodsInQueue() + " arrived: " + task.getArrivalPeriod());
		}

		System.out.println("`````````");

		for (Worker worker : sim.getWorkers()) {
			System.out.println(worker.getTasksCompleted());
		}
	}

	@Test
	public void probabilityQueueSimulationTest() throws TurnoverException {

		WorkerConfiguration workerConfig1 = new WorkerConfiguration();
		workerConfig1.setArrivalPeriod(0);
		workerConfig1.setRestTime(1);
		workerConfig1.setStopPeriod(33);
		workerConfig1.setAdditionalTime(0);
		workerConfig1.setTotal(4);

		List<WorkerConfiguration> workConfs = new ArrayList<WorkerConfiguration>();
		workConfs.add(workerConfig1);

		TaskConfiguration tc1 = new TaskConfiguration();
		tc1.setArrivalRate(2);
		tc1.setStartPeriod(0);
		tc1.setEndPeriod(100);
		tc1.setLowBound(3);
		tc1.setHighBound(7);
		tc1.setProportion(33);

		TaskConfiguration tc2 = new TaskConfiguration();
		tc2.setArrivalRate(1);
		tc2.setStartPeriod(0);
		tc2.setEndPeriod(100);
		tc2.setLowBound(5);
		tc2.setHighBound(7);
		tc2.setProportion(33);

		TaskConfiguration tc3 = new TaskConfiguration();
		tc3.setArrivalRate(2);
		tc3.setStartPeriod(0);
		tc3.setEndPeriod(100);
		tc3.setLowBound(1);
		tc3.setHighBound(4);
		tc3.setProportion(34);

		List<TaskConfiguration> taskConfs = new ArrayList<TaskConfiguration>();
		taskConfs.add(tc1);
		taskConfs.add(tc2);
		taskConfs.add(tc3);

		SimulationConfigurations simConfig = new SimulationConfigurations();
		simConfig.setTotalPeriods(100);
		simConfig.setClockUnits("minutes");
		simConfig.setWorkerConfigurations(workConfs);
		simConfig.setTaskConfigurations(taskConfs);

		ProbabilityQueueSimulationBuilder pqsm = new ProbabilityQueueSimulationBuilder(simConfig);
		Simulation s = pqsm.build();

	}

	@Test
	public void linearSimTest() throws TurnoverException {

		WorkerConfiguration workerConfig1 = new WorkerConfiguration();
		workerConfig1.setArrivalPeriod(0);
		workerConfig1.setRestTime(1);
		workerConfig1.setStopPeriod(33);
		workerConfig1.setAdditionalTime(0);
		workerConfig1.setTotal(4);

		LinearQueueSimulationBuilder builder = new LinearQueueSimulationBuilder(100, "Minutes");
		builder.addWorkerConfig(workerConfig1);

		WorkerConfiguration workerConfig2 = new WorkerConfiguration();
		workerConfig2.setAdditionalTime(1);
		workerConfig2.setArrivalPeriod(33);
		workerConfig2.setRestTime(2);
		workerConfig2.setStopPeriod(66);
		workerConfig2.setTotal(5);
		builder.addWorkerConfig(workerConfig2);

		WorkerConfiguration workerConfig3 = new WorkerConfiguration();
		workerConfig3.setAdditionalTime(-1);
		workerConfig3.setArrivalPeriod(66);
		workerConfig3.setRestTime(1);
		workerConfig3.setStopPeriod(100);
		workerConfig2.setTotal(3);
		builder.addWorkerConfig(workerConfig3);

		TaskConfiguration taskConf1 = new TaskConfiguration();
		taskConf1.setEndPeriod(25);
		taskConf1.setTaskLength(4);
		taskConf1.setArrivalRate(4);
		taskConf1.setStartPeriod(0);
		builder.addTaskConfig(taskConf1);

		TaskConfiguration taskConf2 = new TaskConfiguration();
		taskConf2.setEndPeriod(66);
		taskConf2.setTaskLength(3);
		taskConf2.setArrivalRate(5);
		taskConf2.setStartPeriod(25);
		builder.addTaskConfig(taskConf2);

		TaskConfiguration taskConf3 = new TaskConfiguration();
		taskConf3.setEndPeriod(100);
		taskConf3.setTaskLength(5);
		taskConf3.setArrivalRate(5);
		taskConf3.setStartPeriod(66);
		builder.addTaskConfig(taskConf3);

		Simulation results = builder.build();
		results.getCompletedTasks();

	}
}
