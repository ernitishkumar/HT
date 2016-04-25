package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ht.beans.Plant;
import com.ht.utility.GlobalResources;

public class PlantsDAO {

	public void insert(Plant plant){
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into plants(code, address, Contact_no, Contact_person, email, commissioned_date, type, circuit_voltage, injecting_substation, feeder_name, region, circle, division, main_meter_no, check_meter_no, standby_meter_no, developer_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, plant.getCode());
			ps.setString(2, plant.getAddress());
			ps.setString(3, plant.getContactNo());
			ps.setString(4, plant.getContactPerson());
			ps.setString(5, plant.getEmail());
			ps.setString(6, plant.getCommissionedDate());
			ps.setString(7, plant.getType());
			ps.setString(8, plant.getCircuitVoltage());
			ps.setString(9, plant.getInjectingSubstation());
			ps.setString(10, plant.getFeederName());
			ps.setString(11, plant.getRegion());
			ps.setString(12, plant.getCircle());
			ps.setString(13, plant.getDivision());
			ps.setString(14, plant.getMainMeterNo());
			ps.setString(15, plant.getCheckMeterNo());
			ps.setString(16, plant.getStandByMeterNo());
			ps.setInt(17, plant.getDeveloperId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantDetailsDAO : method : [insert(PlantDetails)] "+e);
		}
	}
	
	public void update(Plant plant){
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("update plants set code=?, address=?, Contact_no=?, Contact_person=?, email=?, commissioned_date=?, type=?, circuit_voltage=?, injecting_substation=?, feeder_name=?, region=?, circle=?, division=?, main_meter_no=?, check_meter_no=?, standby_meter_no=?, developer_id=? where id=?");
			ps.setString(1, plant.getCode());
			ps.setString(2, plant.getAddress());
			ps.setString(3, plant.getContactNo());
			ps.setString(4, plant.getContactPerson());
			ps.setString(5, plant.getEmail());
			ps.setString(6, plant.getCommissionedDate());
			ps.setString(7, plant.getType());
			ps.setString(8, plant.getCircuitVoltage());
			ps.setString(9, plant.getInjectingSubstation());
			ps.setString(10, plant.getFeederName());
			ps.setString(11, plant.getRegion());
			ps.setString(12, plant.getCircle());
			ps.setString(13, plant.getDivision());
			ps.setString(14, plant.getMainMeterNo());
			ps.setString(15, plant.getCheckMeterNo());
			ps.setString(16, plant.getStandByMeterNo());
			ps.setInt(17, plant.getDeveloperId());
			ps.setInt(18, plant.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantDetailsDAO : method : [update(PlantDetails)] "+e);
		}
	}
	
	public Plant getById(int id){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where id = ?");
			ps.setInt(1,id);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getById(int)] "+e);
		}
		
		return plantList.get(0);
	}

	public Plant getByCode(String code){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where code = ?");
			ps.setString(1,code);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByCode(String)] "+e);
		}
		
		return plantList.get(0);
	}
    
    public ArrayList<Plant> getAll(){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants");
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getAll()] "+e);
		}
		
		return plantList;
	}
	
	public ArrayList<Plant> getByDeveloperId(int developerId){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where developer_id = ?");
			ps.setInt(1,developerId);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByDeveloperId(int)] "+e);
		}
		
		return plantList;
	}
	
	public ArrayList<Plant> getByRegion(String region){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where region = ?");
			ps.setString(1,region);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByRegion(String)] "+e);
		}
		
		return plantList;
	}
	
	public ArrayList<Plant> getByCircle(String circle){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where circle = ?");
			ps.setString(1,circle);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByCircle(String)] "+e);
		}
		
		return plantList;
	}
	
	public ArrayList<Plant> getByDivision(String division){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where division = ?");
			ps.setString(1,division);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByDivision(String)] "+e);
		}
		
		return plantList;
	}
	
	public Plant getByMainMeterNo(String mainMeterNo){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where main_meter_no = ?");
			ps.setString(1,mainMeterNo);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByMainMeterNo(String)] "+e);
		}
		
		return plantList.get(0);
	}
	
	public Plant getByCheckMeterNo(String checkMeterNo){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where check_meter_no = ?");
			ps.setString(1,checkMeterNo);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByCheckMeterNo(String)] "+e);
		}
		
		return plantList.get(0);
	}
	
	public Plant getByStandbyMeterNo(String standbyMeterNo){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where standby_meter_no = ?");
			ps.setString(1,standbyMeterNo);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByCheckMeterNo(String)] "+e);
		}
		
		return plantList.get(0);
	}
	
	public ArrayList<Plant> getByInjectingSubstation(String injectingSubstation){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where injecting_substation = ?");
			ps.setString(1,injectingSubstation);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByInjectingSubstation(String)] "+e);
		}
		
		return plantList;
	}
	
	public ArrayList<Plant> getByFeederName(String feederName){
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from plants where feeder_name = ?");
			ps.setString(1,feederName);
			ResultSet rs = ps .executeQuery();
			plantList = plantMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [getByFeederName(String)] "+e);
		}
		
		return plantList;
	}
	
	private ArrayList<Plant> plantMapper(ResultSet rs) {
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		try {
			while(rs.next()){
                Plant plant = new Plant();
				plant.setId(rs.getInt(1));
				plant.setCode(rs.getString(2));
				plant.setAddress(rs.getString(3));
				plant.setContactNo(rs.getString(4));
				plant.setContactPerson(rs.getString(5));
				plant.setEmail(rs.getString(6));
				plant.setCommissionedDate(rs.getString(7));
				plant.setType(rs.getString(8));
				plant.setCircuitVoltage(rs.getString(9));
				plant.setInjectingSubstation(rs.getString(10));
				plant.setFeederName(rs.getString(11));
				plant.setRegion(rs.getString(12));
				plant.setCircle(rs.getString(13));
				plant.setDivision(rs.getString(14));
				plant.setMainMeterNo(rs.getString(15));
				plant.setCheckMeterNo(rs.getString(16));
				plant.setStandByMeterNo(rs.getString(17));
				plant.setDeveloperId(rs.getInt(18));
				plantList.add(plant);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantsDAO : method : [plantMapper(ResultSet)] "+e);
		}
		return plantList;
	}
}
