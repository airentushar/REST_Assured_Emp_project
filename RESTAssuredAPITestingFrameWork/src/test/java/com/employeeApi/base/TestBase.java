package com.employeeApi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empId="22";
	
	public Logger logger;
	
	
	@BeforeClass
	public void setup(){
		logger=Logger.getLogger("employeeApiLog");//added logger
		PropertyConfigurator.configure("Log4j.properties");//added logger
		logger.setLevel(Level.DEBUG);
	}
}
