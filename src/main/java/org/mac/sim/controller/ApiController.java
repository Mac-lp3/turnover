package org.mac.sim.controller;

import org.mac.sim.domain.SimulationForm;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(path = "/simulation", method = RequestMethod.POST, consumes="application/json;charset=UTF-8")
	public void postSimulationForm(@RequestParam(value = "type", required = false) String type,
			@ModelAttribute SimulationForm simulationForm) {

		System.out.println(simulationForm.getQuickStartPeriods());
		System.out.println(simulationForm.getQuickStartRate());
		System.out.println(simulationForm.getQuickStartUnits());
		System.out.println(simulationForm.getQuickStartWorkers());

	}

}
