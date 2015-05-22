package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.BusMan;
import domain.Driver;
import domain.Passenger;
import domain.User;

@ManagedBean(name = "identity")
@SessionScoped
public class IdentityBean {

	private User object;

	public IdentityBean() {
	}

	public User getObject() {
		return object;
	}

	public void setObject(User object) {
		this.object = object;
	}

	public Boolean hasRole(String role) {
		Boolean response = false;
		switch (role) {
		case "Driver":
			response = object instanceof Driver;
			break;
		case "BusMan":
			response = object instanceof BusMan;
			break;
		case "Passenger":
			response = object instanceof Passenger;
			break;
		}
		return response;
	}

}
