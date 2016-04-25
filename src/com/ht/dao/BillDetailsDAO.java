package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.BillDetails;
import com.ht.utility.GlobalResources;

public class BillDetailsDAO {

	public boolean insert(BillDetails billDetails){
		boolean added=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into bill_details (bill_no, invoice_no, meter_reading_id, investor_id, consumption_id, meter_no, reading_date, bill_generation_date, total_kwh, total_rkvh, kwh_rate, rkvh_rate, active amount, reactive_amount, total_amount, total_amount_roundoff) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,billDetails.getBillNo());
			ps.setString(2, billDetails.getInvoiceNo());
			ps.setInt(3, billDetails.getMeterReadingId());
			ps.setInt(4, billDetails.getInvestorId());
			ps.setInt(5, billDetails.getConsumptionId());
			ps.setString(6, billDetails.getMeterNo());
			ps.setString(7, billDetails.getReadingDate());
			ps.setString(8, billDetails.getBillGenerationDate());
			ps.setFloat(9, billDetails.getTotalKWH());
			ps.setFloat(10, billDetails.getTotalRKVH());
			ps.setFloat(11, billDetails.getKwhRate());
			ps.setFloat(12, billDetails.getRkvhRate());
			ps.setFloat(13, billDetails.getActiveAmount());
			ps.setFloat(14, billDetails.getReactiveAmount());
			ps.setFloat(15, billDetails.getTotalAmount());
			ps.setFloat(16, billDetails.getTotalAmountRoundOff());
			
			ps.executeUpdate();
			ps.close();
			added=true;
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in class : BillDetailsDAO : method : [insert(BillDetails)] "+e.getMessage());
		}
		return added;
	}
	
	public boolean update(BillDetails billDetails){
		boolean updated=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("update bill_details set bill_no=?, invoice_no=?, meter_reading_id=?, investor_id=?, consumption_id=?, meter_no=?, reading_date=?, bill_generation_date=?, total_kwh=?, total_rkvh=?, kwh_rate=?, rkvh_rate=?, active amount=?, reactive_amount=?, total_amount=?, total_amount_roundoff=? where id = ?");
			ps.setString(1,billDetails.getBillNo());
			ps.setString(2, billDetails.getInvoiceNo());
			ps.setInt(3, billDetails.getMeterReadingId());
			ps.setInt(4, billDetails.getInvestorId());
			ps.setInt(5, billDetails.getConsumptionId());
			ps.setString(6, billDetails.getMeterNo());
			ps.setString(7, billDetails.getReadingDate());
			ps.setString(8, billDetails.getBillGenerationDate());
			ps.setFloat(9, billDetails.getTotalKWH());
			ps.setFloat(10, billDetails.getTotalRKVH());
			ps.setFloat(11, billDetails.getKwhRate());
			ps.setFloat(12, billDetails.getRkvhRate());
			ps.setFloat(13, billDetails.getActiveAmount());
			ps.setFloat(14, billDetails.getReactiveAmount());
			ps.setFloat(15, billDetails.getTotalAmount());
			ps.setFloat(16, billDetails.getTotalAmountRoundOff());
			ps.setInt(17, billDetails.getId());
			ps.executeUpdate();
			ps.close();
			updated=true;
		} catch (SQLException e) {
			updated=false;
			System.out.println("Exception in class : BillDetailsDAO : method : [update(BillDetails)] "+e.getMessage());
		}
		return updated;
	}
	
	public BillDetails getById(int id){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where id = ?");
			ps .setInt(1, id);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getById(int)] "+e.getMessage());
		}
		return billDetails.get(0);
	}

	public ArrayList<BillDetails> getAll(){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from bill_details");
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getAll()] "+e.getMessage());
		}
		return billDetails;
	}
	
	public BillDetails getByBillNo(String billNo){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where bill_no=?");
			ps.setString(1, billNo);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByBillNo(String)] "+e.getMessage());
		}
		return billDetails.get(0);
	}
	
	public ArrayList<BillDetails> getByInvestorId(int investorId){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where investor_id=?");
			ps.setInt(1, investorId);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByInvestorId(int)] "+e.getMessage());
		}
		return billDetails;
	}
	
	public ArrayList<BillDetails> getByConsumptionId(int ConsumptionId){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where consumption_id=?");
			ps.setInt(1, ConsumptionId);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByConsumptionId(int)] "+e.getMessage());
		}
		return billDetails;
	}
	
	private ArrayList<BillDetails> billDetailsMapper(ResultSet rs) {
		ArrayList<BillDetails> billDetailsList = new ArrayList<BillDetails>();
		try {
			while(rs.next()){
				
				BillDetails billDetails = new BillDetails();
				billDetails.setId(rs.getInt(1));
				billDetails.setBillNo(rs.getString(2));
				billDetails.setInvoiceNo(rs.getString(3));
				billDetails.setMeterReadingId(rs.getInt(4));
				billDetails.setInvestorId(rs.getInt(5));
				billDetails.setConsumptionId(rs.getInt(6));
				billDetails.setMeterNo(rs.getString(7));
				billDetails.setReadingDate(rs.getString(8));
				billDetails.setBillGenerationDate(rs.getString(9));
				billDetails.setTotalKWH(rs.getFloat(10));
				billDetails.setTotalRKVH(rs.getFloat(11));
				billDetails.setKwhRate(rs.getFloat(12));
				billDetails.setRkvhRate(rs.getFloat(13));
				billDetails.setActiveAmount(rs.getFloat(14));
				billDetails.setReactiveAmount(rs.getFloat(15));
				billDetails.setTotalAmount(rs.getFloat(16));
				billDetails.setTotalAmountRoundOff(rs.getFloat(17));
				billDetailsList.add(billDetails);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [billDetailsMapper(ResultSet)] "+e.getMessage());
		}
		return billDetailsList;
	}
}
