package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.InvestorPlantMapping;
import com.ht.utility.GlobalResources;

public class InvestorPlantMappingDAO {

	public boolean insert(InvestorPlantMapping investorPlantMapping){
		boolean added = false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into investor_plant_mapping (plant_id,plant_code,investor_id,investor_code) values(?,?,?,?)");
			ps.setInt(1, investorPlantMapping.getPlantId());
			ps.setString(2, investorPlantMapping.getPlantCode());
			ps.setInt(3, investorPlantMapping.getInvestorId());
			ps.setString(4, investorPlantMapping.getInvestorCode());
			ps.executeUpdate();
			ps.close();
			added =true;
		} catch (SQLException e) {
			added = false;
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [insert(InvestorPlantMapping)] "+e.getMessage());
		}
		return added;
	}
	
	public boolean update(InvestorPlantMapping investorPlantMapping){
		boolean updated = false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("update investor_plant_mapping set plant_id=?,plant_code=?,investor_id=?,investor_code=? where id=?");
			ps.setInt(1, investorPlantMapping.getPlantId());
			ps.setString(2, investorPlantMapping.getPlantCode());
			ps.setInt(3, investorPlantMapping.getInvestorId());
			ps.setString(4, investorPlantMapping.getInvestorCode());
			ps.setInt(5, investorPlantMapping.getId());
			ps.executeUpdate();
			ps.close();
			updated =true;
		} catch (SQLException e) {
			updated = false;
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [insert(InvestorPlantMapping)] "+e.getMessage());
		}
		return updated;
	}
	
	public ArrayList<InvestorPlantMapping> getAllMappings(){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping");
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getAllMappings()] "+e.getMessage());
		}
		return mappingList;
	}

	public InvestorPlantMapping getById(int id){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getById(int)] "+e.getMessage());
		}
		return mappingList.get(0);
	}
	
	public ArrayList<InvestorPlantMapping> getByPlantId(int plantId){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping where plant_id=?");
			ps.setInt(1,plantId);
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getByPlantId(int)] "+e.getMessage());
		}
		return mappingList;
	}
	
	public ArrayList<InvestorPlantMapping> getByInvestorId(int investorId){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping where investor_id=?");
			ps.setInt(1,investorId);
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getByInvestorId(int)] "+e.getMessage());
		}
		return mappingList;
	}
	
	public ArrayList<InvestorPlantMapping> getByInvestorCode(String investorCode){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping where investor_code=?");
			ps.setString(1,investorCode);
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getByInvestorCode(String)] "+e.getMessage());
		}
		return mappingList;
	}
	
	public ArrayList<InvestorPlantMapping> getByPlantCode(String plantCode){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping where plant_code=?");
			ps.setString(1,plantCode);
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getByPlantCode(String)] "+e.getMessage());
		}
		return mappingList;
	}
	
	public ArrayList<InvestorPlantMapping> getByPlantCodeAndInvestorCode(String plantCode, String investorCode){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping where plant_code=? and investor_code=?");
			ps.setString(1,plantCode);
			ps.setString(2,investorCode);
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getByPlantCodeAndInvestorCode(String,String)] "+e.getMessage());
		}
		return mappingList;
	}
	
	public ArrayList<InvestorPlantMapping> getByPlantIdAndInvestorId(int plantId, int investorId){
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_plant_mapping where plant_code=? and investor_code=?");
			ps.setInt(1,plantId);
			ps.setInt(2,investorId);
			ResultSet rs = ps.executeQuery();
			mappingList = investorPlantMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [getByPlantIdAndInvestorId(int,int)] "+e.getMessage());
		}
		return mappingList;
	}
	
	private ArrayList<InvestorPlantMapping> investorPlantMappingParser(ResultSet rs) {
		ArrayList<InvestorPlantMapping> mappingList = new ArrayList<InvestorPlantMapping>();
		
		try {
			while(rs.next()){
                InvestorPlantMapping investorPlantMapping = new InvestorPlantMapping();
				investorPlantMapping.setId(rs.getInt(1));
				investorPlantMapping.setPlantCode(rs.getString(3));
				investorPlantMapping.setInvestorCode(rs.getString(5));
				investorPlantMapping.setPlantId(rs.getInt(2));
				investorPlantMapping.setInvestorId(rs.getInt(4));
				mappingList.add(investorPlantMapping);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [investorPlantMappingParser(ResultSet)] "+e.getMessage());
		}
		return mappingList;
	}
}
