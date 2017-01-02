package com.dev.challenge.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author tapan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {
	
	@JsonProperty("address_components")
	private AddressComponent[] addressComponents;

	@JsonProperty("formatted_address")
	private String formattedAddress;

	@JsonIgnore
	private String[] postcodeLocalities;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("types")
	private String[] types;

	private boolean partialMatch;

	@JsonProperty("place_id")
	private String placeId;

	
	/**
	 * @return the addressComponents element
	 */
	public AddressComponent[] getAddressComponents() {
		return addressComponents;
	}

	
	/**
	 * @param addressComponents sets the addressComponents element
	 */
	public void setAddressComponents(AddressComponent[] addressComponents) {
		this.addressComponents = addressComponents;
	}

	
	/**
	 * @return the formattedAddress element
	 */
	public String getFormattedAddress() {
		return formattedAddress;
	}

	
	/**
	 * @param formattedAddress sets the formattedAddress element 
	 */
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	
	/**
	 * @return the postcodeLocalities element
	 */
	public String[] getPostcodeLocalities() {
		return postcodeLocalities;
	}

	
	/**
	 * @param postcodeLocalities sets the postcodeLocalities element 
	 */
	public void setPostcodeLocalities(String[] postcodeLocalities) {
		this.postcodeLocalities = postcodeLocalities;
	}

	/**
	 * @return the geometry element
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	
	/**
	 * @param geometry sets the geometry element 
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	
	/**
	 * @return the types element
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

	
	/**
	 * @return the partialMatch element
	 */
	public boolean isPartialMatch() {
		return partialMatch;
	}

	
	/**
	 * @param partialMatch sets the partialMatch element 
	 */
	public void setPartialMatch(boolean partialMatch) {
		this.partialMatch = partialMatch;
	}

	
	/**
	 * @return the placeId element
	 */
	public String getPlaceId() {
		return placeId;
	}

	
	/**
	 * @param placeId sets the placeId element 
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
}
