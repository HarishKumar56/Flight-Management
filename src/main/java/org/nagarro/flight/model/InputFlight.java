package org.nagarro.flight.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.rmi.CORBA.UtilDelegate;

import org.springframework.format.annotation.DateTimeFormat;

public class InputFlight {

	private String arrLocation;

	private String depLocation;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private java.util.Date flightDate;

	private String flightClass;

	private String outPutPref;

	public InputFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getArrLocation() {
		return arrLocation;
	}

	public void setArrLocation(String arrLocation) {
		this.arrLocation = arrLocation.toUpperCase();
	}

	public String getDepLocation() {
		return depLocation;
	}

	public void setDepLocation(String depLocation) {
		this.depLocation = depLocation.toUpperCase();
	}


	public String getFlightClass() {
		return flightClass;
	}
	
	public Date getSqlDate() {
		Date date = new Date(flightDate.getTime());
		return date;
	}

	public java.util.Date getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(java.util.Date flightDate) {
		this.flightDate = flightDate;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass.toUpperCase();
	}

	public String getOutPutPref() {
		return outPutPref;
	}

	public void setOutPutPref(String outPutPref) {
		this.outPutPref = outPutPref.toUpperCase();
	}


	@Override
	public String toString() {
		return "InputFlight [arrLocation=" + arrLocation + ", depLocation=" + depLocation + ", flightDate=" + flightDate
				+ ", flightClass=" + flightClass + ", outPutPref=" + outPutPref + "]";
	}
	
	

}
