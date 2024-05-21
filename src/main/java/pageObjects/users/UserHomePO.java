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

    public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, UserHomePUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, UserHomePUI.MY_ACCOUNT_LINK);
    }

	public UserCustomerInfoPO clickToMyAccountLink() {
		waitForElementClickable(driver, UserHomePUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPO(driver);
	}

	public UserProductDetailsPO clickToProductDetailByName(String productName) {
		waitForElementClickable(driver, UserHomePUI.DYNAMIC_PRODUCT_NAME_LINK, productName);
		clickToElement(driver, UserHomePUI.DYNAMIC_PRODUCT_NAME_LINK, productName);
		return PageGeneratorManager.getUserProductDetailsPO(driver);
	}

	public UserSearchPO clickToSearchLink() {
		waitForElementClickable(driver, UserHomePUI.SEARCH_LINK);
		clickToElement(driver, UserHomePUI.SEARCH_LINK);
		return PageGeneratorManager.getUserSearchPO(driver);
	}
}
