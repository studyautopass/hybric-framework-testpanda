package common;

import java.sql.Driver;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	//WebBrowser

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
	
	public Alert waitForElementAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
		
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
	

		//WebElement
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}
	
	public By getByXpath( String locator) {
		return By.xpath(locator);
		
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();;
	}
	
	
	public void senkeyToElement(WebDriver driver, String locator, String valueSenkey) {
		WebElement element = getElement(driver, locator);
		element.clear();
		element.sendKeys(valueSenkey);
	}
	
	public String getTextElement(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}
	
	public void getSelectDefaultInDropdown(WebDriver driver, String locator, String textDropdown) {
		Select select = new Select( getElement(driver, locator));
		select.selectByVisibleText(textDropdown);
	}
	
	public String getFirstSelectedItem(WebDriver driver, String locator) {
		Select select = new Select (getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String locator,String parentxPath, String childxPath, String expectedtemText) {
		
		getElement(driver, parentxPath).click();
		sleepInSecond(2);
		//Tim xong tra ve list cac element
		List<WebElement> childItems = new WebDriverWait(driver, longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childxPath)));
		
		// duyệt qua từng item
		for (WebElement tempElement : childItems) {			
			if (tempElement.getText().trim().equals(expectedtemText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0],scrollIntoView(false);", tempElement);
				sleepInSecond(2);
				tempElement.click();
				sleepInSecond(2);
				
				break;
				}
		}		
	}	
	
	public String getElementAttributeValue(WebDriver driver, String locator, String valueAttribute) {
		return getElement(driver, locator).getAttribute(valueAttribute);
	}
	
	public String getCssValue(WebDriver driver, String locator, String cssValue) {
		return getElement(driver, locator).getCssValue(cssValue);
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if(!getElement(driver, locator).isSelected()) {
			clickToElement(driver, locator);
		}
		
	}
	
	public void uncheckToCheckboxOrRadio(WebDriver driver, String locator) {
		if(getElement(driver, locator).isSelected()) {
			clickToElement(driver, locator);
		}
		
	}
		
	public boolean isElementDisplay(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	
	public void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMoveToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}
	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}
	public void dragAndDropElement(WebDriver driver, String locator, String sourceValue, String descValue) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceValue),getElement(driver, descValue)).perform();
	}
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		 action.sendKeys(getElement(driver, locator), key).perform();
	}
	
	public void hightlightElement(WebDriver driver,String locator) {
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	
	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
		
	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
		
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
		
	}
	
	
	public void sleepInSecond(long second) {
		
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private long longTimeout = 20;


}
