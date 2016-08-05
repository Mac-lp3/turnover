package org.mac.sim.controller;

import org.mac.sim.domain.SimulationConfigrations;
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
			@RequestBody SimulationConfigrations simulationConfig) {

		Simulation sim = null;

		// TODO make this a const
		if ("simple".equalsIgnoreCase(type)) {
			
			// Simple simulations only rely on a single task and worker configuration
			SimpleQueueSimulationBuilder qsb = new SimpleQueueSimulationBuilder(simulationConfig);
			
			qsb.addWorkers(simulationConfig.getWorkerConfigurations().get(0).getTotal(),
					simulationConfig.getWorkerConfigurations().get(0).getAdditionalTime(),
					simulationConfig.getWorkerConfigurations().get(0).getRestTime());

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
