package org.nagarro.flight.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.nagarro.flight.model.CSVFiles;
import org.nagarro.flight.model.Flights;
import org.nagarro.flight.model.InputFlight;
import org.nagarro.flight.repository.interfaces.FlightsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("flightsDao")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class FlightsDaoImpl implements FlightsDao {

	@Autowired
	SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nagarro.flight.repository.FlightsDao#addFlights(org.nagarro.flight.model.
	 * CSVFiles)
	 */
	@Override
	public void addFlights(CSVFiles csvFiles) {
		getSession().saveOrUpdate(csvFiles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nagarro.flight.repository.FlightsDao#getCsvFile(java.lang.String)
	 */
	@Override
	public CSVFiles getCsvFile(String fileName) {
		try {
			CSVFiles csvFiles = new CSVFiles();
			Query query = getSession().createQuery("from CSVFiles where fileName = :fileName");
			query.setParameter("fileName", fileName);
			if (query.getResultList().size() > 0) {
				csvFiles = (CSVFiles) query.getResultList().get(0);
			}
			return csvFiles;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nagarro.flight.repository.FlightsDao#seachFlights(java.lang.String,
	 * org.nagarro.flight.model.InputFlight)
	 */
	@Override
	public List<Flights> seachFlights(String string, InputFlight inputFlight) {
		List<Flights> flights = new ArrayList<>();
		try {
			Query query = getSession().createQuery("from Flights where arrLocation = :arr and depLocation = :dep "
					+ "and validTill > :valid and (flightClass = :fclass or flightClass = :both) and seatAvail = :avail order by "
					+ string);
			query.setParameter("arr", inputFlight.getArrLocation());
			query.setParameter("dep", inputFlight.getDepLocation());
			query.setParameter("valid", inputFlight.getFlightDate());
			query.setParameter("fclass", inputFlight.getFlightClass());
			query.setParameter("both", "EB");

			query.setParameter("avail", "Y");
			flights = (List<Flights>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nagarro.flight.repository.FlightsDao#deleteCsvFile(org.nagarro.flight.
	 * model.CSVFiles)
	 */
	@Override
	public void deleteCsvFile(CSVFiles csvFile) {
		if (csvFile != null) {
			try {
				getSession().delete(csvFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
