package com.ht.beans;

public class InvestorConsumption {

	private int id;
	private int consumptionId;
	private int investorId;
	private float activeConsumption;
	private float reactiveConsumption;
    private int circleValidation;
    private int billGenerated;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConsumptionId() {
		return consumptionId;
	}
	public void setConsumptionId(int consumptionId) {
		this.consumptionId = consumptionId;
    }
    
	public int getInvestorId() {
		return investorId;
	}
	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}
	public float getActiveConsumption() {
		return activeConsumption;
	}
	public void setActiveConsumption(float activeConsumption) {
		this.activeConsumption = activeConsumption;
	}
	public float getReactiveConsumption() {
		return reactiveConsumption;
	}
	public void setReactiveConsumption(float reactiveConsumption) {
		this.reactiveConsumption = reactiveConsumption;
	}
	
	/**
	 * @return the circleValidation
	 */
	public int getCircleValidation() {
		return circleValidation;
	}
	/**
	 * @param circleValidation the circleValidation to set
	 */
	public void setCircleValidation(int circleValidation) {
		this.circleValidation = circleValidation;
	}
	/**
	 * @return the billGenerated
	 */
	public int getBillGenerated() {
		return billGenerated;
	}
	/**
	 * @param billGenerated the billGenerated to set
	 */
	public void setBillGenerated(int billGenerated) {
		this.billGenerated = billGenerated;
	}
	
}
