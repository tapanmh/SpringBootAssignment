package com.dev.challenge.dao.shop.details.controller;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dev.challenge.dao.controller.ShopController;
import com.dev.challenge.request.Shop;
import com.dev.challenge.request.dto.ShopDTO;
import com.dev.challenge.shop.service.ShopService;
/**
 * Contains 2 positive and 2 negative tests
 * @author tapan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ShopDetailsControllerTest {

	@InjectMocks
	private ShopController shopController = new ShopController();

	@Mock
	private ShopService shopService;

	private MockMvc mockMvc;


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();
	}

	@Test
	public void testSaveShop() throws Exception {
		when(shopService.saveShop((Shop)anyObject())).thenReturn(1);
		mockMvc.perform(post("/shopapi").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"shopName\" : \"Parijat Mithai\",\"shopAddress\" : {\"address\" : \"Sai Apex\",\"postCode\" : 411014}}")).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetNearByShops() throws Exception {
		when(shopService.findShopByCoordinates(anyString(), anyString())).thenReturn(new ArrayList<ShopDTO>());
		mockMvc.perform(get("/shopapi").param("latitude", "11").param("longitude", "11")).andExpect(status().isOk());
	}

	@Test
	public void testSaveShopBadRequest() throws Exception {
		when(shopService.saveShop((Shop)anyObject())).thenReturn(0);
		mockMvc.perform(post("/shopapi").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"shopName\" : \"Parijat Mithai\",\"shopAddress\" : {\"address\" : \"Sai Apex\",\"postCode\" : 411014}}")).andExpect(status().isBadRequest());
	}

	
	@Test
	public void testGetNearByShopsException() throws Exception {
		when(shopService.findShopByCoordinates(anyString(), anyString())).thenThrow(new RuntimeException());
		mockMvc.perform(get("/shopapi").param("latitude", "10").param("longitude", "10")).andExpect(status().isInternalServerError());
	}

}
