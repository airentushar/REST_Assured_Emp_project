package com.employeeApi.testCases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC_005_DeleteEmp extends TestBase{
	
	@BeforeClass
	void deleteEmployee()throws InterruptedException{
		logger.info("*********Started TC005_DeleteFirstEmployee**********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		
		response=httpRequest.request(Method.GET,"/employee/"+empId);
		logger.info(response.getBody().asString());
		
		//Getting JsonPath Object Instance from response interface
		JsonPath jsonPathEvaluator=response.jsonPath();
		
		//Capture ID
		String empId=jsonPathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/delete/"+empId);
		logger.info(response.getBody().asString());
		Thread.sleep(100);
	}
	
	@Test
	void checkResponseBody(){
		
		String resBody=response.getBody().asString();
		logger.info("Response Body is "+resBody);
		Assert.assertTrue(resBody.contains("Successfully! Record has been deleted"));
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
	
	//just arrange log 4jpropertiews file 
	@AfterClass
	void TearDown() {
		logger.info("Finished with TC_005_Delete");
	}
	
}
