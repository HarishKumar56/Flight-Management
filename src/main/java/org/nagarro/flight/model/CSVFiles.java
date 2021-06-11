package org.nagarro.flight.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "csv_files")
public class CSVFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "file_id")
	private int fileId;

	@Column(name = "file_name")
	private String fileName;

	@OneToMany(mappedBy = "csvFiles", cascade = CascadeType.ALL)
	private Collection<Flights> flights = new ArrayList<Flights>();

	public CSVFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Collection<Flights> getFlights() {
		return flights;
	}

	public void setFlights(Collection<Flights> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "CSVFiles [fileId=" + fileId + ", fileName=" + fileName + ", flights=" + flights + "]";
	}

}
