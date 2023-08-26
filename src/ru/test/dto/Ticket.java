package ru.test.dto;

public class Ticket {
	
	private String origin;
	private String origin_name;
	private String destination;
	private String destination_name;
	private String departure_date;
	private String departure_time;
	private String arrival_date;
	private String arrival_time;
	private String carrier;
	private Integer stops;
	private Integer price;
	private Long duration;

	public Ticket(String origin, String origin_name, String destination,
			String destination_name, String departure_date,
			String departure_time, String arrival_date,
			String arrival_time, String carrier, Integer stops, Integer price) {
		super();
		this.origin = origin;
		this.origin_name = origin_name;
		this.destination = destination;
		this.destination_name = destination_name;
		this.departure_date = departure_date;
		this.departure_time = departure_time;
		this.arrival_date = arrival_date;
		this.arrival_time = arrival_time;
		this.carrier = carrier;
		this.stops = stops;
		this.price = price;
	}
	
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDestination_name() {
		return destination_name;
	}
	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	public String getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public Integer getStops() {
		return stops;
	}
	public void setStops(Integer stops) {
		this.stops = stops;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(" origin"+" : "+this.getOrigin()+",\n");
		sb.append(" origin_name"+" : "+this.getOrigin_name()+",\n");
		sb.append(" destination"+" : "+this.getDestination()+",\n");
		sb.append(" destination_name"+" : "+this.getDestination_name()+",\n");
		sb.append(" departure_date"+" : "+this.getDeparture_date()+",\n");
		sb.append(" departure_time"+" : "+this.getDeparture_time()+",\n");
		sb.append(" arrival_date"+" : "+this.getArrival_date()+",\n");
		sb.append(" arrival_time"+" : "+this.getArrival_time()+",\n");
		sb.append(" carrier"+" : "+this.getCarrier()+",\n");
		sb.append(" stops"+" : "+this.getStops()+",\n");
		sb.append(" price"+" : "+this.getPrice()+",\n");
		sb.append(" duration"+" : "+this.getDuration()+",\n");
		sb.append("}");
		return sb.toString();
	}


}
