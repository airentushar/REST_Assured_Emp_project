package com.employeeApi.testCases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_001_employeeApi extends TestBase
{

	@BeforeClass
	void getAllEmployees()throws InterruptedException{
		logger.info("*********Started TC001_GetAllEmployees**********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody(){
		logger.info("********  Checking Response Body ********");
		
		String resBody=response.getBody().asString();
		logger.info("Response Body is "+resBody);
		Assert.assertTrue(resBody!=null);
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
		Assert.assertEquals("nginx/1.21.6",serverType);
	
	}
	@Test
	void checkContentEncoding() {
		logger.info("********  Checking Content Encoding ********");
		
		String contentEncoding=response.header("Content-Encoding");
		logger.info("Content Encoding is "+contentEncoding);
		Assert.assertEquals("gzip",contentEncoding);
	
	}
	
	@Test
	void checkCookiesh() {
		logger.info("********  Checking Cookies ********");
		
		String cookies=response.getCookie("PHPSESSID");
		}	

	//just arrange log 4jpropertiews file 
	@AfterClass
	void TearDown() {
		logger.info("Finished with TC_001");
	}
}