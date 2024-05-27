package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.users.UserDesktopsPUI;
import pageUIs.users.UserNotebooksPUI;
import pageUIs.users.UserProductDetailsPUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDesktopsPO extends BasePage {
	private WebDriver driver;

	public UserDesktopsPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Product {0}")
	public UserProductDetailsPO clickToProductByProductName(String productName) {
		waitForElementClickable(driver, UserDesktopsPUI.PRODUCT_NAME_LINK, productName);
		clickToElement(driver, UserDesktopsPUI.PRODUCT_NAME_LINK, productName);
		return PageGeneratorManager.getUserProductDetailsPO(driver);
	}
}
