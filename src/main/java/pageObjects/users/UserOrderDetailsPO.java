package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserOrderDetailsPUI;

public class UserOrderDetailsPO extends BasePage {
	private WebDriver driver;

	public UserOrderDetailsPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderNumberValue() {
		waitForElementVisible(driver, UserOrderDetailsPUI.ORDER_NUMBER_VALUE);
		return getElementText(driver, UserOrderDetailsPUI.ORDER_NUMBER_VALUE).split("#")[1];
	}

	public String getOrderInformationValueByClass(String className) {
		waitForElementVisible(driver, UserOrderDetailsPUI.ORDER_INFORMATION_VALUE_BY_CLASS, className);
		return getElementText(driver, UserOrderDetailsPUI.ORDER_INFORMATION_VALUE_BY_CLASS, className).split(": ")[1];
	}

	@Step("Get Billing Address Information By Class '{0}'")
	public String getBillingAddressInformationByClass(String className) {
		waitForElementVisible(driver, UserOrderDetailsPUI.BILLING_ADDRESS_INFORMATION_BY_CLASS, className);
		return getElementText(driver, UserOrderDetailsPUI.BILLING_ADDRESS_INFORMATION_BY_CLASS, className);
	}

	@Step("Get Shipping Address Information By Class '{0}'")
	public String getShippingAddressInformationByClass(String className) {
		waitForElementVisible(driver, UserOrderDetailsPUI.SHIPPING_ADDRESS_INFORMATION_BY_CLASS, className);
		return getElementText(driver, UserOrderDetailsPUI.SHIPPING_ADDRESS_INFORMATION_BY_CLASS, className);
	}


	@Step("Get Payment Method")
	public String getPaymentMethod() {
		waitForElementVisible(driver, UserOrderDetailsPUI.PAYMENT_METHOD_INFORMATION);
		return getElementText(driver, UserOrderDetailsPUI.PAYMENT_METHOD_INFORMATION);
	}

	@Step("Get Shipping Method")
	public String getShippingMethod() {
		waitForElementVisible(driver, UserOrderDetailsPUI.SHIPPING_METHOD_INFORMATION);
		return getElementText(driver, UserOrderDetailsPUI.SHIPPING_METHOD_INFORMATION);
	}

	@Step("Get Cell value By Class '{0}'")
	public String getCellValueByClassName(String className) {
		if (className == "product") {
			String elementLocator = "xpath=//td[@class='%s']//a";
			waitForElementVisible(driver, elementLocator, className);
			return getElementText(driver, elementLocator, className);
		}
		waitForElementVisible(driver, UserOrderDetailsPUI.CELL_VALUE_BY_CLASS_NAME, className);
		return getElementText(driver, UserOrderDetailsPUI.CELL_VALUE_BY_CLASS_NAME, className);
	}

	@Step("Get Gift wrapping value")
	public String getGiftWrappingValue() {
		waitForElementVisible(driver, UserOrderDetailsPUI.GIFT_WRAPPING_VALUE);
		return getElementText(driver, UserOrderDetailsPUI.GIFT_WRAPPING_VALUE).split(":")[1].replace(" ", "");
	}

	@Step("Get Value from Cart Total table by Label {0}")
	public String getValueFromCartTotalTableByLabel(String label) {
		waitForElementVisible(driver, UserOrderDetailsPUI.DYNAMIC_VALUE_BY_LABEL_FROM_CART_TOTAL_TABLE, label);
		return getElementText(driver, UserOrderDetailsPUI.DYNAMIC_VALUE_BY_LABEL_FROM_CART_TOTAL_TABLE, label);
	}

	@Step("Get Payment Status value")
	public String getPaymentStatus() {
		waitForElementVisible(driver, UserOrderDetailsPUI.PAYMENT_STATUS_INFORMATION);
		return getElementText(driver, UserOrderDetailsPUI.PAYMENT_STATUS_INFORMATION);
	}

	@Step("Get Shipping Status value")
	public String getShippingStatus() {
		waitForElementVisible(driver, UserOrderDetailsPUI.SHIPPING_STATUS_INFORMATION);
		return getElementText(driver, UserOrderDetailsPUI.SHIPPING_STATUS_INFORMATION);
	}

	@Step("Hover 'Computers' Menu")
	public void hoverComputersMenu() {
		waitForElementVisible(driver, UserOrderDetailsPUI.COMPUTERS_MENU);
		hoverMouseToElement(driver, UserOrderDetailsPUI.COMPUTERS_MENU);
	}

	@Step("Click to 'Notebooks' Submenu")
	public UserNotebooksPO clickToNotebooksSubMenu() {
		waitForElementClickable(driver, UserOrderDetailsPUI.NOTEBOOKS_SUB_MENU);
		clickToElement(driver, UserOrderDetailsPUI.NOTEBOOKS_SUB_MENU);
		return PageGeneratorManager.getUserNotebooksPO(driver);
	}

	public UserShoppingCartPO clickToReOrderButton() {
		waitForElementClickable(driver, UserOrderDetailsPUI.RE_ORDER_BUTTON);
		clickToElement(driver, UserOrderDetailsPUI.RE_ORDER_BUTTON);
		return PageGeneratorManager.getUserShoppingCartPO(driver);
	}
}
