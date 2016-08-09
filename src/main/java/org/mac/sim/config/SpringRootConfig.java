package org.mac.sim.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "org.mac.sim.domain", "org.mac.sim.mediator" })
public class SpringRootConfig {
}
