package com.dev.challenge.shop.details.geo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.dev.challenge.geo.response.AddressComponent;
import com.dev.challenge.geo.response.GeoCodeResponse;
import com.dev.challenge.geo.response.Geometry;
import com.dev.challenge.geo.response.Coordinates;
import com.dev.challenge.geo.response.Results;
import com.dev.challenge.geo.service.ShopGeoService;
import com.dev.challenge.geo.service.impl.ShopGeoServiceImpl;
/**
 * contains 2 positive and 2 negative tests
 * @author tapan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ShopGeoServiceTest {

	@InjectMocks
	private ShopGeoService geoService = new ShopGeoServiceImpl();

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(geoService, "coordinateURL", "https://website.com");
		ReflectionTestUtils.setField(geoService, "locationURL", "https://website.com");
	}

	@Test
	public void test_getLngLatByAddress_Success() {
		GeoCodeResponse codingResponse = testGeoCodingResponse();
		when(restTemplate.getForObject(anyString(), eq(GeoCodeResponse.class))).thenReturn(codingResponse);
		Map<String, String> map = geoService.getCoordinatesByAddress("london");
		assertEquals(map.get("lat"), Double.toString(11.111111));
		assertEquals(map.get("lng"), Double.toString(24.242424));
		
		
	}

	@Test(expected = RuntimeException.class)
	public void test_getLngLatByAddress_Failure() {
		when(restTemplate.getForObject(anyString(), eq(GeoCodeResponse.class)))
				.thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		geoService.getCoordinatesByAddress("london");
	}

	@Test
	public void test_getNearestShop_Success() {
		GeoCodeResponse codingResponse = testGeoCodingResponse();
		when(restTemplate.getForObject(anyString(), eq(GeoCodeResponse.class))).thenReturn(codingResponse);
		GeoCodeResponse response = geoService.findNearestShopDetails("11.111111,4.242424");
		assertNotNull(response);
	}

	@Test(expected = RuntimeException.class)
	public void test_getNearestShop_Failure() {
		when(restTemplate.getForObject(anyString(), eq(GeoCodeResponse.class)))
				.thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		geoService.findNearestShopDetails("11.111111,4.242424");
	}

	/**
	 * Prepares a test response that resembles the JSON response returned by Google GEO API
	 * @return
	 */
	private GeoCodeResponse testGeoCodingResponse() {
		GeoCodeResponse geoCodeResponse = new GeoCodeResponse();
		AddressComponent addressComponent = new AddressComponent();
		//Prepare Result Object
		Results result = new Results();
		Geometry geometry = new Geometry();
		
		//Prepare Coordinates object
		Coordinates coordinates = new Coordinates();
		coordinates.setLat(11.111111);
		coordinates.setLng(24.242424);
		geometry.setLocation(coordinates);
		String[] types = { "type1", "type2" };
		addressComponent.setLongName("LongName");
		addressComponent.setShortName("ShortName");
		addressComponent.setTypes(types);
		AddressComponent addressComponents[] = { addressComponent };
		result.setAddressComponents(addressComponents);
		result.setFormattedAddress("address");
		result.setGeometry(geometry);
		result.setTypes(types);
		result.setPartialMatch(true);
		Results[] results = { result };
		geoCodeResponse.setResults(results);
		geoCodeResponse.setStatus("SomeStatus");
		return geoCodeResponse;
	}

}
