package com.dev.challenge.request.dto;

import java.io.Serializable;

/**
 * @author tapan
 *
 */
public class ShopDTO implements Serializable {
	
	private static final long serialVersionUID = -2718331400017438593L;;

	private String shopName;
	private String shopAddress;
	private Integer postCode;
	private String longitude;
	private String latitude;
/**
 * constuctor 
 * 
 * @param shopName
 * @param shopAddress
 * @param postCode
 * @param latitude
 * @param longitude
 */
	public ShopDTO(String shopName, String shopAddress, Integer postCode, String latitude, String longitude) {
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.postCode = postCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @return the shopAddress
	 */
	public String getShopAddress() {
		return shopAddress;
	}

	/**
	 * @return the postCode
	 */
	public Integer getPostCode() {
		return postCode;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("shopName=").append(shopName).append(", shopAddress=").append(shopAddress).append(", postCode=")
				.append(postCode).append(", longitude=").append(longitude).append(", latitude=").append(latitude);
		return builder.toString();
	}
	
}
