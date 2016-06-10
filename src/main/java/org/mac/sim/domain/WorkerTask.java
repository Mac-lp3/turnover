package org.mac.sim.domain;

public class WorkerTask {

	private int cyclesSinceCreation = 0;
	private boolean served = false;
	private long serviceTimeRequired;

	public WorkerTask(long serviceTimeRequired) {
		this.serviceTimeRequired = serviceTimeRequired;
	}

	public int getCyclesSinceCreation() {
		return cyclesSinceCreation;
	}

	public void setCyclesSinceCreation(int cyclesSinceCreation) {
		this.cyclesSinceCreation = cyclesSinceCreation;
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

}
