package org.mac.sim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimController {
	
	@RequestMapping(value = "/run", method = RequestMethod.GET)
	public void runIt(){
		System.out.println("~~~~~~~~~~~~~~");
	}
}
