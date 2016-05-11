package com.ht.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ht.beans.Developer;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;
import com.ht.utility.GlobalResources;

public class DeveloperController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson=GlobalResources.getGson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		System.out.println("DeveloperController Started");
		HttpSession userSession=httpServletRequest.getSession();
		User user=(User)userSession.getAttribute("User");
		if(user!=null){
			String action=(String)httpServletRequest.getParameter("action");
			System.out.println("Got Action as : "+action);
			DevelopersDAO developersDAO = new DevelopersDAO();
			UserRolesDAO userRolesDAO = new UserRolesDAO();
			UserDAO userDAO = new UserDAO();
			if(action!=null){
				if(action.toLowerCase().equals("getalldeveloper")){
					ArrayList<Developer> developer = developersDAO.getAllDevelopers();
					JsonElement element = gson.toJsonTree(developer,new TypeToken<ArrayList<Developer>>(){}.getType());
					JsonObject jo = new JsonObject();
					jo.add("Developers",element);
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(jo.toString());
				}else if(action.toLowerCase().equals("create")){
					Developer developer=new Developer();
					developer.setName((String)httpServletRequest.getParameter("developerName"));
					developer.setCin((String)httpServletRequest.getParameter("cin"));
					developer.setOfficeAddress((String)httpServletRequest.getParameter("officeAddress"));
					developer.setOfficeContactNo((String)httpServletRequest.getParameter("officeContactNo"));
					developer.setOfficeContactPerson((String)httpServletRequest.getParameter("officeContactPerson"));
					developer.setOfficeEmail((String)httpServletRequest.getParameter("officeEmail"));
					developer.setSiteAddress((String)httpServletRequest.getParameter("siteAddress"));
					developer.setSiteContactNo((String)httpServletRequest.getParameter("siteContactNo"));
					developer.setSiteContactPerson((String)httpServletRequest.getParameter("siteContactPerson"));
					developer.setSiteEmail((String)httpServletRequest.getParameter("siteEmail"));
					developer.setUsername((String)httpServletRequest.getParameter("username"));
					
					Developer dev = developersDAO.getByUsername(developer.getUsername());
					User validUser = userDAO.getByUsername(developer.getUsername());
					JsonObject jo = new JsonObject();
					UserRoles userRoles = userRolesDAO.getByUsername(developer.getUsername()); 
					if(validUser != null && dev == null && userRoles.getRole().equalsIgnoreCase("developer")){
						boolean inserted=developersDAO.insert(developer);
						if(inserted){
							System.out.println("Developer inserted.");
							//result="{\"Result\":\"Success\",\"Message\":"+"\"Meter Readings Added Successfully!\""+"}";
							jo.addProperty("Result","Success");
							jo.addProperty("Message", "Developer Added Successfully!");
						}else{
							//result="{\"Result\":\"Failure\",\"Message\":"+"\"Unable to add Meter Reading! Please try again!\""+"}";
							jo.addProperty("Result","Failure");
							jo.addProperty("Message", "Unable to add Developer! Please try again!");
						}
					}else{
						System.out.println("Username is not available");
						jo.addProperty("Result","Failure");
						jo.addProperty("Message", "Wrong Username provided! Please try again with different user!");
					}
						System.out.println(jo.toString());
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jo.toString());
					
				}else{
						String error="{\"Result\":\"ERROR\",\"Message\":"+"Wrong Action"+"}";
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(error);
				}
			}
		}else{
			System.out.println("Backdoor Entry to DeveloperController!!!!");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
