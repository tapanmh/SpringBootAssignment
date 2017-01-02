package com.dev.challenge.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * @author tapan
 *
 */
public class ShopAddress implements Serializable {

	private static final long serialVersionUID = -7215559486797401949L;;

	@JsonProperty(value = "address", required = true)
	@ApiModelProperty(notes = "The shop address details", required = true)
	private String address;

	@JsonProperty(value = "postCode", required = true)
	@ApiModelProperty(notes = "Shop post code", required = true)
	private Integer postCode;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the postCode
	 */
	public Integer getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopAddress [address=").append(address).append(", postCode=").append(postCode).append("]");
		return builder.toString();
	}

}