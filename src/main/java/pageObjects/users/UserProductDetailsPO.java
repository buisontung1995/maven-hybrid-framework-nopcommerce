package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserProductDetailsPUI;

public class UserProductDetailsPO extends BasePage {
    private WebDriver driver;

    public UserProductDetailsPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click To 'Add your review' link")
    public void clickToAddYourReviewLink() {
        waitForElementClickable(driver, UserProductDetailsPUI.ADD_YOUR_REVIEW_LINK);
        clickToElement(driver, UserProductDetailsPUI.ADD_YOUR_REVIEW_LINK);
    }

    @Step("Input to Review Title textbox with value is {0}")
    public void inputToReviewTitleTextbox(String reviewTitle) {
        waitForElementVisible(driver, UserProductDetailsPUI.REVIEW_TITLE_TEXTBOX);
        sendKeyToElement(driver, UserProductDetailsPUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
    }

    @Step("Input to Review Text textbox with value is {0}")
    public void inputToReviewTextTextbox(String reviewText) {
        waitForElementVisible(driver, UserProductDetailsPUI.REVIEW_TEXT_TEXTBOX);
        sendKeyToElement(driver, UserProductDetailsPUI.REVIEW_TEXT_TEXTBOX, reviewText);
    }

    @Step("Select Rating radio button value is {0}")
    public void selectRatingRadioButton(String rating) {
        waitForElementClickable(driver, UserProductDetailsPUI.DYNAMIC_RATING_RADIO_BUTTON_BY_LABEL, rating);
        checkDefaultCheckboxRadio(driver, UserProductDetailsPUI.DYNAMIC_RATING_RADIO_BUTTON_BY_LABEL, rating);
    }

    @Step("Click to 'Submit review' button")
    public void clickToSubmitReviewButton() {
        waitForElementClickable(driver, UserProductDetailsPUI.SUBMIT_REVIEW_BUTTON);
        clickToElement(driver, UserProductDetailsPUI.SUBMIT_REVIEW_BUTTON);
    }

    @Step("Click to 'My Account' link")
    public UserCustomerInfoPO clickToMyAccountLink() {
        waitForElementClickable(driver, UserProductDetailsPUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserProductDetailsPUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserCustomerInfoPO(driver);
    }

    @Step("Click to 'Add to wishlist' button")
    public void clickToAddToWishlistButton() {
        waitForElementClickable(driver, UserProductDetailsPUI.ADD_TO_WISHLIST_BUTTON);
        clickToElement(driver, UserProductDetailsPUI.ADD_TO_WISHLIST_BUTTON);
    }

    @Step("Click to 'Wishlist' link from Notification")
    public UserWishlistPO clickToWishlistLinkFromNotification() {
        waitForElementClickable(driver, UserProductDetailsPUI.WISHLIST_LINK_FROM_NOTIFICATION);
        clickToElement(driver, UserProductDetailsPUI.WISHLIST_LINK_FROM_NOTIFICATION);
        return PageGeneratorManager.getUserWishlistPO(driver);
    }

    @Step("Select Processor Drop Down List with value {0}")
    public void selectProcessorDropDownList(String value) {
        waitForElementClickable(driver, UserProductDetailsPUI.PROCESSOR_DROP_DOWN_LIST);
        selectItemInDefaultDropdown(driver, UserProductDetailsPUI.PROCESSOR_DROP_DOWN_LIST, value);
    }

    @Step("Select RAM Drop Down List with value {0}")
    public void selectRAMDropDownList(String value) {
        waitForElementClickable(driver, UserProductDetailsPUI.RAM_DROP_DOWN_LIST);
        selectItemInDefaultDropdown(driver, UserProductDetailsPUI.RAM_DROP_DOWN_LIST, value);
    }

    @Step("Select HDD Radio button with value {0}")
    public void selectHDDRadioButton(String value) {
        waitForElementClickable(driver, UserProductDetailsPUI.HDD_RADIO_BUTTON, value);
        checkDefaultCheckboxRadio(driver, UserProductDetailsPUI.HDD_RADIO_BUTTON, value);
    }

    @Step("Select OS Radio button with value {0}")
    public void selectOSRadioButton(String value) {
        waitForElementClickable(driver, UserProductDetailsPUI.OS_RADIO_BUTTON, value);
        checkDefaultCheckboxRadio(driver, UserProductDetailsPUI.OS_RADIO_BUTTON, value);
    }

    @Step("Select Software Checkbox with value {0}")
    public void selectSoftwareCheckbox(String value) {
        waitForElementClickable(driver, UserProductDetailsPUI.SOFTWARE_CHECKBOX, value);
        checkDefaultCheckboxRadio(driver, UserProductDetailsPUI.SOFTWARE_CHECKBOX, value);
    }

    @Step("Click to 'ADD TO CART' button")
    public void clickToAddToCartButton() {
        waitForElementClickable(driver, UserProductDetailsPUI.ADD_TO_CART_BUTTON);
        clickToElement(driver, UserProductDetailsPUI.ADD_TO_CART_BUTTON);
    }

    @Step("Get Total item from Shopping cart Link")
    public String getTotalItemFromShoppingCartLink() {
        waitForElementVisible(driver, UserProductDetailsPUI.TOTAL_ITEM_FROM_SHOPPING_CART_LINK);
        return getElementText(driver, UserProductDetailsPUI.TOTAL_ITEM_FROM_SHOPPING_CART_LINK).replace("(", "").replace(")", "");
    }

    @Step("Hover Shopping cart link")
    public void hoverShoppingCartLink() {
        waitForElementVisible(driver, UserProductDetailsPUI.SHOPPING_CART_LINK);
        hoverMouseToElement(driver, UserProductDetailsPUI.SHOPPING_CART_LINK);
    }

    @Step("Get Total Items Notification from Flyout")
    public String getTotalItemNotificationFromFlyout() {
        waitForElementVisible(driver, UserProductDetailsPUI.TOTAL_ITEM_FROM_FLYOUT);
        return getElementText(driver, UserProductDetailsPUI.TOTAL_ITEM_FROM_FLYOUT);
    }

    @Step("Get Product Name from Flyout")
    public String getProductNameFromFlyout() {
        waitForElementVisible(driver, UserProductDetailsPUI.PRODUCT_NAME_FROM_FLYOUT);
        return getElementText(driver, UserProductDetailsPUI.PRODUCT_NAME_FROM_FLYOUT);
    }

    @Step("Get Product Attribute from Flyout")
    public String getProductAttributeFromFlyout() {
        waitForElementVisible(driver, UserProductDetailsPUI.PRODUCT_ATTRIBUTE_FROM_FLYOUT);
        return getElementText(driver, UserProductDetailsPUI.PRODUCT_ATTRIBUTE_FROM_FLYOUT);
    }

    @Step("Get Product Price from Flyout")
    public String getProductPriceFromFlyout() {
        waitForElementVisible(driver, UserProductDetailsPUI.PRODUCT_PRICE_FROM_FLYOUT);
        return getElementText(driver, UserProductDetailsPUI.PRODUCT_PRICE_FROM_FLYOUT);
    }

    @Step("Get Product Quantity from Flyout")
    public String getProductQuantityFromFlyout() {
        waitForElementVisible(driver, UserProductDetailsPUI.PRODUCT_QUANTITY_FROM_FLYOUT);
        return getElementText(driver, UserProductDetailsPUI.PRODUCT_QUANTITY_FROM_FLYOUT);
    }

    @Step("Get Sub-total value from Flyout")
    public String getSubTotalValueFromFlyout() {
        waitForElementVisible(driver, UserProductDetailsPUI.SUB_TOTAL_VALUE_FROM_FLYOUT);
        return getElementText(driver, UserProductDetailsPUI.SUB_TOTAL_VALUE_FROM_FLYOUT);
    }

    @Step("Click To 'Go to cart' button from Flyout")
    public UserShoppingCartPO clickToGoToCartButtonFromFlyout() {
        waitForElementClickable(driver, UserProductDetailsPUI.GO_TO_CART_BUTTON_FROM_FLYOUT);
        clickToElement(driver, UserProductDetailsPUI.GO_TO_CART_BUTTON_FROM_FLYOUT);
        return PageGeneratorManager.getUserShoppingCartPO(driver);
    }

    @Step("Unselect {0} checkbox")
    public void unselectSoftwareCheckbox(String software) {
        waitForElementClickable(driver, UserProductDetailsPUI.SOFTWARE_CHECKBOX, software);
        uncheckDefaultCheckbox(driver, UserProductDetailsPUI.SOFTWARE_CHECKBOX, software);
    }

    @Step("Get Product Price")
    public String getProductPrice() {
        waitForElementVisible(driver, UserProductDetailsPUI.PRODUCT_PRICE);
        return getElementText(driver, UserProductDetailsPUI.PRODUCT_PRICE);
    }

    @Step("Input to Product Quantity checkbox with value is {0}")
    public void inputToProductQuantityTextbox(String number) {
        waitForElementVisible(driver, UserProductDetailsPUI.PRODUCT_QUANTITY_TEXTBOX);
        sendKeyToElement(driver, UserProductDetailsPUI.PRODUCT_QUANTITY_TEXTBOX, number);
    }

    @Step("Click to 'Update' button")
    public void clickToUpdateButton() {
        waitForElementClickable(driver, UserProductDetailsPUI.UPDATE_BUTTON);
        clickToElement(driver, UserProductDetailsPUI.UPDATE_BUTTON);
    }

    @Step("Get SKU Value")
    public String getSKUValue() {
        waitForElementVisible(driver, UserProductDetailsPUI.SKU_VALUE);
        return getElementText(driver, UserProductDetailsPUI.SKU_VALUE);
    }

    @Step("Get Quantity Value")
    public String getQuantityValue() {
        waitForElementVisible(driver, UserProductDetailsPUI.QUANTITY_TEXTBOX);
        return getElementAttribute(driver, UserProductDetailsPUI.QUANTITY_TEXTBOX, "value");
    }
}
