package com.dev.challenge.geo.service;

import java.util.Map;

import com.dev.challenge.geo.response.GeoCodeResponse;

/**
 * @author tapan
 *
 */
public interface ShopGeoService {

	/**
	 * This method takes address as input and uses Google Geo api to retrieve the coordinates
	 * @param address
	 * @return the latitude and longitudes
	 * @throws RuntimeException
	 */
	Map<String, String> getCoordinatesByAddress(final String address) throws RuntimeException;
	
	/**
	 * This method takes coordinates (latitude/longitude) as input and finds the shop details
	 * @param coordinates
	 * @return
	 * @throws RuntimeException
	 */
	GeoCodeResponse findNearestShopDetails(final String coordinates) throws RuntimeException;
}
