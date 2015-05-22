package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import services.interfaces.StationServicesLocal;
import services.interfaces.TicketServicesLocal;
import domain.Passenger;
import domain.Station;
import domain.Ticket;

@ManagedBean
@ViewScoped
public class TicketBean {
	private Date date1;
	private List<Station> stations = new ArrayList<>();
	private List<Station> stations2 = new ArrayList<>();
	private int Stars = 0;
	private String erreur500 = "/erreur?faces-redirect=true";
	private String lista = "/erreur?faces-redirect=true";
	private String navigateUpdate = "/index?faces-redirect=true";
	//
	// @EJB
	// private BusinessLogicServicesLocal businessLogicServicesLocal;

	@EJB
	private TicketServicesLocal ticketServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	Ticket ticket = new Ticket();
	Station stationDeparture = new Station();
	Station stationArrival = new Station();

	@ManagedProperty("#{identity}")
	private Passenger passenger;

	@PostConstruct
	public void initModel() {
		stations = stationServicesLocal.findAllStations();
		stations2 = stationServicesLocal.findAllStations();
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public String doSearchBus() {
		System.out.println("Hello");
		// List<Bus> buses = businessLogicServicesLocal
		// .findComingSoonBusesGoingToStation(stationDeparture.getName(),
		// stationArrival.getName());
		// System.out.println("size : " + buses.size());
		Station station1 = stationDeparture;
		Station station2 = stationArrival;
		System.out.println("affichage=" + station1);
		System.out.println("affichage=" + station2);

		return "";

	}

	public String doReserve() {
		try {
			ticket.setStar(Stars);
			ticket.setPassenger(passenger);
			ticketServicesLocal.addTicket(ticket);
			return lista;
		} catch (Exception e) {
			return erreur500;
		}
	}

	public Station getStationDeparture() {
		return stationDeparture;
	}

	public void setStationDeparture(Station stationDeparture) {
		this.stationDeparture = stationDeparture;
	}

	public Station getStationArrival() {
		return stationArrival;
	}

	public void setStationArrival(Station stationArrival) {
		this.stationArrival = stationArrival;
	}

	public List<Station> getStations() {
		stations = stationServicesLocal.findAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	// public Station doFindStationByName(String name) {
	// return businessLogicServicesLocal.findStationByName(name);
	// }

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public String doSome() {
		return "";
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Station> getStations2() {
		stations2 = stationServicesLocal.findAllStations();
		return stations2;
	}

	public void setStations2(List<Station> stations2) {
		this.stations2 = stations2;
	}

	public int getStars() {
		return Stars;
	}

	public void setStars(int stars) {
		Stars = stars;
	}

}