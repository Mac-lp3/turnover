package org.mac.sim.simulation;

import java.util.Iterator;
import java.util.List;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.TaskConfiguration;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.SimpleWorker;
import org.mac.sim.thread.WorkerTask;

public class LinearQueueSimulationImpl extends Simulation {

	private int totalPeriods = 0;

	protected LinearQueueSimulationImpl(List<Worker> workers, SimulationParameters simulationParameters)
			throws TurnoverException {

		super(workers, simulationParameters);

	}

	@Override
	protected void execute(SimulationParameters params) throws TurnoverException {

		this.taskConfigurations = ((LinearQueueSimulationParameters) simulationParameters).getTaskConfigurations();
		this.workerConfigurations = ((LinearQueueSimulationParameters) simulationParameters).getWorkerConfigurations();
		this.totalPeriods = ((LinearQueueSimulationParameters) simulationParameters).getTotalPeriods();

		WorkerTask tempCompletedTask = null;

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

		// not used in this simulation
		return 0;
	}

	protected void addTasks(final int currentPeriod) {

		WorkerTask tempWorkerTask;
		int tempTotalToAdd = 0;
		int tempTaskLength = 0;

		// For each task config...
		TaskConfiguration config;
		for (Iterator<TaskConfiguration> iterator = taskConfigurations.iterator(); iterator.hasNext();) {

			config = iterator.next();

			// Check if these tasks begin arriving at this time...
			if (config.getStartPeriod() <= currentPeriod && config.isActive()) {

				// and make sure that they have not stopped arriving...
				if (config.getEndPeriod() <= currentPeriod) {

					// ...then remove this configuration from the list.
					config.setActive(false);

				} else {

					// ...then add the specified number of tasks.
					tempTaskLength = config.getTaskLength();
					tempTotalToAdd = config.getArrivalRate();

					for (int j = 0; j < tempTotalToAdd; j++) {
						tempWorkerTask = new WorkerTask(tempTaskLength);
						tempWorkerTask.setArrivalPeriod(currentPeriod);
						tempWorkerTask.setConfigHashCode(config.hashCode());
						this.tasks.add(tempWorkerTask);
					}
				}
			}
		}
	}

	/**
	 * Checks if this is a period to add additional workers. If it is, this
	 * method will automatically add the specified number of workers with the
	 * provided configuration.
	 * 
	 * @param currentPeriod
	 * @return
	 */
	protected void addWorkers(final int currentPeriod) {

		int tempTotalToAdd = 0;
		SimpleWorker tempWorker;
		int tempHashCode = 0;

		// For each worker configuration...
		WorkerConfiguration conf;
		for (Iterator<WorkerConfiguration> iterator = workerConfigurations.iterator(); iterator.hasNext();) {

			conf = iterator.next();

			// check if the workers "arrive" this period...
			if (conf.getArrivalPeriod() == currentPeriod) {

				// ...and check that they do not leave...
				if (conf.getStopPeriod() <= currentPeriod) {

					// stop workers tied to this config...
					tempHashCode = conf.hashCode();
					for (Worker worker : this.workers) {
						if (((SimpleWorker) worker).getWorkerConfigHash() == tempHashCode) {
							((SimpleWorker) worker).setActive(false);
						}
					}

					// and remove the configuration.
					iterator.remove();
				}

				// ... and add the specified amount if so
				tempTotalToAdd = conf.getTotal();
				for (int i = 0; i < tempTotalToAdd; i++) {
					tempWorker = new SimpleWorker(conf.getAdditionalTime(), conf.getRestTime());
					tempWorker.setWorkerConfigHash(conf.hashCode());
					workers.add(tempWorker);
				}

			}
		}
	}

}
