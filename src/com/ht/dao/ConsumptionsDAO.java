package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import com.ht.beans.Consumption;
import com.ht.utility.GlobalResources;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class ConsumptionsDAO {

	public boolean insert(Consumption consumption){
		boolean added=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into consumptions (meter_no, date, active_consumption, reactive_consumption, plant_id, plant_code, meter_reading_id, consumption_bifercated) values(?,?,?,?,?,?,?,?)");
			ps.setString(1,consumption.getMeterNo());
			ps.setString(2, consumption.getDate());
			ps.setFloat(3, consumption.getActiveConsumption());
			ps.setFloat(4, consumption.getReactiveConsumption());
			ps.setInt(5, consumption.getPlantId());
			ps.setString(6, consumption.getPlantCode());
			ps.setInt(7, consumption.getMeterReadingId());
			ps.setInt(8, consumption.getConsumptionBifercated());
			ps.executeUpdate();
			ps.close();
			added=true;
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in class : ConsumptionsDAO : method : [insert(Consumption)] "+e.getMessage());
		}
		return added;
	}
	
	public boolean update(Consumption consumption){
		boolean updated=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("update consumptions set meter_no=?, date=?, active_consumption=?, reactive_consumption=?, plant_id=?, plant_code=?, meter_reading_id=?, consumption_bifercated=? where id=?");
			ps.setString(1,consumption.getMeterNo());
			ps.setString(2, consumption.getDate());
			ps.setFloat(3, consumption.getActiveConsumption());
			ps.setFloat(3, consumption.getReactiveConsumption());
			ps.setInt(5, consumption.getPlantId());
			ps.setString(6, consumption.getPlantCode());
			ps.setInt(7, consumption.getMeterReadingId());
			ps.setInt(8, consumption.getConsumptionBifercated());
			ps.setInt(9, consumption.getId());
			ps.executeUpdate();
			ps.close();
			updated=true;
		} catch (SQLException e) {
			updated=false;
			System.out.println("Exception in class : ConsumptionsDAO : method : [update(Consumption)] "+e.getMessage());
		}
		return updated;
	}
	
	public ArrayList<Consumption> getAllConsumption(){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions");
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getAllConsumption()] "+e.getMessage());
		}
		return consumptionsList;
	}
	
	public Consumption getByMeterNo(String meterNo){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where meter_no=?");
			ps.setString(1, meterNo);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByMeterNo(String)] "+e.getMessage());
		}
		return consumptionsList.get(0);
	}
	
	public ArrayList<Consumption> getByMeterReadingId(int id){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where meter_reading_id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByMeterNo(String)] "+e.getMessage());
		}
		return consumptionsList;
	}
	
	public Consumption getById(int id){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getById(int)] "+e.getMessage());
		}
		return consumptionsList.size()>0?consumptionsList.get(0):null;
	}
	
	public ArrayList<Consumption> getByPlantId(int plantId){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where plant_id=?");
			ps.setInt(1, plantId);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByPlantId(int)] "+e.getMessage());
		}
		return consumptionsList;
	}
	
	public ArrayList<Consumption> getByPlantCode(String plantCode){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where plant_code=?");
			ps.setString(1, plantCode);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByPlantCode(String)] "+e.getMessage());
		}
		return consumptionsList;
	}
	
	
	public ArrayList<Consumption> getByDeveloperId(int developerId){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where developer_id=?");
			ps.setInt(1, developerId);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByDeveloperId(int)] "+e.getMessage());
		}
		return consumptionsList;
	}
	
	
	public ArrayList<Consumption> getByDeveloperIdAndPlantId(int developerId, int plantId){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where developer_id=? and plant_id=?");
			ps.setInt(1, developerId);
			ps.setInt(2, plantId);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByDeveloperIdAndPlantId(int,int)] "+e.getMessage());
		}
		return consumptionsList;
	}
	
	public ArrayList<Consumption> getByDeveloperIdPlantIdAndDate(int developerId, int plantId,String date){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where developer_id=? and plant_id=? and date like '%"+date+"%'");
			ps.setInt(1, developerId);
			ps.setInt(2, plantId);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByDeveloperIdPlantIdAndDate(int,int,String)] "+e.getMessage());
		}
		return consumptionsList;
	}
    
    public Consumption getByMeterNoAndDate(String meterNo,String date){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse(date));
				int year = c.get(Calendar.YEAR);
				//System.out.println("Year is :"+year);
				int month = c.get(Calendar.MONTH)+1;
				//System.out.println("Month is :"+month );
				String mon = null;
				if (month < 10) {
		           mon  = "0"+month;
		        }
				String dateTrim = mon+"-"+year;
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where meter_no=? and date like '%"+dateTrim+"%'");
			ps.setString(1, meterNo);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		}catch(ParseException e){
            System.out.println("Exception in class : ConsumptionsDAO : method : [getByMeterNoAndDate(String,String)] "+e.getMessage());
        }catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByMeterNoAndDate(String,String)] "+e.getMessage());
		}
		return consumptionsList.get(0);
	}
    
     public Consumption getByPlantIdAndDate(int plantId,String date){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse(date));
				int year = c.get(Calendar.YEAR);
				//System.out.println("Year is :"+year);
				int month = c.get(Calendar.MONTH)+1;
				//System.out.println("Month is :"+month );
				String mon = null;
				if (month < 10) {
		           mon  = "0"+month;
		        }
				String dateTrim = mon+"-"+year;
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where plant_id=? and date like '%"+dateTrim+"%'");
			ps.setInt(1, plantId);
			ResultSet rs = ps.executeQuery();
			consumptionsList = ConsumptionsMapper(rs);
		}catch(ParseException e){
            System.out.println("Exception in class : ConsumptionsDAO : method : [getByPlantIdAndDate(String,String)] "+e.getMessage());
        }catch(SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getByPlantIdAndDate(String,String)] "+e.getMessage());
		}
		
		return consumptionsList.size()>0?consumptionsList.get(0):null;
	}
	
		public boolean updateConsumptionBifercated(int bifercated, int id){
		boolean updated= false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("update consumptions set consumption_bifercated=? where id=?");
			ps.setInt(1, bifercated);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
			updated = true;
		} catch (SQLException e) {
			updated = false;
			System.out.println("Exception in class : ConsumptionsDAO : method : [updateConsumptionBifercated(int,int)] "+e.getMessage());
		}
		return updated;
	}
	
		public Consumption getBifercationFlagByPlantIdAndDate(int plantId, String date){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		Connection connection = GlobalResources.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		ResultSet rs=null;
		 try {
			Calendar c = Calendar.getInstance();
			c.setTime(formatter.parse(date));
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			String mon = null;
			if (month < 10) {
	           mon  = "0"+month;
	        }
			String dateTrim = mon+"-"+year;
			PreparedStatement ps = connection.prepareStatement("select * from consumptions where plant_id=? and date like '%"+dateTrim+"%'");
			ps.setInt(1, plantId);
			rs = ps.executeQuery();
			consumptionsList=ConsumptionsMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getBifercationFlagByPlantIdAndDate(int,String)] "+e.getMessage());
		} catch (ParseException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [getBifercationFlagByPlantIdAndDate(int,String)] "+e.getMessage());
		}
		return consumptionsList.get(0);
	}
    
	private ArrayList<Consumption> ConsumptionsMapper(ResultSet rs){
		ArrayList<Consumption> consumptionsList = new ArrayList<Consumption>();
		
		try {
			while(rs.next()){
                Consumption consumption =new Consumption();
				consumption.setId(rs.getInt(1));
				consumption.setMeterNo(rs.getString(2));
				consumption.setDate(rs.getString(3));
				consumption.setActiveConsumption(rs.getFloat(4));
				consumption.setReactiveConsumption(rs.getFloat(5));
				consumption.setPlantId(rs.getInt(6));
				consumption.setPlantCode(rs.getString(7));
				consumption.setMeterReadingId(rs.getInt(8));
				consumption.setConsumptionBifercated(rs.getInt(9));
				consumptionsList.add(consumption);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : ConsumptionsDAO : method : [ConsumptionsMapper(ResultSet)] "+e.getMessage());
		}
		return consumptionsList;
	}
}
