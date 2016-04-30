package com.ht.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.UserDAO;

public class GlobalResources {

	private static Connection connection = null;
	private static Gson gson=new Gson();
	private static UserDAO userDAO=new UserDAO();
	private static MeterDetailsDAO meterDetailsDAO=new MeterDetailsDAO();
    private static MeterReadingsDAO meterReadingsDAO=new MeterReadingsDAO();
	
	public static Connection getConnection()
	{
		try {
			if(connection==null){
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ht","ht","ht");	
			}
		} catch (SQLException exception) {
			System.out.println("Not able to connect to the Database "+exception.getMessage());
			exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found "+e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Gson getGson(){
		return gson;
	}
	
	public static UserDAO getUserDAO(){
		return userDAO;
	}
	
	public static MeterDetailsDAO getMeterDetailsDAO() {
		return meterDetailsDAO;
	}

    public static MeterReadingsDAO getMeterReadingsDAO() {
		return meterReadingsDAO;
	}
}
