package org.mac.sim.domain;

public class Employee {

	int totalExperience;

	/**
	 * New employees obviously start with zero experience
	 */
	public Employee() {
		this.totalExperience = 0;
	}

	/**
	 * Generates a boolean value based on the eployee's probability of leaving
	 * the company. The probability is dependant on how long that employee has
	 * been with the company.
	 * 
	 * @return boolean value indicating if this empolyee has quit
	 */
	public boolean isTurnedOver() {
		boolean turnOver = false;
		if (Math.random() <= getTurnOverProbability()) {
			turnOver = true;
		}
		return turnOver;
	}

	/**
	 * An employee's productivity is based on how long they have been with the
	 * company. A negative value for the first months indicates the additional
	 * resources required to train a new employee.
	 * 
	 * @return the productivity value (-.25 - 1) of this employee
	 */
	public double getProductivity() {

		double productivity;

		switch (totalExperience) {
		case 0:
			productivity = -.25;
			break;
		case 1:
			productivity = 0;
			break;
		case 2:
			productivity = .1;
			break;
		case 3:
			productivity = .2;
			break;
		case 4:
			productivity = .3;
			break;
		case 5:
			productivity = .4;
			break;
		case 6:
			productivity = .5;
			break;
		case 7:
			productivity = .6;
			break;
		case 8:
			productivity = .7;
			break;
		case 9:
			productivity = .8;
			break;
		case 10:
			productivity = .9;
			break;
		default:
			productivity = 1;
			break;
		}

		return productivity;
	}

	public double getTurnOverProbability() {

		double turnOverProbability;

		switch (totalExperience) {
		case 0:
			turnOverProbability = .03;
			break;
		case 1:
			turnOverProbability = .03;
			break;
		case 2:
			turnOverProbability = .03;
			break;
		case 3:
			turnOverProbability = .05;
			break;
		case 4:
			turnOverProbability = .05;
			break;
		case 5:
			turnOverProbability = .05;
			break;
		case 6:
			turnOverProbability = .07;
			break;
		case 7:
			turnOverProbability = .07;
			break;
		case 8:
			turnOverProbability = .09;
			break;
		case 9:
			turnOverProbability = .11;
			break;
		case 10:
			turnOverProbability = .13;
			break;
		default:
			turnOverProbability = .15;
			break;
		}

		return turnOverProbability;
	}

	public int getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(int totalExperience) {
		this.totalExperience = totalExperience;
	}

}
