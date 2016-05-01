package org.mac.sim.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Department {

	private List<Employee> employees;
	private int totalEmployees;
	private int monthsToPeakProductivity;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public int getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(int totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	public int getMonthsToPeakProductivity() {
		return monthsToPeakProductivity;
	}

	public void setMonthsToPeakProductivity(int monthsToPeakProductivity) {
		this.monthsToPeakProductivity = monthsToPeakProductivity;
	}

	/**
	 * Returns a 0 - 1 value for the total overall productivity of the
	 * department.
	 * 
	 * @return the 0-1 percent value of the department's productivity
	 */
	public double getOverallProductivity() {

		double productivity = 0;
		for (Employee e : employees) {
			productivity += e.getProductivity();
		}

		return productivity / totalEmployees;
	}

	/**
	 * Selects an employee to replace based on their individual turn over
	 * probability. Replaces employee with highest probability if multiple
	 * employees return as leave candidates.
	 */
	public void executeTurnOver() {

		List<Integer> leaveCandidates = turnOverLoop();
		int terminateIndex = 0;

		// make sure we get at least one employee to terminate
		while (leaveCandidates.isEmpty()) {
			leaveCandidates = turnOverLoop();
		}

		// If multiple candidates, randomly select one
		if (leaveCandidates.size() > 1) {
			Random r = new Random();
			terminateIndex = leaveCandidates.get(r.nextInt(leaveCandidates.size()));
		} else {
			terminateIndex = leaveCandidates.get(0);
		}
		
		// Replace the leaving employee
		employees.remove(terminateIndex);
		addNewEmployee();

	}

	private List<Integer> turnOverLoop() {

		List<Integer> leaveCandidates = new ArrayList<Integer>();

		// Generate a random number
		double random = Math.random();

		// Compare it to each employee's turn over probability
		for (int i = 0; i < this.employees.size(); i++) {

			// If turn over probability is <= this is a possible leave candidate
			if (random <= this.employees.get(i).getTurnOverProbability()) {
				leaveCandidates.add(i);
			}
		}

		return leaveCandidates;
	}
	
	public void addNewEmployee(){
		Employee newGuy = new Employee();
		newGuy.setMonthsToPeakProductivity(this.monthsToPeakProductivity);
		employees.add(newGuy);
	}

}
