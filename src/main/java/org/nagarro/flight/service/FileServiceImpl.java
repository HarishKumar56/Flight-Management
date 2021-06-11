package org.nagarro.flight.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.transaction.Transactional;

import org.nagarro.flight.model.Flights;
import org.nagarro.flight.repository.interfaces.FlightsDao;
import org.nagarro.flight.service.interfaces.FileService;
import org.nagarro.flight.service.interfaces.FlightsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service("fileService")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class FileServiceImpl implements FileService {

	private Path file;
	
	@Autowired
	FlightsDaoService flightsDaoService;

	@Override
	public void run() {
		fileReader(file);
	}

	/* (non-Javadoc)
	 * @see org.nagarro.flight.service.FileService#fileReader(java.nio.file.Path)
	 */
	@Override
	public void fileReader(Path file) {
		System.out.println(Thread.currentThread().getId());

		Set<Flights> allFlights = new HashSet<>();
		CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
		try {
			CSVReader reader = new CSVReaderBuilder(Files.newBufferedReader(file)).withCSVParser(parser).build();
			List<String[]> myList = reader.readAll();
			myList.forEach(f -> {

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmm", Locale.ENGLISH);

				try {
					Time time = new Time(timeFormatter.parse(f[4]).getTime());
					Date date = new Date(formatter.parse(f[3]).getTime());
					float duration = Float.parseFloat(f[5]);
					float fare = Float.parseFloat(f[6]);

					Flights flight = new Flights(f[0], f[1], f[2], date, time, duration, fare, f[7], f[8]);
					allFlights.add(flight);
				} catch (ParseException e) {

				}

			});
			
			flightsDaoService.addFlights(file.getFileName().toString(), allFlights);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/* (non-Javadoc)
	 * @see org.nagarro.flight.service.FileService#setFile(java.nio.file.Path)
	 */
	@Override
	public void setFile(Path file) {
		this.file = file;
	}

}
