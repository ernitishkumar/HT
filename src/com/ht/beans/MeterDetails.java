package com.ht.beans;

public class MeterDetails {

	private String meterNo;
	private String make;
	private String category;
	private String type;
	private String meterClass;
	private String ctr;
	private String ptr;
	private int mf;
	private String equipmemntClass;
	private String phase;
	private String meterGroup;
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMeterClass() {
		return meterClass;
	}
	public void setMeterClass(String meterClass) {
		this.meterClass = meterClass;
	}
	public String getCtr() {
		return ctr;
	}
	public void setCtr(String ctr) {
		this.ctr = ctr;
	}
	public String getPtr() {
		return ptr;
	}
	public void setPtr(String ptr) {
		this.ptr = ptr;
	}
	public int getMf() {
		return mf;
	}
	public void setMf(int mf) {
		this.mf = mf;
	}
	public String getEquipmemntClass() {
		return equipmemntClass;
	}
	public void setEquipmemntClass(String equipmemntClass) {
		this.equipmemntClass = equipmemntClass;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getMeterGroup() {
		return meterGroup;
	}
	public void setMeterGroup(String meterGroup) {
		this.meterGroup = meterGroup;
	}
	public MeterDetails(String meterNo, String category,
			String type, String meterClass, String ctr, String ptr, int mf,
			String equipmemntClass, String phase, String meterGroup, String make) {
		this.meterNo = meterNo;
		this.make = make;
		this.category = category;
		this.type = type;
		this.meterClass = meterClass;
		this.ctr = ctr;
		this.ptr = ptr;
		this.mf = mf;
		this.equipmemntClass = equipmemntClass;
		this.phase = phase;
		this.meterGroup = meterGroup;
	}
	public MeterDetails(String meterNo) {
		this.meterNo = meterNo;
	}
	public MeterDetails() {
		
	}
	@Override
	public String toString() {
		return "MeterDetails [meterNo=" + meterNo + ", make=" + make
				+ ", category=" + category + ", type=" + type + ", meterClass="
				+ meterClass + ", ctr=" + ctr + ", ptr=" + ptr + ", mf=" + mf
				+ ", equipmemntClass=" + equipmemntClass + ", phase=" + phase
				+ ", meterGroup=" + meterGroup + "]";
	}
	
	
}
