package com.inetBanking.TestCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.Utilities.XLUtils;
import com.inetBanking.pageObjects.LoginPage;

public class TC_Login_DDT_002 extends BaseClass{
	
	@Test(dataProvider="loginData")
	public void loginDDT(String user,String passwd) throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Username Provided");
		lp.setPassword(passwd);
		logger.info("Password Provided");
		lp.clickSubmit();
		logger.info("Clicked on Submit Button");
		Thread.sleep(500);
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("login Failed");
		}
		else {
			Assert.assertTrue(true);
			logger.info("Login Successful");
			lp.clickLogout();
			Thread.sleep(500);
			driver.switchTo().alert().accept(); //close logout alert
			driver.switchTo().defaultContent();
		}
		
	}
	
	public boolean isAlertPresent() //User defined method for check alert is present or not
	{
		try {
		driver.switchTo().alert();
		return true;
		}catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	@DataProvider(name="loginData")
	String [][] getData() throws IOException
	{
		String Path = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/TestData/LoginData.xlsx";
		
		int rowcount = XLUtils.getRowCount(Path, "sheet1");
		int colcount = XLUtils.getCellCount(Path, "sheet1", 1);
		
		String loginData[][] = new String [rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				loginData[i-1][j]=XLUtils.getCellData(Path, "sheet1", i, j);
			}
			
		}
		return loginData;
	}
	

}
