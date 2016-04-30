package org.mac.sim.domain;

public class Employee {

	int totalExperience;
	double productivity;
	double turnOverProbability;

	public boolean isTurnedOver() {
		return false;
	}

	public int getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(int totalExperience) {
		this.totalExperience = totalExperience;
	}

	public double getProductivity() {
		return productivity;
	}

	public void setProductivity(double productivity) {
		this.productivity = productivity;
	}

	public double getTurnOverProbability() {
		return turnOverProbability;
	}

	public void setTurnOverProbability(double turnOverProbability) {
		this.turnOverProbability = turnOverProbability;
	}

}
