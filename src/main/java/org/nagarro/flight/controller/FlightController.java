package org.nagarro.flight.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.nagarro.flight.model.Flights;
import org.nagarro.flight.model.InputFlight;
import org.nagarro.flight.service.interfaces.FlightsDaoService;
import org.nagarro.flight.service.interfaces.WatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FlightController {
	
	@Autowired
	WatcherService watcherService;
	
	@Autowired
	FlightsDaoService flightsDaoService;
	
	
	@PostConstruct
	public void init() {
		Thread thread = new Thread(watcherService);
		thread.start();
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/")
	public String getFlights(@ModelAttribute("inputFlight")InputFlight inputFlight , 
			Map<String ,List<Flights>> model) {
		model.put("searchedFlights", null);
		return "home";
	}
	
	@PostMapping("/")
	public String searchFlights(@ModelAttribute("inputFlight")InputFlight inputFlight ,
			Map<String ,List<Flights>> model) {
		List<Flights> allFlights = flightsDaoService.searchFlights(inputFlight);
		model.put("searchedFlights", allFlights);
		return "home";
	}

}
