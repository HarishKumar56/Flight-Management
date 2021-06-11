package org.nagarro.flight.repository.interfaces;

import java.util.List;

import org.nagarro.flight.model.CSVFiles;
import org.nagarro.flight.model.Flights;
import org.nagarro.flight.model.InputFlight;

public interface FlightsDao {

	/* (non-Javadoc)
	 * @see org.nagarro.flight.repository.FlightsDao#addFlights(java.lang.String, java.util.Set)
	 */
	void addFlights(CSVFiles csvFiles);

	/* (non-Javadoc)
	 * @see org.nagarro.flight.repository.FlightsDao#getCsvFile(java.lang.String)
	 */
	CSVFiles getCsvFile(String fileName);

	List<Flights> seachFlights(String string, InputFlight inputFlight);

	void deleteCsvFile(CSVFiles csvFile);

}