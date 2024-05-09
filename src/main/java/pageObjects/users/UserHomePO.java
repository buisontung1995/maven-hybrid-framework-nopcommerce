package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.users.UserHomePUI;

public class UserHomePO extends BasePage {
	
	private WebDriver driver;
	
	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Register Link")
	public UserRegisterPO clickToRegisterLink() {
		waitForElementClickable(driver, UserHomePUI.REGISTER_LINK);
		clickToElement(driver, UserHomePUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPO(driver);
	}

	@Step("Click to Login Link")
	public UserLoginPO clickToLoginLink() {
		waitForElementClickable(driver, UserHomePUI.LOGIN_LINK);
		clickToElement(driver, UserHomePUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPO(driver);
	}
}
