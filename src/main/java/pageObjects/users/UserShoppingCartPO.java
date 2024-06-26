package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserShoppingCartPUI;

public class UserShoppingCartPO extends BasePage {
    private WebDriver driver;

    public UserShoppingCartPO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Verify {0} product is displayed")
    public boolean isSelectedProductDisplayed(String productName) {
        waitForElementVisible(driver, UserShoppingCartPUI.SELECTED_PRODUCT_LINK, productName);
        return isElementDisplayed(driver, UserShoppingCartPUI.SELECTED_PRODUCT_LINK, productName);
    }

    @Step("Click to Wishlist link")
    public UserWishlistPO clickToWishlistLink() {
        waitForElementClickable(driver, UserShoppingCartPUI.WISHLIST_LINK);
        clickToElement(driver, UserShoppingCartPUI.WISHLIST_LINK);
        return PageGeneratorManager.getUserWishlistPO(driver);
    }

    @Step("Waiting Ajax Loading Icon disappear")
    public void waitAjaxLoadingIconDisappear() {
        waitForElementInvisible(driver, UserShoppingCartPUI.AJAX_LOADING_ICON);
    }

    @Step("Click to 'Edit' link from product {0}")
    public UserProductDetailsPO clickToEditLink(String productName) {
        waitForElementClickable(driver, UserShoppingCartPUI.EDIT_LINK_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserShoppingCartPUI.EDIT_LINK_BY_PRODUCT_NAME, productName);
        return PageGeneratorManager.getUserProductDetailsPO(driver);
    }

    @Step("Click to 'Remove' button from product {0}")
    public void clickToRemoveButtonByProductName(String productName) {
        waitForElementClickable(driver, UserShoppingCartPUI.REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserShoppingCartPUI.REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
    }

    @Step("Get Shopping cart empty message")
    public String getShoppingCartEmptyMessage() {
        waitForElementVisible(driver, UserShoppingCartPUI.SHOPPING_CART_EMPTY_MESSAGE);
        return getElementText(driver, UserShoppingCartPUI.SHOPPING_CART_EMPTY_MESSAGE);
    }

    @Step("Verify Product {0} is not displayed")
    public boolean isSelectedProductUndisplayed(String productName) {
        return isElementUndisplayed(driver, UserShoppingCartPUI.SELECTED_PRODUCT_LINK, productName);
    }

    @Step("Hover 'Computers' Menu")
    public void hoverComputersMenu() {
        waitForElementVisible(driver, UserShoppingCartPUI.COMPUTERS_MENU);
        hoverMouseToElement(driver, UserShoppingCartPUI.COMPUTERS_MENU);
    }

    @Step("Click to 'Desktops' Submenu")
    public UserDesktopsPO clickToDesktopsSubMenu() {
        waitForElementClickable(driver, UserShoppingCartPUI.DESKTOPS_SUB_MENU);
        clickToElement(driver, UserShoppingCartPUI.DESKTOPS_SUB_MENU);
        return PageGeneratorManager.getUserDesktopsPO(driver);
    }

    @Step("Input to QTY. checkbox of product {0} with value {1}")
    public void inputToQTYCheckboxByProductName(String productName, String number) {
        waitForElementVisible(driver, UserShoppingCartPUI.QUANTITY_CHECKBOX_BY_PRODUCT_NAME, productName);
        pressKeyToElement(driver, UserShoppingCartPUI.QUANTITY_CHECKBOX_BY_PRODUCT_NAME, Keys.DELETE, productName);
        sendKeyToElement(driver, UserShoppingCartPUI.QUANTITY_CHECKBOX_BY_PRODUCT_NAME, number, productName);
        pressKeyToElement(driver, UserShoppingCartPUI.QUANTITY_CHECKBOX_BY_PRODUCT_NAME, Keys.ENTER, productName);
        waitAjaxLoadingIconDisappear();
    }

    @Step("Get Total price of product {0}")
    public String getTotalPriceByProductName(String productName) {
        waitForElementVisible(driver, UserShoppingCartPUI.TOTAL_PRICE_BY_PRODUCT_NAME, productName);
        return getElementText(driver, UserShoppingCartPUI.TOTAL_PRICE_BY_PRODUCT_NAME, productName);
    }

    @Step("Click to 'Notebooks' Submenu")
    public UserNotebooksPO clickToNotebooksSubMenu() {
        waitForElementClickable(driver, UserShoppingCartPUI.NOTEBOOKS_SUB_MENU);
        clickToElement(driver, UserShoppingCartPUI.NOTEBOOKS_SUB_MENU);
        return PageGeneratorManager.getUserNotebooksPO(driver);
    }

    @Step("Select Gift Wrapping Drop Down list with value '{0}'")
    public void selectGiftWrappingDropDownList(String value) {
        waitForElementClickable(driver, UserShoppingCartPUI.GIFT_WRAPPING_DROP_DOWN_LIST);
        selectItemInDefaultDropdown(driver, UserShoppingCartPUI.GIFT_WRAPPING_DROP_DOWN_LIST, value);
    }

    @Step("Select Term of Service Checkbox")
    public void selectTermOfServiceCheckbox() {
        waitForElementClickable(driver, UserShoppingCartPUI.TERM_OF_SERVICE_CHECKBOX);
        checkDefaultCheckboxRadio(driver, UserShoppingCartPUI.TERM_OF_SERVICE_CHECKBOX);
    }

    @Step("Click to 'Checkout' button")
    public UserCheckoutPO clickToCheckoutButton() {
        waitForElementClickable(driver, UserShoppingCartPUI.CHECKOUT_BUTTON);
        clickToElement(driver, UserShoppingCartPUI.CHECKOUT_BUTTON);
        return PageGeneratorManager.getUserCheckoutPO(driver);
    }
}
