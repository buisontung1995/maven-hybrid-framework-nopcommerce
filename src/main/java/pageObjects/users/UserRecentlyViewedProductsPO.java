package pageObjects.users;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserRecentlyViewedProductsPUI;

public class UserRecentlyViewedProductsPO extends BasePage {
	private WebDriver driver;

	public UserRecentlyViewedProductsPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Verify Product '{0}' is displayed")
	public boolean isProductDisplayedByProductName(String productName) {
		waitForElementVisible(driver, UserRecentlyViewedProductsPUI.PRODUCT_NAME_LINK, productName);
		return isElementDisplayed(driver, UserRecentlyViewedProductsPUI.PRODUCT_NAME_LINK, productName);
	}
}
