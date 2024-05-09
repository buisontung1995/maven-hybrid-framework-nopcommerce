package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.users.UserLoginPUI;

public class UserLoginPO extends BasePage {
	private WebDriver driver;

	public UserLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, UserLoginPUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserLoginPUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, UserLoginPUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserLoginPUI.PASSWORD_TEXTBOX, password);
	}

	public UserHomePO clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePO(driver);
	}
}
