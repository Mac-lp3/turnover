package org.mac.sim.simulation;

import java.util.ArrayList;

import org.mac.sim.thread.Worker;
import org.mac.sim.thread.WorkerTask;

public class SimpleQueueSimulationBuilder {

	private ArrayList<Worker> workerPool;
	private ArrayList<WorkerTask> taskQueue;
	private int tasksPerPeriod;

	public SimpleQueueSimulationBuilder(final int tasksPerPeriod) {
		this.tasksPerPeriod = tasksPerPeriod;
	}
}
