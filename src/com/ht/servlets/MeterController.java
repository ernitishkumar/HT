package com.ht.servlets;
import java.io.IOException;
import java.util.ArrayList;

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
import com.ht.beans.User;
import com.ht.dao.MeterDetailsDAO;
import com.ht.utility.GlobalResources;

public class MeterController extends HttpServlet{
	private MeterDetailsDAO meterDetailsDAO = GlobalResources.getMeterDetailsDAO();
	private Gson gson=GlobalResources.getGson();

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		System.out.println("MeterController Started");
		HttpSession userSession=httpServletRequest.getSession();
		User user=(User)userSession.getAttribute("User");
		//System.out.println("User from session : "+user);
		if(user!=null){
			String action=(String)httpServletRequest.getParameter("action");
			System.out.println("Got Action : "+action);
			if(action!=null){
				JsonObject jo = new JsonObject();
				if(action.toLowerCase().equals("getall")){
					ArrayList<MeterDetails> meterDetails = meterDetailsDAO.getAll();
					JsonElement element = gson.toJsonTree(meterDetails,new TypeToken<ArrayList<MeterDetails>>(){}.getType());
					jo.add("Meters",element);
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().print(jo.toString());
					System.out.println(jo.toString());
					
				}else if(action.toLowerCase().equals("list")){

				}else if(action.toLowerCase().equals("create")||action.toLowerCase().equals("update")){
					if(action.toLowerCase().equals("create")){
						System.out.println("Comming in create");
						MeterDetails meterDetails = new MeterDetails();
						meterDetails.setMeterNo((String)httpServletRequest.getParameter("meterNo"));
						meterDetails.setMake((String)httpServletRequest.getParameter("make"));
						meterDetails.setCategory((String)httpServletRequest.getParameter("category"));
						meterDetails.setType((String)httpServletRequest.getParameter("type"));
						meterDetails.setMeterClass((String)httpServletRequest.getParameter("meterClass"));
						meterDetails.setCtr((String)httpServletRequest.getParameter("ctr"));
						meterDetails.setPtr((String)httpServletRequest.getParameter("ptr"));
						meterDetails.setMf(Integer.parseInt(httpServletRequest.getParameter("mf")));
						meterDetails.setEquipmemntClass((String)httpServletRequest.getParameter("equipmentClass"));
						meterDetails.setPhase((String)httpServletRequest.getParameter("phase"));
						meterDetails.setMeterGroup((String)httpServletRequest.getParameter("meterGroup"));
						//System.out.println("Data saving in DB as : "+meterNo+make+category+type+meterClass+ctr+ptr+mf+equipmentClass+phase+meterGroup);
						//MeterDetails meterDetails = new MeterDetails(meterNo,make,category,type,meterClass,ctr,ptr,mf,equipmentClass,phase,meterGroup);
						boolean inserted = meterDetailsDAO.insert(meterDetails);
						String result="";
						if(inserted){
							System.out.println("Meter Readings inserted.");
							result="{\"Result\":\"Success\",\"Message\":"+"\"Meter Added Successfully!\""+"}";
						}else{
							result="{\"Result\":\"Failure\",\"Message\":"+"\"Unable to add Meter Please try again!\""+"}";
						}
						httpServletResponse.setContentType("text/json");
						httpServletResponse.getWriter().write(result);
					}else if(action.toLowerCase().equals("update")){

					}
				}else if(action.toLowerCase().equals("getmetersnotinuse")){
					MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
					ArrayList<MeterDetails> meterDetails = meterDetailsDAO.getMetersNotInUse();
					
					JsonElement element = gson.toJsonTree(meterDetails,new TypeToken<ArrayList<MeterDetails>>(){}.getType());
					jo.add("MetersNotInUse",element);
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().print(jo.toString());
					System.out.println(jo.toString());
				}

			}else{
				//System.out.println("Error coming here");
				JSONObject error = new JSONObject();
				error.put("Result","Error");
				error.put("Message","Wrong Action");
				httpServletResponse.getWriter().print(error.toString());

			}
		}else{
			System.out.println("Back door entry to MeterController !!!");
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
