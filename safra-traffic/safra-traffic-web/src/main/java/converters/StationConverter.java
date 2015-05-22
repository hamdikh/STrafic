package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import services.interfaces.StationServicesLocal;
import domain.Station;

@FacesConverter("sl")
public class StationConverter implements Converter {

	@Inject
	private StationServicesLocal stationServicesLocal;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Station station = null;
		if (!value.trim().equals("")) {
			station = stationServicesLocal.findStationByName(value);
		}
		return station;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String eqString = null;
		if (value == null || value.equals("")) {
			eqString = "";
		} else {
			eqString = ((Station) value).getName();
		}
		return eqString;
	}

}
