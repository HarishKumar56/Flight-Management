package org.nagarro.flight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.nagarro.flight.model.CSVFiles;
import org.nagarro.flight.model.Flights;
import org.nagarro.flight.model.InputFlight;
import org.nagarro.flight.repository.interfaces.FlightsDao;
import org.nagarro.flight.service.interfaces.FlightsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("flightsDaoService")
public class FlightsDaoServiceImpl implements FlightsDaoService {
	
	@Autowired
	FlightsDao flightsDao;
	
	/* (non-Javadoc)
	 * @see org.nagarro.flight.service.FlightsDaoService#addFlights(java.lang.String, java.util.Set)
	 */
	@Override
	@Transactional
	public synchronized void addFlights(String fileName, Set<Flights> allFlights) {
		CSVFiles csvFiles = flightsDao.getCsvFile(fileName);
		csvFiles.setFileName(fileName);
		allFlights.forEach(f -> f.setCsvFiles(csvFiles));
		csvFiles.setFlights(allFlights);
		flightsDao.addFlights(csvFiles);		
	}
	
	
	/* (non-Javadoc)
	 * @see org.nagarro.flight.service.FlightsDaoService#deleteFlights(java.lang.String)
	 */
	@Override
	@Transactional
	public synchronized void deleteFlights(String fileName) {
		CSVFiles csvFile = flightsDao.getCsvFile(fileName);
		flightsDao.deleteCsvFile(csvFile);
	}
	
	
	@Override
	@Transactional
	public List<Flights> searchFlights(InputFlight inputFlight) {

		List<Flights> result = new ArrayList<>();
		if (inputFlight.getOutPutPref().equals("F")) {
			result = flightsDao.seachFlights("fare", inputFlight);
		} else if (inputFlight.getOutPutPref().equals("FD")) {
			result = flightsDao.seachFlights("flightDuration", inputFlight);
		}

		return result;

	}

}
