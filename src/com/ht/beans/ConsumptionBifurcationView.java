package com.ht.beans;

import java.util.ArrayList;

public class ConsumptionBifurcationView {
	
	private String meterNo;
	
	private Plant plant;
	
	private Consumption consumption;
	
	private ArrayList<InvestorConsumptionView> investorBifurcations;

	/**
	 * 
	 */
	public ConsumptionBifurcationView() {
		investorBifurcations = new ArrayList<InvestorConsumptionView>();
	}

	/**
	 * @return the meterNo
	 */
	public String getMeterNo() {
		return meterNo;
	}

	/**
	 * @param meterNo the meterNo to set
	 */
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	/**
	 * @return the plant
	 */
	public Plant getPlant() {
		return plant;
	}

	/**
	 * @param plant the plant to set
	 */
	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	/**
	 * @return the consumption
	 */
	public Consumption getConsumption() {
		return consumption;
	}

	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
	}

	/**
	 * @return the investorBifurcations
	 */
	public ArrayList<InvestorConsumptionView> getInvestorBifurcations() {
		return investorBifurcations;
	}

	/**
	 * @param investorBifurcations the investorBifurcations to set
	 */
	public void setInvestorBifurcations(ArrayList<InvestorConsumptionView> investorBifurcations) {
		this.investorBifurcations = investorBifurcations;
	}

}
