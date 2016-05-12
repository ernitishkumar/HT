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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ht.beans.Investor;
import com.ht.beans.InvestorConsumptionView;
import com.ht.beans.InvestorPlantMapping;
import com.ht.beans.User;
import com.ht.dao.InvestorPlantMappingDAO;
import com.ht.dao.InvestorsDAO;
import com.ht.dao.MachinesDAO;
import com.ht.utility.GlobalResources;

public class InvestorController extends HttpServlet{
	private Gson gson=GlobalResources.getGson();

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		System.out.println("Investor Controller Started");
		HttpSession userSession=httpServletRequest.getSession();
		User user=(User)userSession.getAttribute("User");
		JsonObject jo=new JsonObject();
		if(user!=null){
			String action=(String)httpServletRequest.getParameter("action");
			System.out.println("Got Action as : "+action);
			JSONObject jsonObject = new JSONObject();
			if(action!=null){
				InvestorPlantMappingDAO investorPlantMappingDAO=new InvestorPlantMappingDAO();
				InvestorsDAO investorsDAO = new InvestorsDAO();
				MachinesDAO machinesDAO = new MachinesDAO();
				if(action.toLowerCase().equals("getinvestorforbifurcation")){
					String plantId=(String)httpServletRequest.getParameter("plantId");
					//System.out.println("Inside if for get with plant id : "+plantId);
					if(plantId!=null){
						
						ArrayList<InvestorPlantMapping> investorPlantMappings = investorPlantMappingDAO.getByPlantId(Integer.parseInt(plantId.trim()));
						
						ArrayList<InvestorConsumptionView> investors=new ArrayList<InvestorConsumptionView>();
						for(InvestorPlantMapping ipm:investorPlantMappings){
							Investor investor=investorsDAO.getById(ipm.getInvestorId());
							InvestorConsumptionView investorConsumptionView = new InvestorConsumptionView();
							investorConsumptionView.setInvestor(investor);
							investorConsumptionView.setMachines(machinesDAO.getByInvestorId(investor.getId()));
							investors.add(investorConsumptionView);
						}
						JsonElement element = gson.toJsonTree(investors,new TypeToken<ArrayList<InvestorConsumptionView>>(){}.getType());
						JsonArray jsonArray = element.getAsJsonArray();
						
						jo.addProperty("Result", "OK");
						jo.add("InvestorsData",jsonArray);
						System.out.print("getinvestorforbifurcation : "+jo.toString());
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().print(jo.toString());    
					}
				}else if(action.toLowerCase().equals("create")){
					Investor investor = new Investor();
					investor.setName((String)httpServletRequest.getParameter("name"));
					investor.setCode((String)httpServletRequest.getParameter("code"));
					investor.setCin((String)httpServletRequest.getParameter("cin"));
					investor.setTin((String)httpServletRequest.getParameter("tin"));
					investor.setVat((String)httpServletRequest.getParameter("vat"));
					investor.setInvoiceNo((String)httpServletRequest.getParameter("invoiceNo"));
					investor.setOfficeAddress((String)httpServletRequest.getParameter("officeAddress"));
					investor.setOfficeContactNo((String)httpServletRequest.getParameter("officeContactNo"));
					investor.setOfficeContactPerson((String)httpServletRequest.getParameter("officeContactPerson"));
					investor.setOfficeEmail((String)httpServletRequest.getParameter("officeEmail"));
					investor.setSiteAddress((String)httpServletRequest.getParameter("siteAddress"));
					investor.setSiteContactNo((String)httpServletRequest.getParameter("siteContactNo"));
					investor.setSiteContactPerson((String)httpServletRequest.getParameter("siteContactPerson"));
					investor.setSiteEmail((String)httpServletRequest.getParameter("siteEmail"));
					boolean inserted = investorsDAO.insert(investor);
					if(inserted){
						System.out.println("Investor inserted.");
						//result="{\"Result\":\"Success\",\"Message\":"+"\"Meter Readings Added Successfully!\""+"}";
						jo.addProperty("Result","Success");
						jo.addProperty("Message", "Investor Added Successfully!");
					}else{
						jo.addProperty("Result","Failure");
						jo.addProperty("Message", "Unable to add Investor! Please try again!");
					}
					System.out.println(jo.toString());
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(jo.toString());
				}else if(action.toLowerCase().equals("getbydeveloperid")){
					int plantId = Integer.parseInt(httpServletRequest.getParameter("plantId"));
					ArrayList<InvestorPlantMapping> investorPlantList = investorPlantMappingDAO.getByPlantId(plantId);  
					JsonElement element = gson.toJsonTree(investorPlantList,new TypeToken<ArrayList<InvestorPlantMapping>>(){}.getType());
					jo.add("Investors",element);
					System.out.println(jo.toString());
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(jo.toString());
					
				}else{
					
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
			System.out.println("Backdoor entry to Investor Controller !!!");
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
