package com.ww.rentokil.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Order")
public class OrderVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute(name = "orderNumber")
	private String orderNumber;

	@XmlAttribute(name = "accountName")
	private String accountName;

	@XmlAttribute(name = "contactNumber")
	private String contactNumber;

	@XmlAttribute(name = "orderAmount")
	private String orderAmount;

	@XmlAttribute(name = "orderStatus")
	private String orderStatus;

	@XmlAttribute(name = "billingAddress")
	private String billingAddress;

	@XmlAttribute(name = "shippingAddress")
	private String shippingAddress;

	@XmlAttribute(name = "creatdBy")
	private String creatdBy;

	@XmlAttribute(name = "activatedBy")
	private String activatedBy;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getCreatdBy() {
		return creatdBy;
	}

	public void setCreatdBy(String creatdBy) {
		this.creatdBy = creatdBy;
	}

	public String getActivatedBy() {
		return activatedBy;
	}

	public void setActivatedBy(String activatedBy) {
		this.activatedBy = activatedBy;
	}
}
