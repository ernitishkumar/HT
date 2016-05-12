package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.Developer;
import com.ht.utility.GlobalResources;

public class DevelopersDAO {

	public boolean insert(Developer developer){
		boolean added=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into developers (name, cin, office_address, office_contact_no, office_contact_person, office_email, site_address, site_contact_no, site_contact_person, site_email, username) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, developer.getName());
			ps.setString(2, developer.getCin());
			ps.setString(3, developer.getOfficeAddress());
			ps.setString(4, developer.getOfficeContactNo());
			ps.setString(5, developer.getOfficeContactPerson());
			ps.setString(6, developer.getOfficeEmail());
			ps.setString(7, developer.getSiteAddress());
			ps.setString(8, developer.getSiteContactNo());
			ps.setString(9, developer.getSiteContactPerson());
			ps.setString(10, developer.getSiteEmail());
            ps.setString(11, developer.getUsername());
			ps.executeUpdate();
			ps.close();
            added=true;
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in class : DevelpersDAO : method : [insert(Developer)] "+e.getMessage());
			e.printStackTrace();
		}
		return added;
	}
	
	public boolean update(Developer developer){
		boolean updated=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("update developers set name=?, cin=?, office_address=?, office_contact_no=?, office_contact_person=?, office_email=?, site_address=?, site_contact_no=?, site_contact_person=?, site_email=?, username=? where id =?");
			ps.setString(1, developer.getName());
			ps.setString(2, developer.getCin());
			ps.setString(3, developer.getOfficeAddress());
			ps.setString(4, developer.getOfficeContactNo());
			ps.setString(5, developer.getOfficeContactPerson());
			ps.setString(6, developer.getOfficeEmail());
			ps.setString(7, developer.getSiteAddress());
			ps.setString(8, developer.getSiteContactNo());
			ps.setString(9, developer.getSiteContactPerson());
			ps.setString(10, developer.getSiteEmail());
			ps.setString(11, developer.getUsername());
			ps.setInt(12, developer.getId());
			ps.executeUpdate();
			ps.close();
			updated=true;
		} catch (SQLException e) {
			updated=false;
			System.out.println("Exception in class : DevelpersDAO : method : [update(Developer)] "+e.getMessage());
		}
		return updated;
	}
	
	public ArrayList<Developer> getAllDevelopers(){
		Connection connection = GlobalResources.getConnection();
		ArrayList<Developer> developersArray = new ArrayList<Developer>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from developers");
			ResultSet rs = ps.executeQuery();
			developersArray = developerMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : DevelpersDAO : method : [getAllDevelopers()] "+e.getMessage());
		}
		return developersArray;
	}
	
	
	public Developer getById(int id){
		ArrayList<Developer> developersArray = new ArrayList<Developer>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from developers where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			developersArray = developerMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : DevelpersDAO : method : [getById(int)] "+e.getMessage());
		}
		return developersArray.get(0);
	}
	
	public Developer getByCIN(String cin){
		ArrayList<Developer> developersArray = new ArrayList<Developer>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from developers where cin = ?");
			ps.setString(1, cin);
			ResultSet rs = ps.executeQuery();
			developersArray = developerMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : DevelpersDAO : method : [getByCIN(String)] "+e.getMessage());
		}
		return developersArray.get(0);
	}
	
	public Developer getByName(String name){
		ArrayList<Developer> developersArray = new ArrayList<Developer>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from developers where name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			developersArray = developerMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : DevelpersDAO : method : [getByName(String)] "+e.getMessage());
		}
		return developersArray.get(0);
	}
    
    public Developer getByUsername(String username){
		ArrayList<Developer> developersArray = new ArrayList<Developer>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from developers where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			developersArray = developerMapper(rs);
            rs.close();
            ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : DevelpersDAO : method : [getByUsername(String)] "+e.getMessage());
		}
		return developersArray.size()>0?developersArray.get(0):null;
	}
	
    
	private ArrayList<Developer> developerMapper(ResultSet resultSet){
		ArrayList<Developer> developersArray = new ArrayList<Developer>();
		
		try {
			while(resultSet.next()){
                Developer developer = new Developer();
                System.out.println(resultSet.getInt(1));
				developer.setId(resultSet.getInt(1));
				developer.setName(resultSet.getString(2));
				developer.setCin(resultSet.getString(3));
				developer.setOfficeAddress(resultSet.getString(4));
				developer.setOfficeContactNo(resultSet.getString(5));
				developer.setOfficeContactPerson(resultSet.getString(6));
				developer.setOfficeEmail(resultSet.getString(7));
				developer.setSiteAddress(resultSet.getString(8));
				developer.setSiteContactNo(resultSet.getString(9));
				developer.setSiteContactPerson(resultSet.getString(10));
				developer.setSiteEmail(resultSet.getString(11));
                developer.setUsername(resultSet.getString(12));
				developersArray.add(developer);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : DevelpersDAO : method : [developerMapper(ResultSet)] "+e.getMessage());
		}
		return developersArray;
	}
	
}
