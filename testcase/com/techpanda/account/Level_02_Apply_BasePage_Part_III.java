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

public class Level_02_Apply_BasePage_Part_III extends BasePage{
	//Khai báo : Declare
	WebDriver driver;
	
	
	//Khai báo + khởi tạo+ Initial
	
	String projectPath = System.getProperty("user.dir");

	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	openPageURL(driver, "http://live.techpanda.org");
	
	
}
	@BeforeMethod
	public void beforeMethod() {
		
		clickToElement(driver, "//div[@class='account-cart-wrapper']//span[text()='Account']");
		clickToElement(driver, "//header[@id='header']//a[text()='My Account']");
		
	}

	
  @Test
  public void TC01_Empty() {
	  
	  senkeyToElement(driver, "//input[@id='email']", "");
	  senkeyToElement(driver, "//input[@id='pass']", "");
	  clickToElement(driver, "//button[@id='send2']");
	  Assert.assertEquals(getTextElement(driver, "//div[@id='advice-required-entry-email']"), "This is a required field.");
	  Assert.assertEquals(getTextElement(driver, "//div[@id='advice-required-entry-pass']"), "This is a required field.");
	 
	  	  
  }
  
  @Test
  public void TC02_InvalidPass() {
	  senkeyToElement(driver, "//input[@id='email']", "thuy@gmail.com");
	  senkeyToElement(driver, "//input[@id='pass']", "111");
	  clickToElement(driver, "//button[@id='send2']");

	  Assert.assertEquals(getTextElement(driver, "//div[@id='advice-validate-password-pass']"), "Please enter 6 or more characters without leading or trailing spaces.");
	  
  }
  
  @Test
  public void TC03_InvalidMail() {
	  senkeyToElement(driver, "//input[@id='email']", "thuy@com.v");
	  senkeyToElement(driver, "//input[@id='pass']", "1234567890");
	  clickToElement(driver, "//button[@id='send2']");

	  Assert.assertEquals(getTextElement(driver, "//div[@id='advice-validate-email-email']"), "Please enter a valid email address. For example johndoe@domain.com.");
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
