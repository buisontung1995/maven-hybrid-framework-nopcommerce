package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.users.UserRegisterPUI;

public class UserRegisterPO extends BasePage{

	private WebDriver driver;
	
	public UserRegisterPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Register button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPUI.REGISTER_BUTTON);
	}

	@Step("Get Error Message At Fistname Textbox")
	public String getErrorMessageAtFirstNameTextBox() {
		waitForElementVisible(driver, UserRegisterPUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPUI.FIRST_NAME_ERROR_MESSAGE);
	}

	@Step("Get Error Message At Lastname Textbox")
	public String getErrorMessageAtLastNameTextBox() {
		waitForElementVisible(driver, UserRegisterPUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPUI.LAST_NAME_ERROR_MESSAGE);
	}

	@Step("Get Error Message At Email Textbox")
	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, UserRegisterPUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPUI.EMAIL_ERROR_MESSAGE);
	}

//	@Step("Get Error Message At Password Textbox")
//	public String getErrorMessageAtPasswordTextBox() {
//		waitForElementVisible(driver, UserRegisterPUI.PASSWORD_ERROR_MESSAGE);
//		return getElementText(driver, UserRegisterPUI.PASSWORD_ERROR_MESSAGE);
//	}

	public String getErrorMessageAtConfirmPasswordTextBox() {
		waitForElementVisible(driver, UserRegisterPUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Input to Firstname textbox with value is {0}")
	public void inputToFirstNameTextBox(String firstName) {
		waitForElementVisible(driver, UserRegisterPUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPUI.FIRST_NAME_TEXTBOX, firstName);
	}

	@Step("Input to Lastname textbox with value is {0}")
	public void inputToLastNameTextBox(String lastName) {
		waitForElementVisible(driver, UserRegisterPUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPUI.LAST_NAME_TEXTBOX, lastName);
	}

	@Step("Input to Email textbox with value is {0}")
	public void inputToEmailTextBox(String email) {
		waitForElementVisible(driver, UserRegisterPUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPUI.EMAIL_TEXTBOX, email);
	}
	
	@Step("Input to Password textbox with value is {0}")
	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, UserRegisterPUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Input to Confirm Password textbox with value is {0}")
	public void inputToConfirmPasswordTextBox(String password) {
		waitForElementVisible(driver, UserRegisterPUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, UserRegisterPUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}

	@Step("Get Register Success Message")
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, UserRegisterPUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPUI.REGISTER_SUCCESS_MESSAGE);
	}

	@Step("Click to Homepage Logo")
	public UserHomePO clickToHomePageLogo() {
		waitForElementClickable(driver, UserRegisterPUI.HOMEPAGE_LOGO);
		clickToElement(driver, UserRegisterPUI.HOMEPAGE_LOGO);
		return PageGeneratorManager.getUserHomePO(driver);
	}

	@Step("Get Existing Email Error Message")
	public String getExistingEmailErrorMessage() {
		waitForElementVisible(driver, UserRegisterPUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}

	@Step("Click to Log out link")
    public UserHomePO clickToLogOutLink() {
		waitForElementClickable(driver, UserRegisterPUI.LOGOUT_LINK);
		clickToElement(driver, UserRegisterPUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePO(driver);
    }
}
