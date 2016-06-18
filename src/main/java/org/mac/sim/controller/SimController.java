package org.mac.sim.controller;

import org.mac.sim.clock.ClockBuilder;
import org.mac.sim.domain.SimulationBuilder;
import org.mac.sim.thread.QueueManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class SimController {

	@RequestMapping(path = "/run")
	public void runIt() {

		SimulationBuilder sb = new SimulationBuilder();
		sb.setMonthsToPeakProductivity(12);
		sb.setTotalEmployees(100);
		sb.setPercentYearlyTurnOver(.06);
		sb.setYearsToSimulate(2);
		sb.build();

	}

	@RequestMapping("/thread")
	public void runThreads() {

		System.out.println("does this work?");
		QueueManager qm = new QueueManager(null, 0, ClockBuilder.build(0));
		qm.run();
	}
}
