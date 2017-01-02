package com.dev.challenge.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author tapan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bounds {

	/**
	 * northeast coordinates elements as returned by googlemap api
	 */
	private Coordinates northeast;
	
	/**
	 * southwest coordinates elements as returned by googlemap api
	 */
	private Coordinates southwest;
	
	
	
	
	/**
	 * @return the northeast element
	 */
	public Coordinates getNortheast() {
		return northeast;
	}
	
	
	
	/**
	 * @param northeast sets the northeast element
	 */
	public void setNortheast(Coordinates northeast) {
		this.northeast = northeast;
	}
	
	
	
	/**
	 * @return the southwest element
	 */
	public Coordinates getSouthwest() {
		return southwest;
	}
	
	
	
	/**
	 * @param southwest sets the southwest element
	 */
	public void setSouthwest(Coordinates southwest) {
		this.southwest = southwest;
	}
	  
}
