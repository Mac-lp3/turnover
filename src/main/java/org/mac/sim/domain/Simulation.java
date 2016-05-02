package org.mac.sim.domain;

import java.util.HashMap;

/**
 * Class which executes the simulation loop and maintains the result data.
 * 
 * @author Home
 *
 */
public class Simulation {

	/**
	 * Constructor which runs the simulation.
	 * 
	 * @param department
	 */
	public Simulation(Department department, double monthlyTurnOverInterval, int yearsToSimulate) {

		double previousTurnOverValue = 0;
		double currentTurnOverValue = 0;

		int monthsToSimulate = yearsToSimulate * 12;

		// Run the event loop
		for (int i = 1; i < monthsToSimulate + 1; i++) {

			System.out.println("Current month: " + i);

			previousTurnOverValue = currentTurnOverValue;
			currentTurnOverValue = Math.floor(monthlyTurnOverInterval * i);

			if (currentTurnOverValue > previousTurnOverValue) {

				System.out.println("executing turnover");
				department.executeTurnOver();

			}

			System.out.println("Overall Productivity: " + department.getOverallProductivity());
			System.out.println("Incrementing experience");
			department.incrementExperience();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
		}

		// Sort employees by months of experience
		int tempVal;
		HashMap<String, Integer> experienceMap = new HashMap<String, Integer>();
		for (Employee emp : department.getEmployees()) {

			// Can group everyone at or above peak experience
			if (emp.getTotalExperience() >= department.getMonthsToPeakProductivity()) {

				if (experienceMap.containsKey(department.getMonthsToPeakProductivity() + "+")) {
					tempVal = experienceMap.get(department.getMonthsToPeakProductivity() + "+");
					tempVal++;
					experienceMap.put(department.getMonthsToPeakProductivity() + "+", tempVal);
				} else {
					experienceMap.put(department.getMonthsToPeakProductivity() + "+", 1);
				}
			} else {

				// Sort count by months of experience
				if (experienceMap.containsKey(emp.getTotalExperience())) {
					tempVal = experienceMap.get("" + emp.getTotalExperience());
					tempVal++;
					experienceMap.put("" + emp.getTotalExperience(), tempVal);
				} else {
					experienceMap.put("" + emp.getTotalExperience(), 1);
				}
			}
		}

		System.out.println("Simulation completed...");

		for (String key : experienceMap.keySet()) {
			System.out.println("Employees with " + key + " months of experience: " + experienceMap.get(key));
		}

	}
}
