package com.dev.challenge.geo.service.impl;

import static com.dev.challenge.dao.constants.Constants.LATITUDE;
import static com.dev.challenge.dao.constants.Constants.LONGITUDE;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dev.challenge.geo.response.GeoCodeResponse;
import com.dev.challenge.geo.response.Results;
import com.dev.challenge.geo.service.ShopGeoService;

/**
 * @author tapan
 *
 */
@Service("shopGeoService")
public class ShopGeoServiceImpl implements ShopGeoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopGeoServiceImpl.class);

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Value("${googlemap.geo.api}")
	private String coordinateURL;

	@Value("${googlemap.geolocation.api}")
	private String locationURL;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.task.shop.details.geo.service.ShopDetailsGeoService#getCoordinatesByAddress(java.lang.String)
	 */
	@Override
	public Map<String, String> getCoordinatesByAddress(final String address) throws RuntimeException {
		LOGGER.debug("START method getCoordinatesByAddress", address);
		final String url = coordinateURL + address;
		try {
			final GeoCodeResponse geocodingResult = restTemplate.getForObject(url, GeoCodeResponse.class);
			final Map<String, String> coordinateMap = new HashMap<>();
			Results[] results = geocodingResult.getResults();
			for (Results res : results) {
				coordinateMap.put(LATITUDE, Double.toString(res.getGeometry().getLocation().getLat()));
				coordinateMap.put(LONGITUDE, Double.toString(res.getGeometry().getLocation().getLng()));
			}
			return coordinateMap;
		} catch (RestClientException e) {
			LOGGER.error("Could not find the coordinates, please retry again", e.getMessage());
			throw new RuntimeException("Failed to find latitude/longitude of address, please retry again", e);
		}
	}

	@Override
	public GeoCodeResponse findNearestShopDetails(final String coordinates) throws RuntimeException {
		LOGGER.debug("START method could not find the shop", coordinates);
		final String url = locationURL + coordinates;
		try {
			return restTemplate.getForObject(url, GeoCodeResponse.class);
		} catch (RestClientException e) {
			LOGGER.error("could not find the shop , please retry again", e);
			throw new RuntimeException("could not find the shop data, please retry again", e);
		}
	}
}
