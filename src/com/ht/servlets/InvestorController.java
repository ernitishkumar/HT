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
import com.ht.dao.InvestorsDAO;
import com.ht.dao.InvestorPlantMappingDAO;
import com.ht.beans.*;
import com.ht.utility.GlobalResources;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class InvestorController extends HttpServlet{
	private Gson gson=GlobalResources.getGson();

	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		System.out.println("Investor Controller Started");
		HttpSession userSession=httpServletRequest.getSession();
		User user=(User)userSession.getAttribute("User");
		if(user!=null){
			String action=(String)httpServletRequest.getParameter("action");
			System.out.println("Got Action as : "+action);
			JSONObject jsonObject = new JSONObject();
			if(action!=null){
				if(action.toLowerCase().equals("get")){
					String plantId=(String)httpServletRequest.getParameter("plantId");
					System.out.println("Inside if for get with plant id : "+plantId);
					if(plantId!=null){
						InvestorPlantMappingDAO investorPlantMappingDAO=new InvestorPlantMappingDAO();
						ArrayList<InvestorPlantMapping> investorPlantMappings = investorPlantMappingDAO.getByPlantId(Integer.parseInt(plantId.trim()));
						InvestorsDAO investorsDAO = new InvestorsDAO();
						BifircationData bifircationData=new BifircationData();
						ArrayList<Investor> investors=new ArrayList<Investor>();
						for(InvestorPlantMapping ipm:investorPlantMappings){
							Investor investor=investorsDAO.getById(ipm.getInvestorId());
							investors.add(investor);
						}
						JsonElement element = gson.toJsonTree(investors,new TypeToken<ArrayList<Investor>>(){}.getType());
						JsonArray jsonArray = element.getAsJsonArray();
						JsonObject jo=new JsonObject();
						jo.addProperty("Result", "OK");
						jo.add("Investors",jsonArray);
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().print(jo.toString());    
					}

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
