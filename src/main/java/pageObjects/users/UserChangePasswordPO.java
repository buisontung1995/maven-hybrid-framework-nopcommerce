package pageObjects.users;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserChangePasswordPUI;

public class UserChangePasswordPO extends UserSideBarMyAccountPO {
	private WebDriver driver;

	public UserChangePasswordPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void inputToOldPasswordTextbox(String password) {
		waitForAllElementVisible(driver, UserChangePasswordPUI.OLD_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserChangePasswordPUI.OLD_PASSWORD_TEXTBOX, password);
	}

	public void inputToNewPasswordTextbox(String password) {
		waitForAllElementVisible(driver, UserChangePasswordPUI.NEW_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserChangePasswordPUI.NEW_PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextbox(String password) {
		waitForAllElementVisible(driver, UserChangePasswordPUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserChangePasswordPUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}

	public void clickToChangePasswordButton() {
		waitForElementClickable(driver, UserChangePasswordPUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, UserChangePasswordPUI.CHANGE_PASSWORD_BUTTON);
	}

	public UserHomePO clickToLogOutLink() {
		waitForElementClickable(driver, UserChangePasswordPUI.LOGOUT_LINK);
		clickToElement(driver, UserChangePasswordPUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePO(driver);
	}
}
