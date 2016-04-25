package com.ht.beans;

public class BillDetails {

	private int id;
	private String billNo;
	private String invoiceNo;
	private int meterReadingId;
	private int investorId;
	private int consumptionId;
	private int consumptionBifurcationId;
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
	private float totalAmountRoundOff;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public int getMeterReadingId() {
		return meterReadingId;
	}
	public void setMeterReadingId(int meterReadingId) {
		this.meterReadingId = meterReadingId;
	}
	public int getInvestorId() {
		return investorId;
	}
	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}
	public int getConsumptionId() {
		return consumptionId;
	}
	public void setConsumptionId(int consumptionId) {
		this.consumptionId = consumptionId;
	}
	
	/**
	 * @return the consumptionBifurcationId
	 */
	public int getConsumptionBifurcationId() {
		return consumptionBifurcationId;
	}
	/**
	 * @param consumptionBifurcationId the consumptionBifurcationId to set
	 */
	
	public void setConsumptionBifurcationId(int consumptionBifurcationId) {
		this.consumptionBifurcationId = consumptionBifurcationId;
	}
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	public String getReadingDate() {
		return readingDate;
	}
	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}
	public String getBillGenerationDate() {
		return billGenerationDate;
	}
	public void setBillGenerationDate(String billGenerationDate) {
		this.billGenerationDate = billGenerationDate;
	}
	public float getTotalKWH() {
		return totalKWH;
	}
	public void setTotalKWH(float totalKWH) {
		this.totalKWH = totalKWH;
	}
	public float getTotalRKVH() {
		return totalRKVH;
	}
	public void setTotalRKVH(float totalRKVH) {
		this.totalRKVH = totalRKVH;
	}
	public float getKwhRate() {
		return kwhRate;
	}
	public void setKwhRate(float kwhRate) {
		this.kwhRate = kwhRate;
	}
	public float getRkvhRate() {
		return rkvhRate;
	}
	public void setRkvhRate(float rkvhRate) {
		this.rkvhRate = rkvhRate;
	}
	public float getActiveAmount() {
		return activeAmount;
	}
	public void setActiveAmount(float activeAmount) {
		this.activeAmount = activeAmount;
	}
	public float getReactiveAmount() {
		return reactiveAmount;
	}
	public void setReactiveAmount(float reactiveAmount) {
		this.reactiveAmount = reactiveAmount;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public float getTotalAmountRoundOff() {
		return totalAmountRoundOff;
	}
	public void setTotalAmountRoundOff(float totalAmountRoundOff) {
		this.totalAmountRoundOff = totalAmountRoundOff;
	}
	/**
	 * @param id
	 * @param billNo
	 * @param invoiceNo
	 * @param meterReadingId
	 * @param investorId
	 * @param consumptionId
	 * @param consumptionBifurcationId
	 * @param meterNo
	 * @param readingDate
	 * @param billGenerationDate
	 * @param totalKWH
	 * @param totalRKVH
	 * @param kwhRate
	 * @param rkvhRate
	 * @param activeAmount
	 * @param reactiveAmount
	 * @param totalAmount
	 * @param totalAmountRoundOff
	 */
	public BillDetails(int id, String billNo, String invoiceNo, int meterReadingId, int investorId, int consumptionId,
			int consumptionBifurcationId, String meterNo, String readingDate, String billGenerationDate, float totalKWH,
			float totalRKVH, float kwhRate, float rkvhRate, float activeAmount, float reactiveAmount, float totalAmount,
			float totalAmountRoundOff) {
		this.id = id;
		this.billNo = billNo;
		this.invoiceNo = invoiceNo;
		this.meterReadingId = meterReadingId;
		this.investorId = investorId;
		this.consumptionId = consumptionId;
		this.consumptionBifurcationId = consumptionBifurcationId;
		this.meterNo = meterNo;
		this.readingDate = readingDate;
		this.billGenerationDate = billGenerationDate;
		this.totalKWH = totalKWH;
		this.totalRKVH = totalRKVH;
		this.kwhRate = kwhRate;
		this.rkvhRate = rkvhRate;
		this.activeAmount = activeAmount;
		this.reactiveAmount = reactiveAmount;
		this.totalAmount = totalAmount;
		this.totalAmountRoundOff = totalAmountRoundOff;
	}
	/**
	 * @param billNo
	 * @param invoiceNo
	 * @param meterReadingId
	 * @param investorId
	 * @param consumptionId
	 * @param consumptionBifurcationId
	 * @param meterNo
	 * @param readingDate
	 * @param billGenerationDate
	 * @param totalKWH
	 * @param totalRKVH
	 * @param kwhRate
	 * @param rkvhRate
	 * @param activeAmount
	 * @param reactiveAmount
	 * @param totalAmount
	 * @param totalAmountRoundOff
	 */
	public BillDetails(String billNo, String invoiceNo, int meterReadingId, int investorId, int consumptionId,
			int consumptionBifurcationId, String meterNo, String readingDate, String billGenerationDate, float totalKWH,
			float totalRKVH, float kwhRate, float rkvhRate, float activeAmount, float reactiveAmount, float totalAmount,
			float totalAmountRoundOff) {
		this.billNo = billNo;
		this.invoiceNo = invoiceNo;
		this.meterReadingId = meterReadingId;
		this.investorId = investorId;
		this.consumptionId = consumptionId;
		this.consumptionBifurcationId = consumptionBifurcationId;
		this.meterNo = meterNo;
		this.readingDate = readingDate;
		this.billGenerationDate = billGenerationDate;
		this.totalKWH = totalKWH;
		this.totalRKVH = totalRKVH;
		this.kwhRate = kwhRate;
		this.rkvhRate = rkvhRate;
		this.activeAmount = activeAmount;
		this.reactiveAmount = reactiveAmount;
		this.totalAmount = totalAmount;
		this.totalAmountRoundOff = totalAmountRoundOff;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BillDetails [id=" + id + ", billNo=" + billNo + ", invoiceNo=" + invoiceNo + ", meterReadingId="
				+ meterReadingId + ", investorId=" + investorId + ", consumptionId=" + consumptionId
				+ ", consumptionBifurcationId=" + consumptionBifurcationId + ", meterNo=" + meterNo + ", readingDate="
				+ readingDate + ", billGenerationDate=" + billGenerationDate + ", totalKWH=" + totalKWH + ", totalRKVH="
				+ totalRKVH + ", kwhRate=" + kwhRate + ", rkvhRate=" + rkvhRate + ", activeAmount=" + activeAmount
				+ ", reactiveAmount=" + reactiveAmount + ", totalAmount=" + totalAmount + ", totalAmountRoundOff="
				+ totalAmountRoundOff + "]";
	}

	public BillDetails(){
		
	}
}
