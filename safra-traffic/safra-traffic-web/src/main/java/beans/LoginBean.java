package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import services.interfaces.IdentificationServiceLocal;
import domain.BusMan;
import domain.Driver;
import domain.Passenger;
import domain.User;

@ManagedBean
@SessionScoped
public class LoginBean {

	@EJB
	IdentificationServiceLocal identificationServiceLocal;

	@ManagedProperty("#{identity}")
	private IdentityBean identityBean;

	private String login;
	private String pwd;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public IdentityBean getIdentityBean() {
		return identityBean;
	}

	public void setIdentityBean(IdentityBean identityBean) {
		this.identityBean = identityBean;
	}

	public String doLogin() {
		String navigateTo = null;
		User user = identificationServiceLocal.login(login, pwd);
		if (user != null) {
			identityBean.setUser(user);
			if (user instanceof BusMan) {
				navigateTo = "/BusMan/Lines/ViewLines?faces-redirect=true";
			} else if (user instanceof Passenger) {
				navigateTo = "/Passenger/BuyTicket?faces-redirect=true";
			} else if (user instanceof Driver) {
				navigateTo = "/Driver/ListOfAssignedBuses?faces-redirect=true";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"NON AUTORISE", null

					));
		}
		return navigateTo;
	}

}
