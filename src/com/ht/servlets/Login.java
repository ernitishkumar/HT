package com.ht.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.ht.beans.ErrorBean;
import com.ht.beans.Result;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.UserRolesDAO;
import com.ht.dao.UserDAO;
import com.ht.utility.GlobalResources;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson=GlobalResources.getGson();
    private UserDAO userDAO=GlobalResources.getUserDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login Called");
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null){
			ipAddress = request.getRemoteAddr();
		}
		System.out.println("Requesting from IP : "+ipAddress);
		
		UserRolesDAO userRolesDAO = new UserRolesDAO();
		UserRoles userRoles = null;
		String username=(String)request.getParameter("username");
		String password=(String)request.getParameter("password");
        Result result=new Result();
        HttpSession userSession=request.getSession();
		if(username!=null && password!=null){
			User user=userDAO.getByUsername(username);
			if(user!=null && password.trim().equals(user.getPassword())){
				System.out.println("Login Successfull : "+user.getName());
				userRoles = userRolesDAO.getByUsername(username);
				user.setPassword("");
                userSession.setAttribute("User",user);
				userSession.setAttribute("UserRole",userRoles);
				result.setLoginResult("Success");
				result.setUser(user);
				result.setUserRoles(userRoles);
			}else{
				result.setLoginResult("Failure");
			}
		}else{
			result.setLoginResult("Failure");
		}
		System.out.println(gson.toJson(result));
		response.setContentType("text/json");
		response.getWriter().write(gson.toJson(result));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
