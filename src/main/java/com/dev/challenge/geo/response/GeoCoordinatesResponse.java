package com.dev.challenge.geo.response;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * @author tapan
 *
 */
public class GeoCoordinatesResponse implements Serializable {

	private static final long serialVersionUID = -4145237230932282167L;;
	private Map<String, Object> geoResponse = new TreeMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> get() {
		return geoResponse;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		geoResponse.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [responseMap=").append(geoResponse).append("]");
		return builder.toString();
	}
}
