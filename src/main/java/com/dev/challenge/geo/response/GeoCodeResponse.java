package com.dev.challenge.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author tapan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodeResponse {

	@JsonProperty("results")
	private Results[] results;
	
	@JsonProperty("status")
	private String status;

	/**
	 * @return the results element
	 */
	public Results[] getResults() {
		return results;
	}

	/**
	 * @param results sets the results element
	 */
	public void setResults(Results[] results) {
		this.results = results;
	}

	
	/**
	 * @return the status element
	 */
	public String getStatus() {
		return status;
	}

	
	/**
	 * @param status sets the status element
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	

}
