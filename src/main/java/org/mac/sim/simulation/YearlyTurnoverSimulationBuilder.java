package org.mac.sim.simulation;

import org.mac.sim.domain.Department;

public class YearlyTurnoverSimulationBuilder {

	private int totalEmployees;
	private double percentYearlyTurnOver;
	private int yearsToSimulate;
	private int monthsToPeakProductivity;

	// TODO enforce some actual rules here
	public Simulation build() {

		// calculate turn over interval
		double monthlyTurnOverInterval = Math.floor(totalEmployees * percentYearlyTurnOver) / 12;

		// Construct the department
		Department department = new Department(totalEmployees, monthsToPeakProductivity);

		// return new SimulationImpl(department, monthlyTurnOverInterval,
		// yearsToSimulate);
		return null;

	}

	public int getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(int totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	public double getPercentYearlyTurnOver() {
		return percentYearlyTurnOver;
	}

	public void setPercentYearlyTurnOver(double percentYearlyTurnOver) {
		this.percentYearlyTurnOver = percentYearlyTurnOver;
	}

	public int getMonthsToPeakProductivity() {
		return monthsToPeakProductivity;
	}

	public void setMonthsToPeakProductivity(int monthsToPeakProductivity) {
		this.monthsToPeakProductivity = monthsToPeakProductivity;
	}

	public int getYearsToSimulate() {
		return yearsToSimulate;
	}

	public void setYearsToSimulate(int yearsToSimulate) {
		this.yearsToSimulate = yearsToSimulate;
	}

}
