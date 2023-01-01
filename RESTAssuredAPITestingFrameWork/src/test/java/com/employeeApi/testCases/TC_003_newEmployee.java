package com.employeeApi.testCases;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import com.employeeApi.utilities.*;

public class TC_003_newEmployee extends TestBase{

	String empName=RestUtils.empName();
	String empAge=RestUtils.empAge();
	String empSal=RestUtils.empSal();
	
	//POST Request
	@BeforeClass
		void newEmployee() throws Exception {
		logger.info("*********Started TC003_Post New Employees**********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject requestParams= new JSONObject();
		requestParams.put("employee_name", empName);
		requestParams.put("employee_age", empAge);
		requestParams.put("employee_salary", empSal);
		
		httpRequest.header("Content-Type", "application/json ; charset= UTF-8");
		
		httpRequest.body(requestParams.toJSONString());
		
		response=httpRequest.request(Method.POST,"/create");
	
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody(){
		logger.info("********  Checking Response Body ********");
		
		String resBody=response.getBody().asString();
		logger.info("Response Body is "+resBody);
		Assert.assertTrue(resBody.contains(empName));
		Assert.assertTrue(resBody.contains(empAge));		
		Assert.assertTrue(resBody.contains(empSal));
	}
	
	@Test
	void checkresponseTime(){
		logger.info("********  Checking Status Code ********");
		
		long responseTime=response.getTime();
		logger.info("Response Time is =>>"+responseTime);
		
		if(responseTime>5000) {
			logger.warn("Warning Response Time is > 5000ms");
		}
		
		Assert.assertTrue(responseTime<5000);
			
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********  Checking Status Line *******");
		
		String statusLine=response.getStatusLine();
		logger.info("status Line is =>>"+statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
	}
	
	@Test
	void checkContentType() {
		logger.info("********  Checking Content Type ********");
		
		String contentType=response.header("Content-Type");
		logger.info("Content Type is "+contentType);
		Assert.assertEquals("application/json",contentType);
	}
	
	@Test
	void checkServerType() {
		logger.info("********  Checking Server Type ********");
		
		String serverType=response.header("Server");
		logger.info("Server Type is "+serverType);
		Assert.assertEquals("Apache",serverType);
	
	}
	
	@Test
	void checkCookiesh() {
		logger.info("********  Checking Cookies ********");
		
		String cookies=response.getCookie("PHPSESSID");
		}	

	//just arrange log 4jpropertiews file 
	@AfterClass
	void TearDown() {
		logger.info("Finished with TC_003_Post");
	}
	

}
