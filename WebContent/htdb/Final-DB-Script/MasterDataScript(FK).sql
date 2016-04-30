/*
Created		08-04-2016
Modified		30-04-2016
Project		
Model		
Company		
Author		
Version		
Database		mySQL 5 
*/

















drop table IF EXISTS bill_details;
drop table IF EXISTS user_roles;
drop table IF EXISTS plants;
drop table IF EXISTS Users;
drop table IF EXISTS meter_details;
drop table IF EXISTS meter_readings;
drop table IF EXISTS machines;
drop table IF EXISTS investors;
drop table IF EXISTS investor_plant_mapping;
drop table IF EXISTS investor_consumption;
drop table IF EXISTS developers;
drop table IF EXISTS consumptions;




Create table consumptions (
	id Int NOT NULL AUTO_INCREMENT,
	meter_no Varchar(50) NOT NULL,
	date Varchar(50) NOT NULL,
	active_consumption Float NOT NULL,
	reactive_consumption Float NOT NULL,
	plant_id Int NOT NULL,
	plant_code Varchar(50),
	meter_reading_id Int NOT NULL,
	consumption_bifercated Int,
 Primary Key (id)) ENGINE = InnoDB;

Create table developers (
	id Int NOT NULL AUTO_INCREMENT,
	name Varchar(200),
	cin Varchar(20),
	office_address Varchar(200),
	office_contact_no Varchar(20),
	office_contact_person Varchar(50),
	office_email Varchar(50),
	site_address Varchar(200),
	site_contact_no Varchar(20),
	site_contact_person Varchar(50),
	site_email Varchar(50),
	username Varchar(50) NOT NULL,
	UNIQUE (cin),
 Primary Key (id)) ENGINE = InnoDB;

Create table investor_consumption (
	id Int NOT NULL AUTO_INCREMENT,
	investor_id Int NOT NULL,
	consumption_id Int NOT NULL,
	active_consumption Float,
	reactive_consumption Float,
	circle_validation Int,
	bill_generated Int,
 Primary Key (id)) ENGINE = InnoDB;

Create table investor_plant_mapping (
	id Int NOT NULL AUTO_INCREMENT,
	plant_id Int NOT NULL,
	plant_code Varchar(50),
	investor_id Int NOT NULL,
	investor_code Varchar(50),
 Primary Key (id)) ENGINE = InnoDB;

Create table investors (
	id Int NOT NULL AUTO_INCREMENT,
	code Varchar(50),
	name Varchar(200),
	cin Varchar(50),
	tin Varchar(50),
	vat Varchar(50),
	invoice_no Varchar(50),
	office_address Varchar(300),
	office_contact_no Char(30),
	office_email Varchar(100),
	office_contact_person Varchar(100),
	site_address Varchar(300),
	site_contact_no Char(30),
	site_contact_person Char(30),
	site_email Varchar(100),
	UNIQUE (cin),
	UNIQUE (tin),
 Primary Key (id)) ENGINE = InnoDB;

Create table machines (
	id Int NOT NULL AUTO_INCREMENT,
	code Varchar(50),
	capacity Varchar(50),
	commissioned_date Varchar(20),
	active_rate Float NOT NULL,
	reactive_rate Float NOT NULL,
	ppa_letter_no Varchar(100),
	ppa_date Varchar(20),
	developer_id Int NOT NULL,
	plant_id Int NOT NULL,
	investor_id Int NOT NULL,
 Primary Key (id)) ENGINE = InnoDB;

Create table meter_readings (
	id Int NOT NULL AUTO_INCREMENT,
	meter_no Varchar(100) NOT NULL,
	mf Int,
	reading_date Varchar(20),
	active_reading Float NOT NULL,
	active_tod1 Float NOT NULL,
	active_tod2 Float NOT NULL,
	active_tod3 Float,
	reactive_q1 Float NOT NULL,
	reactive_q2 Float NOT NULL,
	reactive_q3 Float NOT NULL,
	reactive_q4 Float NOT NULL,
	ht_cell_validation Int,
	circle_cell_validation Int,
	developer_validation Int,
	discarded_flag Int,
	discarded_by Varchar(50),
	discarded_on Varchar(20),
 Primary Key (id)) ENGINE = InnoDB;

Create table meter_details (
	meter_no Varchar(50) NOT NULL,
	make Varchar(50),
	category Varchar(50),
	type Varchar(50),
	meter_class Varchar(50),
	ctr Varchar(20),
	ptr Varchar(20),
	mf Int NOT NULL,
	equip_class Varchar(50),
	phase Varchar(50),
	meter_group Varchar(50),
	UNIQUE (meter_no),
 Primary Key (meter_no)) ENGINE = InnoDB;

Create table Users (
	id Int NOT NULL AUTO_INCREMENT,
	username Varchar(50) NOT NULL,
	password Varchar(50) NOT NULL,
	name Varchar(50),
	UNIQUE (username),
 Primary Key (id)) ENGINE = InnoDB;

Create table plants (
	id Int NOT NULL AUTO_INCREMENT,
	code Varchar(50),
	name Varchar(200),
	address Varchar(200),
	contact_no Varchar(20),
	contact_person Varchar(50),
	email Varchar(50),
	commissioned_date Varchar(20),
	type Varchar(50),
	circuit_voltage Varchar(50),
	injecting_substation Varchar(50),
	feeder_name Varchar(50),
	region Varchar(50),
	circle Varchar(50),
	division Varchar(50),
	main_meter_no Varchar(100) NOT NULL,
	check_meter_no Varchar(100) NOT NULL,
	standby_meter_no Varchar(100),
	developer_id Int NOT NULL,
	UNIQUE (code),
	UNIQUE (main_meter_no),
 Primary Key (id)) ENGINE = InnoDB;

Create table user_roles (
	id Int NOT NULL AUTO_INCREMENT,
	username Varchar(50) NOT NULL,
	user_role Varchar(20) NOT NULL,
	region Varchar(50),
	circle Varchar(50),
	division Varchar(50),
 Primary Key (id)) ENGINE = InnoDB;

Create table bill_details (
	id Int NOT NULL AUTO_INCREMENT,
	bill_no Varchar(200) NOT NULL,
	invoice_no Varchar(200),
	meter_readings_id Int NOT NULL,
	investor_id Int NOT NULL,
	consumption_id Int NOT NULL,
	consumption_bifurcation_id Int NOT NULL,
	meter_no Varchar(50) NOT NULL,
	reading_date Char(20),
	bill_generation_date Char(20),
	total_kwh Float NOT NULL,
	total_rkvh Float NOT NULL,
	kwh_rate Float NOT NULL,
	rkvh_rate Float NOT NULL,
	active_amount Float NOT NULL,
	reactive_amount Float NOT NULL,
	total_amount Float NOT NULL,
	total_amount_roundoff Float,
	UNIQUE (bill_no),
	UNIQUE (invoice_no),
 Primary Key (id)) ENGINE = InnoDB;












Alter table investor_consumption add Foreign Key (consumption_id) references consumptions (id) on delete  restrict on update  restrict;
Alter table bill_details add Foreign Key (consumption_id) references consumptions (id) on delete  restrict on update  restrict;
Alter table plants add Foreign Key (developer_id) references developers (id) on delete  restrict on update  restrict;
Alter table machines add Foreign Key (developer_id) references developers (id) on delete  restrict on update  restrict;
Alter table bill_details add Foreign Key (consumption_bifurcation_id) references investor_consumption (id) on delete  restrict on update  restrict;
Alter table investor_plant_mapping add Foreign Key (investor_id) references investors (id) on delete  restrict on update  restrict;
Alter table machines add Foreign Key (investor_id) references investors (id) on delete  restrict on update  restrict;
Alter table investor_consumption add Foreign Key (investor_id) references investors (id) on delete  restrict on update  restrict;
Alter table bill_details add Foreign Key (investor_id) references investors (id) on delete  restrict on update  restrict;
Alter table consumptions add Foreign Key (meter_reading_id) references meter_readings (id) on delete  restrict on update  restrict;
Alter table bill_details add Foreign Key (meter_readings_id) references meter_readings (id) on delete  restrict on update  restrict;
Alter table user_roles add Foreign Key (username) references Users (username) on delete  restrict on update  restrict;
Alter table developers add Foreign Key (username) references Users (username) on delete  restrict on update  restrict;
Alter table investor_plant_mapping add Foreign Key (plant_id) references plants (id) on delete  restrict on update  restrict;
Alter table machines add Foreign Key (plant_id) references plants (id) on delete  restrict on update  restrict;
Alter table meter_readings add Foreign Key (meter_no) references plants (main_meter_no) on delete  restrict on update  restrict;
Alter table consumptions add Foreign Key (plant_id) references plants (id) on delete  restrict on update  restrict;















/* Users permissions */






