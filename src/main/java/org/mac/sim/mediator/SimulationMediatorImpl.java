package org.mac.sim.mediator;

import org.mac.sim.domain.SimulationConfigrations;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.simulation.SimpleQueueSimulationBuilder;
import org.mac.sim.simulation.Simulation;
import org.springframework.stereotype.Component;

@Component
public class SimulationMediatorImpl implements SimulationMediator {

	private static final String SIMPLE_TYPE_NAME = "simple";
	private static final String PROBABILITY_TYPE_NAME = "probability";
	private static final String LINEAR_TYPE_NAME = "linear";

	public Simulation runSimulationByType(String type, SimulationConfigrations configurations)
			throws TurnoverException {

		Simulation sim = null;

		if (SIMPLE_TYPE_NAME.equalsIgnoreCase(type)) {

			// Simple simulations only rely on a single task and worker
			// configuration
			SimpleQueueSimulationBuilder qsb = new SimpleQueueSimulationBuilder(configurations);

			qsb.addWorkers(configurations.getWorkerConfigurations().get(0).getTotal(),
					configurations.getWorkerConfigurations().get(0).getAdditionalTime(),
					configurations.getWorkerConfigurations().get(0).getRestTime());

			try {

				sim = qsb.build();

			} catch (TurnoverException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (PROBABILITY_TYPE_NAME.equalsIgnoreCase(type)) {

		} else if (LINEAR_TYPE_NAME.equalsIgnoreCase(type)) {

		}

		return sim;

	}

}
