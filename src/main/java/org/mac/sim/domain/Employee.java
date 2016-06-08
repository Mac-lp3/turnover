package org.mac.sim.domain;

import java.util.HashMap;
import java.util.Map;

public class Employee {

	private int totalExperience;
	private int monthsToPeakProductivity;
	private Map<Integer, Double> productivityMap;

	/**
	 * New employees obviously start with zero experience
	 */
	public Employee() {
		productivityMap = new HashMap<Integer, Double>();
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
	 * company. These values are constructed based on the
	 * numberOfMonthsToPeakProductivity value.
	 * 
	 * @return This employee's productivity for a given month past start date
	 */
	public double getProductivity() {

		double productivity;

		if (this.productivityMap.containsKey(totalExperience)) {
			productivity = this.productivityMap.get(totalExperience);
		} else {
			productivity = 1;
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

	public int getMonthsToPeakProductivity() {
		return monthsToPeakProductivity;
	}

	/**
	 * Constructs a map of efficiency values for each month once an employee is
	 * on-boarded. Initial efficiency is -1/x (x being months to peak) to
	 * represent the effort of training.
	 * 
	 * @param monthsToPeakEfficiency
	 */
	public void setMonthsToPeakProductivity(int monthsToPeakProductivity) {

		this.monthsToPeakProductivity = monthsToPeakProductivity;

		double tempProd;
		for (int i = 0; i <= monthsToPeakProductivity; i++) {

			if (i == 0) {
				tempProd = -(1 / Float.valueOf(this.monthsToPeakProductivity));
			} else {
				tempProd = i / Float.valueOf(this.monthsToPeakProductivity);
			}

			this.productivityMap.put(i, tempProd);
		}
	}
}