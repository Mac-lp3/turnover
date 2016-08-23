package org.mac.sim.simulation;

import org.mac.sim.domain.SimulationConfigurations;
import org.mac.sim.domain.SimulationParameters;
import org.mac.sim.exception.TurnoverException;

/**
 * SimulationBuilder implementations are meant to 1) encapsulate the logic which
 * extracts relevant information from the configuration, 2) provide simple
 * methods for modifying/editing this information, all while 3) hiding the
 * details of the Simulation implementation itself.
 * 
 * @author Home
 *
 */
public abstract class SimulationBuilder {
	
	protected SimulationParameters simulationParams;
	
	protected SimulationBuilder(SimulationConfigurations simulationConfig) {

	}

	public abstract Simulation build() throws TurnoverException;

}
