package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import com.ht.beans.MeterReadings;
import com.ht.utility.GlobalResources;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import com.ht.beans.Plant;
import com.ht.beans.Developer;
import com.ht.dao.DevelopersDAO;
import com.ht.beans.MeterDetails;
import com.ht.dao.PlantsDAO;

public class MeterReadingsDAO {

	
	public boolean insert(MeterReadings readings) throws ParseException{
		Connection connection = GlobalResources.getConnection();
        boolean added=false;
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into meter_readings(meter_no, mf, reading_date, active_reading, active_tod1, active_tod2, active_tod3, reactive_q1, reactive_q2, reactive_q3, reactive_q4, ht_cell_validation, circle_cell_validation, developer_validation, discarded_flag, discarded_by, discarded_on) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, readings.getMeterNo());
			ps.setInt(2, readings.getMf());
			ps.setString(3, readings.getReadingDate());
			ps.setFloat(4, readings.getActiveEnergy());
			ps.setFloat(5, readings.getActiveTodOne());
			ps.setFloat(6, readings.getActiveTodTwo());
			ps.setFloat(7, readings.getActiveTodThree());
			ps.setFloat(8, readings.getReactiveQuadrantOne());
			ps.setFloat(9, readings.getReactiveQuadrantTwo());
			ps.setFloat(10, readings.getReactiveQuadrantThree());
			ps.setFloat(11, readings.getReactiveQuadrantFour());
			ps.setInt(12,0);
			ps.setInt(13,0);
			ps.setInt(14,0);
			ps.setInt(15,0);
			ps.setString(16,"null");
			ps.setString(17,"null");
			ps.executeUpdate();
			ps.close();
            added=true;
		} catch (SQLException e) {
            added=false;
			System.out.println("Exception in class : MeterReadingsDAO : method : [insert(Readings)] "+e.getMessage());
		}
        return added;
	}
	
	public boolean update(MeterReadings readings){
		Connection connection = GlobalResources.getConnection();
        boolean updated=false;
        try {
			PreparedStatement ps = connection
					.prepareStatement("update meter_readings set meter_no=?, mf=?, reading_date=?, active_reading=?, active_tod1=?, active_tod2=?, active_tod3=?, reactive_q1=?, reactive_q2=?, reactive_q3=?, reactive_q4=?, ht_cell_validation=?, circle_cell_validation=?, developer_validation=?, discarded_flag=?, discarded_by=?, discarded_on=? where id=?");
			ps.setString(1, readings.getMeterNo());
			ps.setInt(2, readings.getMf());
			ps.setString(3, readings.getReadingDate());
			ps.setFloat(4, readings.getActiveEnergy());
			ps.setFloat(5, readings.getActiveTodOne());
			ps.setFloat(6, readings.getActiveTodTwo());
			ps.setFloat(7, readings.getActiveTodThree());
			ps.setFloat(8, readings.getReactiveQuadrantOne());
			ps.setFloat(9, readings.getReactiveQuadrantTwo());
			ps.setFloat(10, readings.getReactiveQuadrantThree());
			ps.setFloat(11, readings.getReactiveQuadrantFour());
			ps.setInt(12,readings.getHtCellValidation());
			ps.setInt(13,readings.getCircleCellValidation());
			ps.setInt(14,readings.getDeveloperValidation());
			ps.setInt(15,readings.getDiscardedFlag());
			ps.setString(16,readings.getDiscardedBy());
			ps.setString(17,readings.getDiscardedOn());
			ps.setInt(18, readings.getId());
			ps.executeUpdate();
			ps.close();
            updated=true;
		} catch (SQLException e) {
            updated=false;
			System.out.println("Exception in class : MeterReadingsDAO : method : [insert(Readings)] "+e.getMessage());
		}
        return updated;
	}
	
	public boolean delete(int id) {
		Connection connection = GlobalResources.getConnection();
        boolean deleted=false;
		try {
			PreparedStatement ps = connection
					.prepareStatement("delete from meter_readings where id=?");
            ps.setInt(1,id);
			ps.executeUpdate();
			ps.close();
            deleted=true;
		} catch (SQLException e) {
            deleted=false;
			System.out.println("Exception in class : MeterReadingsDAO : method : [delete(int)] "+e.getMessage());
		}
       return deleted;
	}
	
	public ArrayList<MeterReadings> getAll(){
		Connection connection = GlobalResources.getConnection();
		ArrayList<MeterReadings> readings = new ArrayList<MeterReadings>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from meter_readings");
			ResultSet resultSet = ps.executeQuery();
			resultSetParser(resultSet,readings);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getAll] "+e.getMessage());
		}
		return readings;
	}
	
	public int getCount(){
Connection connection = GlobalResources.getConnection();
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("select count(*) as count from meter_readings");
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			count=Integer.parseInt(resultSet.getString("count").trim());
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getCount()] "+e.getMessage());
		}
		return count;
	}

	public MeterReadings getById(int id){
		Connection connection = GlobalResources.getConnection();
		ArrayList<MeterReadings> readings = new ArrayList<MeterReadings>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from meter_readings where id=?");
            ps.setInt(1,id);
			ResultSet resultSet = ps.executeQuery();
			resultSetParser(resultSet,readings);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getById(id)] "+e.getMessage());
		}
		return readings.get(0);
	}
	
	public ArrayList<MeterReadings> getByMeterNo(String meterNo){
		Connection connection = GlobalResources.getConnection();
		ArrayList<MeterReadings> readings = new ArrayList<MeterReadings>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from meter_readings where meter_no=?");
            ps.setString(1,meterNo);
			ResultSet resultSet = ps.executeQuery();
			resultSetParser(resultSet,readings);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getByMeterNo(String)] "+e.getMessage());
		}
		return readings;
	}
	
	public boolean deleteByMeterNo(String meterNo) {
		Connection connection = GlobalResources.getConnection();
        boolean deleted=false;
		try {
			PreparedStatement ps = connection
					.prepareStatement("delete from meter_readings where meter_no=?");
            ps.setString(1,meterNo);
			ps.executeUpdate();
			ps.close();
            deleted=true;
		} catch (SQLException e) {
            deleted=true;
			System.out.println("Exception in class : MeterReadingsDAO : method : [deleteByMeterNo(String)] "+e.getMessage());
		}
        return deleted;
	}
	
		public boolean updateHTCellValidation(int id, int valid){
			Connection connection = GlobalResources.getConnection();
			boolean validated = false;
			try {
                System.out.println("updating id and value "+id+" "+valid);
				PreparedStatement ps = connection.prepareStatement("update meter_readings set ht_cell_validation=? where id=?");
				ps.setInt(1,valid);
				ps.setInt(2,id);
				ps.executeUpdate();
                validated=true;
				ps.close();
			} catch (SQLException e) {
				System.out.println("Exception in class : MeterDetailsDAO : method : [updateHTCellValidation(int,int)] "+e.getMessage());
			}
			return validated;
		}
		
		public boolean updateCircleCellValidation(int id, int valid){
			Connection connection = GlobalResources.getConnection();
			boolean validation = false;
			try {
				PreparedStatement ps = connection.prepareStatement("update meter_readings set circle_cell_validation=? where id=?");
				ps.setInt(1,valid);
				ps.setInt(2,id);
				ps.executeUpdate();
                validation=true;
				ps.close();
			} catch (SQLException e) {
				System.out.println("Exception in class : MeterDetailsDAO : method : [updateCircleCellValidation(int,int)] "+e.getMessage());
			}
			return validation;
		}
		
		public boolean updateDeveloperValidation(int id, int valid){
			Connection connection = GlobalResources.getConnection();
			boolean validation = false;
			try {
				PreparedStatement ps = connection.prepareStatement("update meter_readings set developer_validation=? where id=?");
				ps.setInt(1,valid);
				ps.setInt(2,id);
				ps.executeUpdate();
                validation=true;
				ps.close();
			} catch (SQLException e) {
				System.out.println("Exception in class : MeterDetailsDAO : method : [updateDeveloperValidation(int,int)] "+e.getMessage());
			}
			return validation;
		}
	

	public MeterReadings getCurrentMonthMeterReadings(String meterNo, String date){
			Connection connection = GlobalResources.getConnection();
			ArrayList<MeterReadings> readings = new ArrayList<MeterReadings>();
			MeterReadings meterReadings = new MeterReadings();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			 try {
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
				//System.out.println("trimmed date is  :"+dateTrim );
				PreparedStatement ps = connection.prepareStatement("select * from meter_readings where meter_no=? and reading_date like '%"+dateTrim+"%'");
				ps.setString(1, meterNo);
				ResultSet rs = ps.executeQuery();
				resultSetParser(rs,readings);
				if(readings.isEmpty()){
					MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
					MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterNo);
					meterReadings.setId(-1);
		            meterReadings.setMeterNo(meterNo);
		            meterReadings.setMf(meterDetails.getMf());
		            meterReadings.setReadingDate(date);
		            meterReadings.setActiveEnergy(0);
		            meterReadings.setActiveTodOne(0);
		            meterReadings.setActiveTodTwo(0);
		            meterReadings.setActiveTodThree(0);
		            meterReadings.setReactiveQuadrantOne(0);
		            meterReadings.setReactiveQuadrantTwo(0);
		            meterReadings.setReactiveQuadrantThree(0);
		            meterReadings.setReactiveQuadrantFour(0);
		            meterReadings.setHtCellValidation(0);
		            meterReadings.setCircleCellValidation(0);
		            meterReadings.setDeveloperValidation(0);
					meterReadings.setDiscardedFlag(0);
		            meterReadings.setDiscardedBy("null");
		            meterReadings.setDiscardedOn("null");
				}else{
					meterReadings = readings.get(0);
				}
			} catch (ParseException e) {
				System.out.println("Exception in class : MeterReadingsDAO : method : [getCurrentMonthMeterReadings(String,String)] "+e.getMessage());
			} catch (SQLException e)   {
				System.out.println("Exception in class : MeterReadingsDAO : method : [getCurrentMonthMeterReadings(String,String)] "+e.getMessage());
			}

			return meterReadings;
		}
		
    public MeterReadings getPreviousMonthMeterReadings(String meterNo, String date){
			Connection connection = GlobalResources.getConnection();
			ArrayList<MeterReadings> readings = new ArrayList<MeterReadings>();
			MeterReadings meterReadings = new MeterReadings();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			 try {
				Calendar c = Calendar.getInstance();
				c.setTime(formatter.parse(date));
				c.add(Calendar.MONTH,-1);
				int day = c.get(Calendar.DATE);
				String readingDay= null;
				if(day < 10){
					readingDay = "0"+day;
				}
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH)+1;
				String mon = null;
				if (month < 10) {
		           mon  = "0"+month;
		        }
				String dateTrim = mon+"-"+year;
				PreparedStatement ps = connection.prepareStatement("select * from meter_readings where meter_no=? and reading_date like '%"+dateTrim+"%'");
				ps.setString(1, meterNo);
				ResultSet rs = ps.executeQuery();
				resultSetParser(rs,readings);
				if(readings.isEmpty()){
					MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
					MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterNo);
					meterReadings.setId(-1);
		            meterReadings.setMeterNo(meterNo);
		            meterReadings.setMf(meterDetails.getMf());
		            meterReadings.setReadingDate(readingDay+"-"+dateTrim);
		            meterReadings.setActiveEnergy(0);
		            meterReadings.setActiveTodOne(0);
		            meterReadings.setActiveTodTwo(0);
		            meterReadings.setActiveTodThree(0);
		            meterReadings.setReactiveQuadrantOne(0);
		            meterReadings.setReactiveQuadrantTwo(0);
		            meterReadings.setReactiveQuadrantThree(0);
		            meterReadings.setReactiveQuadrantFour(0);
		            meterReadings.setHtCellValidation(0);
		            meterReadings.setCircleCellValidation(0);
		            meterReadings.setDeveloperValidation(0);
					meterReadings.setDiscardedFlag(0);
		            meterReadings.setDiscardedBy("null");
		            meterReadings.setDiscardedOn("null");
				}else{
					meterReadings = readings.get(0);
				}
			} catch (ParseException e) {
				System.out.println("Exception in class : MeterReadingsDAO : method : [getPreviousMonthMeterReadings(String,String)] "+e.getMessage());
			} catch (SQLException e)   {
				System.out.println("Exception in class : MeterReadingsDAO : method : [getPreviousMonthMeterReadings(String,String)] "+e.getMessage());
			}

			return meterReadings;
		}

		
		
    private void resultSetParser(ResultSet resultSet,ArrayList<MeterReadings> readings){
        try{
        while(resultSet.next()){
        	MeterReadings meterReadings=new MeterReadings();
            meterReadings.setId(resultSet.getInt(1));
            meterReadings.setMeterNo(resultSet.getString(2));
            meterReadings.setMf(resultSet.getInt(3));
            meterReadings.setReadingDate(resultSet.getString(4));
            meterReadings.setActiveEnergy(resultSet.getFloat(5));
            meterReadings.setActiveTodOne(resultSet.getFloat(6));
            meterReadings.setActiveTodTwo(resultSet.getFloat(7));
            meterReadings.setActiveTodThree(resultSet.getFloat(8));
            meterReadings.setReactiveQuadrantOne(resultSet.getFloat(9));
            meterReadings.setReactiveQuadrantTwo(resultSet.getFloat(10));
            meterReadings.setReactiveQuadrantThree(resultSet.getFloat(11));
            meterReadings.setReactiveQuadrantFour(resultSet.getFloat(12));
            meterReadings.setHtCellValidation(resultSet.getInt(13));
            meterReadings.setCircleCellValidation(resultSet.getInt(14));
            meterReadings.setDeveloperValidation(resultSet.getInt(15));
			meterReadings.setDiscardedFlag(resultSet.getInt(16));
            meterReadings.setDiscardedBy(resultSet.getString(17));
            meterReadings.setDiscardedOn(resultSet.getString(18));
            readings.add(meterReadings);
        }
        }catch(Exception e){
            System.out.println("Exception in class : MeterReadingsDAO : method : resultSetParser(resultSet,arrayList) "+e.getMessage());
        }
    }

}
