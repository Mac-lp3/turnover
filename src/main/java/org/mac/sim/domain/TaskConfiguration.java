package org.mac.sim.domain;

public class TaskConfiguration {

	private int arrivalRate;
	private int taskLength;
	private int startPeriod;
	private int endPeriod;
	private int lowBound;
	private int highBound;
	private int proportion;
	private boolean isActive = true;

	public int getArrivalRate() {
		return arrivalRate;
	}

	public void setArrivalRate(int arrivalRate) {
		this.arrivalRate = arrivalRate;
	}

	public int getTaskLength() {
		return taskLength;
	}

	public void setTaskLength(int taskLength) {
		this.taskLength = taskLength;
	}

	public int getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(int startPeriod) {
		this.startPeriod = startPeriod;
	}

	public int getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(int endPeriod) {
		this.endPeriod = endPeriod;
	}

	public int getLowBound() {
		return lowBound;
	}

	public void setLowBound(int lowBound) {
		this.lowBound = lowBound;
	}

	public int getHighBound() {
		return highBound;
	}

	public void setHighBound(int highBound) {
		this.highBound = highBound;
	}

	public int getProportion() {
		return proportion;
	}

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
