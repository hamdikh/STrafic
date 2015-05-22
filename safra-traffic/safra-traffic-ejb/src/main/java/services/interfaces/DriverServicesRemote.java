package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Bus;
import domain.Driver;

@Remote
public interface DriverServicesRemote {

	Driver findDriverById(Integer id);

	List<Bus> findBusesByDriverId(Integer idDriver);

	Boolean addDriver(Driver driver);

	void editDriver(int id);

	Driver findDriverById(int id);

	List<Driver> findAllDrivers();

	void deleteDriver(int id);

	Driver getDriverByName(String value);
}
