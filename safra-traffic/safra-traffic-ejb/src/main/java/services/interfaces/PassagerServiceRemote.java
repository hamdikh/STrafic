package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import domain.Passenger;

@Remote
public interface PassagerServiceRemote {

	Boolean addPassager(Passenger passager);

	Passenger findPassagerById(int id);

	List<Passenger> findAllPassangers();

	void deletePassager(int id);

}
