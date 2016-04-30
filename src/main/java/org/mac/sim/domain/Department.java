package org.mac.sim.domain;

import java.util.List;

public class Department {

	private List<Employee> employees;
	private int totalEmployees;

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

}
