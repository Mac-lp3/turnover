package org.mac.sim.controller;

import org.mac.sim.domain.SimulationBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class SimController {

	@RequestMapping("/")
	public String index() {
		System.out.println("plsplsplsplspl");
		return "index";
	}

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
	public String runThreads() {

		System.out.println("does this work?");
		return "index.html";

	}
}
