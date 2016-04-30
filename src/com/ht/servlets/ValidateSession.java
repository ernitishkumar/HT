package com.ht.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.ht.beans.ErrorBean;
import com.ht.beans.UserRoles;
import com.ht.beans.Result;
import com.ht.beans.User;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;
import com.ht.utility.GlobalResources;
import org.json.simple.JSONObject;
public class ValidateSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson=GlobalResources.getGson();
    private UserDAO userDAO=GlobalResources.getUserDAO();
    private UserRolesDAO userRolesDAO = new UserRolesDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ValidateSession Called");
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null){
			ipAddress = request.getRemoteAddr();
		}
		System.out.println("Validating from IP : "+ipAddress);
		HttpSession userSession=request.getSession();
		User user=(User)userSession.getAttribute("User");
		JSONObject jsonObject=new JSONObject();
		if(user!=null){
		UserRoles userRole = (UserRoles)userSession.getAttribute("UserRole");
		jsonObject.put("username",user.getUsername());
		jsonObject.put("password","");
		jsonObject.put("name",user.getName());
		jsonObject.put("user_role",userRole.getRole());
		jsonObject.put("region",userRole.getRegion());
		jsonObject.put("circle",userRole.getCircle());
		jsonObject.put("division",userRole.getDivision());
		//System.out.println("ValidateSession output : "+jsonObject.toString());
		}else{
			System.out.println("Backdoor Entry!!!!! from IP : "+ipAddress);
			jsonObject.put("username",null);
		    jsonObject.put("password",null);
		    jsonObject.put("name",null);
			System.out.println("ValidateSession output : "+jsonObject.toString());
		}
        response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}