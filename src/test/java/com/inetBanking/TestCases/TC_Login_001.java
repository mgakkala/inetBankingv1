package com.inetBanking.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;



public class TC_Login_001 extends BaseClass{
	
	@Test
	public void loginTest() throws IOException{
		
		logger.info("URL is accessed");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered Password");
		
		lp.clickSubmit();
		logger.info("Clicked on Submit Button");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Title Matched");
		}else {
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Title Didnt Match");
		}
		
	}

}
