package org.mac.sim.controller;

import org.mac.sim.domain.SimulationConfigrations;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.mediator.SimulationMediator;
import org.mac.sim.simulation.Simulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private SimulationMediator simMediator;

	@RequestMapping(path = "/simulation", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
	public Simulation postSimulationForm(@RequestParam(value = "type", required = false) String type,
			@RequestBody SimulationConfigrations simulationConfig) {

		Simulation sim = null;

		try {
			
			sim = simMediator.runSimulationByType(type, simulationConfig);
			
		} catch (TurnoverException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sim;

	}
}
