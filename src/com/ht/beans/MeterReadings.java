package com.ht.beans;

public class MeterReadings {
		
	private int id;

	private String meterNo;
	
	private int mf;
	
	private String readingDate;
	
	private float activeEnergy;
	
    private float activeTodOne;
    
    private float activeTodTwo;
    
    private float activeTodThree;
    
    private float reactiveQuadrantOne;
    
    private float reactiveQuadrantTwo;
    
    private float reactiveQuadrantThree;
    
	private float reactiveQuadrantFour;
    
	private int htCellValidation;
	
	private int circleCellValidation;
	
	private int developerValidation;
	
	private int discardedFlag;
	
	private String discardedBy;
	
	private String discardedOn;

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

	public int getMf() {
		return mf;
	}

	public void setMf(int mf) {
		this.mf = mf;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	public float getActiveEnergy() {
		return activeEnergy;
	}

	public void setActiveEnergy(float activeEnergy) {
		this.activeEnergy = activeEnergy;
	}

	public float getActiveTodOne() {
		return activeTodOne;
	}

	public void setActiveTodOne(float activeTodOne) {
		this.activeTodOne = activeTodOne;
	}

	public float getActiveTodTwo() {
		return activeTodTwo;
	}

	public void setActiveTodTwo(float activeTodTwo) {
		this.activeTodTwo = activeTodTwo;
	}

	public float getActiveTodThree() {
		return activeTodThree;
	}

	public void setActiveTodThree(float activeTodThree) {
		this.activeTodThree = activeTodThree;
	}

	public float getReactiveQuadrantOne() {
		return reactiveQuadrantOne;
	}

	public void setReactiveQuadrantOne(float reactiveQuadrantOne) {
		this.reactiveQuadrantOne = reactiveQuadrantOne;
	}

	public float getReactiveQuadrantTwo() {
		return reactiveQuadrantTwo;
	}

	public void setReactiveQuadrantTwo(float reactiveQuadrantTwo) {
		this.reactiveQuadrantTwo = reactiveQuadrantTwo;
	}

	public float getReactiveQuadrantThree() {
		return reactiveQuadrantThree;
	}

	public void setReactiveQuadrantThree(float reactiveQuadrantThree) {
		this.reactiveQuadrantThree = reactiveQuadrantThree;
	}

	public float getReactiveQuadrantFour() {
		return reactiveQuadrantFour;
	}

	public void setReactiveQuadrantFour(float reactiveQuadrantFour) {
		this.reactiveQuadrantFour = reactiveQuadrantFour;
	}

	public int getHtCellValidation() {
		return htCellValidation;
	}

	public void setHtCellValidation(int htCellValidation) {
		this.htCellValidation = htCellValidation;
	}

	public int getCircleCellValidation() {
		return circleCellValidation;
	}

	public void setCircleCellValidation(int circleCellValidation) {
		this.circleCellValidation = circleCellValidation;
	}

	public int getDeveloperValidation() {
		return developerValidation;
	}

	public void setDeveloperValidation(int developerValidation) {
		this.developerValidation = developerValidation;
	}
		public int getDiscardedFlag() {
		return discardedFlag;
	}

	public void setDiscardedFlag(int discardedFlag) {
		this.discardedFlag = discardedFlag;
	}

	public String getDiscardedBy() {
		return discardedBy;
	}

	public void setDiscardedBy(String discardedBy) {
		this.discardedBy = discardedBy;
	}

	public String getDiscardedOn() {
		return discardedOn;
	}

	public void setDiscardedOn(String discardedOn) {
		this.discardedOn = discardedOn;
	}
	
}
