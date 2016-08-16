package org.mac.sim.simulation;

import java.util.ArrayList;
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

	private List<WorkerConfiguration> workerConfigurations;
	private List<TaskConfiguration> taskConfigurations;
	private List<Worker> workers;
	ArrayList<WorkerTask> tasks;
	private int totalPeriods = 0;

	protected LinearQueueSimulationImpl(List<Worker> workers, SimulationParameters simulationParameters)
			throws TurnoverException {

		super(workers, simulationParameters);
		LinearQueueSimulationParameters params = (LinearQueueSimulationParameters) simulationParameters;
		
		this.workers = new ArrayList<Worker>();
		this.tasks = new ArrayList<WorkerTask>();
		this.taskConfigurations = params.getTaskConfigurations();
		this.workerConfigurations = params.getWorkerConfigurations();
		this. totalPeriods = params.getTotalPeriods();
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
		
		for (int currentPeriod = 0; currentPeriod < totalPeriods; currentPeriod++) {
			
			
			
		}
		
		
		
	}

	@Override
	protected int getTaskLength() {

		// multiple configurations?
		// -- which applies for this period?
		// -- -- return this task length

		return 0;
	}
	
	private void addTasks(final int currentPeriod){
		
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
					
					// ...then remove this configuration from the list.
					iterator.remove();
					
				} else {
					
					// ...then add the specified number of tasks.
					tempTaskLength = config.getLength();
					tempTotalToAdd = config.getRate();
					
					for (int j = 0; j < tempTotalToAdd; j++) {
						tempWorkerTask = new WorkerTask(tempTaskLength);
						tempWorkerTask.setArrivalPeriod(currentPeriod);
						this.tasks.add(tempWorkerTask);
					}
					
				}
			}
			
		}
		
	}

	/**
	 * Checks if this is a period to add additional workers. If it is, this
	 * method will automatically add the specfied number of workers with the
	 * provided configuration.
	 * 
	 * @param currentPeriod
	 * @return
	 */
	private void addNewWorkers(final int currentPeriod) {

		int tempTotalToAdd = 0;

		// For each worker configuration...
		WorkerConfiguration conf;
		for (Iterator<WorkerConfiguration> iterator = workerConfigurations.iterator(); iterator.hasNext();) {

			conf = iterator.next();

			// check if the workers "arrive" this period...
			if (conf.getArrivalPeriod() >= currentPeriod) {

				// and add the specified amount if so
				tempTotalToAdd = conf.getTotal();
				for (int i = 0; i < tempTotalToAdd; i++) {
					workers.add(new SimpleWorker(conf.getAdditionalTime(), conf.getRestTime()));
				}

				// and remove the config from the list
				iterator.remove();
			}

		}
	}

}
