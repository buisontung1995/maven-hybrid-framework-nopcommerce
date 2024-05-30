package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserCheckoutPUI;

public class UserCheckoutPO extends BasePage {
    private WebDriver driver;

    public UserCheckoutPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Unselect 'Ship to the same address' checkbox")
    public void unselectShipToTheSameAddressCheckbox() {
        waitForElementClickable(driver, UserCheckoutPUI.SHIP_TO_THE_SAME_ADDRESS_CHECKBOX);
        uncheckDefaultCheckbox(driver, UserCheckoutPUI.SHIP_TO_THE_SAME_ADDRESS_CHECKBOX);
    }

    @Step("Input to Checkbox ID '{0}' with value is '{1}'")
    public void inputToCheckboxByID(String ID, String value) {
        waitForElementVisible(driver, UserCheckoutPUI.DYNAMIC_CHECKBOX_BY_ID, ID);
        sendKeyToElement(driver, UserCheckoutPUI.DYNAMIC_CHECKBOX_BY_ID, value, ID);
    }

    @Step("Select value '{1}' from Drop Down List ID '{0}'")
    public void selectDropDownListByID(String ID, String value) {
        waitForElementClickable(driver, UserCheckoutPUI.DYNAMIC_DROP_DOWN_LIST_BY_ID, ID);
        selectItemInDefaultDropdown(driver, UserCheckoutPUI.DYNAMIC_DROP_DOWN_LIST_BY_ID, value, ID);
    }

    @Step("Click to 'Continue' button")
    public void clickToContinueButtonOfCheckoutStep(String stepID) {
        waitForElementClickable(driver, UserCheckoutPUI.CONTINUE_BUTTON_BY_STEP_ID, stepID);
        clickToElement(driver, UserCheckoutPUI.CONTINUE_BUTTON_BY_STEP_ID, stepID);
    }

    @Step("Select Shipping Method Radio Button with value '{0}'")
    public void selectShippingMethodRadioButton(String value) {
        waitForElementClickable(driver, UserCheckoutPUI.SHIPPING_METHOD_RADIO_BUTTON, value);
        checkDefaultCheckboxRadio(driver, UserCheckoutPUI.SHIPPING_METHOD_RADIO_BUTTON, value);
    }

    public void selectPaymentMethodRadioButton(String value) {
        waitForElementClickable(driver, UserCheckoutPUI.PAYMENT_METHOD_RADIO_BUTTON, value);
        checkDefaultCheckboxRadio(driver, UserCheckoutPUI.PAYMENT_METHOD_RADIO_BUTTON, value);
    }

    @Step("Get Payment Information")
    public String getPaymentInformation() {
        waitForElementVisible(driver, UserCheckoutPUI.PAYMENT_INFORMATION);
        return getElementText(driver, UserCheckoutPUI.PAYMENT_INFORMATION);
    }

    @Step("Get Billing Address Information By Class '{0}'")
    public String getBillingAddressInformationByClass(String className) {
        waitForElementVisible(driver, UserCheckoutPUI.BILLING_ADDRESS_INFORMATION_BY_CLASS, className);
        return getElementText(driver, UserCheckoutPUI.BILLING_ADDRESS_INFORMATION_BY_CLASS, className);
    }

    @Step("Get Shipping Address Information By Class '{0}'")
    public String getShippingAddressInformationByClass(String className) {
        waitForElementVisible(driver, UserCheckoutPUI.SHIPPING_ADDRESS_INFORMATION_BY_CLASS, className);
        return getElementText(driver, UserCheckoutPUI.SHIPPING_ADDRESS_INFORMATION_BY_CLASS, className);
    }

    @Step("Get Payment Method")
    public String getPaymentMethod() {
        waitForElementVisible(driver, UserCheckoutPUI.PAYMENT_METHOD_INFORMATION);
        return getElementText(driver, UserCheckoutPUI.PAYMENT_METHOD_INFORMATION);
    }

    @Step("Get Shipping Method")
    public String getShippingMethod() {
        waitForElementVisible(driver, UserCheckoutPUI.SHIPPING_METHOD_INFORMATION);
        return getElementText(driver, UserCheckoutPUI.SHIPPING_METHOD_INFORMATION);
    }

    @Step("Get Cell value By Class '{0}'")
    public String getCellValueByClassName(String className) {
        if (className.equals("product")) {
            String elementLocator = "xpath=//td[@class='%s']/a";
            waitForElementVisible(driver, elementLocator, className);
            return getElementText(driver, elementLocator, className);
        }
        waitForElementVisible(driver, UserCheckoutPUI.CELL_VALUE_BY_CLASS_NAME, className);
        return getElementText(driver, UserCheckoutPUI.CELL_VALUE_BY_CLASS_NAME, className);
    }

    @Step("Get Gift wrapping value")
    public String getGiftWrappingValue() {
        waitForElementVisible(driver, UserCheckoutPUI.GIFT_WRAPPING_VALUE);
        return getElementText(driver, UserCheckoutPUI.GIFT_WRAPPING_VALUE).split(":")[1].replace(" ", "");
    }

    @Step("Get Value from Cart Total table by Label {0}")
    public String getValueFromCartTotalTableByLabel(String label) {
        waitForElementVisible(driver, UserCheckoutPUI.DYNAMIC_VALUE_BY_LABEL_FROM_CART_TOTAL_TABLE, label);
        return getElementText(driver, UserCheckoutPUI.DYNAMIC_VALUE_BY_LABEL_FROM_CART_TOTAL_TABLE, label);
    }

    @Step("Click to 'Confirm' button")
    public void clickToConfirmButton() {
        waitForElementClickable(driver, UserCheckoutPUI.CONFIRM_BUTTON);
        clickToElement(driver, UserCheckoutPUI.CONFIRM_BUTTON);
    }

    @Step("Get Thank You message text")
    public String getThankYouMessageText() {
        waitForElementVisible(driver, UserCheckoutPUI.THANK_YOU_MESSAGE);
        return getElementText(driver, UserCheckoutPUI.THANK_YOU_MESSAGE);
    }

    @Step("Get Order Successfully Message")
    public String getOrderSuccessfullyMessage() {
        waitForElementVisible(driver, UserCheckoutPUI.ORDER_SUCCESSFULLY_MESSAGE);
        return getElementText(driver, UserCheckoutPUI.ORDER_SUCCESSFULLY_MESSAGE);
    }

    @Step("Get Order Number Value")
    public String getOrderNumberValue() {
        waitForElementVisible(driver, UserCheckoutPUI.ORDER_NUMBER_VALUE);
        return getElementText(driver, UserCheckoutPUI.ORDER_NUMBER_VALUE).split(":")[1].replace(" ", "");
    }

    @Step("Click to 'My Account' link")
    public UserCustomerInfoPO clickToMyAccountLink() {
        waitForElementClickable(driver, UserCheckoutPUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserCheckoutPUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserCustomerInfoPO(driver);
    }

    @Step("Wait for Loading Icon invisible")
    public void waitForLoadingIconInvisible() {
        waitForElementInvisible(driver, UserCheckoutPUI.LOADING_ICON);
    }
}
