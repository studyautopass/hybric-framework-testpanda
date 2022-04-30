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
import org.testng.annotations.Test;

public class Account_01_Register {
	//mở trình duyệt
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	

		  

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
}

	
  @Test
  public void TC01_LoginSuccessful() {
	  driver.get("https://www.facebook.com/");
  }
  
  @Test
  public void TC02_InvalidPass() {
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
