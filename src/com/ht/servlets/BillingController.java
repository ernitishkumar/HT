package com.ht.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import com.ht.utility.GlobalResources;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.google.gson.*;
import org.json.simple.JSONObject;
import com.ht.dao.*;
import com.ht.beans.*;

public class BillingController extends HttpServlet{
	private Gson gson=GlobalResources.getGson();
	protected void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		try{
			System.out.println("BillingController Started");
			HttpSession userSession=httpServletRequest.getSession();
			User user=(User)userSession.getAttribute("User");
			if(user!=null){
				String action=(String)httpServletRequest.getParameter("action");
				System.out.println("Got Action : "+action);
				if(action!=null){
					ConsumptionsDAO consumptionsDAO=new ConsumptionsDAO();
					InvestorsDAO investorsDAO = new InvestorsDAO();
					InvestorConsumptionDAO investorConsumptionDAO = new InvestorConsumptionDAO();
					BillDetailsDAO billDetailsDAO = new BillDetailsDAO();
					MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();
					MachinesDAO machinesDAO = new MachinesDAO();
					if(action.toLowerCase().equals("generate")){
						System.out.println("Comming in generate");
						String consumptionId=(String)httpServletRequest.getParameter("consumptionId");
						String investorId=(String)httpServletRequest.getParameter("investorId");
						String bifurcationId=(String)httpServletRequest.getParameter("bifurcationId");
						System.out.println("Consumption data from front end : "+consumptionId+" "+investorId+" "+bifurcationId);
						
						SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
						Date date = new Date();
						String currentDate = formater.format(date);
						
						int lastInsertedId = -1;

						if(consumptionId!=null && investorId!=null && bifurcationId!=null){
							Consumption consumption = consumptionsDAO.getById(Integer.parseInt(consumptionId.trim()));
							Investor investor = investorsDAO.getById(Integer.parseInt(investorId.trim()));
							InvestorConsumption investorConsumption = investorConsumptionDAO.getById(Integer.parseInt(bifurcationId.trim()));
							MeterReadings meterReadings = meterReadingsDAO.getById(consumption.getMeterReadingId());
							Machine machine = machinesDAO.getByInvestorId(investor.getId()).get(0);
							
							BillDetails billDetails = new BillDetails();
							billDetails.setMeterReadingId(consumption.getMeterReadingId());
							billDetails.setInvestorId(investor.getId());
							billDetails.setConsumptionId(consumption.getId());
							billDetails.setMeterNo(consumption.getMeterNo());
							billDetails.setReadingDate(meterReadings.getReadingDate());
							billDetails.setBillGenerationDate(currentDate);
							
							float activeConsumption = investorConsumption.getActiveConsumption();
							float reactiveConsumption = investorConsumption.getReactiveConsumption();
							
							float activeRate = machine.getActiveRate();
							float reactiveRate = machine.getReactiveRate();
							
							float activeAmount = activeConsumption * activeRate;
							float reactiveAmount = reactiveConsumption * reactiveRate;
							
							float totalAmount = activeAmount + reactiveAmount;
							float totalAmountRoundOff = Math.round(totalAmount);
							
							billDetails.setTotalKWH(activeConsumption);
							billDetails.setTotalRKVH(reactiveConsumption);
							billDetails.setKwhRate(activeRate);
							billDetails.setRkvhRate(reactiveRate);
							
						    billDetails.setActiveAmount(activeAmount);
						    billDetails.setReactiveAmount(reactiveAmount);
						    
						    billDetails.setTotalAmount(totalAmount);
						    billDetails.setTotalAmountRoundOff(totalAmountRoundOff);
						    
						    System.out.println("Created bill : "+billDetails);
						    
						    lastInsertedId = billDetailsDAO.insert(billDetails);
						    if(lastInsertedId != -1){
						    	billDetails.setId(lastInsertedId);
							    System.out.println("Creating bill no for id : "+lastInsertedId+" on : "+currentDate);
							    Calendar calendar = Calendar.getInstance();
							    calendar.setTime(date);
							    int m = calendar.get(Calendar.MONTH)+1;
							    String month="";
							    if(m<10){
							    	month="0"+m;
							    }else{
							    	month=""+m;
							    }
							    System.out.println("Calendar date : "+calendar.getTime()+" month : "+calendar.get(Calendar.MONTH));
							    String billNo = consumption.getMeterNo()+calendar.get(Calendar.YEAR)+month+lastInsertedId;
							    System.out.println("bill no for id : "+lastInsertedId+"  is : "+billNo);
							    billDetails.setBillNo(billNo);
							    billDetailsDAO.update(billDetails);
							    System.out.println("Bill Updated with bill details");
							    
							    investorConsumption.setBillGenerated(1);
							    investorConsumptionDAO.update(investorConsumption);
						    }
						}
						JSONObject jsonObject=new JSONObject();
						if(lastInsertedId != -1){
							System.out.println("Bills inserted successfully.");
							jsonObject.put("Result","OK");
							jsonObject.put("BillId",lastInsertedId);
						}else{
							jsonObject.put("Result","Failure");
							jsonObject.put("Message","Unable to generate Bill");
						}
						httpServletResponse.setContentType("application/json");
						httpServletResponse.getWriter().write(jsonObject.toString());
					}
				}else{
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("Result","ERROR");
					jsonObject.put("Message","Wrong Action");
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
