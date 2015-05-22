package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import services.interfaces.DriverServicesLocal;
import domain.Bus;
import domain.Driver;

@ManagedBean
@ViewScoped
public class Driverbean {

	private List<Bus> buses;
	@EJB
	DriverServicesLocal driverServicesLocal;
	@ManagedProperty("#{identity.user}")
	private Driver driver;

	@PostConstruct
	public void initModel() {
		buses = driverServicesLocal.findBusesByDriverId(driver.getId());
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

}
