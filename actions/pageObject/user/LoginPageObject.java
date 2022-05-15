package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.user.LoginPageUI;

public class LoginPageObject extends BasePage {

	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputEmailAddressTextbox(String emailValue) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		senkeyToElement(driver,LoginPageUI.EMAIL_ADDRESS_TEXTBOX , emailValue);
	}

	public void inputPasswordTextbox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver,LoginPageUI.PASSWORD_TEXTBOX , password);
	}
	

	public void clickLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		
	}

	public String getEmailAddressEmptyErrrorMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MESSGAGE);
		return getTextElement(driver, LoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MESSGAGE);
	}
	public String getEmailAddressInvalidErrrorMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MESSGAGE);
		return getTextElement(driver, LoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MESSGAGE);
	}
	public String gêtmailPasswordIncorrectErrorMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MESSGAGE);
		return getTextElement(driver, LoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MESSGAGE);
	}
	

	public String getPasswordEmptyErrrorMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MESSGAGE);
		return getTextElement(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MESSGAGE);
	}
	public String getPasswordInvalidErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_INVALID_ERROR_MESSGAGE);
		return getTextElement(driver, LoginPageUI.PASSWORD_INVALID_ERROR_MESSGAGE);
	}
	
}
