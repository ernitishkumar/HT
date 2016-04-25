package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ht.beans.BillDetails;
import com.ht.utility.GlobalResources;

public class BillDetailsDAO {

	public int insert(BillDetails billDetails){
		boolean added=false;
		Connection connection = GlobalResources.getConnection();
		int lastInsertedId = -1;
		try {
			/*String query = "insert into bill_details (meter_reading_id, investor_id, consumption_id, meter_no, reading_date, bill_generation_date, total_kwh, total_rkvh, kwh_rate, rkvh_rate, active amount, reactive_amount, total_amount, total_amount_roundoff) values("+billDetails.getMeterReadingId()+","+billDetails.getInvestorId()
			               +","+billDetails.getConsumptionId()+",'"+billDetails.getMeterNo()+"','"+billDetails.getReadingDate()+"','"+billDetails.getBillGenerationDate()+"',"
			               +billDetails.getTotalKWH()+","+billDetails.getTotalRKVH()+","+billDetails.getKwhRate()+","+billDetails.getRkvhRate()+","
			               +billDetails.getActiveAmount()+","+billDetails.getReactiveAmount()+","+billDetails.getTotalAmount()+","+billDetails.getTotalAmountRoundOff()+")";*/
			
			PreparedStatement ps = connection.prepareStatement("insert into bill_details (bill_no, invoice_no, meter_readings_id, investor_id, consumption_id, consumption_bifurcation_id,meter_no, reading_date, bill_generation_date, total_kwh, total_rkvh, kwh_rate, rkvh_rate, active_amount, reactive_amount, total_amount, total_amount_roundoff) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,"");
			ps.setString(2, "");
			ps.setInt(3, billDetails.getMeterReadingId());
			ps.setInt(4, billDetails.getInvestorId());
			ps.setInt(5, billDetails.getConsumptionId());
			ps.setInt(6,billDetails.getConsumptionBifurcationId());
			ps.setString(7, billDetails.getMeterNo());
			ps.setString(8, billDetails.getReadingDate());
			ps.setString(9, billDetails.getBillGenerationDate());
			ps.setFloat(10, billDetails.getTotalKWH());
			ps.setFloat(11, billDetails.getTotalRKVH());
			ps.setFloat(12, billDetails.getKwhRate());
			ps.setFloat(13, billDetails.getRkvhRate());
			ps.setFloat(14, billDetails.getActiveAmount());
			ps.setFloat(15, billDetails.getReactiveAmount());
			ps.setFloat(16, billDetails.getTotalAmount());
			ps.setFloat(17, billDetails.getTotalAmountRoundOff());
			//PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();    
			keys.next();  
			lastInsertedId = keys.getInt(1);
			System.out.println("Last inserted id for bill details is : "+lastInsertedId);
			ps.close();
			added=true;
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in class : BillDetailsDAO : method : [insert(BillDetails)] "+e.getMessage());
			e.printStackTrace();
		}
		return lastInsertedId;
	}
	
	public boolean update(BillDetails billDetails){
		boolean updated=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("update bill_details set bill_no=?, invoice_no=?, meter_readings_id=?, investor_id=?, consumption_id=?, consumption_bifurcation_id=?,meter_no=?, reading_date=?, bill_generation_date=?, total_kwh=?, total_rkvh=?, kwh_rate=?, rkvh_rate=?, active_amount=?, reactive_amount=?, total_amount=?, total_amount_roundoff=? where id = ?");
			ps.setString(1,billDetails.getBillNo());
			ps.setString(2, billDetails.getInvoiceNo());
			ps.setInt(3, billDetails.getMeterReadingId());
			ps.setInt(4, billDetails.getInvestorId());
			ps.setInt(5, billDetails.getConsumptionId());
			ps.setInt(6,billDetails.getConsumptionBifurcationId());
			ps.setString(7, billDetails.getMeterNo());
			ps.setString(8, billDetails.getReadingDate());
			ps.setString(9, billDetails.getBillGenerationDate());
			ps.setFloat(10, billDetails.getTotalKWH());
			ps.setFloat(11, billDetails.getTotalRKVH());
			ps.setFloat(12, billDetails.getKwhRate());
			ps.setFloat(13, billDetails.getRkvhRate());
			ps.setFloat(14, billDetails.getActiveAmount());
			ps.setFloat(15, billDetails.getReactiveAmount());
			ps.setFloat(16, billDetails.getTotalAmount());
			ps.setFloat(17, billDetails.getTotalAmountRoundOff());
			ps.setInt(18, billDetails.getId());
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
				billDetails.setConsumptionBifurcationId(rs.getInt(7));
				billDetails.setMeterNo(rs.getString(8));
				billDetails.setReadingDate(rs.getString(9));
				billDetails.setBillGenerationDate(rs.getString(10));
				billDetails.setTotalKWH(rs.getFloat(11));
				billDetails.setTotalRKVH(rs.getFloat(12));
				billDetails.setKwhRate(rs.getFloat(13));
				billDetails.setRkvhRate(rs.getFloat(14));
				billDetails.setActiveAmount(rs.getFloat(15));
				billDetails.setReactiveAmount(rs.getFloat(16));
				billDetails.setTotalAmount(rs.getFloat(17));
				billDetails.setTotalAmountRoundOff(rs.getFloat(18));
				billDetailsList.add(billDetails);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [billDetailsMapper(ResultSet)] "+e.getMessage());
		}
		return billDetailsList;
	}
}
