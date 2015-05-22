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

	private User user;

	public IdentityBean() {
	}

	public Boolean hasRole(String role) {
		Boolean response = false;
		switch (role) {
		case "Driver":
			response = user instanceof Driver;
			break;
		case "BusMan":
			response = user instanceof BusMan;
			break;
		case "Passenger":
			response = user instanceof Passenger;
			break;
		}
		return response;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
