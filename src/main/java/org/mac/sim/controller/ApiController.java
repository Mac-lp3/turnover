package org.mac.sim.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(path = "/simulation", method = RequestMethod.POST)
	public void postSimulationForm(@RequestParam(value = "type", required = false) String type) {

	}

}
