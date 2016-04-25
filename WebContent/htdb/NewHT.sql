/*
Created		19-02-2016
Modified		19-02-2016
Project		
Model		
Company		
Author		
Version		
Database		mySQL 5 
*/

















drop table IF EXISTS Users;
drop table IF EXISTS readings;
drop table IF EXISTS plant_details;
drop table IF EXISTS meter_details;




Create table meter_details (
	meter_no Char(20) NOT NULL,
	make Char(20),
	category Char(20),
	type Char(20),
	meter_class Char(20),
	ctr Char(20),
	ptr Char(20),
	mf Int,
	equip_class Char(20),
	phase Char(20),
	meter_group Char(20),
	UNIQUE (meter_no),
 Primary Key (meter_no)) ENGINE = InnoDB;

Create table plant_details (
	id Int NOT NULL AUTO_INCREMENT,
	name Char(100) NOT NULL,
	circuit_connected_to Char(100),
	type Char(20),
	region Char(20),
	circle Char(20),
	division Char(20),
	commission_date Char(50),
	developer_name Char(100),
	no_of_investors Int,
	no_of_machines Int,
	contract_demand Char(20),
	supply_voltage Char(20),
	installed_meter_no Char(20) NOT NULL,
	meter_installation_date Char(50),
	UNIQUE (installed_meter_no),
 Primary Key (id,installed_meter_no)) ENGINE = InnoDB;

Create table readings (
	id Int NOT NULL AUTO_INCREMENT,
	meter_no Char(20) NOT NULL,
	mf Int,
	reading_date_time Char(50),
	cmvh_import Char(20),
	cmvh_export Char(20),
	mvarh_lagging_import Char(20),
	mvarh_lagging_export Char(20),
	mvarh_leading_import Char(20),
	mvarh_leading_export Char(20),
 Primary Key (id,meter_no)) ENGINE = InnoDB;

Create table Users (
	id Int NOT NULL AUTO_INCREMENT,
	username Char(50) NOT NULL,
	password Char(50),
	name Char(50),
	UNIQUE (username),
 Primary Key (id)) ENGINE = MyISAM;












Alter table readings add Foreign Key (meter_no) references meter_details (meter_no) on delete  restrict on update  restrict;
Alter table plant_details add Foreign Key (installed_meter_no) references meter_details (meter_no) on delete  restrict on update  restrict;















/* Users permissions */






