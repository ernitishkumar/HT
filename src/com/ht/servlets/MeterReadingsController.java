package com.ht.servlets;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import com.ht.dao.MeterReadingsDAO;
import com.ht.beans.MeterReadings;
import com.ht.utility.GlobalResources;
import com.ht.beans.ViewMeterReadings;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.google.gson.*;
import com.google.gson.reflect.*;
import org.json.simple.JSONObject;
import com.ht.dao.DevelopersDAO;
import com.ht.beans.Developer;
import com.ht.dao.PlantsDAO;
import com.ht.dao.ConsumptionsDAO;
import com.ht.beans.Plant;
import com.ht.beans.User;

public class MeterReadingsController extends HttpServlet{

	private MeterReadingsDAO readingsDAO=GlobalResources.getMeterReadingsDAO();
	private Gson gson=GlobalResources.getGson();

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		try{
			System.out.println("MeterReadings Controller Started");
			HttpSession userSession=httpServletRequest.getSession();
			User user=(User)userSession.getAttribute("User");
			if(user!=null){
				String action=(String)httpServletRequest.getParameter("action");
				System.out.println("Got Action : "+action);
				if(action!=null){
					if(action.toLowerCase().equals("create")){
						System.out.println("Comming in create");
						MeterReadings readings=new MeterReadings();
						readings.setMeterNo((String)httpServletRequest.getParameter("meterno"));
						readings.setMf(Integer.parseInt(httpServletRequest.getParameter("mf")));
						readings.setReadingDate((String)httpServletRequest.getParameter("readingDate"));
						readings.setActiveEnergy(Float.valueOf(httpServletRequest.getParameter("activeEnergy")));
						readings.setActiveTodOne(Float.valueOf(httpServletRequest.getParameter("activeTodOne")));
						readings.setActiveTodTwo(Float.valueOf(httpServletRequest.getParameter("activeTodTwo")));
						readings.setActiveTodThree(Float.valueOf(httpServletRequest.getParameter("activeTodThree")));
						readings.setReactiveQuadrantOne(Float.valueOf(httpServletRequest.getParameter("reactiveTodOne")));
						readings.setReactiveQuadrantTwo(Float.valueOf(httpServletRequest.getParameter("reactiveTodTwo")));
						readings.setReactiveQuadrantThree(Float.valueOf(httpServletRequest.getParameter("reactiveTodThree")));
						readings.setReactiveQuadrantFour(Float.valueOf(httpServletRequest.getParameter("reactiveTodFour")));
						boolean inserted=readingsDAO.insert(readings);
						String result="";
						if(inserted){
							System.out.println("Meter Readings inserted.");
							result="{\"Result\":\"Success\",\"Message\":"+"\"Meter Readings Added Successfully!\""+"}";
						}else{
							result="{\"Result\":\"Failure\",\"Message\":"+"\"Unable to add Meter Reading! Please try again!\""+"}";
						}
						httpServletResponse.setContentType("text/json");
						httpServletResponse.getWriter().write(result);
					}else if(action.toLowerCase().equals("get")){

					}else if(action.toLowerCase().equals("list")){
						ArrayList<ViewMeterReadings> meterReadingsArray = new ArrayList<ViewMeterReadings>();
						ArrayList<Plant> plants=null;
						String circle=(String)httpServletRequest.getParameter("location");
						System.out.println("Circle is "+circle);
						PlantsDAO plantsDAO=new PlantsDAO();
						if(circle!=null){
							plants = plantsDAO.getByCircle(circle.toLowerCase());
						}else{
							plants = plantsDAO.getAll();
						}
						SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
						Date date = new Date();
						String currentDate = formater.format(date);
						for(Plant p:plants){
							ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
							String meterNo=p.getMainMeterNo();
							viewMeterReadings.setMeterNo(meterNo);
							viewMeterReadings.setPlant(p);
							viewMeterReadings.setCurrentMeterReading(readingsDAO.getCurrentMonthMeterReadings(meterNo, currentDate));
							viewMeterReadings.setPreviousMeterReading(readingsDAO.getPreviousMonthMeterReadings(meterNo, currentDate));
							meterReadingsArray.add(viewMeterReadings);
						}
						JsonElement element = gson.toJsonTree(meterReadingsArray,new TypeToken<ArrayList<ViewMeterReadings>>(){}.getType());
						JsonArray jsonArray = element.getAsJsonArray();
						JsonObject jo=new JsonObject();
						String listData=jsonArray.toString();
						//System.out.println("List of readings in json : "+listData); //remove after testing
						String json="{\"Result\":\"OK\",\"Records\":"+listData+"}";
						//System.out.println("Sending Json response as : "+json);
						jo.addProperty("Result","OK");
						jo.add("Records",jsonArray);
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jo.toString());
					}else if(action.toLowerCase().equals("validatereading")){
						System.out.println("Comming in validateReading");
						String role=(String)httpServletRequest.getParameter("role");
						String readingId=(String)httpServletRequest.getParameter("readingId");
						String result="";
						boolean validated=false;
						System.out.println("Reading ID : "+readingId+"  and role : "+role);
						if(role!=null){
							int id=Integer.parseInt(readingId);
							if(role.equalsIgnoreCase("htcell")||role.equalsIgnoreCase("admin")){
								System.out.println("Updating id "+id);
								validated=readingsDAO.updateHTCellValidation(id,1);
							}else if(role.equalsIgnoreCase("circle")){
								System.out.println("Updating id "+id);
								validated=readingsDAO.updateCircleCellValidation(id,1);
								ConsumptionsDAO consumptionsDAO = new ConsumptionsDAO();
							}
							else if(role.equalsIgnoreCase("developer")){
								System.out.println("Updating id "+id);
								validated=readingsDAO.updateDeveloperValidation(id,1); 
							}
						}
						JSONObject jsonObject=new JSONObject();
						if(validated){
							jsonObject.put("Result","Success");
						}else{
							jsonObject.put("Result","Failure");
							jsonObject.put("Message","Unable to validate Meter Reading! Please try again!");
						}
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jsonObject.toString());
					}else if(action.toLowerCase().equals("listdeveloperreadings")){
						String username=(String)httpServletRequest.getParameter("username");
						System.out.println("Fetching readings for developer : "+username);
						String meterNo=(String)httpServletRequest.getParameter("meterNo");
						if(username!=null){
							DevelopersDAO developersDAO=new DevelopersDAO();
							Developer developer=developersDAO.getByUsername(username);
							if(developer!=null){
								PlantsDAO plantsDAO=new PlantsDAO();
								ConsumptionsDAO consumptionsDAO = new ConsumptionsDAO();
								ArrayList<Plant> plants=plantsDAO.getByDeveloperId(developer.getId());  
								SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
								Date date = new Date();
								String currentDate = formater.format(date);
								ArrayList<ViewMeterReadings> meterReadings = new ArrayList<ViewMeterReadings>();
								for(Plant p:plants){
									ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
									String mmn=p.getMainMeterNo();
									viewMeterReadings.setMeterNo(mmn);
									viewMeterReadings.setPlant(p);
									MeterReadings currentMeterReadings = readingsDAO.getCurrentMonthMeterReadings(mmn, currentDate);
									viewMeterReadings.setCurrentMeterReading(readingsDAO.getCurrentMonthMeterReadings(mmn, currentDate));

									viewMeterReadings.setPreviousMeterReading(readingsDAO.getPreviousMonthMeterReadings(mmn, currentDate));
									System.out.println("plant id : "+p.getId());
									System.out.println("developer validation : "+currentMeterReadings.getDeveloperValidation());
									if(currentMeterReadings.getDeveloperValidation() == 1){
										System.out.println("inside if");
										viewMeterReadings.setConsumption(consumptionsDAO.getBifercationFlagByPlantIdAndDate(p.getId(),currentDate));
										System.out.println(consumptionsDAO.getBifercationFlagByPlantIdAndDate(p.getId(),currentDate).getId());
									}
									meterReadings.add(viewMeterReadings);  
								}
								JsonElement element = gson.toJsonTree(meterReadings,new TypeToken<ArrayList<ViewMeterReadings>>(){}.getType());
								JsonArray jsonArray = element.getAsJsonArray();
								String listData=jsonArray.toString();
								//System.out.println("List of readings in json : "+listData); //remove after testing
								String json="{\"Result\":\"OK\",\"Records\":"+listData+"}";
								//System.out.println("Sending Json response as : "+json);
								httpServletResponse.setContentType("application/json");
								httpServletResponse.getWriter().write(json);
							}
						}else if(meterNo!=null){
							PlantsDAO plantsDAO=new PlantsDAO();  
							Plant plant=plantsDAO.getByMainMeterNo(meterNo.trim());  
							SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
							Date date = new Date();
							String currentDate = formater.format(date);

							ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
							viewMeterReadings.setMeterNo(plant.getMainMeterNo());
							viewMeterReadings.setPlant(plant);
							viewMeterReadings.setCurrentMeterReading(readingsDAO.getCurrentMonthMeterReadings(plant.getMainMeterNo(), currentDate));
							viewMeterReadings.setPreviousMeterReading(readingsDAO.getPreviousMonthMeterReadings(plant.getMainMeterNo(), currentDate));

							JsonElement element = gson.toJsonTree(viewMeterReadings,new TypeToken<ViewMeterReadings>(){}.getType());

							String listData=element.toString();
							//System.out.println("List of readings in json : "+listData); //remove after testing
							String json="{\"Result\":\"OK\",\"Record\":"+listData+"}";
							//System.out.println("Sending Json response as : "+json);
							httpServletResponse.setContentType("application/json");
							httpServletResponse.getWriter().write(json);
						}
					}
				}else{
					//System.out.println("Error coming here");
					String error="{\"Result\":\"ERROR\",\"Message\":"+"Wrong Action"+"}";
					httpServletResponse.setContentType("application/json");
					httpServletResponse.getWriter().write(error);
				}
			}else{
				System.out.println("Backdoor Entry to MeterReadingsController!!!!");
			}
		}catch(Exception e){
			System.out.println("Exception in class : MeterReadingsController method: processRequest "+e);
			e.printStackTrace();
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
