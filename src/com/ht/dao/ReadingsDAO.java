package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.Readings;
import com.ht.utility.GlobalResources;

public class ReadingsDAO {

	public void insert(Readings readings){
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into readings(meter_no, mf, reading_date_time, cmvh_import, cmvh_export, mvarh_lagging_import, mvarh_lagging_export, mvarh_leading_import, mvarh_leading_export) VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, readings.getMeterNo());
			ps.setInt(2, readings.getMf());
			ps.setString(3, readings.getRedingDateTime());
			ps.setString(4, readings.getCmvhImport());
			ps.setString(5, readings.getCmvhExport());
			ps.setString(6, readings.getMvarhLaggingImport());
			ps.setString(7, readings.getMvarhLaggingExport());
			ps.setString(8, readings.getMvarhLeadingImport());
			ps.setString(9, readings.getMvarhLeadingExport());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : ReadingsDAO : method : [insert(Readings)] "+e);
		}
	}
	
	public void update(Readings readings){
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("update readings set meter_no=?, mf=?, reading_date_time=?, cmvh_import=?, cmvh_export=?, mvarh_lagging_import=?, mvarh_lagging_export=?, mvarh_leading_import=?, mvarh_leading_export=? where id = ?");
			ps.setString(1, readings.getMeterNo());
			ps.setInt(2, readings.getMf());
			ps.setString(3, readings.getRedingDateTime());
			ps.setString(4, readings.getCmvhImport());
			ps.setString(5, readings.getCmvhExport());
			ps.setString(6, readings.getMvarhLaggingImport());
			ps.setString(7, readings.getMvarhLaggingExport());
			ps.setString(8, readings.getMvarhLeadingImport());
			ps.setString(9, readings.getMvarhLeadingExport());
			ps.setInt(10, readings.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : ReadingsDAO : method : [update(Readings)] "+e);
		}
	}
	
	public void delete(int id) {
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("delete from readings where id="+id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : ReadingsDAO : method : [delete(int)] "+e);
		}
	}
	
	public ArrayList<Readings> getAll(){
		Connection connection = GlobalResources.getConnection();
		ArrayList<Readings> readingsArray = new ArrayList<Readings>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from readings");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				Readings readings = new Readings();
				readings.setId(resultSet.getInt(1));
				readings.setMeterNo(resultSet.getString(2));
				readings.setRedingDateTime(resultSet.getString(3));
				readings.setCmvhImport(resultSet.getString(4));
				readings.setCmvhExport(resultSet.getString(5));
				readings.setMvarhLaggingImport(resultSet.getString(6));
				readings.setMvarhLaggingExport(resultSet.getString(7));
				readings.setMvarhLeadingImport(resultSet.getString(8));
				readings.setMvarhLeadingExport(resultSet.getString(9));
				readingsArray.add(readings);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getAll] "+ e);
		}
		return readingsArray;
	}
	
	public Readings getById(int id){
		Connection connection = GlobalResources.getConnection();
		Readings readings = null;
		try {
			PreparedStatement ps = connection.prepareStatement("select * from readings where id="+id);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				readings = new Readings();
				readings.setId(resultSet.getInt(1));
				readings.setMeterNo(resultSet.getString(2));
				readings.setRedingDateTime(resultSet.getString(3));
				readings.setCmvhImport(resultSet.getString(4));
				readings.setCmvhExport(resultSet.getString(5));
				readings.setMvarhLaggingImport(resultSet.getString(6));
				readings.setMvarhLaggingExport(resultSet.getString(7));
				readings.setMvarhLeadingImport(resultSet.getString(8));
				readings.setMvarhLeadingExport(resultSet.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getById(id)] "+ e);
		}
		return readings;
	}
	
	public Readings getByMeterNo(String meterNo){
		Connection connection = GlobalResources.getConnection();
		Readings readings = null;
		try {
			PreparedStatement ps = connection.prepareStatement("select * from readings where meter_no="+meterNo);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				readings = new Readings();
				readings.setId(resultSet.getInt(1));
				readings.setMeterNo(resultSet.getString(2));
				readings.setRedingDateTime(resultSet.getString(3));
				readings.setCmvhImport(resultSet.getString(4));
				readings.setCmvhExport(resultSet.getString(5));
				readings.setMvarhLaggingImport(resultSet.getString(6));
				readings.setMvarhLaggingExport(resultSet.getString(7));
				readings.setMvarhLeadingImport(resultSet.getString(8));
				readings.setMvarhLeadingExport(resultSet.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getByMeterNo(String)] "+ e);
		}
		return readings;
	}
	
	public void deleteByMeterNo(String meterNo) {
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("delete from readings where meter_no="+meterNo);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : ReadingsDAO : method : [deleteByMeterNo(String)] "+e);
		}
	}
}
