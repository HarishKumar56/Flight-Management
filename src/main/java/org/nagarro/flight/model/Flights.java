package org.nagarro.flight.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flights {

	@Id
	@Column(name = "flight_no")
	private String flightNo;

	@Column(name = "dep_location")
	private String depLocation;

	@Column(name = "arr_location")
	private String arrLocation;

	@Column(name = "valid_till")
	private Date validTill;

	@Column(name = "flight_time")
	private Time flightTime;

	@Column(name = "flight_duration")
	private float flightDuration;

	@Column(name = "fare")
	private float fare;

	@Column(name = "seat_avail")
	private String seatAvail;

	@Column(name = "flight_class")
	private String flightClass;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "csv_files")
	private CSVFiles csvFiles;

	public Flights() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flights(String flightNo, String depLocation, String arrLocation, Date validTill, Time flightTime,
			float flightDuration, float fare, String seatAvail, String flightClass) {
		super();
		this.flightNo = flightNo;
		this.depLocation = depLocation;
		this.arrLocation = arrLocation;
		this.validTill = validTill;
		this.flightTime = flightTime;
		this.flightDuration = flightDuration;
		this.fare = fare;
		this.seatAvail = seatAvail;
		this.flightClass = flightClass;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDepLocation() {
		return depLocation;
	}

	public void setDepLocation(String depLocation) {
		this.depLocation = depLocation;
	}

	public String getArrLocation() {
		return arrLocation;
	}

	public void setArrLocation(String arrLocation) {
		this.arrLocation = arrLocation;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Time getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Time flightTime) {
		this.flightTime = flightTime;
	}

	public float getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(float flightDuration) {
		this.flightDuration = flightDuration;
	}

	public float getFare() {
		return fare;
	}

	public void setFare(float fare) {
		this.fare = fare;
	}

	public String getSeatAvail() {
		return seatAvail;
	}

	public void setSeatAvail(String seatAvail) {
		this.seatAvail = seatAvail;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public CSVFiles getCsvFiles() {
		return csvFiles;
	}

	public void setCsvFiles(CSVFiles csvFiles) {
		this.csvFiles = csvFiles;
	}

	@Override
	public boolean equals(Object obj) {
		Flights flight = (Flights) obj;
		if (this.flightNo.equals(flight.getFlightNo())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return flightNo.hashCode();
	}

	@Override
	public String toString() {
		return "Flights [flightNo=" + flightNo + ", depLocation=" + depLocation + ", arrLocation=" + arrLocation
				+ ", validTill=" + validTill + ", flightTime=" + flightTime + ", flightDuration=" + flightDuration
				+ ", fare=" + fare + ", seatAvail=" + seatAvail + ", flightClass=" + flightClass + "]";
	}

}
