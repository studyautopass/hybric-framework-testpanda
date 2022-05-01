package common;

import java.sql.Driver;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
	
	/** Author: ThuyVT
	 * open URL
	 * @param driver
	 * @param pageURL
	 */
	public void openPageURL(WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToUrl(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cacelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void getTextAlert(WebDriver driver) {
		driver.switchTo().alert().getText();
	}
	
	public void senKeyToAlert(WebDriver driver, String valueToSenkey) {
		  driver.switchTo().alert().sendKeys(valueToSenkey);
	}
	
	public void switchToWindown(WebDriver driver, String expectedID) {
		Set<String> allTabIDs = driver.getWindowHandles();
		for(String id : allTabIDs) {
			if (id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindownByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allTabIDs = driver.getWindowHandles();
		for(String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTite = driver.getTitle();
			if (actualTite.equals(expectedTitle)) {
				driver.switchTo().window(expectedTitle);
				break;
			}
		}
	}
	
	public boolean closeWindownWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String runWindow : allWindows) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();				
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size() ==1) {
			return true;			
		}
		else {
			return false;
		}
		
		
		
		
	}
	
}
