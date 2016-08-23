package org.mac.sim.thread;

public class WorkerTask {

	private int periodsInQueue = 0;
	private boolean served = false;
	private int serviceTimeRequired;
	private int arrivalPeriod;
	private int periodServiced;
	private int servedById;

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

	public int getPeriodServiced() {
		return periodServiced;
	}

	public void setPeriodServiced(int periodServiced) {
		this.periodServiced = periodServiced;
	}

	public int getServedById() {
		return servedById;
	}

	public void setServedById(int servedById) {
		this.servedById = servedById;
	}

}
