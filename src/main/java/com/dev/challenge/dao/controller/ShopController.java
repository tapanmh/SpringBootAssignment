package com.dev.challenge.dao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.challenge.request.Shop;
import com.dev.challenge.request.dto.ShopDTO;
import com.dev.challenge.shop.service.ShopService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author tapan
 *
 */
@RestController
@RequestMapping("/shopapi")
public class ShopController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	@Qualifier("shopService")
	private ShopService shopService;

	@ApiOperation(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", value = "", response = String.class, 
			notes = "This method/operation saves shop information and also internally retrieve latitude and longitude for the provided Shop Address  using  Google Maps Geocoding API")
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveShop(@RequestBody Shop shop) {
		LOGGER.debug("START method saveShop", shop.toString());
		try {
			int saveCount = shopService.saveShop(shop);
			if (saveCount == 0) {
				return new ResponseEntity<String>(
						"cannot find latitude and logitude of provided shop",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOGGER.error("Exception Details : ", e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			LOGGER.debug("END method saveShop");
		}
		return new ResponseEntity<String>("Shop address and coordinates saved successfully to in memory database!", HttpStatus.OK);
	}

	@ApiOperation(consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", value = "", response = ShopDTO.class, notes = "Find List of shop matching to provided latitude and longitude", responseContainer = "List")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "latitude", value = "Laitude of the shop", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "longitude", value = "Longitude of the shop", required = true, dataType = "string", paramType = "query") })
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getNearByShops(
			@RequestParam(required = true, name = "latitude") String latitude,
			@RequestParam(required = true, name = "longitude") String longitude) {
		LOGGER.debug("START method getNearByShops", new Object[] { latitude, longitude });
		List<ShopDTO> output = null;
		try {
			output = shopService.findShopByCoordinates(latitude, longitude);
		} catch (Exception e) {
			LOGGER.error("Exception ", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			LOGGER.debug("END method saveShopDetails");
		}
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
}
