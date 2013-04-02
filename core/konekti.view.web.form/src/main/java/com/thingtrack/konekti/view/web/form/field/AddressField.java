package com.thingtrack.konekti.view.web.form.field;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addon.customfield.CustomField;
import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GoogleGeocoder;
import org.vaadin.addons.locationtextfield.LocationTextField;
import org.vaadin.vol.Control;
import org.vaadin.vol.Marker;
import org.vaadin.vol.MarkerLayer;
import org.vaadin.vol.OpenLayersMap;
import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.Popup;
import org.vaadin.vol.Popup.PopupStyle;

import com.thingtrack.konekti.domain.Address;
import com.thingtrack.konekti.service.api.AddressService;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AddressField extends CustomField {

	static final String CLASSNAME = "v-addressfield";

	private VerticalLayout mainLayout;
	
	private OpenLayersMap openLayersMap;
	private OpenStreetMapLayer openStreetMapLayer;
	private MarkerLayer markerLayer;
	private Marker addressMarker;	
	private LocationTextField<GeocodedLocation> addressTextField;
	private final GoogleGeocoder googleGeocoder;

	private AddressChangeListener listenerAddressChange = null;
	
	private Address address;

	// Enterprise service
	@Autowired
	private AddressService addressService;

	public AddressField() {
		// Style name
		setStyleName(CLASSNAME);

		// Google Geocoder to search the stop addresses
		googleGeocoder = GoogleGeocoder.getInstance();
		googleGeocoder.setLimit(25);

		mainLayout = buildMainLayout();
		setCompositionRoot(mainLayout);

		// Calculate latitude and longitude
		addressTextField.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {

						GeocodedLocation geocodedLocation = (GeocodedLocation) event.getProperty().getValue();

						if (geocodedLocation == null)
							return;

						if (addressMarker != null)
							markerLayer.removeComponent(addressMarker);

						addressMarker = createStopMarker(geocodedLocation);
						markerLayer.addComponent(addressMarker);

						// Center and Zoom the map
						//Bounds bounds = new Bounds(new Point(addressMarker.getLon(), addressMarker.getLat()));

						openLayersMap.setCenter(addressMarker.getLon(), addressMarker.getLat());
						//openLayersMap.zoomToExtent(bounds);
						openLayersMap.setZoom(18);

						// Check if the address already exists in the database
						address = getAddressByLontideLatitude(geocodedLocation.getLon(), geocodedLocation.getLat());
						
						if(!(address instanceof Address))
							address = fulfillAddress(geocodedLocation);
						
						if(listenerAddressChange != null)
							listenerAddressChange.addressChange(new AddressChangeEvent(address));
					}
				});

	}

	private VerticalLayout buildMainLayout() {

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);

		// Map definition
		openLayersMap = new OpenLayersMap();
		openLayersMap.setWidth("100%");
		openLayersMap.setHeight("100%");
		openLayersMap.setImmediate(false);
				
		openLayersMap.getControls().clear();
        openLayersMap.addControl(Control.ZoomPanel);
        openLayersMap.addControl(Control.TouchNavigation);
        
		mainLayout.addComponent(openLayersMap);
		mainLayout.setExpandRatio(openLayersMap, 1.0f);
        
		// Apply Map Layers
		openStreetMapLayer = new OpenStreetMapLayer();
		openLayersMap.addLayer(openStreetMapLayer);
		
		markerLayer = new MarkerLayer();
		openLayersMap.addLayer(markerLayer);

		HorizontalLayout addressTextFieldLayout = buildLocationTextFieldLayout();
		mainLayout.addComponent(addressTextFieldLayout);

		return mainLayout;
	}

	private HorizontalLayout buildLocationTextFieldLayout() {

		HorizontalLayout addressTextFieldLayout = new HorizontalLayout();
		addressTextFieldLayout = new HorizontalLayout();
		addressTextFieldLayout.setImmediate(false);
		addressTextFieldLayout.setWidth("100.0%");
		addressTextFieldLayout.setHeight("-1px");
		addressTextFieldLayout.setMargin(false);
		addressTextFieldLayout.setSpacing(false);

		// LocationTextField definition
		addressTextField = new LocationTextField<GeocodedLocation>(googleGeocoder, GeocodedLocation.class, "Dirección");
		addressTextField.setWidth("100%");
		addressTextField.setHeight("-1px");
		//addressTextField.setRequired(true);
		addressTextField.setRequiredError(addressTextField.getCaption() + " es un campo requerido");
		addressTextField.setImmediate(true);
		
		addressTextFieldLayout.addComponent(addressTextField);
		addressTextFieldLayout.setExpandRatio(addressTextField, 1.0f);

		return addressTextFieldLayout;
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {

		address = (Address)newDataSource.getValue();

		// Remove prexisting markers from the map
		if (addressMarker != null)
			markerLayer.removeComponent(addressMarker);

		// Create stops
		if (address == null) {
			address = new Address();

			newDataSource.setValue(address);

			// Reset the fields
			addressTextField.setValue(null);

			return;
		}

		// Origin Stop
		GeocodedLocation addressGeocoded = fulfillGeocodedAddress(address);
		addressTextField.setLocation(addressGeocoded);

		super.setPropertyDataSource(newDataSource);

	}

	@Override
	public Class<?> getType() {
		return Address.class;
		
	}

	@Override
	public Object getValue() {		
		return address;
		
	}
	
	@Override
	protected boolean isEmpty() {
		if (addressTextField.getValue() == null) {
			setRequiredError("Dirección es campo obligatorio");
			return true;
		}
		return false;
	}

	private Marker createStopMarker(GeocodedLocation geocodedLocation) {
		Marker marker = new Marker(geocodedLocation.getLon(), geocodedLocation.getLat());
		marker.setIcon(new ThemeResource("images/icons/scheduler-module/marker.png"));

		String street = geocodedLocation.getGeocodedAddress() != null ? geocodedLocation.getGeocodedAddress() : "";

		final Popup popup = new Popup(marker.getLon(), marker.getLat(), "</b></p><p><b>Dirección: </b>" + street);
		// + "</p><p><b> Localidad: </b>" + locality
		// + "</p><p><b> Código Postal: </b>" + postalCode
		// + "</p><p><b> Provincia: </b>" + province
		// + "</p><p><b> País: </b>" + country + "</p>");
		popup.setPopupStyle(PopupStyle.FRAMED_CLOUD);
		popup.setAnchor(marker);

		marker.addClickListener(new ClickListener() {

			@Override
			public void click(ClickEvent event) {

				openLayersMap.addPopup(popup);
			}
		});

		return marker;
	}

	private Address getAddressByLontideLatitude(double longitude, double latitude) {
		Address address = null;
		
		try {
			address = addressService.getByLongitudeLatitude(longitude, latitude);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return address;
	}

	private GeocodedLocation fulfillGeocodedAddress(Address address) {

		if (address == null)
			return null;

		GeocodedLocation addressGeocoded = new GeocodedLocation();

		addressGeocoded.setAdministrativeAreaLevel1(address.getProvince());
		addressGeocoded.setCountry(address.getCountry());
		addressGeocoded.setGeocodedAddress(address.getStreet());
		addressGeocoded.setLocality(address.getCity());
		addressGeocoded.setPostalCode(address.getZipCode());
		addressGeocoded.setStreetNumber(address.getLetter());
		addressGeocoded.setLon(address.getLongitude());
		addressGeocoded.setLat(address.getLatitude());

		return addressGeocoded;
	}

	private Address fulfillAddress(GeocodedLocation geocodedLocation) {
		Address address = new Address();
		address.setCity(geocodedLocation.getLocality());
		address.setCountry(geocodedLocation.getCountry());
		address.setLatitude(geocodedLocation.getLat());
		address.setLongitude(geocodedLocation.getLon());
		address.setProvince(geocodedLocation.getAdministrativeAreaLevel1());
		address.setStreet(geocodedLocation.getGeocodedAddress());
		address.setNumber(geocodedLocation.getStreetNumber());
		address.setZipCode(geocodedLocation.getPostalCode());

		return address;

	}

	public void addListenerAddressChange(AddressChangeListener listener) {
		this.listenerAddressChange = listener;
		
	}
	
	public interface AddressChangeListener extends Serializable {
		public void addressChange(AddressChangeEvent event);

	}

	public static class AddressChangeEvent {
		private Address address;

		public AddressChangeEvent(Address address) {
			this.address = address;
		}

		public Address getAddress() {
			return this.address;

		}

	}
}
