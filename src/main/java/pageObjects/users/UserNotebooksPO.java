package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import pageUIs.users.UserNotebooksPUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserNotebooksPO extends BasePage {
	private WebDriver driver;

	public UserNotebooksPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Select {0} option in Sort By Drop down list")
	public void selectSortByDropDownList(String sortValue) {
		waitForElementClickable(driver, UserNotebooksPUI.SORT_BY_DROP_DOWN_LIST);
		selectItemInDefaultDropdown(driver, UserNotebooksPUI.SORT_BY_DROP_DOWN_LIST, sortValue);
	}

	@Step("Verify is Product Name sort by ascending")
	public boolean isProductNameSortByAscending() {
		ArrayList<String> productUINameList = new ArrayList<String>();
		List<WebElement> productNameList = getListWebElement(driver, UserNotebooksPUI.PRODUCT_NAME);

		for (WebElement productName : productNameList){
			productUINameList.add(productName.getText());
		}

		ArrayList<String> productSortNameList = new ArrayList<String>();
		for (String product : productUINameList){
			productSortNameList.add(product);
		}

		Collections.sort(productSortNameList);
		return productSortNameList.equals(productUINameList);
	}

	@Step("Verify is Product Name sort by descending")
	public boolean isProductNameSortByDescending() {
		ArrayList<String> productUINameList = new ArrayList<String>();
		List<WebElement> productNameList = getListWebElement(driver, UserNotebooksPUI.PRODUCT_NAME);

		for (WebElement productName : productNameList){
			productUINameList.add(productName.getText());
		}

		ArrayList<String> productSortNameList = new ArrayList<String>();
		for (String product : productUINameList){
			productSortNameList.add(product);
		}

		Collections.sort(productSortNameList);
		Collections.reverse(productSortNameList);
		return productSortNameList.equals(productUINameList);
	}

	@Step("Verify is Product Price sort by ascending")
	public boolean isProductPriceSortByAscending() {
		ArrayList<Float> productUIPriceList = new ArrayList<Float>();
		List<WebElement> productPriceList = getListWebElement(driver, UserNotebooksPUI.PRODUCT_PRICE);

		for (WebElement productPrice : productPriceList){
			productUIPriceList.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}

		ArrayList<Float> productSortPriceList = new ArrayList<Float>();
		for (Float productPrice : productUIPriceList){
			productSortPriceList.add(productPrice);
		}

		Collections.sort(productSortPriceList);
		return productSortPriceList.equals(productUIPriceList);
	}

	@Step("Verify is Product Price sort by descending")
	public boolean isProductPriceSortByDescending() {
		ArrayList<Float> productUIPriceList = new ArrayList<Float>();
		List<WebElement> productPriceList = getListWebElement(driver, UserNotebooksPUI.PRODUCT_PRICE);

		for (WebElement productPrice : productPriceList){
			productUIPriceList.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}

		ArrayList<Float> productSortPriceList = new ArrayList<Float>();
		for (Float productPrice : productUIPriceList){
			productSortPriceList.add(productPrice);
		}

		Collections.sort(productSortPriceList);
		Collections.reverse(productSortPriceList);
		return productSortPriceList.equals(productUIPriceList);
	}

	@Step("Select {0} value from Displayed Drop Down list")
	public void selectDisplayedDropDownList(String number) {
		waitForElementClickable(driver, UserNotebooksPUI.DISPLAYED_DROP_DOWN);
		selectItemInDefaultDropdown(driver, UserNotebooksPUI.DISPLAYED_DROP_DOWN, number);
	}

	@Step("Get Total Products Displayed Per Page")
	public int getTotalProductsDisplayedPerPage() {
		waitForAllElementVisible(driver, UserNotebooksPUI.PRODUCT_ITEM);
		return getElementSize(driver, UserNotebooksPUI.PRODUCT_ITEM);
	}

	@Step("Verify Next Page icon is displayed")
	public boolean isNextPageIconDisplayed() {
		waitForElementVisible(driver, UserNotebooksPUI.NEXT_PAGE_ICON);
		return isElementDisplayed(driver, UserNotebooksPUI.NEXT_PAGE_ICON);
	}

	@Step("Click To Next Page Icon")
	public void clickToNextPageIcon() {
		waitForElementVisible(driver, UserNotebooksPUI.NEXT_PAGE_ICON);
		clickToElement(driver, UserNotebooksPUI.NEXT_PAGE_ICON);
	}

	@Step("Verify Previous icon is displayed")
	public boolean isPreviousPageIconDisplayed() {
		waitForElementVisible(driver, UserNotebooksPUI.PREVIOUS_PAGE_ICON);
		return isElementDisplayed(driver, UserNotebooksPUI.PREVIOUS_PAGE_ICON);
	}

	@Step("Verify Next Page icon is undisplayed")
	public boolean isNextPageIconUndisplayed() {
		return isElementUndisplayed(driver, UserNotebooksPUI.NEXT_PAGE_ICON);
	}

	@Step("Click to '{0}' product")
    public UserProductDetailsPO clickToProductByProductName(String productName) {
		waitForElementClickable(driver, UserNotebooksPUI.PRODUCT_NAME_LINK, productName);
		clickToElement(driver, UserNotebooksPUI.PRODUCT_NAME_LINK, productName);
		return PageGeneratorManager.getUserProductDetailsPO(driver);
    }

	@Step("Click To 'Recently viewed products' link")
	public UserRecentlyViewedProductsPO clickToRecentlyViewedProductsLink() {
		waitForElementClickable(driver, UserNotebooksPUI.RECENTLY_VIEWED_PRODUCTS_LINK);
		clickToElement(driver, UserNotebooksPUI.RECENTLY_VIEWED_PRODUCTS_LINK);
		return PageGeneratorManager.getUserRecentlyViewedProductsPO(driver);
	}
}
