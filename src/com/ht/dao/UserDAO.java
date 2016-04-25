package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ht.beans.User;
import com.ht.utility.GlobalResources;

public class UserDAO {
  private Connection connection = GlobalResources.getConnection();
  
  public User getByUsername(String username){
	  User user=null;
	  try{
		  PreparedStatement ps = connection.prepareStatement("select * from users where username=?");
		  ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			  user=new User(rs.getString("username").trim(),rs.getString("password").trim(),rs.getString("name").trim());
			}
	  }catch(SQLException sqlException){
		  System.out.println("Exception in class UserDAO : method : getByUsername(String) : "+sqlException);
	  }
	  return user;
  }
}
