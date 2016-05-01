package org.mac.sim.domain;

import java.util.ArrayList;
import java.util.List;

public class SimulationBuilder {

	private int totalEmployees;
	private double percentYearlyTurnOver;
	private int yearsToSimulate;
	private int monthsToPeakProductivity;

	public Simulation build() {

		List<Employee> employees = new ArrayList<Employee>();
		Employee employee;
		for (int i = 0; i < totalEmployees; i++) {
			employee = new Employee();
			employee.setMonthsToPeakProductivity(monthsToPeakProductivity);
			employees.add(employee);
		}

		Department department = new Department();
		department.setTotalEmployees(totalEmployees);
		department.setEmployees(employees);

		return new Simulation(department, yearsToSimulate);

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
