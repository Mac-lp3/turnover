package org.mac.sim.simulation;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.SimpleWorker;
import org.mac.sim.thread.WorkerTask;

public class ProbabilityQueueSimulationImpl extends Simulation {

	private List<WorkerConfiguration> workerConfigurations;
	private List<TaskConfiguration> taskConfigurations;
	private int totalPeriods = 0;
	private double rand = Math.random();

	protected ProbabilityQueueSimulationImpl(List<Worker> workers, SimulationParameters simulationParameters)
			throws TurnoverException {

		// Calls execute immediately
		super(workers, simulationParameters);

	}

	@Override
	protected void execute(SimulationParameters params) throws TurnoverException {

		// set class variables
		this.totalPeriods = ((ProbabilityQueueSimulationParameters) params).getTotalPeriods();
		this.workerConfigurations = ((ProbabilityQueueSimulationParameters) params).getWorkerConfigurations();
		this.taskConfigurations = ((ProbabilityQueueSimulationParameters) params).getTaskConfigurations();

		// declare temp value
		WorkerTask tempCompletedTask = null;

		// begin the simulation loop
		for (int currentPeriod = 0; currentPeriod < totalPeriods; currentPeriod++) {

			addTasks(currentPeriod);
			addWorkers(currentPeriod);

			// Allow each worker to act (or wait)
			for (Worker worker : workers) {

				// If a task was completed, add it to the completed list
				tempCompletedTask = worker.action(currentPeriod, tasks);
				if (tempCompletedTask != null) {
					tempCompletedTask.setPeriodServiced(currentPeriod);
					completedTasks.add(tempCompletedTask);
				}
			}

			// Update remaining tasks
			for (WorkerTask task : tasks) {
				task.incrementPeriodsInQueue();
			}
		}

	}

	@Override
	protected int getTaskLength() {

		// generate a random int 1-100.
		double temp = rand * 100;
		int percentage = (int) temp + 1;

		// compare it with each config's probability.
		TaskConfiguration currentLowestProportion = null;
		for (TaskConfiguration tc : this.taskConfigurations) {
			if (percentage <= tc.getProportion()) {
				// mark the one with the
				if (currentLowestProportion == null || currentLowestProportion.getProportion() > tc.getProportion()) {
					currentLowestProportion = tc;
				}
			}
		}

		// use the config's low/high bound to generate task time
		Random r = new Random();
		int taskTime = r.nextInt(currentLowestProportion.getHighBound() - currentLowestProportion.getLowBound())
				+ currentLowestProportion.getLowBound();

		return taskTime;
	}

	@Override
	protected void addWorkers(int currentPeriod) {

		// declare temp vairables
		int tempTotalToAdd = 0;
		SimpleWorker tempWorker;
		int tempHashCode = 0;

		// For each worker configuration...
		WorkerConfiguration conf;
		for (Iterator<WorkerConfiguration> iterator = workerConfigurations.iterator(); iterator.hasNext();) {

			conf = iterator.next();

			// check if the workers "arrive" this period...
			if (conf.getArrivalPeriod() == currentPeriod) {

				// ...and check that they do not leave this period...
				if (conf.getStopPeriod() <= currentPeriod) {

					// if so, stop all workers tied to this config...
					tempHashCode = conf.hashCode();
					for (Worker worker : this.workers) {

						// (workers are matched via the config's hash code)
						if (((SimpleWorker) worker).getWorkerConfigHash() == tempHashCode) {
							((SimpleWorker) worker).setActive(false);
						}
					}

					// and remove the configuration.
					iterator.remove();
				}

				// ... add the specified amount if they arrive
				tempTotalToAdd = conf.getTotal();
				for (int i = 0; i < tempTotalToAdd; i++) {
					tempWorker = new SimpleWorker(conf.getAdditionalTime(), conf.getRestTime());
					tempWorker.setWorkerConfigHash(conf.hashCode());
					workers.add(tempWorker);
				}

			}
		}

	}

	@Override
	protected void addTasks(int currentPeriod) {

		// declare temp variables
		WorkerTask tempWorkerTask;
		int tempTotalToAdd = 0;
		int tempTaskLength = 0;

		// For each task config...
		TaskConfiguration config;
		for (Iterator<TaskConfiguration> iterator = taskConfigurations.iterator(); iterator.hasNext();) {

			config = iterator.next();

			// Check if these tasks begin arriving at this time...
			if (config.getStartPeriod() <= currentPeriod) {

				// and make sure that they have not stopped arriving...
				if (config.getEndPeriod() <= currentPeriod) {

					// ...and remove this configuration if so.
					iterator.remove();

				} else {

					// ... otherwise add the specified number of tasks.
					tempTaskLength = config.getTaskLength();
					tempTotalToAdd = config.getArrivalRate();

					for (int j = 0; j < tempTotalToAdd; j++) {
						tempWorkerTask = new WorkerTask(tempTaskLength);
						tempWorkerTask.setArrivalPeriod(currentPeriod);
						this.tasks.add(tempWorkerTask);
					}

				}
			}
		}

	}

}
