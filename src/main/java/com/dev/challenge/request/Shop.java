package com.dev.challenge.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author tapan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class Shop implements Serializable {

	private static final long serialVersionUID = -3325046099625445967L;;

	@JsonProperty(value = "shopName", required = true)
    @ApiModelProperty(notes = "Shop Name", required = true)
	private String shopName;

	@JsonProperty(value = "shopAddress", required = true)
	@ApiModelProperty(notes = "Shop Address - number, post code etc ", required = true)
	private ShopAddress shopAddress;

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName
	 *            the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return the shopAddress
	 */
	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	/**
	 * @param shopAddress
	 *            the shopAddress to set
	 */
	public void setShopAddress(ShopAddress address) {
		this.shopAddress = address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopDetails [shopName=").append(shopName).append(", shopAddress=").append(shopAddress)
				.append("]");
		return builder.toString();
	}

}
