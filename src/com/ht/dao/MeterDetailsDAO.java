package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.MeterDetails;
import com.ht.utility.GlobalResources;

public class MeterDetailsDAO {

	private Connection connection = GlobalResources.getConnection();
	
	public boolean insert(MeterDetails meterDetails) {
		boolean added=false;
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into meter_details(meter_no, make, category, type, meter_class, ctr, ptr, mf, equip_class, phase, meter_group) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, meterDetails.getMeterNo());
			ps.setString(2, meterDetails.getMake());
			ps.setString(3, meterDetails.getCategory());
			ps.setString(4, meterDetails.getType());
			ps.setString(5, meterDetails.getMeterClass());
			ps.setString(6, meterDetails.getCtr());
			ps.setString(7, meterDetails.getPtr());
			ps.setInt(8, meterDetails.getMf());
			ps.setString(9, meterDetails.getEquipmemntClass());
			ps.setString(10, meterDetails.getPhase());
			ps.setString(11, meterDetails.getMeterGroup());
			ps.executeUpdate();
			ps.close();
			added = true;
		} catch (SQLException e) {
			added=false;
			System.out
					.println("Exception in class : MeterDetailsDAO : method : [insert(MeterDetails)] "
							+ e);
		}
		return added;
	}

	public void update(MeterDetails meterDetails) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("update meter_details set make=?, category=?, type=?, meter_class=?, ctr=?, ptr=?, mf=?, equip_class=?, phase=?, meter_group=? where  meter_no=?)");
			ps.setString(1, meterDetails.getMake());
			ps.setString(2, meterDetails.getCategory());
			ps.setString(3, meterDetails.getType());
			ps.setString(4, meterDetails.getMeterClass());
			ps.setString(5, meterDetails.getCtr());
			ps.setString(6, meterDetails.getPtr());
			ps.setInt(7, meterDetails.getMf());
			ps.setString(8, meterDetails.getEquipmemntClass());
			ps.setString(9, meterDetails.getPhase());
			ps.setString(10, meterDetails.getMeterGroup());
			ps.setString(11, meterDetails.getMeterNo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out
					.println("Exception in class : MeterDetailsDAO : method : [update(MeterDetails)] "
							+ e);
		}
	}

	public void delete(String meterNo) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("delete from meter_details where meter_no="+meterNo);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [delete(String)] "+ e);
			
		}
	}
	
	public ArrayList<MeterDetails> getAll(){
		ArrayList<MeterDetails> meterDetails = new ArrayList<MeterDetails>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from meter_details");
			ResultSet resultSet = ps.executeQuery();
			meterDetails = resultSetMapper(resultSet);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getAll] "+ e);
		}
		return meterDetails;
	}
	
	public MeterDetails getByMeterNo(String meterNo){
		MeterDetails meterDetails = new MeterDetails();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from meter_details where meter_no=?");
            ps.setString(1,meterNo);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				meterDetails.setMeterNo(resultSet.getString(1));
				meterDetails.setMake(resultSet.getString(2));
				meterDetails.setCategory(resultSet.getString(3));
				meterDetails.setType(resultSet.getString(4));
				meterDetails.setMeterClass(resultSet.getString(5));
				meterDetails.setCtr(resultSet.getString(6));
				meterDetails.setPtr(resultSet.getString(7));
				meterDetails.setMf(resultSet.getInt(8));
				meterDetails.setEquipmemntClass(resultSet.getString(9));
				meterDetails.setPhase(resultSet.getString(10));
				meterDetails.setMeterGroup(resultSet.getString(11));
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getByMeterNo(String)] "+ e);
            e.printStackTrace();
		}
		return meterDetails;
	}
	
	public ArrayList<MeterDetails> resultSetMapper(ResultSet rs){
		ArrayList<MeterDetails> meterDetailsArray = new ArrayList<MeterDetails>();
		try {
			while(rs.next()){
				MeterDetails meterDetails = new MeterDetails();
				meterDetails.setMeterNo(rs.getString(1));
				meterDetails.setMake(rs.getString(2));
				meterDetails.setCategory(rs.getString(3));
				meterDetails.setType(rs.getString(4));
				meterDetails.setMeterClass(rs.getString(5));
				meterDetails.setCtr(rs.getString(6));
				meterDetails.setPtr(rs.getString(7));
				meterDetails.setMf(rs.getInt(8));
				meterDetails.setEquipmemntClass(rs.getString(9));
				meterDetails.setPhase(rs.getString(10));
				meterDetails.setMeterGroup(rs.getString(11));
				meterDetailsArray.add(meterDetails);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [resultSetMapper(ResultSet)] "+ e);
		}
		return meterDetailsArray;
	}
}
