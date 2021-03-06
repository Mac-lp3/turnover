package org.mac.sim.simulation;

import org.mac.sim.domain.Department;
import org.mac.sim.domain.SimulationParameters;

class YearlyTurnoverSimulationParameters implements SimulationParameters {

	private Department department;
	private double monthlyTurnOverInterval;
	private int yearsToSimulate;

	// TODO constant naming conventions
	YearlyTurnoverSimulationParameters(final Department department, final double monthlyTurnOverInterval,
			final int yearsToSimulate) {

		this.department = department;
		this.monthlyTurnOverInterval = monthlyTurnOverInterval;
		this.yearsToSimulate = yearsToSimulate;
	}

	public Department getDepartment() {
		return department;
	}

	public double getMonthlyTurnOverInterval() {
		return monthlyTurnOverInterval;
	}

	public int getYearsToSimulate() {
		return yearsToSimulate;
	}

	public void setTotalPeriods(int totalPeriods) {
		// TODO Auto-generated method stub

	}

	public void setPeriodUnits(String units) {
		// TODO Auto-generated method stub

	}

}
