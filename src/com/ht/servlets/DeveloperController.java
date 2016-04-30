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
import com.ht.dao.DevelopersDAO;
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
			if(action!=null){
				if(action.toLowerCase().equals("getalldeveloper")){
					DevelopersDAO developersDAO = new DevelopersDAO();
					ArrayList<Developer> developer = developersDAO.getAllDevelopers();
					JsonElement element = gson.toJsonTree(developer,new TypeToken<ArrayList<Developer>>(){}.getType());
					JsonObject jo = new JsonObject();
					jo.add("AllDevelopers",element);
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(jo.toString());
				}
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
