package pageObjects.users;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserSearchPUI;

public class UserSearchPO extends BasePage {
	private WebDriver driver;

	public UserSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Search Button")
	public void clickToSearchButton() {
		waitForElementClickable(driver, UserSearchPUI.SEARCH_BUTTON);
		clickToElement(driver, UserSearchPUI.SEARCH_BUTTON);
	}

	@Step("Get Search Error Message")
	public String getSearchErrorMessage() {
		waitForElementVisible(driver, UserSearchPUI.SEARCH_ERROR_MESSAGE);
		return getElementText(driver, UserSearchPUI.SEARCH_ERROR_MESSAGE);
	}

	@Step("Input to Search Textbox with value is {0}")
	public void inputToSearchTextbox(String searchData) {
		waitForElementVisible(driver, UserSearchPUI.SEARCH_TEXTBOX);
		sendKeyToElement(driver, UserSearchPUI.SEARCH_TEXTBOX, searchData);
	}

	@Step("Get Total Search Result")
	public int getTotalSearchResult() {
		waitForAllElementVisible(driver, UserSearchPUI.PRODUCT_ITEM);
		return getElementSize(driver, UserSearchPUI.PRODUCT_ITEM);
	}

	@Step("Select Advanced Search Checkbox")
	public void selectAdvancedSearchCheckbox() {
		waitForElementVisible(driver, UserSearchPUI.ADVANCED_SEARCH_CHECKBOX);
		checkDefaultCheckboxRadio(driver, UserSearchPUI.ADVANCED_SEARCH_CHECKBOX);
	}

	@Step("Select Category Drop Down List with value is {0}")
	public void selectCategoryDropDownList(String categoryValue) {
		waitForElementClickable(driver, UserSearchPUI.CATEGORY_DROP_DOWN_LIST);
		selectItemInDefaultDropdown(driver, UserSearchPUI.CATEGORY_DROP_DOWN_LIST, categoryValue);
	}

	@Step("Select Automatically Search Sub Categories Checkbox")
	public void selectAutomaticallySearchSubCategoriesCheckbox() {
		waitForElementVisible(driver, UserSearchPUI.AUTOMATICALLY_SEARCH_SUB_CATEGORIES_CHECKBOX);
		checkDefaultCheckboxRadio(driver, UserSearchPUI.AUTOMATICALLY_SEARCH_SUB_CATEGORIES_CHECKBOX);
	}

	public void selectManufacturerDropDownList(String manufacturerValue) {
		waitForElementClickable(driver, UserSearchPUI.MANUFACTURER_DROP_DOWN_LIST);
		selectItemInDefaultDropdown(driver, UserSearchPUI.MANUFACTURER_DROP_DOWN_LIST, manufacturerValue);
	}
}
