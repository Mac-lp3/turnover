package org.mac.sim.thread;

public class WorkerTask {

	private int periodsInQueue = 0;
	private boolean served = false;
	private int serviceTimeRequired;
	private int arrivalPeriod;

	public WorkerTask(int serviceTimeRequired) {
		this.serviceTimeRequired = serviceTimeRequired;
	}

	public boolean isServed() {
		return served;
	}

	public void setServed(boolean served) {
		this.served = served;
	}

	public int getServiceTimeRequired() {
		return serviceTimeRequired;
	}

	public void setServiceTimeRequired(int serviceTimeRequired) {
		this.serviceTimeRequired = serviceTimeRequired;
	}

	public void incrementPeriodsInQueue() {
		periodsInQueue++;
	}

	public int getPeriodsInQueue() {
		return periodsInQueue;
	}

	public int getArrivalPeriod() {
		return arrivalPeriod;
	}

	public void setArrivalPeriod(int arrivalPeriod) {
		this.arrivalPeriod = arrivalPeriod;
	}

}
