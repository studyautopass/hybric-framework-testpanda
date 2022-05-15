package com.techpanda.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import common.BasePage;
import pageObject.user.HomePageObject;
import pageObject.user.LoginPageObject;
import pageObject.user.MyDashboardPageObject;

public class Level_03_Base_Object_Pattern {
	//Khai báo : Declare
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		
		

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	driver.get( "http://live.techpanda.org");
	homePage = new HomePageObject(driver);
	
}
	
	

  public void TC01_Empty() {
	  homePage.clickToMyAccountLink();
	  loginPage = new LoginPageObject(driver);	
	  
	  loginPage.inputEmailAddressTextbox("");
	  loginPage.inputPasswordTextbox("");
	  loginPage.clickLoginButton();
	 
	  Assert.assertEquals(loginPage.getEmailAddressEmptyErrrorMessage(), "This is a required field.");
	  Assert.assertEquals(loginPage.getPasswordEmptyErrrorMessage(), "This is a required field.");
	 
	  	  
  }

 public void TC02_InvalidPass() {
	  homePage.clickToMyAccountLink();
	  loginPage = new LoginPageObject(driver);
	  loginPage.inputEmailAddressTextbox("thuy@gmail.com");
	  loginPage.inputPasswordTextbox("111");
	  loginPage.clickLoginButton();
	  
	
	  Assert.assertEquals(loginPage.getPasswordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	  
  }
  

  public void TC03_InvalidMail() {
	  homePage.clickToMyAccountLink();
	  loginPage = new LoginPageObject(driver);
	  loginPage.inputEmailAddressTextbox("thuy@gmail.1");
	  loginPage.inputPasswordTextbox("11111111");
	  loginPage.clickLoginButton();
	  
	//System.out.println(loginPage.getEmailAddressInvalidErrrorMessage());

	  Assert.assertEquals(loginPage.getEmailAddressInvalidErrrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	  
  }
  
  @Test
  public void TC03_ValidValue() {
	  homePage.clickToMyAccountLink();
	  loginPage = new LoginPageObject(driver);
	  loginPage.inputEmailAddressTextbox("automationfc.vn@gmail.com");
	  loginPage.inputPasswordTextbox("123123");
	  loginPage.clickLoginButton();
	  
	  myDashboardPage = new MyDashboardPageObject(driver);
	 System.out.println(myDashboardPage.getContactInforUser()); 

	 // Assert.assertEquals(myDashboardPage.getContactInforUser(), "");
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
