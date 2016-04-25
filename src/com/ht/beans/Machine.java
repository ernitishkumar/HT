package com.ht.beans;

public class Machine {

	private int id;
	
	private String code;
	
	private String capacity;
	
	private String commissionedDate;
	
	private float activeRate;
	
	private float reactiveRate;
	
	private String ppaDate;
	
	private String ppaLetterNo;
	
	private int developerId;
	
	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	private int plantId;
	
	private int investorId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getCommissionedDate() {
		return commissionedDate;
	}

	public void setCommissionedDate(String commissionedDate) {
		this.commissionedDate = commissionedDate;
	}

	public float getActiveRate() {
		return activeRate;
	}

	public void setActiveRate(float activeRate) {
		this.activeRate = activeRate;
	}

	public float getReactiveRate() {
		return reactiveRate;
	}

	public void setReactiveRate(float reactiveRate) {
		this.reactiveRate = reactiveRate;
	}

	public String getPpaDate() {
		return ppaDate;
	}

	public void setPpaDate(String ppaDate) {
		this.ppaDate = ppaDate;
	}

	public String getPpaLetterNo() {
		return ppaLetterNo;
	}

	public void setPpaLetterNo(String ppaLetterNo) {
		this.ppaLetterNo = ppaLetterNo;
	}

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}
	
}
