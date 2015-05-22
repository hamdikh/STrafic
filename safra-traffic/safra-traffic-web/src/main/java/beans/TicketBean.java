package beans;

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
	private List<Station> stations;
	private List<Station> stations2;
	private String Stars;
	private String erreur500 = "/erreur?faces-redirect=true";
	private String lista = "/index?faces-redirect=true";
	private String navigateUpdate = "/index?faces-redirect=true";

	@EJB
	private TicketServicesLocal ticketServicesLocal;

	@EJB
	private StationServicesLocal stationServicesLocal;

	Ticket ticket = new Ticket();
	Station stationDeparture = new Station();
	Station stationArrival = new Station();

	@ManagedProperty("#{identity.user}")
	private Passenger passenger;

	@PostConstruct
	public void initModel() {
		stations = stationServicesLocal.findAllStations();
		stations2 = stationServicesLocal.findAllStations();
	}

	public String doReserve() {
		try {
			ticket.setPassenger(passenger);
			switch (Stars) {
			case "1":
				ticket.setPrice(500.000);
				ticket.setStar(1);
				break;
			case "2":
				ticket.setPrice(500.000);
				ticket.setStar(2);
				break;
			case "3":
				ticket.setPrice(500.000);
				ticket.setStar(3);
				break;
			default:
				break;
			}
			ticket.setDate(new Date());
//			ticket.setBus(stationServicesLocal
//					.findBusByStations(stationArrival));
			ticketServicesLocal.addTicket(ticket);
			System.out.println(ticket.getDate());
			return lista;
		} catch (Exception e) {
			return erreur500;
		}
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public List<Station> getStations2() {
		return stations2;
	}

	public void setStations2(List<Station> stations2) {
		this.stations2 = stations2;
	}

	public String getStars() {
		return Stars;
	}

	public void setStars(String stars) {
		Stars = stars;
	}

	public String getErreur500() {
		return erreur500;
	}

	public void setErreur500(String erreur500) {
		this.erreur500 = erreur500;
	}

	public String getLista() {
		return lista;
	}

	public void setLista(String lista) {
		this.lista = lista;
	}

	public String getNavigateUpdate() {
		return navigateUpdate;
	}

	public void setNavigateUpdate(String navigateUpdate) {
		this.navigateUpdate = navigateUpdate;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

}