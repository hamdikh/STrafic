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
			String jpql = "SELECT bd.id FROM BusDriv bd WHERE bd.driver = :param1";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param1", driver);
			busDrivers = query.getResultList();

			for (BusDriv busDriv : busDrivers) {
				Bus bus = stationServices.findBusById(busDriv.getBus().getId());
				buses.add(bus);

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
		return entityManager.createQuery("Select b from Buses b")
				.getResultList();
	}

}
