package services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.DriverServicesLocal;
import services.interfaces.DriverServicesRemote;
import domain.Bus;
import domain.BusDriv;
import domain.Driver;

/**
 * Session Bean implementation class DriverServices
 */
@Stateless
public class DriverServices implements DriverServicesRemote,
		DriverServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	private StationServices stationServices;

	/**
	 * Default constructor.
	 */
	public DriverServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Driver findDriverById(Integer id) {
		return entityManager.find(Driver.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bus> findBusesByDriverId(Integer idDriver) {
		List<Bus> buses = new ArrayList<>();
		List<BusDriv> busDrivers = new ArrayList<>();
		try {

			Driver driver = findDriverById(idDriver);
			String jpql = "SELECT bd FROM BusDriv bd WHERE bd.driver = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", driver);
			busDrivers = query.getResultList();

			for (BusDriv busDriv : busDrivers) {
				buses.add(busDriv.getBus());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Driver> findAllBuses() {
		return entityManager.createQuery("Select b from Bus b").getResultList();
	}

	@Override
	public Boolean addDriver(Driver driver) {
		Boolean b = false;
		try {
			entityManager.merge(driver);
			b = true;
		} catch (Exception e) {
			System.out.println("problem ...");
		}
		return b;
	}

	@Override
	public void editDriver(int id) {
		entityManager.merge(findDriverById(id));
	}

	@Override
	public Driver findDriverById(int id) {
		return entityManager.find(Driver.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Driver> findAllDrivers() {
		List<Driver> drivers = new ArrayList<>();
		try {
			String jpql = "select d from User d where DTYPE = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", "Driver");
			drivers = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return drivers;
	}

	@Override
	public void deleteDriver(int id) {
		entityManager.remove(findDriverById(id));
	}

	@Override
	public Driver getDriverByName(String value) {
		return (Driver) entityManager.createQuery(
				"select p from User p where DTYPE= Driver and name = " + value)
				.getSingleResult();
	}

}
