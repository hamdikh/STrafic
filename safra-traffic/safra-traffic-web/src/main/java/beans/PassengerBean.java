package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.PassagerServiceLocal;
import domain.Passenger;

@ManagedBean
@ViewScoped
public class PassengerBean {

	@EJB
	private PassagerServiceLocal passagerServiceLocal;

	private Passenger passenger;
	private List<Passenger> passengers;
	private Boolean visibility = false;

	@PostConstruct
	public void initModel() {
		passenger = new Passenger();
		passengers = new ArrayList<Passenger>();
		passengers = passagerServiceLocal.findAllPassangers();
	}

	public void doSelect() {
		setVisibility(true);
	}

	public String doSaveOrUpdate() {
		passagerServiceLocal.addPassager(passenger);
		passengers = passagerServiceLocal.findAllPassangers();
		setVisibility(false);
		return "";
	}

	public String doDeletePass() {
		passagerServiceLocal.deletePassager(passenger.getId());
		setVisibility(false);
		return "";
	}

	public String doAddBus() {
		passagerServiceLocal.addPassager(passenger);
		;
		setVisibility(false);
		return "";
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public List<Passenger> getPassengers() {
		passengers = passagerServiceLocal.findAllPassangers();
		for (Passenger passenger : passengers) {
			System.out.println(passenger.getName());
		}
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

}
