package com.dev.challenge.shop.service;

import static com.dev.challenge.dao.constants.Constants.LATITUDE;
import static com.dev.challenge.dao.constants.Constants.LONGITUDE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev.challenge.dao.ShopDAO;
import com.dev.challenge.geo.response.AddressComponent;
import com.dev.challenge.geo.response.GeoCodeResponse;
import com.dev.challenge.geo.response.Results;
import com.dev.challenge.geo.service.ShopGeoService;
import com.dev.challenge.request.ShopAddress;
import com.dev.challenge.request.dto.ShopDTO;
import com.dev.challenge.request.Shop;

/**
 * @author tapan
 *
 */
@Service("shopService")
public class ShopService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopService.class);

	@Autowired
	@Qualifier("shopDAO")
	private ShopDAO shopDAO;

	@Autowired
	@Qualifier("shopGeoService")
	private ShopGeoService shopGeoService;

	private static final List<ShopDTO> shopsList = new CopyOnWriteArrayList<>();

	/**
	 * Service to find latitude and longitude of location and save shop details
	 * 
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public int saveShop(final Shop shop) throws Exception {
		LOGGER.debug("START method saveShop", shop.toString());
		final ShopAddress address = shop.getShopAddress();
		final Map<String, String> coordinateMap = shopGeoService.getCoordinatesByAddress(address.getAddress());
		if (!coordinateMap.isEmpty()) {
			final ShopDTO shopDTO = new ShopDTO(shop.getShopName(), address.getAddress(),
					address.getPostCode(), coordinateMap.get(LATITUDE), coordinateMap.get(LONGITUDE));
			shopsList.add(shopDTO);
			return shopDAO.saveShop(shopDTO);
		}
		return 0;
	}

	/**
	 * This method accepts latitude and longitude and find shop
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws Exception
	 */
	public List<ShopDTO> findShopByCoordinates(final String latitude, final String longitude) throws Exception {
		LOGGER.debug("START method findShopByCoordinates", new Object[] { latitude, longitude });
		final String coordinates = latitude + "," + longitude;
		final GeoCodeResponse codingResponse = shopGeoService.findNearestShopDetails(coordinates);
		Results[] results = codingResponse.getResults();
		final List<ShopDTO> output = new ArrayList<>();
		final Map<String, ShopDTO> shopTemporaryMap = new HashMap<>();
		//Normally one result element with multiple address_components are returned by gooble api 
		for (int i = 0; i < results.length; i++) {
			Results result = results[i];
			AddressComponent[] addressComponents = result.getAddressComponents();
			for (int j = 0; j < addressComponents.length; j++) {

				processResultElement(shopTemporaryMap, addressComponents[j]);
			}
		}
		output.addAll(shopTemporaryMap.values());
		return output;
	}

	/**
	 * This method process the result address component
	 * @param shopTemporaryMap
	 * @param addressComponent
	 */
	private void processResultElement(final Map<String, ShopDTO> shopTemporaryMap, AddressComponent addressComponent) {
		for (String types : addressComponent.getTypes()) {
			if (types.equalsIgnoreCase("postal_code")) {
				for (ShopDTO shopDTO : shopsList) {
					String pCode = Integer.toString(shopDTO.getPostCode());
					if (addressComponent.getLongName().equalsIgnoreCase(pCode) || addressComponent.getShortName().equalsIgnoreCase(pCode)) {
						shopTemporaryMap.put(shopDTO.getShopName(), shopDTO);
					}
				}
			}
		}
	}
}
