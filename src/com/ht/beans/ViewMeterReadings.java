package com.ht.beans;

public class ViewMeterReadings {
	
	private String meterNo;
    
    private Plant plant;
    
	private MeterReadings currentMeterReading;
	
	private MeterReadings previousMeterReading;
	
	private Consumption consumption;

	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	public MeterReadings getCurrentMeterReading() {
		return currentMeterReading;
	}

	public void setCurrentMeterReading(MeterReadings currentMeterReading) {
		this.currentMeterReading = currentMeterReading;
	}

	public MeterReadings getPreviousMeterReading() {
		return previousMeterReading;
	}

	public void setPreviousMeterReading(MeterReadings previousMeterReading) {
		this.previousMeterReading = previousMeterReading;
	}
	
     public void setPlant(Plant plant){
        this.plant=plant;
    }
    
    public Plant getPlant(){
        return this.plant;
    }
	
	public Consumption getConsumption() {
		return consumption;
	}

	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
	}
	
	}
