package com.dev.challenge.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author tapan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressComponent {

	@JsonProperty("long_name")
	private String longName;
	@JsonProperty("short_name")
	private String shortName;
	@JsonProperty("types")
	private String[] types;
	
	/**
	 * @return the longName element
	 */
	public String getLongName() {
		return longName;
	}
	
	
	
	/**
	 * @param longName sets the longName element
	 */
	public void setLongName(String longName) {
		this.longName = longName;
	}
	
	
	
	/**
	 * @return the shortName element
	 */
	public String getShortName() {
		return shortName;
	}
	
	
	
	/**
	 * @param shortName sets the shortName element
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	
	
	/**
	 * @return the types
	 */
	public String[] getTypes() {
		return types;
	}
	
	
	
	/**
	 * @param types sets the types element
	 */
	public void setTypes(String[] types) {
		this.types = types;
	}
	
	
	
	 
}
