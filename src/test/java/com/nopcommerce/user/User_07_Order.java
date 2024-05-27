package com.nopcommerce.user;

import com.nopcommerce.common.Common_01_Register;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.users.*;
import utilities.Environment;

public class User_07_Order extends BaseTest {
    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserProductDetailsPO userProductDetailsPage;
    private UserShoppingCartPO userShoppingCartPage;
    private UserDesktopsPO userDesktopsPage;
    private String productName;
    private Environment env;

    @Parameters({"envName", "browserName", "browserVersion", "osName"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("chrome") String browserName, @Optional("latest") String browserVersion, @Optional("Windows 11") String osName) {
        String environmentConfig = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentConfig);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(env.appUrl(), envName, browserName, browserVersion, osName);
        userHomePage = PageGeneratorManager.getUserHomePO(driver);

        log.info("Precondition - Home page - Step 01: Click to Login Link");
        userLoginPage = userHomePage.clickToLoginLink();

        log.info("Precondition - Login Page - Step 02 - Input to Email textbox with value '" + Common_01_Register.emailAddress + "'");
        userLoginPage.inputToEmailTextBox(Common_01_Register.emailAddress);

        log.info("Precondition - Login Page - Step 03 - Input to Password textbox with value '" + Common_01_Register.password + "'");
        userLoginPage.inputToPasswordTextBox(Common_01_Register.password);

        log.info("Precondition - Login Page - Step 04 - Click to Login button");
        userHomePage = userLoginPage.clickToLoginButton();

        productName = "Build your own computer";
        log.info("Precondition - Home Page - Step 05: Click to " + productName + " product");
        userProductDetailsPage = userHomePage.clickToProductDetailByName(productName);
    }

    @Description("Order_01 - Add product to cart")
    @Story("Order")
    @Test
    public void Order_01_Add_Product_To_Cart() {
        log.info("Start test case 'Order_01 - Add product to cart'");
        log.info("Order_01 - Product Details Page - Step 01 - Select Processor");
        userProductDetailsPage.selectProcessorDropDownList("2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");

        log.info("Order_01 - Product Details Page - Step 02 - Select RAM");
        userProductDetailsPage.selectRAMDropDownList("8GB [+$60.00]");

        log.info("Order_01 - Product Details Page - Step 03 - Select HDD");
        userProductDetailsPage.selectHDDRadioButton("400 GB [+$100.00]");

        log.info("Order_01 - Product Details Page - Step 04 - Select OS");
        userProductDetailsPage.selectOSRadioButton("Vista Premium [+$60.00]");

        log.info("Order_01 - Product Details Page - Step 05 - Select Software");
        userProductDetailsPage.selectSoftwareCheckbox("Microsoft Office [+$50.00]");
        userProductDetailsPage.selectSoftwareCheckbox("Acrobat Reader [+$10.00]");
        userProductDetailsPage.selectSoftwareCheckbox("Total Commander [+$5.00]");

        String productAttribute = "Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\n" +
                "RAM: 8GB [+$60.00]\n" +
                "HDD: 400 GB [+$100.00]\n" +
                "OS: Vista Premium [+$60.00]\n" +
                "Software: Microsoft Office [+$50.00]\n" +
                "Software: Acrobat Reader [+$10.00]\n" +
                "Software: Total Commander [+$5.00]";

        log.info("Order_01 - Product Details Page - Step 06 - Click to 'Add to cart' button");
        userProductDetailsPage.clickToAddToCartButton();

        log.info("Order_01 - Product Details Page - Step 07 - Get Bar Notification text");
        Assert.assertEquals(userProductDetailsPage.getBarNotificationText(driver), "The product has been added to your shopping cart");

        log.info("Order_01 - Product Details Page - Step 08 - Click Close button from notification");
        userProductDetailsPage.clickToCloseNotificationButton(driver);

        log.info("Order_01 - Product Details Page - Step 09 - Get Total Item from Shopping cart link");
        Assert.assertEquals(userProductDetailsPage.getTotalItemFromShoppingCartLink(), "1");

        log.info("Order_01 - Product Details Page - Step 10 - Hover Shopping cart link");
        userProductDetailsPage.hoverShoppingCartLink();

        log.info("Order_01 - Product Details Page - Step 11 - Get Total item notification from Flyout");
        Assert.assertEquals(userProductDetailsPage.getTotalItemNotificationFromFlyout(), "1 item(s)");

        log.info("Order_01 - Product Details Page - Step 12 - Get Product name from Flyout");
        Assert.assertEquals(userProductDetailsPage.getProductNameFromFlyout(), productName);

        log.info("Order_01 - Product Details Page - Step 13 - Get Product attribute from Flyout");
        Assert.assertTrue(userProductDetailsPage.getProductAttributeFromFlyout().contains(productAttribute));

        log.info("Order_01 - Product Details Page - Step 14 - Get Product price from Flyout");
        Assert.assertEquals(userProductDetailsPage.getProductPriceFromFlyout(), "$1,500.00");

        log.info("Order_01 - Product Details Page - Step 15 - Get Product quantity from Flyout");
        Assert.assertEquals(userProductDetailsPage.getProductQuantityFromFlyout(), "1");

        log.info("Order_01 - Product Details Page - Step 16 - Get Sub-total value from Flyout");
        Assert.assertEquals(userProductDetailsPage.getSubTotalValueFromFlyout(), "$1,500.00");
    }

    @Description("Order_02 - Edit product in shopping cart")
    @Story("Order")
    @Test
    public void Order_02_Edit_Product_In_Shopping_Cart() {
        log.info("Start test case 'Order_02 - Edit product in shopping cart'");
        log.info("Order_02 - Product Details Page - Step 01 - Select Processor");
        userShoppingCartPage = userProductDetailsPage.clickToGoToCartButtonFromFlyout();

        log.info("Order_02 - Shopping Cart Page - Step 02 - Waiting Ajax Loading icon disappear");
        userShoppingCartPage.waitAjaxLoadingIconDisappear();

        log.info("Order_02 - Shopping Cart Page - Step 03 - Click to 'Edit' link of product name " + productName);
        userProductDetailsPage = userShoppingCartPage.clickToEditLink(productName);

        log.info("Order_02 - Product Details Page - Step 04 - Select Processor");
        userProductDetailsPage.selectProcessorDropDownList("2.2 GHz Intel Pentium Dual-Core E2200");

        log.info("Order_02 - Product Details Page - Step 05 - Select RAM");
        userProductDetailsPage.selectRAMDropDownList("4GB [+$20.00]");

        log.info("Order_02 - Product Details Page - Step 06 - Select HDD");
        userProductDetailsPage.selectHDDRadioButton("320 GB");

        log.info("Order_02 - Product Details Page - Step 07 - Select OS");
        userProductDetailsPage.selectOSRadioButton("Vista Home [+$50.00]");

        log.info("Order_02 - Product Details Page - Step 08 - Select Software");
        userProductDetailsPage.selectSoftwareCheckbox("Microsoft Office [+$50.00]");
        userProductDetailsPage.unselectSoftwareCheckbox("Acrobat Reader [+$10.00]");
        userProductDetailsPage.unselectSoftwareCheckbox("Total Commander [+$5.00]");

        String productAttribute = "Processor: 2.2 GHz Intel Pentium Dual-Core E2200\n" +
                "RAM: 4GB [+$20.00]\n" +
                "HDD: 320 GB\n" +
                "OS: Vista Home [+$50.00]\n" +
                "Software: Microsoft Office [+$50.00]";

        log.info("Order_02 - Product Details Page - Step 09 - Input to Product Quantity checkbox with value '2'");
        userProductDetailsPage.inputToProductQuantityTextbox("2");
        userProductDetailsPage.sleepInSecond(3);

        //log.info("Order_02 - Product Details Page - Step 10 - Get Product Price and compare with '$1,320.00'");
        //Assert.assertEquals(userProductDetailsPage.getProductPrice(), "$1,320.00");

        log.info("Order_02 - Product Details Page - Step 11 - Click to 'Update' button");
        userProductDetailsPage.clickToUpdateButton();

        log.info("Order_02 - Product Details Page - Step 12 - Get Bar Notification text");
        Assert.assertEquals(userProductDetailsPage.getBarNotificationText(driver), "The product has been added to your shopping cart");

        log.info("Order_02 - Product Details Page - Step 13 - Click Close button from notification");
        userProductDetailsPage.clickToCloseNotificationButton(driver);

        log.info("Order_02 - Product Details Page - Step 14 - Get Total Item from Shopping cart link");
        Assert.assertEquals(userProductDetailsPage.getTotalItemFromShoppingCartLink(), "2");

        log.info("Order_02 - Product Details Page - Step 15 - Hover Shopping cart link");
        userProductDetailsPage.hoverShoppingCartLink();

        log.info("Order_02 - Product Details Page - Step 16 - Get Total item notification from Flyout");
        Assert.assertEquals(userProductDetailsPage.getTotalItemNotificationFromFlyout(), "2 item(s)");

        log.info("Order_02 - Product Details Page - Step 17 - Get Product name from Flyout");
        Assert.assertEquals(userProductDetailsPage.getProductNameFromFlyout(), productName);

        log.info("Order_02 - Product Details Page - Step 18 - Get Product attribute from Flyout");
        Assert.assertTrue(userProductDetailsPage.getProductAttributeFromFlyout().contains(productAttribute));

        log.info("Order_02 - Product Details Page - Step 19 - Get Product price from Flyout");
        Assert.assertEquals(userProductDetailsPage.getProductPriceFromFlyout(), "$1,320.00");

        log.info("Order_02 - Product Details Page - Step 20 - Get Product quantity from Flyout");
        Assert.assertEquals(userProductDetailsPage.getProductQuantityFromFlyout(), "2");

        log.info("Order_02 - Product Details Page - Step 21 - Get Sub-total value from Flyout");
        Assert.assertEquals(userProductDetailsPage.getSubTotalValueFromFlyout(), "$2,640.00");
    }

    @Description("Order_03 - Remove product from cart")
    @Story("Order")
    @Test
    public void Order_03_Remove_Product_From_Cart() {
        log.info("Start test case 'Order_03 - Remove product from cart'");
        log.info("Order_03 - Product Details Page - Step 01 - Click to 'Go to cart' button from flyout");
        userShoppingCartPage = userProductDetailsPage.clickToGoToCartButtonFromFlyout();

        log.info("Order_03 - Shopping Cart Page - Step 02 - Waiting Ajax Loading icon disappear");
        userShoppingCartPage.waitAjaxLoadingIconDisappear();

        log.info("Order_03 - Shopping Cart Page - Step 03 - Click to Remove button by product " + productName);
        userShoppingCartPage.clickToRemoveButtonByProductName(productName);

        log.info("Order_03 - Shopping Cart Page - Step 04 - Get Shopping cart empty message");
        Assert.assertEquals(userShoppingCartPage.getShoppingCartEmptyMessage(), "Your Shopping Cart is empty!");

        log.info("Order_03 - Shopping Cart Page - Step 05 - Verify Product " + productName + " is not displayed");
        Assert.assertTrue(userShoppingCartPage.isSelectedProductUndisplayed(productName));
    }

    @Description("Order_04 - Update shopping cart")
    @Story("Order")
    @Test
    public void Order_04_Update_Shopping_Cart() {
        log.info("Start test case 'Order_04 - Update shopping cart'");
        log.info("Order_04 - Shopping Cart Page - Step 01 - Hover 'Computers' menu");
        userShoppingCartPage.hoverComputersMenu();

        log.info("Order_04 - Shopping Cart Page - Step 02 - Click to 'Desktops' menu");
        userDesktopsPage = userShoppingCartPage.clickToDesktopsSubMenu();

        productName = "Lenovo IdeaCentre 600 All-in-One PC";

        log.info("Order_04 - Desktops Page - Step 03 - Click to product " + productName);
        userProductDetailsPage = userDesktopsPage.clickToProductByProductName(productName);

        log.info("Order_04 - Product Details Page - Step 03 - Click to 'Add to cart' button");
        userProductDetailsPage.clickToAddToCartButton();

        log.info("Order_04 - Product Details Page - Step 04 - Get Bar Notification text");
        Assert.assertEquals(userProductDetailsPage.getBarNotificationText(driver), "The product has been added to your shopping cart");

        log.info("Order_04 - Product Details Page - Step 05 - Click Close button from notification");
        userProductDetailsPage.clickToCloseNotificationButton(driver);

        log.info("Order_04 - Product Details Page - Step 06 - Hover Shopping cart link");
        userProductDetailsPage.hoverShoppingCartLink();

        log.info("Order_04 - Product Details Page - Step 07 - Click to 'Go to cart' button from flyout");
        userShoppingCartPage = userProductDetailsPage.clickToGoToCartButtonFromFlyout();

        log.info("Order_04 - Shopping Cart Page - Step 08 - Waiting Ajax Loading icon disappear");
        userShoppingCartPage.waitAjaxLoadingIconDisappear();
        userShoppingCartPage.sleepInSecond(3);

        log.info("Order_04 - Shopping Cart Page - Step 09 - Input to QTY. checkbox by product " + productName + " with value is '5'");
        userShoppingCartPage.inputToQTYCheckboxByProductName(productName, "5");

        log.info("Order_04 - Shopping Cart Page - Step 10 - Get Total Price by product " + productName);
        Assert.assertEquals(userShoppingCartPage.getTotalPriceByProductName(productName), "$2,500.00");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
