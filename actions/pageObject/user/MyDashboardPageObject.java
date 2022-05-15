package pageObject.user;
import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.user.LoginPageUI;
import pageUIs.user.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage {
	WebDriver driver;
	public MyDashboardPageObject(WebDriver driver) {
		this.driver=  driver;
	}
	public String getContactInforUser() {
		waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
		return getTextElement(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
	}

}



