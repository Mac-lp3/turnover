package org.mac.sim.mediator;

import org.mac.sim.domain.SimulationConfigurations;
import org.mac.sim.exception.TurnoverException;
import org.mac.sim.simulation.Simulation;

public interface SimulationMediator {

	public Simulation runSimulationByType(final String type, final SimulationConfigurations configurations) throws TurnoverException;

}
