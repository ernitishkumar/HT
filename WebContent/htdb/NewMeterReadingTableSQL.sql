/*
Created		04-03-2016
Modified		04-03-2016
Project		
Model		
Company		
Author		
Version		
Database		mySQL 5 
*/

















drop table IF EXISTS meter_readings;




Create table meter_readings (
	id Int NOT NULL AUTO_INCREMENT,
	meter_no Char(50),
	mf Char(20),
	reading_date Char(30),
	reading_time Char(30),
	active_energy Char(50),
	tod_1 Char(50),
	tod_2 Char(50),
	tod_3 Char(50),
	reactive_energy Char(50),
 Primary Key (id)) ENGINE = InnoDB;



























/* Users permissions */






