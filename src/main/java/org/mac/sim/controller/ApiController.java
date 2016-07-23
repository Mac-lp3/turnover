package org.mac.sim.controller;

import org.mac.sim.domain.SimulationForm;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.simulation.SimpleQueueSimulationBuilder;
import org.mac.sim.simulation.Simulation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(path = "/simulation", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
	public Simulation postSimulationForm(@RequestParam(value = "type", required = false) String type,
			@RequestBody SimulationForm simulationForm) {

		Simulation sim = null;

		// TODO make this a const
		if ("simple".equalsIgnoreCase(type)) {

			SimpleQueueSimulationBuilder qsb = new SimpleQueueSimulationBuilder(simulationForm.getQuickStartPeriods(),
					simulationForm.getQuickStartRate());

			qsb.addWorkers(simulationForm.getQuickStartWorkers(), simulationForm.getQuickStartTaskTime(),
					simulationForm.getQuickStartRestTime());

			try {

				sim = qsb.build();

			} catch (TurnoverException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return sim;

	}
}
