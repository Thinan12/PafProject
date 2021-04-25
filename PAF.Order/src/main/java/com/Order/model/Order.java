package com.Order.model;

public class Order {
	
	private String orderId;
	private String userId;
	private String fundingBodiesId;
	private String researchProjectId;
	private String researchProjectFundId;
	private String orderTotalAmount;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFundingBodiesId() {
		return fundingBodiesId;
	}
	public void setFundingBodiesId(String fundingBodiesId) {
		this.fundingBodiesId = fundingBodiesId;
	}
	public String getResearchProjectId() {
		return researchProjectId;
	}
	public void setResearchProjectId(String researchProjectId) {
		this.researchProjectId = researchProjectId;
	}
	public String getResearchProjectFundId() {
		return researchProjectFundId;
	}
	public void setResearchProjectFundId(String researchProjectFundId) {
		this.researchProjectFundId = researchProjectFundId;
	}
	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(String orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	

	
	
}
