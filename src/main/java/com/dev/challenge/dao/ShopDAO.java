package com.dev.challenge.dao;

import com.dev.challenge.request.dto.ShopDTO;

/**
 * @author tapan
 *
 */
public interface ShopDAO {

	/**
	 * This method saves shop data to the in-memory database (H2)
	 * @param shopDTO
	 * @return
	 * @throws Exception 
	 */
	int saveShop(final ShopDTO shopDTO) throws Exception;
}
