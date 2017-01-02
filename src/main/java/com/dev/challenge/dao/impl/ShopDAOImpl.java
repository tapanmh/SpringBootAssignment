package com.dev.challenge.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dev.challenge.dao.ShopDAO;
import com.dev.challenge.request.dto.ShopDTO;

/**
 * @author tapan
 *
 */
@Repository("shopDAO")
public class ShopDAOImpl implements ShopDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDAOImpl.class);
	public static final String SAVE_SHOP_DML = "INSERT INTO SHOP_DETAILS VALUES(?, ?, ?, ?, ?)";

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveShop(final ShopDTO shopDTO) throws Exception {
		LOGGER.debug("START method saveShop", shopDTO.toString());
		Object[] params = { shopDTO.getShopName(), shopDTO.getShopAddress(), shopDTO.getPostCode(),
				shopDTO.getLatitude(), shopDTO.getLongitude() };
		try{
			int j  = jdbcTemplate.update(SAVE_SHOP_DML, params);
			return j;
		} catch (RuntimeException e){
			throw new RuntimeException("Cannot add shop details to in-memory database, please retry again", e);
		}
	}

}
