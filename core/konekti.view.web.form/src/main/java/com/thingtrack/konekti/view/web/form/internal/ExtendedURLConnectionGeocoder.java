package com.thingtrack.konekti.view.web.form.internal;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GeocodingException;
import org.vaadin.addons.locationtextfield.URLConnectionGeocoder;

public abstract class ExtendedURLConnectionGeocoder<T extends GeocodedLocation> extends
		URLConnectionGeocoder<GeocodedLocation> {
	
	/**
     * Retrieve the full URL to fetch
     * @param geological coordinates input
     * @return full URL
     * @throws UnsupportedEncodingException if subclass uses {@link java.net.URLEncoder} and it fails
     */
    protected abstract String getReverseURL(double latitude,  double laitude) throws UnsupportedEncodingException;
    
    
    public abstract Collection<GeocodedLocation> reverseGeocode(double latitude, double longitude) throws GeocodingException; 

}
