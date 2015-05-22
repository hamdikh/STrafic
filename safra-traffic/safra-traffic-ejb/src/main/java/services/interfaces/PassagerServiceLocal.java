package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.Passenger;

@Local
public interface PassagerServiceLocal {

	Boolean addPassager(Passenger passager);

	Passenger findPassagerById(int id);

	List<Passenger> findAllPassangers();

	void deletePassager(int id);

	Passenger getPassengerByName(String value);

}
