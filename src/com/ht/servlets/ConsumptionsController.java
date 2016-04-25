package com.ht.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import com.ht.utility.GlobalResources;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.google.gson.*;
import com.google.gson.reflect.*;
import org.json.simple.JSONObject;

import com.ht.dao.*;
import com.ht.beans.*;

public class ConsumptionsController extends HttpServlet{
	private Gson gson=GlobalResources.getGson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		try{
			System.out.println("ConsumptionsController Started");
			HttpSession userSession=httpServletRequest.getSession();
			User user=(User)userSession.getAttribute("User");
			if(user!=null){
				String action=(String)httpServletRequest.getParameter("action");
				System.out.println("Got Action : "+action);
				if(action!=null){
					
					ConsumptionsDAO consumptionsDAO=new ConsumptionsDAO();
					if(action.toLowerCase().equals("insert")){
						System.out.println("Comming in insert");
						String meterNo=(String)httpServletRequest.getParameter("meterNo");
						String activeConsumption=(String)httpServletRequest.getParameter("activeConsumption");
						String reactiveConsumption=(String)httpServletRequest.getParameter("reactiveConsumption");
						String plantId=(String)httpServletRequest.getParameter("plantId");
						String plantCode=(String)httpServletRequest.getParameter("plantCode");
						String meterReadingId=(String)httpServletRequest.getParameter("meterReadingId");

						System.out.println("Consumption data from front end : "+meterNo+" "+activeConsumption+" "+reactiveConsumption+" "+plantId+" "+plantCode+" "+meterReadingId);

						SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
						Date date = new Date();
						String currentDate = formater.format(date);

						boolean inserted=false;

						if(meterNo!=null && activeConsumption!=null && reactiveConsumption!=null && plantId!=null && plantCode!=null && meterReadingId!=null){
							Consumption consumption =new Consumption();
							consumption.setMeterNo(meterNo.trim());
							consumption.setDate(currentDate);
							consumption.setActiveConsumption(Float.parseFloat(activeConsumption.trim()));
							consumption.setReactiveConsumption(Float.parseFloat(reactiveConsumption.trim()));
							consumption.setPlantId(Integer.parseInt(plantId.trim()));
							consumption.setPlantCode(plantCode.trim());
							consumption.setMeterReadingId(Integer.parseInt(meterReadingId));
							inserted=consumptionsDAO.insert(consumption);    
						}

						JSONObject jsonObject=new JSONObject();
						if(inserted){
							System.out.println("Meter Readings inserted.");
							//result="{\"Result\":\"Success\",\"Message\":"+"\"Consumptions Added Successfully!\""+"}";
							jsonObject.put("Result","Success");
							jsonObject.put("Message","Consumptions Added Successfully!");
						}else{
							//result="{\"Result\":\"Failure\",\"Message\":"+"\"Unable to add Consumptions! Please try again!\""+"}";
							jsonObject.put("Result","Failure");
							jsonObject.put("Message","Unable to add Consumptions! Please try again!");
						}
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jsonObject.toString());
					}else if(action.toLowerCase().equals("getconsumption")){
						System.out.println("Comming in get");
						String meterNo=(String)httpServletRequest.getParameter("meterNo");
						String plantId=(String)httpServletRequest.getParameter("plantId");
						SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
						Date date = new Date();
						String currentDate = formater.format(date);
						Consumption consumption=null;
						if(meterNo!=null){
							consumption=consumptionsDAO.getByMeterNoAndDate(meterNo.trim(),currentDate);
						}else if(plantId!=null){
							consumption=consumptionsDAO.getByPlantIdAndDate(Integer.parseInt(plantId.trim()),currentDate);
						}
						JsonElement element = gson.toJsonTree(consumption,new TypeToken<Consumption>(){}.getType());
						JsonObject jo=new JsonObject();
						if(consumption!=null){
							jo.addProperty("Result", "OK");
							jo.add("Consumption",element);
						}else{
							jo.addProperty("Result", "Failure");
						}
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jo.toString());
					}
					else if(action.toLowerCase().equals("updatebifercationflag")){
						System.out.println("Comming in to updateBifercationFlag");
						String flag=(String)httpServletRequest.getParameter("flag");
						System.out.println("Flag received : "+ flag);
						String consumptionId=(String)httpServletRequest.getParameter("id");
						System.out.println("Id received : "+ consumptionId);

						boolean result = consumptionsDAO.updateConsumptionBifercated(Integer.parseInt(flag),Integer.parseInt(consumptionId));
						//JsonElement element = gson.toJsonTree(consumption,new TypeToken<Consumption>(){}.getType());
						JsonObject jo=new JsonObject();
						if(result == true){
							jo.addProperty("Result", "OK");
						}else{
							jo.addProperty("Result", "Failure");
						}
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jo.toString());
					}
				}else{
					//System.out.println("Error coming here");
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("Result","ERROR");
					jsonObject.put("Message","Wrong Action");
					//String error="{\"Result\":\"ERROR\",\"Message\":"+"Wrong Action"+"}";
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(jsonObject.toString());
				}
			}else{
				System.out.println("Back door entry to ConsumptionsController !!!");
			}
		}catch(Exception e){
			System.out.println("Exception in class : MeterReadingsController method: processRequest "+e);
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("Result","ERROR");
			jsonObject.put("Message","Wrong Action");
			//String error="{\"Result\":\"ERROR\",\"Message\":"+"Wrong Action"+"}";
			httpServletResponse.setContentType("application/json");
			httpServletResponse.getWriter().write(jsonObject.toString());
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
