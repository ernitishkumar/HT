/*
Created		08-04-2016
Modified		29-04-2016
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
	plant_id Int,
	plant_code Varchar(50),
	developer_id Int,
	meter_reading_id Int,
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
	UNIQUE (username),
 Primary Key (id)) ENGINE = InnoDB;

Create table investor_consumption (
	id Int NOT NULL AUTO_INCREMENT,
	consumption_id Int NOT NULL,
	investor_id Int NOT NULL,
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
	username Varchar(20) NOT NULL,
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
	main_meter_no Varchar(50) NOT NULL,
	check_meter_no Varchar(50) NOT NULL,
	standby_meter_no Varchar(50),
	developer_id Int NOT NULL,
	UNIQUE (code),
 Primary Key (id)) ENGINE = InnoDB;

Create table user_roles (
	id Int NOT NULL AUTO_INCREMENT,
	username Varchar(100) NOT NULL,
	user_role Varchar(20) NOT NULL,
	region Varchar(50),
	circle Varchar(50),
	division Varchar(50),
	UNIQUE (username),
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



























/* Users permissions */






