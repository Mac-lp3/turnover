package org.mac.sim.domain;

import java.util.ArrayList;
import java.util.List;

public class SimulationBuilder {

	private int totalEmployees;
	private double percentYearlyTurnOver;
	private int yearsToSimulate;

	// for version 2
	private int monthsToPeakEfficiency;

	public void build() {

		List<Employee> employees = new ArrayList<Employee>();
		Employee employee;
		for (int i = 0; i < totalEmployees; i++) {
			employee = new Employee();
			employee.setMonthsToPeakEfficiency(monthsToPeakEfficiency);
			employees.add(employee);
		}

		Department department = new Department();
		department.setTotalEmployees(totalEmployees);
		department.setEmployees(employees);

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

	public int getMonthsToPeakEfficiency() {
		return monthsToPeakEfficiency;
	}

	public void setMonthsToPeakEfficiency(int monthsToPeakEfficiency) {
		this.monthsToPeakEfficiency = monthsToPeakEfficiency;
	}

	public int getYearsToSimulate() {
		return yearsToSimulate;
	}

	public void setYearsToSimulate(int yearsToSimulate) {
		this.yearsToSimulate = yearsToSimulate;
	}

}
