package org.mac.sim.domain;

public class SimulationForm {

	private int numberOfWorkers;
	private int periodsToRun;
	private String periodUnits;
	private int tasksPerPeriod;

	public int getNumberOfWorkers() {
		return numberOfWorkers;
	}

	public void setNumberOfWorkers(int numberOfWorkers) {
		this.numberOfWorkers = numberOfWorkers;
	}

	public int getPeriodsToRun() {
		return periodsToRun;
	}

	public void setPeriodsToRun(int periodsToRun) {
		this.periodsToRun = periodsToRun;
	}

	public String getPeriodUnits() {
		return periodUnits;
	}

	public void setPeriodUnits(String periodUnits) {
		this.periodUnits = periodUnits;
	}

	public int getTasksPerPeriod() {
		return tasksPerPeriod;
	}

	public void setTasksPerPeriod(int tasksPerPeriod) {
		this.tasksPerPeriod = tasksPerPeriod;
	}

}
