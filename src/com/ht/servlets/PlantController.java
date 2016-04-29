package com.ht.servlets;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ht.beans.MeterDetails;
import com.ht.beans.MeterReadings;
import com.ht.beans.Plant;
import com.ht.beans.User;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.PlantsDAO;
import com.ht.utility.GlobalResources;

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
			if(action!=null){
				if(action.toLowerCase().equals("get")){
					String meterno=(String)httpServletRequest.getParameter("meterno");
					System.out.println("Inside if for get with meter : "+meterno);
					MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterno);
					PlantsDAO plantsDAO=new PlantsDAO();
					Plant plant = plantsDAO.getByMainMeterNo(meterno);
					JsonObject jo = new JsonObject();
					if(meterDetails!=null && plant!=null){
						MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();
						MeterReadings meterReadings = meterReadingsDAO.getLatestInsertedByMeterNo(meterno);
						JsonElement element = gson.toJsonTree(meterReadings,new TypeToken<MeterReadings>(){}.getType());
						jo.addProperty("ValidMeter","Yes");
						JsonObject meterData = new JsonObject();
						meterData.addProperty("MeterNo", plant.getMainMeterNo());
						meterData.addProperty("Name",plant.getCircle());
						meterData.addProperty("MF",meterDetails.getMf());
						jo.add("MeterData",meterData);
						jo.add("LastReading", element);
					}else{
						jo.addProperty("ValidMeter","No");
						jo.add("MeterData",null);
					}
					//System.out.println("Sent String is :"+jo.toString());
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().print(jo.toString());
				}else if(action.toLowerCase().equals("list")){

				}else if(action.toLowerCase().equals("create")||action.toLowerCase().equals("update")){

					if(action.toLowerCase().equals("create")){

					}else if(action.toLowerCase().equals("update")){

					}
				}else if(action.toLowerCase().equals("delete")){

				}

			}else{
				JsonObject error = new JsonObject();
				error.addProperty("Result","Error");
				error.addProperty("Message","Wrong Action");
				httpServletResponse.setContentType("application/json");
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
