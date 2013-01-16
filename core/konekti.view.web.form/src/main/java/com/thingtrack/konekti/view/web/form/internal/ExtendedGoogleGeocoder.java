package com.thingtrack.konekti.view.web.form.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GeocodingException;
import org.vaadin.addons.locationtextfield.LocationProvider;
import org.vaadin.addons.locationtextfield.LocationType;

/**
 * {@link LocationProvider} which uses Google.
 * 
 * This class can only be used when used in conjunction with Google Maps API See
 * http://code.google.com/apis/maps/documentation/geocoding/ for details
 */
public class ExtendedGoogleGeocoder extends
		ExtendedURLConnectionGeocoder<GeocodedLocation> {

	// URL
	private static final String URL = "maps.googleapis.com/maps/api/geocode/json?";
	private static final String INSECURE_URL = "http://" + URL;
	private static final String SECURE_URL = "https://" + URL;

	// URL COMMON PARAMETERS
	private static final String ADDRESS_PARAM_KEY = "address=";
	private static final String SENSOR_PARAM_KEY = "sensor=";
	private static final String LATLNG_PARAM_KEY = "latlng=";
	private static final String LANGUAGE_PARAM_KEY = "language=";

	private Locale language;

	private static ExtendedGoogleGeocoder instance;

	private boolean useSecureConnection;

	private ExtendedGoogleGeocoder() {
	}

	private ExtendedGoogleGeocoder(Locale locale) {
		this.language = locale;
	}

	public static ExtendedGoogleGeocoder getInstance() {

		if (instance == null)
			instance = new ExtendedGoogleGeocoder();

		return instance;
	}

	public static ExtendedGoogleGeocoder getInstance(Locale locale) {

		if (instance == null)
			instance = new ExtendedGoogleGeocoder(locale);

		return instance;
	}

	@Override
	protected String getURL(String address) throws UnsupportedEncodingException {
		return (this.useSecureConnection ? SECURE_URL : INSECURE_URL)
				+ ADDRESS_PARAM_KEY + URLEncoder.encode(address, "UTF-8") + "&"
				+ SENSOR_PARAM_KEY + "false";
	}

	@Override
	protected String getReverseURL(double latitude, double longitude) {
		return (this.useSecureConnection ? SECURE_URL : INSECURE_URL)
				+ LATLNG_PARAM_KEY + latitude + "," + longitude + "&"
				+ SENSOR_PARAM_KEY + "false";
	}

	@Override
	protected Collection<GeocodedLocation> createLocations(String address,
			String input) throws GeocodingException {

		final Set<GeocodedLocation> locations = new LinkedHashSet<GeocodedLocation>();
		try {
			JSONObject obj = new JSONObject(input);
			if ("OK".equals(obj.getString("status"))) {
				JSONArray results = obj.getJSONArray("results");
				boolean ambiguous = results.length() > 1;
				int limit = results.length();
				if (this.getLimit() > 0)
					limit = Math.min(this.getLimit(), limit);
				for (int i = 0; i < limit; i++) {
					JSONObject result = results.getJSONObject(i);
					GeocodedLocation loc = new GeocodedLocation();
					loc.setAmbiguous(ambiguous);
					loc.setOriginalAddress(address);
					loc.setGeocodedAddress(result
							.getString("formatted_address"));
					JSONArray components = result
							.getJSONArray("address_components");
					for (int j = 0; j < components.length(); j++) {
						JSONObject component = components.getJSONObject(j);
						String value = component.getString("short_name");
						JSONArray types = component.getJSONArray("types");
						for (int k = 0; k < types.length(); k++) {
							String type = types.getString(k);
							if ("street_number".equals(type))
								loc.setStreetNumber(value);
							else if ("route".equals(type))
								loc.setRoute(value);
							else if ("locality".equals(type))
								loc.setLocality(value);
							else if ("administrative_area_level_1".equals(type))
								loc.setAdministrativeAreaLevel1(value);
							else if ("administrative_area_level_2".equals(type))
								loc.setAdministrativeAreaLevel2(value);
							else if ("country".equals(type))
								loc.setCountry(value);
							else if ("postal_code".equals(type))
								loc.setPostalCode(value);
						}
					}
					JSONObject location = result.getJSONObject("geometry")
							.getJSONObject("location");
					loc.setLat(location.getDouble("lat"));
					loc.setLon(location.getDouble("lng"));
					loc.setType(getLocationType(result));
					locations.add(loc);
				}
			}
		} catch (JSONException e) {
			throw new GeocodingException(e.getMessage(), e);
		}
		return locations;
	}

	private LocationType getLocationType(JSONObject result)
			throws JSONException {
		if (!result.has("types"))
			return LocationType.UNKNOWN;
		JSONArray types = result.getJSONArray("types");
		for (int i = 0; i < types.length(); i++) {
			final String type = types.getString(i);
			if ("street_address".equals(type))
				return LocationType.STREET_ADDRESS;
			else if ("route".equals(type))
				return LocationType.ROUTE;
			else if ("intersection".equals(type))
				return LocationType.INTERSECTION;
			else if ("country".equals(type))
				return LocationType.COUNTRY;
			else if ("administrative_area_level_1".equals(type))
				return LocationType.ADMIN_LEVEL_1;
			else if ("administrative_area_level_2".equals(type))
				return LocationType.ADMIN_LEVEL_2;
			else if ("locality".equals(type))
				return LocationType.LOCALITY;
			else if ("neighborhood".equals(type))
				return LocationType.NEIGHBORHOOD;
			else if ("postal_code".equals(type))
				return LocationType.POSTAL_CODE;
			else if ("point_of_interest".equals(type))
				return LocationType.POI;
		}
		return LocationType.UNKNOWN;
	}

	public boolean isUseSecureConnection() {
		return useSecureConnection;
	}

	public void setUseSecureConnection(boolean useSecureConnection) {
		this.useSecureConnection = useSecureConnection;
	}

	@Override
	public Collection<GeocodedLocation> reverseGeocode(double latitude, double longitude)
			throws GeocodingException {

		final Set<GeocodedLocation> locations = new LinkedHashSet<GeocodedLocation>();
		BufferedReader reader = null;
		try {
			String addr = getReverseURL(latitude, longitude);
			URLConnection con = new URL(addr).openConnection();
			con.setDoOutput(true);
			con.connect();
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream(), getEncoding()));
			final StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
				builder.append(line);
			Collection<GeocodedLocation> locs = createLocations(null, builder.toString());
			if (getLimit() > 0 && locs.size() > getLimit()) {
				List<GeocodedLocation> list = new ArrayList<GeocodedLocation>(locs);
				locations.addAll(list.subList(0, getLimit()));
			} else {
				locations.addAll(locs);
			}
		} catch (Exception e) {
			throw new GeocodingException(e.getMessage(), e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
		return locations;
	}

}
