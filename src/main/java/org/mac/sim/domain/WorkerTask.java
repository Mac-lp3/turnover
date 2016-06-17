package org.mac.sim.domain;

public class WorkerTask {

	private int periodsSinceCreation = 0;
	private boolean served = false;
	private long serviceTimeRequired;

	public WorkerTask(long serviceTimeRequired) {
		this.serviceTimeRequired = serviceTimeRequired;
	}

	public int getPeriodsSinceCreation() {
		return periodsSinceCreation;
	}

	public void setPeriodsSinceCreation(int periodsSinceCreation) {
		this.periodsSinceCreation = periodsSinceCreation;
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
