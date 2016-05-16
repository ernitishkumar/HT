package com.ht.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ht.beans.Investor;
import com.ht.beans.Machine;
import com.ht.beans.User;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.MachinesDAO;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;
import com.ht.utility.GlobalResources;

@WebServlet("/MachineController")
public class MachineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson=GlobalResources.getGson();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		System.out.println("DeveloperController Started");
		HttpSession userSession=httpServletRequest.getSession();
		User user=(User)userSession.getAttribute("User");
		
		if(user!=null){
			String action=(String)httpServletRequest.getParameter("action");
			System.out.println("Got Action as : "+action);
			JsonObject jo = new JsonObject();
			DevelopersDAO developersDAO = new DevelopersDAO();
			MachinesDAO machinesDAO = new MachinesDAO();
			if(action!=null){
				if(action.toLowerCase().equals("create")){
					Machine machine = new Machine();
					machine.setCode((String)httpServletRequest.getParameter("code"));
					machine.setCapacity((String)httpServletRequest.getParameter("capacity"));
					machine.setCommissionedDate((String)httpServletRequest.getParameter("commissionedDate"));
					machine.setActiveRate(Float.valueOf(httpServletRequest.getParameter("activeRate")));
					machine.setReactiveRate(Float.valueOf(httpServletRequest.getParameter("reactiveRate")));
					machine.setPpaLetterNo((String)httpServletRequest.getParameter("ppaLetterNo"));
					machine.setPpaDate((String)httpServletRequest.getParameter("ppaDate"));
					machine.setDeveloperId(Integer.parseInt(httpServletRequest.getParameter("developerId")));
					machine.setPlantId(Integer.parseInt(httpServletRequest.getParameter("plantId")));
					machine.setInvestorId(Integer.parseInt(httpServletRequest.getParameter("investorId")));
					boolean inserted =  machinesDAO.insert(machine);
					if(inserted){
						System.out.println("Machine inserted.");
						jo.addProperty("Result","Success");
						jo.addProperty("Message", "Machine Added Successfully!");
					}else{
						jo.addProperty("Result","Failure");
						jo.addProperty("Message", "Unable to add Machine! Please try again!");
					}
					System.out.println(jo.toString());
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(jo.toString());
				}else if(action.toLowerCase().equals("getall")){
					ArrayList<Machine> machines = machinesDAO.getAll();
					JsonElement element = gson.toJsonTree(machines,new TypeToken<ArrayList<Machine>>(){}.getType());
					jo.add("Machines",element);
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(jo.toString());
					System.out.println(jo.toString());
				}
			}
		}else{
			System.out.println("Backdoor Entry to MachineController!!!!");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
