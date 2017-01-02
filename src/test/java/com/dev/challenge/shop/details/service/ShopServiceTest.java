/**
 * 
 */
package com.dev.challenge.shop.details.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dev.challenge.dao.ShopDAO;
import com.dev.challenge.geo.response.AddressComponent;
import com.dev.challenge.geo.response.GeoCodeResponse;
import com.dev.challenge.geo.response.Geometry;
import com.dev.challenge.geo.response.Coordinates;
import com.dev.challenge.geo.response.Results;
import com.dev.challenge.geo.service.ShopGeoService;
import com.dev.challenge.request.ShopAddress;
import com.dev.challenge.request.dto.ShopDTO;
import com.dev.challenge.request.Shop;
import com.dev.challenge.shop.service.ShopService;

/**
 * Contains 2 positive and a negative test
 * @author tapan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest {

	@InjectMocks
	private ShopService shopService = new ShopService();

	@Mock
	private ShopGeoService shopGeoService;

	@Mock
	private ShopDAO shopDAO;

	private Shop shop;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shop = new Shop();

		final ShopAddress address = new ShopAddress();
		address.setAddress("Pune");
		address.setPostCode(411014);

		shop.setShopName("ABC");
		shop.setShopAddress(address);
	}
	

	@Test
	public void test_saveShop_sccuess() throws Exception {
		Map<String, String> coordinates = new HashMap<>();
		coordinates.put("lat", "14.123156");
		coordinates.put("lng", "29.2548897");
		when(shopGeoService.getCoordinatesByAddress(anyString())).thenReturn(coordinates);
		when(shopDAO.saveShop(any(ShopDTO.class))).thenReturn(1);
		int actual = shopService.saveShop(shop);
		assertEquals(1, actual);
	}

	@Test(expected = Exception.class)
	public void test_saveShop_failure() throws Exception {
		Map<String, String> coordinates = new HashMap<>();
		coordinates.put("lat", "14.123156");
		coordinates.put("lng", "29.2548897");
		when(shopGeoService.getCoordinatesByAddress(anyString())).thenReturn(coordinates);
		when(shopDAO.saveShop(any(ShopDTO.class))).thenThrow(new Exception());
		shopService.saveShop(shop);
		
		
	}
	
	@Test
	public void testFindShopNearByCoordinates_Success() throws Exception {
		when(shopGeoService.findNearestShopDetails(anyString())).thenReturn(testGeoCodingResponse());
		List<ShopDTO> actual = shopService.findShopByCoordinates("11.25", "12.56");
		assertNotNull(actual);
	}

	private GeoCodeResponse testGeoCodingResponse() {
		GeoCodeResponse geoCodeResponse = new GeoCodeResponse();
		AddressComponent addressComponent = new AddressComponent();
		Results result = new Results();
		Geometry geometry = new Geometry();
		Coordinates coordinates = new Coordinates();
		coordinates.setLat(11.1212);
		coordinates.setLng(12.12);
		geometry.setLocation(coordinates);
		String [] types= {"type1","type2"};
		addressComponent.setLongName("LongName");
		addressComponent.setShortName("shortName");
		addressComponent.setTypes(types);
		AddressComponent addressComponents[]= {addressComponent};
		result.setAddressComponents(addressComponents);
		result.setFormattedAddress("aaddress");
		result.setGeometry(geometry);
		result.setTypes(types);
		result.setPartialMatch(true);
		Results[] resultsArr = {result};
		geoCodeResponse.setResults(resultsArr);
		geoCodeResponse.setStatus("testStatus");
		return geoCodeResponse;
	}
	
	

}
