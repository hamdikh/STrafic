package services.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.interfaces.PassagerServiceLocal;
import services.interfaces.PassagerServiceRemote;
import domain.Driver;
import domain.Passenger;

/**
 * Session Bean implementation class PassagerServices
 */
@Stateless
@LocalBean
public class PassagerServices implements PassagerServiceLocal,
		PassagerServiceRemote {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public PassagerServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean addPassager(Passenger passenger) {
		Boolean b = false;
		try {
			entityManager.merge(passenger);
			b = true;
		} catch (Exception e) {
			System.out.println("problem ...");
		}
		return b;
	}

	@Override
	public Passenger findPassagerById(int id) {
		return entityManager.find(Passenger.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passenger> findAllPassangers() {
		return entityManager.createQuery(
				"select p from User p where DTYPE= Passenger ").getResultList();
	}

	@Override
	public void deletePassager(int id) {
		entityManager.remove(findPassagerById(id));
	}

	@Override
	public Passenger getPassengerByName(String value) {
		return (Passenger) entityManager.createQuery(
				"select p from User p where DTYPE= Passenger and name = "
						+ value).getSingleResult();
	}

}
