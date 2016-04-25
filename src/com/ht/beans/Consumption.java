package com.ht.beans;

public class Consumption {

	private int id;
	private String meterNo;
	private String date;
	private float activeConsumption;
	private float reactiveConsumption;
	private int plantId;
	private String plantCode;
	private int meterReadingId;
	private int consumptionBifercated;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getPlantCode() {
		return plantCode;
	}
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	public int getMeterReadingId() {
		return meterReadingId;
	}
	public void setMeterReadingId(int meterReadingId) {
		this.meterReadingId = meterReadingId;
	}
	public int getConsumptionBifercated() {
		return consumptionBifercated;
	}
	public void setConsumptionBifercated(int consumptionBifercated) {
		this.consumptionBifercated = consumptionBifercated;
	}
	
}
