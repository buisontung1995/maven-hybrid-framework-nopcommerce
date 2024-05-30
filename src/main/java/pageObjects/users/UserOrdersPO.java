package pageObjects.users;

import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserOrdersPUI;

public class UserOrdersPO extends UserSideBarMyAccountPO{
	private WebDriver driver;

	public UserOrdersPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Get Order Information Value by Order Number and Label")
    public String getOrderInformationValueByOrderNumberAndLabel(String orderNumber, String information) {
		waitForElementVisible(driver, UserOrdersPUI.ORDER_INFORMATION_VALUE_BY_ORDER_NUMBER_AND_LABEL, orderNumber, information);
		return getElementText(driver, UserOrdersPUI.ORDER_INFORMATION_VALUE_BY_ORDER_NUMBER_AND_LABEL, orderNumber, information);
    }

	public boolean isLastOrderNumberDisplayed(String orderNumber) {
		waitForElementVisible(driver, UserOrdersPUI.ORDER_NUMBER, orderNumber);
		return isElementDisplayed(driver, UserOrdersPUI.ORDER_NUMBER, orderNumber);
	}

	public UserOrderDetailsPO clickToDetailsButtonByOrderNumber(String orderNumber) {
		waitForElementClickable(driver, UserOrdersPUI.DETAILS_BUTTON_BY_ORDER_NUMBER, orderNumber);
		clickToElement(driver, UserOrdersPUI.DETAILS_BUTTON_BY_ORDER_NUMBER, orderNumber);
		return PageGeneratorManager.getUserOrderDetailsPO(driver);
	}
}
