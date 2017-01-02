package com.dev.challenge.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author tapan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

	@JsonIgnore
	private Bounds bounds;

	@JsonProperty("location")
	private Coordinates location;

	@JsonIgnore
	private String[] locationType;

	@JsonIgnore
	private Bounds viewport;

	/**
	 * @return the bounds element
	 */
	public Bounds getBounds() {
		return bounds;
	}

	
	/**
	 * @param bounds sets the bounds element
	 */
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	
	/**
	 * @return the location element
	 */
	public Coordinates getLocation() {
		return location;
	}

	
	/**
	 * @param location sets the location element
	 */
	public void setLocation(Coordinates location) {
		this.location = location;
	}

	
	/**
	 * @return the locationType element
	 */
	public String[] getLocationType() {
		return locationType;
	}

	
	/**
	 * @param locationType sets the locationType element
	 */
	public void setLocationType(String[] locationType) {
		this.locationType = locationType;
	}

	
	/**
	 * @return the viewport element
	 */
	public Bounds getViewport() {
		return viewport;
	}

	
	/**
	 * @param viewport sets the viewport element
	 */
	public void setViewport(Bounds viewport) {
		this.viewport = viewport;
	}
	
	
}
