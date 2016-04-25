package com.ht.beans;

public class Readings {

	private int id;
	private String meterNo;
	private int mf;
	private String redingDateTime;
	private String cmvhImport;
	private String cmvhExport;
	private String mvarhLaggingImport;
	private String mvarhLaggingExport;
	private String mvarhLeadingImport;
	private String mvarhLeadingExport;
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
	public String getRedingDateTime() {
		return redingDateTime;
	}
	public void setRedingDateTime(String redingDateTime) {
		this.redingDateTime = redingDateTime;
	}
	public String getCmvhImport() {
		return cmvhImport;
	}
	public void setCmvhImport(String cmvhImport) {
		this.cmvhImport = cmvhImport;
	}
	public String getCmvhExport() {
		return cmvhExport;
	}
	public void setCmvhExport(String cmvhExport) {
		this.cmvhExport = cmvhExport;
	}
	public String getMvarhLaggingImport() {
		return mvarhLaggingImport;
	}
	public void setMvarhLaggingImport(String mvarhLaggingImport) {
		this.mvarhLaggingImport = mvarhLaggingImport;
	}
	public String getMvarhLaggingExport() {
		return mvarhLaggingExport;
	}
	public void setMvarhLaggingExport(String mvarhLaggingExport) {
		this.mvarhLaggingExport = mvarhLaggingExport;
	}
	public String getMvarhLeadingImport() {
		return mvarhLeadingImport;
	}
	public void setMvarhLeadingImport(String mvarhLeadingImport) {
		this.mvarhLeadingImport = mvarhLeadingImport;
	}
	public String getMvarhLeadingExport() {
		return mvarhLeadingExport;
	}
	public void setMvarhLeadingExport(String mvarhLeadingExport) {
		this.mvarhLeadingExport = mvarhLeadingExport;
	}
	public Readings(int id, String meterNo, int mf, String redingDateTime,
			String cmvhImport, String cmvhExport, String mvarhLaggingImport,
			String mvarhLaggingExport, String mvarhLeadingImport,
			String mvarhLeadingExport) {
		this.id = id;
		this.meterNo = meterNo;
		this.mf = mf;
		this.redingDateTime = redingDateTime;
		this.cmvhImport = cmvhImport;
		this.cmvhExport = cmvhExport;
		this.mvarhLaggingImport = mvarhLaggingImport;
		this.mvarhLaggingExport = mvarhLaggingExport;
		this.mvarhLeadingImport = mvarhLeadingImport;
		this.mvarhLeadingExport = mvarhLeadingExport;
	}
	public Readings(String meterNo, int mf, String redingDateTime,
			String cmvhImport, String cmvhExport, String mvarhLaggingImport,
			String mvarhLaggingExport, String mvarhLeadingImport,
			String mvarhLeadingExport) {
		this.meterNo = meterNo;
		this.mf = mf;
		this.redingDateTime = redingDateTime;
		this.cmvhImport = cmvhImport;
		this.cmvhExport = cmvhExport;
		this.mvarhLaggingImport = mvarhLaggingImport;
		this.mvarhLaggingExport = mvarhLaggingExport;
		this.mvarhLeadingImport = mvarhLeadingImport;
		this.mvarhLeadingExport = mvarhLeadingExport;
	}
	public Readings() {
	}
	@Override
	public String toString() {
		return "Readings [id=" + id + ", meterNo=" + meterNo + ", mf=" + mf
				+ ", redingDateTime=" + redingDateTime + ", cmvhImport="
				+ cmvhImport + ", cmvhExport=" + cmvhExport
				+ ", mvarhLaggingImport=" + mvarhLaggingImport
				+ ", mvarhLaggingExport=" + mvarhLaggingExport
				+ ", mvarhLeadingImport=" + mvarhLeadingImport
				+ ", mvarhLeadingExport=" + mvarhLeadingExport + "]";
	}

	
}
