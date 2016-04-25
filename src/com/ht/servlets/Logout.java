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
import com.ht.dao.UserDAO;
import com.ht.utility.GlobalResources;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson=GlobalResources.getGson();
    private UserDAO userDAO=GlobalResources.getUserDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logout Called");
		HttpSession userSession=request.getSession();
		userSession.removeAttribute("User");
		userSession.removeAttribute("UserRole");
		userSession.invalidate();
        System.out.println("User Logged Out.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
