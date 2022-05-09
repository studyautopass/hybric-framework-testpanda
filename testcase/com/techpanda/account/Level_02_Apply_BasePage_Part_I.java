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

public class Level_02_Apply_BasePage_Part_I {
	//Khai báo : Declare
	WebDriver driver;
	BasePage basePage;
	
	//Khai báo + khởi tạo+ Initial
	
	String projectPath = System.getProperty("user.dir");

	
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.print(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();	
		basePage = new BasePage();

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://live.techpanda.org");
	
}
	@BeforeMethod
	public void beforeMethod() {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//header[@id='header']//a[text()='My Account']")).click();
	}

	
  @Test
  public void TC01_Empty() {
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.id("pass")).sendKeys("");
	  driver.findElement(By.id("send2")).click();
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	  
	  
  }
  
  @Test
  public void TC02_InvalidPass() {
	  driver.findElement(By.id("email")).sendKeys("testing@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  driver.findElement(By.id("send2")).click();

	  Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	  
  }
  
  @Test
  public void TC03_InvalidMail() {
	  driver.findElement(By.id("email")).sendKeys("123@111");
	  driver.findElement(By.id("pass")).sendKeys("123345");
	  driver.findElement(By.id("send2")).click();

	  Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
