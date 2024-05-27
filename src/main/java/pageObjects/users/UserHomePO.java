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

	@Step("Verify My Account Link is displayed")
    public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, UserHomePUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, UserHomePUI.MY_ACCOUNT_LINK);
    }

	@Step("Click to My Account Link")
	public UserCustomerInfoPO clickToMyAccountLink() {
		waitForElementClickable(driver, UserHomePUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPO(driver);
	}

	@Step("Click to '{0}' product")
	public UserProductDetailsPO clickToProductDetailByName(String productName) {
		waitForElementClickable(driver, UserHomePUI.DYNAMIC_PRODUCT_NAME_LINK, productName);
		clickToElement(driver, UserHomePUI.DYNAMIC_PRODUCT_NAME_LINK, productName);
		return PageGeneratorManager.getUserProductDetailsPO(driver);
	}

	@Step("Click to Search Link")
	public UserSearchPO clickToSearchLink() {
		waitForElementClickable(driver, UserHomePUI.SEARCH_LINK);
		clickToElement(driver, UserHomePUI.SEARCH_LINK);
		return PageGeneratorManager.getUserSearchPO(driver);
	}

	@Step("Click to Computers Menu")
	public UserComputersPO clickToComputersMenu() {
		waitForElementClickable(driver, UserHomePUI.COMPUTERS_MENU);
		clickToElement(driver, UserHomePUI.COMPUTERS_MENU);
		return PageGeneratorManager.getUserComputersPO(driver);
	}

	@Step("Get price of product '{0}'")
    public String getProductPriceByProductName(String productName) {
		waitForElementVisible(driver, UserHomePUI.PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserHomePUI.PRICE_BY_PRODUCT_NAME, productName);
    }

	@Step("Click to 'Add to compare list' button of product {0}")
	public void clickToAddToCompareListButtonByProductName(String productName) {
		waitForElementClickable(driver, UserHomePUI.ADD_TO_COMPARE_LIST_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, UserHomePUI.ADD_TO_COMPARE_LIST_BUTTON_BY_PRODUCT_NAME, productName);
	}

	@Step("Click to 'Compare products list' link")
	public UserCompareProductsListPO clickToCompareProductsListLink() {
		waitForElementClickable(driver, UserHomePUI.COMPARE_PRODUCTS_LIST_LINK);
		clickToElement(driver, UserHomePUI.COMPARE_PRODUCTS_LIST_LINK);
		return PageGeneratorManager.getUserCompareProductsListPO(driver);
	}
}
