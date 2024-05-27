package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserCompareProductsListPUI;

public class UserCompareProductsListPO extends BasePage {
	private WebDriver driver;

	public UserCompareProductsListPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Verify Remove button of product '{0}' is displayed")
	public boolean isRemoveButtonDisplayedByProductName(String productName) {
		int columnIndex = getElementSize(driver, UserCompareProductsListPUI.COLUMN_INDEX_BY_PRODUCT_NAME, productName) +1;

		waitForElementVisible(driver, UserCompareProductsListPUI.REMOVE_BUTTON_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		return isElementDisplayed(driver, UserCompareProductsListPUI.REMOVE_BUTTON_BY_COLUMN_INDEX, String.valueOf(columnIndex));
	}

	@Step("Verify product name '{0}' is displayed")
	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisible(driver, UserCompareProductsListPUI.PRODUCT_NAME, productName);
		return isElementDisplayed(driver, UserCompareProductsListPUI.PRODUCT_NAME, productName);
	}

	@Step("Get price of product '{0}'")
	public String getProductPriceByProductName(String productName) {
		int columnIndex = getElementSize(driver, UserCompareProductsListPUI.COLUMN_INDEX_BY_PRODUCT_NAME, productName) + 1;

		waitForElementVisible(driver, UserCompareProductsListPUI.PRODUCT_PRICE_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		return getElementText(driver, UserCompareProductsListPUI.PRODUCT_PRICE_BY_COLUMN_INDEX, String.valueOf(columnIndex));
	}

	@Step("Click to 'Clear List' button")
	public void clickToClearListButton() {
		waitForElementClickable(driver, UserCompareProductsListPUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, UserCompareProductsListPUI.CLEAR_LIST_BUTTON);
	}

	@Step("Get No Items To Compare Message")
	public String getNoItemsToCompareMessage() {
		waitForElementVisible(driver, UserCompareProductsListPUI.NO_ITEMS_TO_COMPARE_MESSAGE);
		return getElementText(driver, UserCompareProductsListPUI.NO_ITEMS_TO_COMPARE_MESSAGE);
	}

	@Step("Verify product {0} is undisplayed")
	public boolean isProductNameUndisplayed(String productName) {
		return isElementUndisplayed(driver, UserCompareProductsListPUI.PRODUCT_NAME, productName);
	}

	@Step("Click To 'Computers' Menu")
	public UserComputersPO clickToComputersMenu() {
		waitForElementClickable(driver, UserCompareProductsListPUI.COMPUTERS_MENU);
		clickToElement(driver, UserCompareProductsListPUI.COMPUTERS_MENU);
		return PageGeneratorManager.getUserComputersPO(driver);
	}
}
