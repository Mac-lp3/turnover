package org.mac.sim.domain;

import java.util.Collection;

import org.mac.sim.thread.WorkerTask;

public interface Worker {

	// TODO should use Collection instead?
	public WorkerTask action(final int currentPeriod, Collection<WorkerTask> taskQueue);

	public int getTasksCompleted();

}
