package com.ht.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import com.ht.dao.PlantsDAO;
import com.ht.dao.MeterDetailsDAO;
import com.ht.beans.*;
import com.ht.utility.GlobalResources;
import com.google.gson.Gson;

public class PlantController extends HttpServlet{
	private MeterDetailsDAO meterDetailsDAO = GlobalResources.getMeterDetailsDAO();
	private Gson gson=GlobalResources.getGson();

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		System.out.println("PlantController Started");
		HttpSession userSession=httpServletRequest.getSession();
		User user=(User)userSession.getAttribute("User");
		if(user!=null){
			String action=(String)httpServletRequest.getParameter("action");
			System.out.println("Got Action as : "+action);
			JSONObject obj = new JSONObject();
			if(action!=null){
				if(action.toLowerCase().equals("get")){
					String meterno=(String)httpServletRequest.getParameter("meterno");
					System.out.println("Inside if for get with meter : "+meterno);
					MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterno);
					PlantsDAO plantsDAO=new PlantsDAO();

					Plant plant = plantsDAO.getByMainMeterNo(meterno);
					if(meterDetails!=null && plant!=null){
						obj.put("ValidMeter","Yes");
						JSONObject obj1 = new JSONObject();
						obj1.put("MeterNo",plant.getMainMeterNo());
						obj1.put("Name",plant.getCircle());
						obj1.put("MF",meterDetails.getMf());
						obj.put("MeterData",obj1);    
					}else{
						obj.put("ValidMeter","No");
						obj.put("MeterData",null);
					}

					System.out.println("Sent String is :"+obj.toString());
					httpServletResponse.setContentType("text/json");
					httpServletResponse.getWriter().print(obj.toString());
				}else if(action.toLowerCase().equals("list")){

				}else if(action.toLowerCase().equals("create")||action.toLowerCase().equals("update")){

					if(action.toLowerCase().equals("create")){

					}else if(action.toLowerCase().equals("update")){

					}
				}else if(action.toLowerCase().equals("delete")){

				}

			}else{
				//System.out.println("Error coming here");
				//String error="{\"Result\":\"ERROR\",\"Message\":"+"Wrong Action"+"}";
				JSONObject error = new JSONObject();
				error.put("Result","Error");
				error.put("Message","Wrong Action");
				httpServletResponse.getWriter().print(error.toString());
			}
		}else{
			System.out.println("Back door entry to PlantController !!!!");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	} 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
