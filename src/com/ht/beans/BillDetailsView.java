package com.ht.beans;

public class BillDetailsView {

	private int id;
	
	private String billNo;
	
	private String invoiceNo;
	
	private MeterReadings meterReadings;
	
	private Investor investor;
	
	private Consumption consumption;
	
	private InvestorConsumptionView investorConsumptionView;
	
	private String meterNo;
	
	private String readingDate;
	
	private String billGenerationDate;
	
	private float totalKWH;
	
	private float totalRKVH;
	
	private float kwhRate;
	
	private float rkvhRate;
	
	private float activeAmount;
	
	private float reactiveAmount;
	
	private float totalAmount;
	
	private float totalAmountRoundoff;
	
	private String amountInWords;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the meterReadings
	 */
	public MeterReadings getMeterReadings() {
		return meterReadings;
	}

	/**
	 * @param meterReadings the meterReadings to set
	 */
	public void setMeterReadings(MeterReadings meterReadings) {
		this.meterReadings = meterReadings;
	}

	/**
	 * @return the investor
	 */
	public Investor getInvestor() {
		return investor;
	}

	/**
	 * @param investor the investor to set
	 */
	public void setInvestor(Investor investor) {
		this.investor = investor;
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
	 * @return the investorConsumptionView
	 */
	public InvestorConsumptionView getInvestorConsumptionView() {
		return investorConsumptionView;
	}

	/**
	 * @param investorConsumptionView the investorConsumptionView to set
	 */
	public void setInvestorConsumptionView(InvestorConsumptionView investorConsumptionView) {
		this.investorConsumptionView = investorConsumptionView;
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
	 * @return the readingDate
	 */
	public String getReadingDate() {
		return readingDate;
	}

	/**
	 * @param readingDate the readingDate to set
	 */
	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	/**
	 * @return the billGenerationDate
	 */
	public String getBillGenerationDate() {
		return billGenerationDate;
	}

	/**
	 * @param billGenerationDate the billGenerationDate to set
	 */
	public void setBillGenerationDate(String billGenerationDate) {
		this.billGenerationDate = billGenerationDate;
	}

	/**
	 * @return the totalKWH
	 */
	public float getTotalKWH() {
		return totalKWH;
	}

	/**
	 * @param totalKWH the totalKWH to set
	 */
	public void setTotalKWH(float totalKWH) {
		this.totalKWH = totalKWH;
	}

	/**
	 * @return the totalRKVH
	 */
	public float getTotalRKVH() {
		return totalRKVH;
	}

	/**
	 * @param totalRKVH the totalRKVH to set
	 */
	public void setTotalRKVH(float totalRKVH) {
		this.totalRKVH = totalRKVH;
	}

	/**
	 * @return the kwhRate
	 */
	public float getKwhRate() {
		return kwhRate;
	}

	/**
	 * @param kwhRate the kwhRate to set
	 */
	public void setKwhRate(float kwhRate) {
		this.kwhRate = kwhRate;
	}

	/**
	 * @return the rkvhRate
	 */
	public float getRkvhRate() {
		return rkvhRate;
	}

	/**
	 * @param rkvhRate the rkvhRate to set
	 */
	public void setRkvhRate(float rkvhRate) {
		this.rkvhRate = rkvhRate;
	}

	/**
	 * @return the activeAmount
	 */
	public float getActiveAmount() {
		return activeAmount;
	}

	/**
	 * @param activeAmount the activeAmount to set
	 */
	public void setActiveAmount(float activeAmount) {
		this.activeAmount = activeAmount;
	}

	/**
	 * @return the reactiveAmount
	 */
	public float getReactiveAmount() {
		return reactiveAmount;
	}

	/**
	 * @param reactiveAmount the reactiveAmount to set
	 */
	public void setReactiveAmount(float reactiveAmount) {
		this.reactiveAmount = reactiveAmount;
	}

	/**
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the totalAmountRoundoff
	 */
	public float getTotalAmountRoundoff() {
		return totalAmountRoundoff;
	}

	/**
	 * @param totalAmountRoundoff the totalAmountRoundoff to set
	 */
	public void setTotalAmountRoundoff(float totalAmountRoundoff) {
		this.totalAmountRoundoff = totalAmountRoundoff;
	}

	/**
	 * @return the amountInWords
	 */
	public String getAmountInWords() {
		return amountInWords;
	}

	/**
	 * @param amountInWords the amountInWords to set
	 */
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BillDetailsView [id=" + id + ", billNo=" + billNo + ", invoiceNo=" + invoiceNo + ", meterReadings="
				+ meterReadings + ", investor=" + investor + ", consumption=" + consumption
				+ ", investorConsumptionView=" + investorConsumptionView + ", meterNo=" + meterNo + ", readingDate="
				+ readingDate + ", billGenerationDate=" + billGenerationDate + ", totalKWH=" + totalKWH + ", totalRKVH="
				+ totalRKVH + ", kwhRate=" + kwhRate + ", rkvhRate=" + rkvhRate + ", activeAmount=" + activeAmount
				+ ", reactiveAmount=" + reactiveAmount + ", totalAmount=" + totalAmount + ", totalAmountRoundoff="
				+ totalAmountRoundoff + ", amountInWords=" + amountInWords + "]";
	}
	
}
