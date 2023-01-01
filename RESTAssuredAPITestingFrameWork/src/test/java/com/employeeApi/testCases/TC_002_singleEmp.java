package com.employeeApi.testCases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_002_singleEmp extends TestBase {

	@BeforeClass
	void getAllEmployees()throws InterruptedException{
		logger.info("*********Started TC002_GetSingleEmployee**********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee/"+empId);
		
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody(){
		
		String resBody=response.getBody().asString();
		logger.info("Response Body is "+resBody);
		Assert.assertTrue(resBody.contains(empId));
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
	void checkStatusCode() {
		
		int statusCode=response.getStatusCode();
		logger.info("status Code is =>>"+statusCode);
		Assert.assertEquals(200, statusCode);
	}
	
	@Test
	void checkContentType() {
		//logger.info("********  Checking Content Type ********");
		
		String contentType=response.header("Content-Type");
		logger.info("Content Type is "+contentType);
		Assert.assertEquals("application/json",contentType);
	}
	
	@Test
	void checkServerType() {
		//logger.info("********  Checking Server Type ********");
		
		String serverType=response.header("Server");
		logger.info("Server Type is "+serverType);
		Assert.assertEquals("nginx/1.21.6",serverType);
	
	}
	@Test
	void checkCookiesh() {
		//logger.info("********  Checking Cookies ********");
		
		String cookies=response.getCookie("PHPSESSID");
		}	

	//just arrange log 4jpropertiews file 
	@AfterClass
	void TearDown() {
		logger.info("Finished with TC_002_GetSingleEmployee");
	}
}
