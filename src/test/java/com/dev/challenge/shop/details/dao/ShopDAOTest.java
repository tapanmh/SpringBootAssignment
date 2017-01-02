package com.dev.challenge.shop.details.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dev.challenge.dao.ShopDAO;
import com.dev.challenge.dao.impl.ShopDAOImpl;
import com.dev.challenge.request.dto.ShopDTO;
/**
 * Contains a positive and a negative test
 * @author tapan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ShopDAOTest {

	@InjectMocks
	private ShopDAO shopDAO = new ShopDAOImpl();

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	private ShopDTO shopDTO; 
	
	@Before
	public void setUp(){
		shopDTO = new ShopDTO("SomeShop", "Pune", 44444, "14.123156", "-29.25488979");
														      
	}

	@Test
	public void test_saveShop_sccuess() throws Exception {
		when(jdbcTemplate.update(anyString(), (Object[]) anyObject())).thenReturn(0);
		assertEquals(0, shopDAO.saveShop(shopDTO));
	}
	
	@Test(expected = Exception.class)
	public void test_saveShop_Failed() throws Exception {
		when(jdbcTemplate.update(anyString(), (Object[]) anyObject())).thenThrow(new Exception());
		shopDAO.saveShop(shopDTO);
	}

}
