package org.mac.sim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class SimController {

	@RequestMapping(path = "/run")
	public void runIt() {
		System.out.println("~~~~~~~~~~~~~~");
	}
}
