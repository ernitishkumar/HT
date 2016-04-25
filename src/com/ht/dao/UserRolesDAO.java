package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.UserRoles;
import com.ht.utility.GlobalResources;

public class UserRolesDAO {

	public void insert(UserRoles userRoles){
		Connection connection = GlobalResources.getConnection();
		
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into user_roles (username, user_role, region, circle, division) values(?,?,?,?)");
			ps.setString(1, userRoles.getUsername());
			ps.setString(2, userRoles.getRole());
			ps.setString(3, userRoles.getRegion());
			ps.setString(4, userRoles.getCircle());
			ps.setString(5, userRoles.getDivision());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : UserRolesDAO : method : [insert(USerRoles)] "+e);
		}
	}
	
	public void update(UserRoles userRoles){
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("update user_roles set username=?, user_role=?, region=?, circle=?, division=? where id=?");
			ps.setString(1, userRoles.getUsername());
			ps.setString(2, userRoles.getRole());
			ps.setString(3, userRoles.getRegion());
			ps.setString(4, userRoles.getCircle());
			ps.setString(5, userRoles.getDivision());
			ps.setInt(6, userRoles.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : UserRolesDAO : method : [update(USerRoles)] "+e);
		}
	}
	
	public UserRoles getByUsername(String username){
		ArrayList<UserRoles> userRoles = new ArrayList<UserRoles>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from user_roles where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			userRoles = userMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : UserRolesDAO : method : [getByUsername(String username)] "+e);
		}
		
		return userRoles.get(0);
	}
	
	public UserRoles getById(int id){
		ArrayList<UserRoles> userRoles = new ArrayList<UserRoles>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from user_roles where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			userRoles = userMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : UserRolesDAO : method : [getById(int id)] "+e);
		}
		
		return userRoles.get(0);
	}
	
	public ArrayList<UserRoles> getByCircle(String circle){
		ArrayList<UserRoles> userRoles = new ArrayList<UserRoles>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from user_roles where circle=?");
			ps.setString(1, circle);
			ResultSet rs = ps.executeQuery();
			userRoles = userMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : UserRolesDAO : method : [getByCircle(String circle)] "+e);
		}
		
		return userRoles;
	}
	
	private ArrayList<UserRoles> userMapper(ResultSet resultSet){
		UserRoles userRoles = new UserRoles();
		ArrayList<UserRoles> userRolesArray = new ArrayList<UserRoles>();
		try {
			while(resultSet.next()){
				userRoles.setId(resultSet.getInt(1));
				userRoles.setUsername(resultSet.getString(2));
				userRoles.setRole(resultSet.getString(3));
				userRoles.setRegion(resultSet.getString(4));
				userRoles.setCircle(resultSet.getString(5));
				userRoles.setDivision(resultSet.getString(6));
				userRolesArray.add(userRoles);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : UserRolesDAO : method : [userMapper(ResultSet resultSet)] "+e);
		}
		return userRolesArray;
	}
}
