package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserWishlistPUI;

public class UserWishlistPO extends BasePage {
	private WebDriver driver;

	public UserWishlistPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Verify Product with name {0} is displayed")
	public boolean isWishlistProductDisplayed(String productName) {
		waitForElementVisible(driver, UserWishlistPUI.WISHLIST_PRODUCT_LINK, productName);
		return isElementDisplayed(driver, UserWishlistPUI.WISHLIST_PRODUCT_LINK, productName);
	}

	@Step("Click to Wishlist URL for sharing")
	public void clickToWishlistURLForSharing() {
		waitForElementClickable(driver, UserWishlistPUI.WISHLIST_URL_FOR_SHARING);
		clickToElement(driver, UserWishlistPUI.WISHLIST_URL_FOR_SHARING);
	}

	@Step("Get Wishlist Sharing Title")
	public String getWishlistSharingTitle() {
		waitForElementVisible(driver, UserWishlistPUI.WISHLIST_SHARING_TITLE);
		return getElementText(driver, UserWishlistPUI.WISHLIST_SHARING_TITLE);
	}

	@Step("Select Checkbox 'Add to cart' by product name is '{0}'")
	public void selectCheckboxAddToCartByProductName(String productName) {
		waitForElementClickable(driver, UserWishlistPUI.ADD_TO_CART_CHECKBOX_BY_PRODUCT_NAME, productName);
		checkDefaultCheckboxRadio(driver, UserWishlistPUI.ADD_TO_CART_CHECKBOX_BY_PRODUCT_NAME, productName);
	}

	@Step("Click to 'Add to cart' button")
	public UserShoppingCartPO clickToAddToCartButton() {
		waitForElementClickable(driver, UserWishlistPUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, UserWishlistPUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManager.getUserShoppingCartPO(driver);
	}

	@Step("Verify Wishlist Empty message is displayed")
	public boolean isWishlistEmptyMessageDisplayed() {
		waitForElementVisible(driver, UserWishlistPUI.EMPTY_WISHLIST_MESSAGE);
		return isElementDisplayed(driver, UserWishlistPUI.EMPTY_WISHLIST_MESSAGE);
	}

	@Step("Click to Remove button of {0} product")
	public void clickToRemoveButtonByProductName(String productName) {
		waitForElementClickable(driver, UserWishlistPUI.REMOVE_BUTTON, productName);
		clickToElement(driver, UserWishlistPUI.REMOVE_BUTTON, productName);
	}

	@Step("Click to Home Page logo")
	public UserHomePO clickToHomePageLogo() {
		waitForElementClickable(driver, UserWishlistPUI.HOME_PAGE_LOGO);
		clickToElement(driver, UserWishlistPUI.HOME_PAGE_LOGO);
		return PageGeneratorManager.getUserHomePO(driver);
	}
}
