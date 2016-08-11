package org.mac.sim.simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.domain.Worker;
import org.mac.sim.domain.WorkerConfiguration;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.thread.SimpleWorker;

public class LinearQueueSimulationImpl extends Simulation {

	private List<WorkerConfiguration> workerConfigurations;
	private List<Worker> workers;

	protected LinearQueueSimulationImpl(List<Worker> workers, SimulationParameters simulationParameters)
			throws TurnoverException {

		super(workers, simulationParameters);
		workers = new ArrayList<Worker>();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void execute(SimulationParameters params) throws TurnoverException {

		// for each period...
		// -- how many tasks to add?
		// -- -- get length of each new task
		// -- any workers to leave?
		// -- -- remove the workers
		// -- how many workers to add workers?
		// -- -- add each with given values
		// -- for each worker...
		// -- -- serve a task
		// -- -- iterate stats

	}

	@Override
	protected int getTaskLength() {

		// multiple configurations?
		// -- which applies for this period?
		// -- -- return this task length

		return 0;
	}

	/**
	 * Checks if this is a period to add additional workers. If it is, this
	 * method will automatically add the specfied number of workers with the
	 * provided configuration.
	 * 
	 * @param currentPeriod
	 * @return
	 */
	private Worker addNewWorkers(final int currentPeriod) {

		int tempArrivalPeriod = 0;

		// For each worker configuration...
		WorkerConfiguration conf;
		for (Iterator<WorkerConfiguration> iterator = workerConfigurations.iterator(); iterator.hasNext();) {

			conf = iterator.next();

			// check if the workers "arrive" this period...
			if (conf.getArrivalPeriod() >= currentPeriod) {

				// and add the specified amount if so
				tempArrivalPeriod = conf.getTotal();
				for (int i = 0; i < tempArrivalPeriod; i++) {
					workers.add(new SimpleWorker(conf.getAdditionalTime(), conf.getRestTime()));
				}

				// and remove the config from the list
				iterator.remove();
			}

		}

		return null;
	}

}
