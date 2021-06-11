package org.nagarro.flight.service.interfaces;

import java.util.List;
import java.util.Set;

import org.nagarro.flight.model.Flights;
import org.nagarro.flight.model.InputFlight;

public interface FlightsDaoService {

	void addFlights(String fileName, Set<Flights> allFlights);

	void deleteFlights(String fileName);
	
	public List<Flights> searchFlights(InputFlight inputFlight);

}