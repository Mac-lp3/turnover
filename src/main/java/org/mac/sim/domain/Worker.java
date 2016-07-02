package org.mac.sim.domain;

import java.util.List;

import org.mac.sim.thread.WorkerTask;

public interface Worker {

	public WorkerTask action(final int currentPeriod, List<WorkerTask> taskQueue);

	public int getTasksCompleted();

}
