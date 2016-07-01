package org.mac.sim.thread;

public class WorkerTask {

	private int periodsInQueue = 0;
	private boolean served = false;
	private long serviceTimeRequired;

	public WorkerTask(long serviceTimeRequired) {
		this.serviceTimeRequired = serviceTimeRequired;
	}

	public boolean isServed() {
		return served;
	}

	public void setServed(boolean served) {
		this.served = served;
	}

	public long getServiceTimeRequired() {
		return serviceTimeRequired;
	}

	public void setServiceTimeRequired(long serviceTimeRequired) {
		this.serviceTimeRequired = serviceTimeRequired;
	}

	public void incrementPeriodsInQueue() {
		periodsInQueue++;
	}

	public int getPeriodsInQueue() {
		return periodsInQueue;
	}

}
