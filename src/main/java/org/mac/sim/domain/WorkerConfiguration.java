package org.mac.sim.domain;

/**
 * Holds worker configuration that simulations will need to run.
 * 
 * @author Home
 *
 */
public class WorkerConfiguration {
	
	private int total;
	private int additionalTime;
	private int restTime;
	private int arrivalPeriod;
	private int stopPeriod;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAdditionalTime() {
		return additionalTime;
	}

	public void setAdditionalTime(int additionalTime) {
		this.additionalTime = additionalTime;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	public int getArrivalPeriod() {
		return arrivalPeriod;
	}

	public void setArrivalPeriod(int arrivalPeriod) {
		this.arrivalPeriod = arrivalPeriod;
	}

	public int getStopPeriod() {
		return stopPeriod;
	}

	public void setStopPeriod(int stopPeriod) {
		this.stopPeriod = stopPeriod;
	}

}
