package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.Consumption;
import com.ht.beans.Investor;
import com.ht.beans.InvestorConsumption;
import com.ht.beans.InvestorConsumptionView;
import com.ht.utility.GlobalResources;

public class InvestorConsumptionDAO {

	public boolean insert(InvestorConsumption investorConsumption){
		boolean added=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into investor_consumption (consumption_id, investor_id, active_consumption, reactive_consumption,circle_validation,bill_generated) values(?,?,?,?,?,?)");
			ps.setInt(1,investorConsumption.getConsumptionId());
			ps.setInt(2, investorConsumption.getInvestorId());
			ps.setFloat(3, investorConsumption.getActiveConsumption());
			ps.setFloat(4, investorConsumption.getReactiveConsumption());
			ps.setInt(5,0);
			ps.setInt(6,0);
			ps.executeUpdate();
			ps.close();
			added=true;
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [insert(Investors)] "+e.getMessage());
		}
		return added;
	}
	
	public boolean update(InvestorConsumption investorConsumption){
		boolean added=false;
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("update investor_consumption set active_consumption = ?, reactive_consumption=?,bill_generated=?,circle_validation=? where id = ?");
			ps.setFloat(1, investorConsumption.getActiveConsumption());
			ps.setFloat(2, investorConsumption.getReactiveConsumption());
			ps.setInt(3, investorConsumption.getBillGenerated());
			ps.setInt(4, investorConsumption.getCircleValidation());
			ps.setInt(5, investorConsumption.getId());
			ps.executeUpdate();
			ps.close();
			added=true;
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [update(InvestorConsumption)] "+e.getMessage());
		}
		return added;
	}
	
	public ArrayList<InvestorConsumption> getAllInvestorConsumption(){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_consumption");
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getAllInvestorConsumption()] "+e.getMessage());
		}
		return investorConsumtionsList;
	}

	public InvestorConsumption getById(int id){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where id =?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getById(int)] "+e.getMessage());
		}
		return investorConsumtionsList.get(0);
	}
	
	public ArrayList<InvestorConsumption> getByConsumptionId(int consumptionId){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where consumption_id =?");
			ps.setInt(1, consumptionId);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getByConsumptionId(int)] "+e.getMessage());
		}
		return investorConsumtionsList;
	}
	
	public ArrayList<InvestorConsumption> getByInvestorId(int investorId){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where investor_id =?");
			ps.setInt(1, investorId);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getByInvestorId(int)] "+e.getMessage());
		}
		return investorConsumtionsList;
	}
	
	public ArrayList<InvestorConsumption> getByInvestorIdAndConsumptionId(int investorId, int consumtionId){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where investor_id =? and consumption_id =?");
			ps.setInt(1, investorId);
			ps.setInt(1, consumtionId);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getByInvestorIdAndConsumptionId(int,int)] "+e.getMessage());
		}
		return investorConsumtionsList;
	}
	
	private ArrayList<InvestorConsumption> investorConsumptionMapper(ResultSet rs) {
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		try {
			while(rs.next()){
                InvestorConsumption investorConsumtion = new InvestorConsumption();
				investorConsumtion.setId(rs.getInt(1));
				investorConsumtion.setConsumptionId(rs.getInt(3));
				investorConsumtion.setInvestorId(rs.getInt(2));
				investorConsumtion.setActiveConsumption(rs.getFloat(4));
				investorConsumtion.setReactiveConsumption(rs.getFloat(5));
				investorConsumtion.setCircleValidation(rs.getInt(6));
				investorConsumtion.setBillGenerated(rs.getInt(7));
				investorConsumtionsList.add(investorConsumtion);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [investorConsumptionMapper(ResultSet)] "+e.getMessage());
		}
		return investorConsumtionsList;
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
	
	public InvestorConsumptionView getViewFromBean(InvestorConsumption investorConsumption){
		InvestorConsumptionView investorConsumptionView = new InvestorConsumptionView();
		
		InvestorsDAO investorsDAO = new InvestorsDAO();
		MachinesDAO machinesDAO = new MachinesDAO();
		BillDetailsDAO billDetailsDAO = new BillDetailsDAO();
		ConsumptionsDAO consumptionDAO = new ConsumptionsDAO();
		
		investorConsumptionView.setId(investorConsumption.getId());
		investorConsumptionView.setConsumption(consumptionDAO.getById(investorConsumption.getConsumptionId()));
		investorConsumptionView.setActiveConsumption(investorConsumption.getActiveConsumption());
		investorConsumptionView.setReactiveConsumption(investorConsumption.getReactiveConsumption());
		
		Investor investor = investorsDAO.getById(investorConsumption.getInvestorId());
		investorConsumptionView.setInvestor(investor);
		
		investorConsumptionView.setMachines(machinesDAO.getByInvestorId(investor.getId()));
		investorConsumptionView.setCircleValidation(investorConsumption.getCircleValidation());
		investorConsumptionView.setBillGenerated(investorConsumption.getBillGenerated());
		if(investorConsumption.getBillGenerated()==1){
			investorConsumptionView.setBillDetailsId(billDetailsDAO.getByConsumptionBifurcationId(investorConsumption.getId()).getId());
		}
		
		return investorConsumptionView;
	}
}
