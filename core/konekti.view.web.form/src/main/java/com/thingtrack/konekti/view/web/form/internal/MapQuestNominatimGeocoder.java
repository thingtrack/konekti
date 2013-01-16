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
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GeocodingException;
import org.vaadin.addons.locationtextfield.LocationProvider;
import org.vaadin.addons.locationtextfield.LocationType;

/**
 * {@link LocationProvider} which uses MapQuest Nominatim Web Service
 */
public class MapQuestNominatimGeocoder extends
		ExtendedURLConnectionGeocoder<GeocodedLocation> {

	// URLs
	private static final String BASE_URL = "http://open.mapquestapi.com/nominatim/v1/search?";
	private static final String REVERSE_BASE_URL = "http://open.mapquestapi.com/nominatim/v1/reverse?";
	
	// URL COMMON PARAMETERS
	private static final String OUTPUT_FORMAT_PARAM_KEY = "format=";
	private static final String QUERY_PARAM_KEY = "q="; 
	private static final String LIMIT_PARAM_KEY = "limit=";
	private static final String LATITUDE_PARAM_KEY = "lat=";
	private static final String LONGITUDE_PARAM_KEY = "lon=";
	
	private static MapQuestNominatimGeocoder instance;
	
	private MapQuestNominatimGeocoder(){}
	
	public static MapQuestNominatimGeocoder getInstance(){
		
		if(instance == null)
			instance = new MapQuestNominatimGeocoder();
		
		return instance;
	}

	@Override
	public Collection<GeocodedLocation> reverseGeocode(double latitude,
			double longitude) throws GeocodingException {

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

	@Override
	protected String getURL(String address) throws UnsupportedEncodingException {
		
		StringBuilder sb = new StringBuilder();

		// Output format
		sb.append(OUTPUT_FORMAT_PARAM_KEY + "json");
		// Results limit
		if (this.getLimit() > 0)
			sb.append("&" + LIMIT_PARAM_KEY + getLimit());
		//Query String
		sb.append("&" + QUERY_PARAM_KEY + URLEncoder.encode(address, "UTF-8"));
			
		//Compound the url
		return BASE_URL + sb.toString();
	}

	@Override
	protected Collection<GeocodedLocation> createLocations(String address,
			String input) throws GeocodingException {

		 final Set<GeocodedLocation> locations = new LinkedHashSet<GeocodedLocation>();
		 
		 try {
			JSONArray results = new JSONArray(input);
			boolean ambiguous = results.length() > 1;
			
			for (int i = 0; i < results.length(); i++) {
				
				JSONObject result = results.getJSONObject(i);
                GeocodedLocation loc = new GeocodedLocation();
                loc.setAmbiguous(ambiguous);
                loc.setOriginalAddress(address);
                loc.setGeocodedAddress(result.getString("display_name"));
                loc.setLat(Double.parseDouble(result.getString("lat")));
                loc.setLon(Double.parseDouble(result.getString("lon")));
                loc.setType(getLocationType(result));
                if (result.has("address")) {
                    JSONObject obj = result.getJSONObject("address");
                    if (obj.has("house_number"))
                        loc.setStreetNumber(obj.getString("house_number"));
                    if (obj.has("road"))
                        loc.setRoute(obj.getString("road"));
                    if (obj.has("city"))
                        loc.setLocality(obj.getString("city"));
                    if (obj.has("county"))
                        loc.setAdministrativeAreaLevel2(obj.getString("county"));
                    if (obj.has("state"))
                        loc.setAdministrativeAreaLevel1(obj.getString("state"));
                    if (obj.has("postcode"))
                        loc.setPostalCode(obj.getString("postcode"));
                    if (obj.has("country_code"))
                        loc.setCountry(obj.getString("country_code").toUpperCase());
                }
                locations.add(loc);
			}
			
		} catch (JSONException e) {
            throw new GeocodingException(e.getMessage(), e);
		}
		 
		return locations;
	}

	/**
	 * Retrieve the full URL to fetch
	 * 
	 * @param latitude
	 *            and longitude input double
	 * @return full URL
	 * @throws UnsupportedEncodingException
	 *             if subclass uses {@link java.net.URLEncoder} and it fails
	 */
	protected String getReverseURL(double latitude, double longitude)
			throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder();

		// Output format
		sb.append(OUTPUT_FORMAT_PARAM_KEY + "json");
		// Results limit
		if (this.getLimit() > 0)
			sb.append("&" + LIMIT_PARAM_KEY + getLimit());
		// Latitude and longitude
		sb.append("&" + LATITUDE_PARAM_KEY + latitude);
		sb.append("&" + LONGITUDE_PARAM_KEY + longitude);
			
		//Compound the url
		return REVERSE_BASE_URL + sb.toString();


	}
	
	private LocationType getLocationType(JSONObject result) throws JSONException {
        
		if(!(result.has("class") && result.has("type")))
			return null;
		
		final String classValue = result.getString("class");
        final String type = result.getString("type");
        if ("highway".equals(classValue) || "railway".equals(classValue))
            return LocationType.ROUTE;
        else if ("amenity".equals(classValue) || "liesure".equals(classValue) || "natural".equals(type)
          || "shop".equals(classValue) || "tourism".equals(classValue) || "waterway".equals(type)) {
            return LocationType.POI;
        } else if ("building".equals(classValue))
            return LocationType.STREET_ADDRESS;
        else if ("place".equals(classValue)) {
            if ("house".equals(type) || "houses".equals(type) || "airport".equals(type) || "farm".equals(type))
                return LocationType.STREET_ADDRESS;
            else if ("city".equals(type) || "hamlet".equals(type) || "town".equals(type) || "unincorporated_area".equals(type)
              || "locality".equals(type) || "village".equals(type) || "municipality".equals(type)) {
                return LocationType.LOCALITY;
            } else if ("state".equals(type) || "region".equals(type))
                return LocationType.ADMIN_LEVEL_1;
            else if ("postcode".equals(type))
                return LocationType.POSTAL_CODE;
            else if ("country".equals(type))
                return LocationType.COUNTRY;
            else if ("county".equals(type))
                return LocationType.ADMIN_LEVEL_2;
            else if ("subdivision".equals(type) || "suburb".equals(type))
                return LocationType.NEIGHBORHOOD;
            else if ("moor".equals(type) || "island".equals(type) || "islet".equals(type) || "sea".equals(type))
                return LocationType.POI;
        } else if ("boundary".equals(classValue) && "administrative".equals(type))
                return LocationType.ADMIN_LEVEL_1;
        return LocationType.UNKNOWN;
    }
}
