package com.ht.servlets;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ht.beans.Consumption;
import com.ht.beans.ConsumptionBifurcationView;
import com.ht.beans.Investor;
import com.ht.beans.InvestorConsumption;
import com.ht.beans.InvestorConsumptionView;
import com.ht.beans.Plant;
import com.ht.beans.User;
import com.ht.dao.BillDetailsDAO;
import com.ht.dao.ConsumptionsDAO;
import com.ht.dao.InvestorConsumptionDAO;
import com.ht.dao.InvestorsDAO;
import com.ht.dao.MachinesDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.PlantsDAO;
import com.ht.utility.GlobalResources;

public class InvestorConsumptionController extends HttpServlet{
	private Gson gson=GlobalResources.getGson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		try{
			System.out.println("InvestorConsumptionController Started");
			HttpSession userSession=httpServletRequest.getSession();
			User user=(User)userSession.getAttribute("User");
			if(user!=null){
				String action=(String)httpServletRequest.getParameter("action");
				System.out.println("Got Action : "+action);
				if(action!=null){
					ConsumptionsDAO consumptionsDAO=new ConsumptionsDAO();
					InvestorConsumptionDAO investorConsumptionDAO = new InvestorConsumptionDAO();
					if(action.toLowerCase().equals("get")){
						String consumptionId = (String)httpServletRequest.getParameter("consumptionId");
						System.out.println("consumption id is : "+consumptionId);
						ArrayList<InvestorConsumption> consumptionList= investorConsumptionDAO.getByConsumptionId(Integer.parseInt(consumptionId));
						System.out.println("active consumption : "+consumptionList.get(0).getActiveConsumption());
						JsonElement element = gson.toJsonTree(consumptionList,new TypeToken<ArrayList<InvestorConsumption>>(){}.getType());
						JsonObject jo=new JsonObject();
						jo.add("investorConsumption",element);
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jo.toString());
					}if(action.toLowerCase().equals("getinvestorconsumption")){
						System.out.println("Comming in getinvestorconsumption");
						String circle =(String)httpServletRequest.getParameter("circle");
						String meterNo=(String)httpServletRequest.getParameter("meterNo");
						String plantId=(String)httpServletRequest.getParameter("plantId");
						
						System.out.println("InvestorConsumptionController Circle is "+circle);
						
						SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
						Date date = new Date();
						String currentDate = formater.format(date);
						JsonObject jo=new JsonObject();
						if(circle!=null){
							//Getting Investor Wise Consumptions for all the meters under a circle
							//for validation by circle user
							System.out.println("InvestorConsumptionController Circle is "+circle);
							PlantsDAO plantsDAO=new PlantsDAO();
							ArrayList<Plant> plants = plantsDAO.getByCircle(circle.toLowerCase());
							ArrayList<ConsumptionBifurcationView> bifurcations = new ArrayList<ConsumptionBifurcationView>();
							for(Plant plant : plants){
								ConsumptionBifurcationView cbv = new ConsumptionBifurcationView();
								cbv.setMeterNo(plant.getMainMeterNo());
								cbv.setPlant(plant);
								Consumption consumption=consumptionsDAO.getByPlantIdAndDate(plant.getId(),currentDate);
								if(consumption != null){
									ArrayList<InvestorConsumption> investorConsumptionList = investorConsumptionDAO.getByConsumptionId(consumption.getId());
									ArrayList<InvestorConsumptionView> investorConsumptionViews= getViewFromList(investorConsumptionList, consumption);
									cbv.setConsumption(consumption);
									cbv.setInvestorBifurcations(investorConsumptionViews);
								}else{
									continue;
								}
								bifurcations.add(cbv);
							}
							JsonElement element = gson.toJsonTree(bifurcations,new TypeToken<ArrayList<ConsumptionBifurcationView>>(){}.getType());
							if(bifurcations.size()>0){
								jo.addProperty("Result", "OK");
								jo.add("MeterConsumptionData",element);
							}else{
								jo.addProperty("Result", "Failure");
							}
						}else{
							//Getting Investor Wise Consumption for viewing on developers bifurcation page
							Consumption consumption=null;
							if(meterNo!=null){
								consumption=consumptionsDAO.getByMeterNoAndDate(meterNo.trim(),currentDate);
							}else if(plantId!=null){
								consumption=consumptionsDAO.getByPlantIdAndDate(Integer.parseInt(plantId.trim()),currentDate);
							}
							ArrayList<InvestorConsumption> investorConsumptionList = investorConsumptionDAO.getByConsumptionId(consumption.getId());
							ArrayList<InvestorConsumptionView> investorConsumptionViews= getViewFromList(investorConsumptionList, consumption);
							
							JsonElement element = gson.toJsonTree(investorConsumptionViews,new TypeToken<ArrayList<InvestorConsumptionView>>(){}.getType());
							
							if(consumption!=null){
								jo.addProperty("Result", "OK");
								jo.add("InvestorConsumptionData",element);
							}else{
								jo.addProperty("Result", "Failure");
							}
							//System.out.println(jo.toString());
						}
						//Sending final response
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jo.toString());
					}if(action.toLowerCase().equals("validate_consumption")){
						System.out.println("Comming in validate_consumption");
						String consumptionId = (String) httpServletRequest.getParameter("consumptionId");
						boolean updated = false;
						if(consumptionId != null){
							ArrayList<InvestorConsumption> investorConsumptions = investorConsumptionDAO.getByConsumptionId(Integer.parseInt(consumptionId.trim()));
							for(InvestorConsumption ic : investorConsumptions){
								ic.setCircleValidation(1);
								updated = investorConsumptionDAO.update(ic);
							}
							Consumption consumption = consumptionsDAO.getById(Integer.parseInt(consumptionId.trim()));
							MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();
							updated = meterReadingsDAO.updateCircleCellValidation(consumption.getMeterReadingId(),1);
						}
						JsonObject jo=new JsonObject();
						if(updated){
							jo.addProperty("Result", "VALIDATED");
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
				
		} else {
			System.out.println("Back door entry to InvestorConsumptionController !!!!");
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
	System.out.println("InvestorConsumptionController Started for POST");
	try{
		BufferedReader reader=request.getReader();
		String line=reader.readLine();
		System.out.println("Data from reader : "+line);
		JsonParser parser=new JsonParser();
		JsonElement jasonElement=parser.parse(line);
		JsonArray jasonArray = jasonElement.getAsJsonArray();
		int length=jasonArray.size();
		Gson gson =new Gson();
		InvestorConsumptionDAO investorConsumptionDAO=new InvestorConsumptionDAO();
		boolean inserted=false;
		for(int i=0;i<length;i++){
			JsonElement je=jasonArray.get(i);
			InvestorConsumption ic=gson.fromJson(je,InvestorConsumption.class);
			System.out.println("Investor consumption data : "+ic.getConsumptionId()+" "+ic.getInvestorId()+" "+ic.getActiveConsumption()+" "+ic.getReactiveConsumption()+" circle validation : "+ic.getCircleValidation()+" bill generated : "+ic.getBillGenerated());
			inserted = investorConsumptionDAO.insert(ic);
		}	
		JSONObject jsonObject=new JSONObject();
		if(inserted){
			jsonObject.put("Result","OK");
			jsonObject.put("Message","Consumptions Inserted");
		}else{
			jsonObject.put("Result","Failure");
			jsonObject.put("Message","Unable to insert consumptions try again.");
		}
		response.setContentType("application/json");
		response.getWriter().write(jsonObject.toString());
	}catch(Exception e){
		e.printStackTrace();
	}
}

public ArrayList<InvestorConsumptionView> getViewFromList(ArrayList<InvestorConsumption> investorConsumptionList,Consumption consumption){
	ArrayList<InvestorConsumptionView> investorConsumptionViews= new ArrayList<InvestorConsumptionView>();
	InvestorsDAO investorsDAO = new InvestorsDAO();
	MachinesDAO machinesDAO = new MachinesDAO();
	BillDetailsDAO billDetailsDAO = new BillDetailsDAO();
	
	for(InvestorConsumption investorConsumption:investorConsumptionList){
		InvestorConsumptionView investorConsumptionView = new InvestorConsumptionView();
		investorConsumptionView.setId(investorConsumption.getId());
		investorConsumptionView.setConsumption(consumption);
		investorConsumptionView.setActiveConsumption(investorConsumption.getActiveConsumption());
		investorConsumptionView.setReactiveConsumption(investorConsumption.getReactiveConsumption());
		Investor investor = investorsDAO.getById(investorConsumption.getInvestorId());
		System.out.println("Investor ID and name : "+investor.getId()+" "+investor.getName());
		investorConsumptionView.setInvestor(investor);
		investorConsumptionView.setMachines(machinesDAO.getByInvestorId(investor.getId()));
		investorConsumptionView.setCircleValidation(investorConsumption.getCircleValidation());
		investorConsumptionView.setBillGenerated(investorConsumption.getBillGenerated());
		if(investorConsumption.getBillGenerated()==1){
			investorConsumptionView.setBillDetailsId(billDetailsDAO.getByConsumptionBifurcationId(investorConsumption.getId()).getId());
		}
		investorConsumptionViews.add(investorConsumptionView);
	}
	return investorConsumptionViews;
}
}
