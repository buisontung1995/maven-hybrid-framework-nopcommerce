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
    private UserNotebooksPO userNotebooksPage;
    private UserCheckoutPO userCheckoutPage;
    private UserCustomerInfoPO userCustomerInfoPO;
    private UserOrdersPO userOrdersPage;
    private UserOrderDetailsPO userOrderDetailsPage;
    private String productName, SKU, price, quantity, totalPrice;
    private String firstName, lastName, email;
    private String countryBilling, stateBilling, cityBilling, firstAddressBilling, zipBilling, phoneBilling;
    private String countryShipping, stateShipping, cityShipping, firstAddressShipping, zipShipping, phoneShipping, informationShipping;
    private String orderNumber, orderStatus, orderTotalValue;
    private String paymentInformation;
    private String cardHolderName, cardNumber, cardCode;
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

        log.info("Order_04 - Shopping Cart Page - Step 11 - Click to Remove button by product " + productName);
        userShoppingCartPage.clickToRemoveButtonByProductName(productName);
    }

    @Description("Order_05 - Checkout: Payment method by Cheque")
    @Story("Order")
    @Test
    public void Order_05_Checkout_Payment_Cheque() {
        log.info("Start test case 'Checkout: Payment method by Cheque'");
        log.info("Order_05 - Shopping Cart Page - Step 01 - Hover 'Computers' menu");
        userShoppingCartPage.hoverComputersMenu();

        log.info("Order_05 - Shopping Cart Page - Step 02 - Click to 'Notebooks' menu");
        userNotebooksPage = userShoppingCartPage.clickToNotebooksSubMenu();

        productName = "Apple MacBook Pro 13-inch";

        log.info("Order_05 - Notebooks Page - Step 03 - Click to product " + productName);
        userProductDetailsPage = userNotebooksPage.clickToProductByProductName(productName);

        log.info("Order_05 - Product Details Page - Step 04 - Get SKU value");
        SKU = userProductDetailsPage.getSKUValue();

        log.info("Order_05 - Product Details Page - Step 05 - Get Product price value");
        price = userProductDetailsPage.getProductPrice();

        log.info("Order_05 - Product Details Page - Step 06 - Get Product quantity value");
        quantity = userProductDetailsPage.getQuantityValue();

        log.info("Order_05 - Product Details Page - Step 07 - Click to 'Add to cart' button");
        userProductDetailsPage.clickToAddToCartButton();

        log.info("Order_05 - Product Details Page - Step 08 - Click to Close button");
        userProductDetailsPage.clickToCloseNotificationButton(driver);

        log.info("Order_05 - Product Details Page - Step 09 - Hover to 'Shopping cart' link");
        userProductDetailsPage.hoverShoppingCartLink();

        log.info("Order_05 - Product Details Page - Step 08 - Click to 'Go to cart' button");
        userShoppingCartPage = userProductDetailsPage.clickToGoToCartButtonFromFlyout();
        userShoppingCartPage.waitAjaxLoadingIconDisappear();

        log.info("Order_05 - Shopping Page - Step 09 - Get Total price by product " + productName);
        totalPrice = userShoppingCartPage.getTotalPriceByProductName(productName);

        log.info("Order_05 - Shopping Page - Step 10 - Select Gift wrapping drop down list with value 'No'");
        userShoppingCartPage.selectGiftWrappingDropDownList("No");

        log.info("Order_05 - Shopping Page - Step 11 - Select Term of service checkbox");
        userShoppingCartPage.selectTermOfServiceCheckbox();

        log.info("Order_05 - Shopping Page - Step 12 - Click to Checkout button");
        userCheckoutPage = userShoppingCartPage.clickToCheckoutButton();

        log.info("Order_05 - Checkout Page - Step 13 - Unselect Ship to the same address checkbox");
        userCheckoutPage.unselectShipToTheSameAddressCheckbox();

        firstName = Common_01_Register.firstName;
        lastName = Common_01_Register.lastName;
        email = Common_01_Register.emailAddress;
        countryBilling = "Viet Nam";
        stateBilling = "Other";
        cityBilling = "Ha Noi";
        firstAddressBilling = "Bac Tu Liem District";
        zipBilling = "100000";
        phoneBilling = "0987654321";

        log.info("Order_05 - Checkout Page - Step 14 - Input to Firstname textbox with value " + firstName);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_FirstName", firstName);

        log.info("Order_05 - Checkout Page - Step 15 - Input to Lastname textbox with value " + lastName);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_LastName", lastName);

        log.info("Order_05 - Checkout Page - Step 16 - Input to Email textbox with value " + email);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_Email", email);

        log.info("Order_05 - Checkout Page - Step 17 - Select Country dropdown with value " + countryBilling);
        userCheckoutPage.selectDropDownListByID("BillingNewAddress_CountryId", countryBilling);
        userCheckoutPage.sleepInSecond(3);

        log.info("Order_05 - Checkout Page - Step 18 - Select State/Provice dropdown with value " + stateBilling);
        userCheckoutPage.waitForLoadingIconInvisible();
        userCheckoutPage.sleepInSecond(3);
        userCheckoutPage.selectDropDownListByID("BillingNewAddress_StateProvinceId", stateBilling);

        log.info("Order_05 - Checkout Page - Step 19 - Input to City textbox with value " + cityBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_City", cityBilling);

        log.info("Order_05 - Checkout Page - Step 20 - Input to Address 1 textbox with value " + firstAddressBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_Address1", firstAddressBilling);

        log.info("Order_05 - Checkout Page - Step 21 - Input to Zip textbox with value " + zipBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_ZipPostalCode", zipBilling);

        log.info("Order_05 - Checkout Page - Step 22 - Input to Phone textbox with value " + phoneBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_PhoneNumber", phoneBilling);

        log.info("Order_05 - Checkout Page - Step 23 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-billing");

        log.info("Order_05 - Checkout Page - Step 24 - Select Shipping Address drop down list with value 'New Address'");
        userCheckoutPage.selectDropDownListByID("shipping-address-select","New Address");

        countryShipping = "United States";
        stateShipping = "Alabama";
        cityShipping = "Alabama ABCD";
        firstAddressShipping = "Alabama EFGH";
        zipShipping = "800000";
        phoneShipping = "0999666888";

        log.info("Order_05 - Checkout Page - Step 25 - Input to Firstname textbox with value " + firstName);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_FirstName", firstName);

        log.info("Order_05 - Checkout Page - Step 26 - Input to Lastname textbox with value " + lastName);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_LastName", lastName);

        log.info("Order_05 - Checkout Page - Step 27 - Input to Email textbox with value " + email);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_Email", email);

        log.info("Order_05 - Checkout Page - Step 28 - Select Country dropdown with value " + countryShipping);
        userCheckoutPage.selectDropDownListByID("ShippingNewAddress_CountryId", countryShipping);
        userCheckoutPage.sleepInSecond(3);

        log.info("Order_05 - Checkout Page - Step 29 - Select State/Provice dropdown with value " + stateShipping);
        userCheckoutPage.waitForLoadingIconInvisible();
        userCheckoutPage.sleepInSecond(3);
        userCheckoutPage.selectDropDownListByID("ShippingNewAddress_StateProvinceId", stateShipping);

        log.info("Order_05 - Checkout Page - Step 30 - Input to City textbox with value " + cityShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_City", cityShipping);

        log.info("Order_05 - Checkout Page - Step 31 - Input to Address 1 textbox with value " + firstAddressShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_Address1", firstAddressShipping);

        log.info("Order_05 - Checkout Page - Step 32 - Input to Zip textbox with value " + zipShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_ZipPostalCode", zipShipping);

        log.info("Order_05 - Checkout Page - Step 33 - Input to Phone textbox with value " + phoneShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_PhoneNumber", phoneShipping);

        log.info("Order_05 - Checkout Page - Step 34 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-shipping");

        log.info("Order_05 - Checkout Page - Step 35 - Select Shipping Method Radio Button with option Ground");
        userCheckoutPage.selectShippingMethodRadioButton("Ground");

        log.info("Order_05 - Checkout Page - Step 36 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-shipping-method");

        log.info("Order_05 - Checkout Page - Step 37 - Select Payment Method Radio Button with option Check / Money Order");
        userCheckoutPage.selectPaymentMethodRadioButton("Check / Money Order");

        log.info("Order_05 - Checkout Page - Step 38 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-payment-method");

        paymentInformation = "NOP SOLUTIONS\n" +
                "your address here,\n" +
                "New York, NY 10001\n" +
                "USA";

        log.info("Order_05 - Checkout Page - Step 39 - Verify Payment Information");
        Assert.assertTrue(userCheckoutPage.getPaymentInformation().contains(paymentInformation));

        log.info("Order_05 - Checkout Page - Step 40 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-payment-info");

        log.info("Order_05 - Checkout Page - Step 41 - Verify Billing Address Information");
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("phone").contains(phoneBilling));
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("country"), countryBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("city"), cityBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("address1"), firstAddressBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("zippostalcode"), zipBilling);

        log.info("Order_05 - Checkout Page - Step 42 - Verify Shipping Address Information");
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("phone").contains(phoneShipping));
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("country"), countryShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("stateprovince"), stateShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("city"), cityShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("address1"), firstAddressShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("zippostalcode"), zipShipping);

        log.info("Order_05 - Checkout Page - Step 43 - Verify Payment method Information");
        Assert.assertEquals(userCheckoutPage.getPaymentMethod(), "Check / Money Order");

        log.info("Order_05 - Checkout Page - Step 44 - Verify Shipping method Information");
        Assert.assertEquals(userCheckoutPage.getShippingMethod(), "Ground");

        log.info("Order_05 - Checkout Page - Step 45 - Verify Product table Information");
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("sku"), SKU);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("product"), productName);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("unit-price"), price);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("quantity"), quantity);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("subtotal"), totalPrice);

        log.info("Order_05 - Checkout Page - Step 46 - Verify Gift Wrapping Information");
        Assert.assertEquals(userCheckoutPage.getGiftWrappingValue(), "No");

        log.info("Order_05 - Checkout Page - Step 47 - Verify Cart Total Table Information");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Sub-Total"), totalPrice);
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Shipping"), "$0.00");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Tax"), "$0.00");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Total"), "$3,600.00");

        log.info("Order_05 - Checkout Page - Step 48 - Get Total value from Cart Total Table");
        orderTotalValue = userCheckoutPage.getValueFromCartTotalTableByLabel("Total");

        log.info("Order_05 - Checkout Page - Step 49 - Click Confirm button");
        userCheckoutPage.clickToConfirmButton();

        log.info("Order_05 - Checkout Page - Step 50 - Verify Thank you message text");
        Assert.assertEquals(userCheckoutPage.getThankYouMessageText(), "Thank you");

        log.info("Order_05 - Checkout Page - Step 51 - Verify Order Successfully message text");
        Assert.assertEquals(userCheckoutPage.getOrderSuccessfullyMessage(), "Your order has been successfully processed!");

        log.info("Order_05 - Checkout Page - Step 52 - Get Order Number");
        orderNumber = userCheckoutPage.getOrderNumberValue();

        log.info("Order_05 - Checkout Page - Step 53 - Click To My Account Link");
        userCustomerInfoPO = userCheckoutPage.clickToMyAccountLink();

        log.info("Order_05 - Customer Info Page - Step 54 - Click to Orders Page");
        userOrdersPage = (UserOrdersPO) userCustomerInfoPO.openPagesAtMyAccountByName("Orders");

        log.info("Order_05 - Orders Page - Step 55 - Get Order Status and order total");
        orderStatus = userOrdersPage.getOrderInformationValueByOrderNumberAndLabel(orderNumber, "Order status");
        Assert.assertEquals(userOrdersPage.getOrderInformationValueByOrderNumberAndLabel(orderNumber, "Order Total"), orderTotalValue);

        log.info("Order_05 - Orders Page - Step 56 - Verify Order Number is displayed");
        Assert.assertTrue(userOrdersPage.isLastOrderNumberDisplayed(orderNumber));

        log.info("Order_05 - Orders Page - Step 57 - Click To Details button");
        userOrderDetailsPage = userOrdersPage.clickToDetailsButtonByOrderNumber(orderNumber);

        log.info("Order_05 - Orders Details Page - Step 56 - Verify Order Number");
        Assert.assertEquals(userOrderDetailsPage.getOrderNumberValue(), orderNumber);

        log.info("Order_05 - Orders Details Page - Step 57 - Verify Order Status");
        Assert.assertEquals(userOrderDetailsPage.getOrderInformationValueByClass("order-status"), orderStatus);

        log.info("Order_05 - Orders Details Page - Step 58 - Verify Order Total");
        Assert.assertEquals(userOrderDetailsPage.getOrderInformationValueByClass("order-total"), orderTotalValue);

        log.info("Order_05 - Orders Details Page - Step 59 - Verify Billing Address Information");
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("phone").contains(phoneBilling));
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("country"), countryBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("city"), cityBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("address1"), firstAddressBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("zippostalcode"), zipBilling);

        log.info("Order_05 - Orders Details Page - Step 60 - Verify Shipping Address Information");
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("phone").contains(phoneShipping));
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("country"), countryShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("stateprovince"), stateShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("city"), cityShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("address1"), firstAddressShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("zippostalcode"), zipShipping);

        log.info("Order_05 - Orders Details Page - Step 61 - Verify Payment Method Information");
        Assert.assertEquals(userOrderDetailsPage.getPaymentMethod(), "Check / Money Order");

        log.info("Order_05 - Orders Details Page - Step 62 - Verify Payment Status Information");
        Assert.assertEquals(userOrderDetailsPage.getPaymentStatus(), "Pending");

        log.info("Order_05 - Orders Details Page - Step 63 - Verify Shipping Method Information");
        Assert.assertEquals(userOrderDetailsPage.getShippingMethod(), "Ground");

        log.info("Order_05 - Orders Details Page - Step 64 - Verify Shipping Status Information");
        Assert.assertEquals(userOrderDetailsPage.getShippingStatus(), "Not yet shipped");

        log.info("Order_05 - Orders Details Page - Step 65 - Verify Product table Information");
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("sku"), SKU);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("product"), productName);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("unit-price"), price);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("quantity"), quantity);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("total"), totalPrice);

        log.info("Order_05 - Orders Details Page - Step 66 - Verify Gift Wrapping Information");
        Assert.assertEquals(userOrderDetailsPage.getGiftWrappingValue(), "No");

        log.info("Order_05 - Orders Details Page - Step 67 - Verify Cart total table Information");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Sub-Total"), totalPrice);
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Shipping"), "$0.00");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Tax"), "$0.00");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Total"), orderTotalValue);
    }

    @Description("Order_06 - Checkout: Payment method by Card")
    @Story("Order")
    @Test
    public void Order_06_Checkout_Payment_Card() {
        log.info("Start test case 'Order_06 - Checkout: Payment method by Card'");
        log.info("Order_06 - Orders Details Page - Step 01 - Hover 'Computers' menu");
        userOrderDetailsPage.hoverComputersMenu();

        log.info("Order_06 - Shopping Cart Page - Step 02 - Click to 'Notebooks' menu");
        userNotebooksPage = userOrderDetailsPage.clickToNotebooksSubMenu();

        log.info("Order_06 - Notebooks Page - Step 03 - Click to product " + productName);
        userProductDetailsPage = userNotebooksPage.clickToProductByProductName(productName);

        log.info("Order_06 - Product Details Page - Step 04 - Click to 'Add to cart' button");
        userProductDetailsPage.clickToAddToCartButton();

        log.info("Order_06 - Product Details Page - Step 05 - Click to Close button");
        userProductDetailsPage.clickToCloseNotificationButton(driver);

        log.info("Order_06 - Product Details Page - Step 06 - Hover to 'Shopping cart' link");
        userProductDetailsPage.hoverShoppingCartLink();

        log.info("Order_06 - Product Details Page - Step 07 - Click to 'Go to cart' button");
        userShoppingCartPage = userProductDetailsPage.clickToGoToCartButtonFromFlyout();
        userShoppingCartPage.waitAjaxLoadingIconDisappear();

        log.info("Order_06 - Shopping Page - Step 08 - Select Gift wrapping drop down list with value 'No'");
        userShoppingCartPage.selectGiftWrappingDropDownList("No");

        log.info("Order_06 - Shopping Page - Step 09 - Select Term of service checkbox");
        userShoppingCartPage.selectTermOfServiceCheckbox();

        log.info("Order_06 - Shopping Page - Step 10 - Click to Checkout button");
        userCheckoutPage = userShoppingCartPage.clickToCheckoutButton();

        log.info("Order_06 - Checkout Page - Step 11 - Unselect Ship to the same address checkbox");
        userCheckoutPage.unselectShipToTheSameAddressCheckbox();

        log.info("Order_06 - Checkout Page - Step 12 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-billing");

        log.info("Order_06 - Checkout Page - Step 12 - Select Shipping Address Drop Down list with value " + informationShipping);
        informationShipping = firstName + " " + lastName + ", " + countryShipping + ", " + stateShipping + ", " + cityShipping + ", " + firstAddressShipping + ", " + zipShipping;
        userCheckoutPage.selectDropDownListByID("shipping-address-select", informationShipping);

        log.info("Order_06 - Checkout Page - Step 13 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-shipping");

        log.info("Order_06 - Checkout Page - Step 14 - Select Shipping Method Radio Button with option Ground");
        userCheckoutPage.selectShippingMethodRadioButton("Ground");

        log.info("Order_06 - Checkout Page - Step 15 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-shipping-method");

        log.info("Order_06 - Checkout Page - Step 16 - Select Payment Method Radio Button with option Credit Card");
        userCheckoutPage.selectPaymentMethodRadioButton("Credit Card");

        log.info("Order_06 - Checkout Page - Step 17 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-payment-method");

        log.info("Order_06 - Checkout Page - Step 18 - Select Credit card type Drop down list with value 'Visa'");
        userCheckoutPage.selectDropDownListByID("CreditCardType", "Visa");

        cardHolderName = "BUI SON TUNG";
        log.info("Order_06 - Checkout Page - Step 19 - Input to Card Holder textbox with value " + cardHolderName);
        userCheckoutPage.inputToCheckboxByID("CardholderName", cardHolderName);

        cardNumber = "378282246310005";
        log.info("Order_06 - Checkout Page - Step 20 - Input to Card number textbox with value " + cardNumber);
        userCheckoutPage.inputToCheckboxByID("CardNumber", cardNumber);

        log.info("Order_06 - Checkout Page - Step 21 - Select Expire Month Drop down list with value '12'");
        userCheckoutPage.selectDropDownListByID("ExpireMonth", "12");

        log.info("Order_06 - Checkout Page - Step 22 - Select Expire Year Drop down list with value '2028'");
        userCheckoutPage.selectDropDownListByID("ExpireYear", "2028");

        cardCode = "1234";
        log.info("Order_06 - Checkout Page - Step 23 - Input to Card code textbox with value " + cardCode);
        userCheckoutPage.inputToCheckboxByID("CardCode", cardCode);

        log.info("Order_06 - Checkout Page - Step 24 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-payment-info");

        log.info("Order_06 - Checkout Page - Step 25 - Verify Billing Address Information");
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("phone").contains(phoneBilling));
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("country"), countryBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("city"), cityBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("address1"), firstAddressBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("zippostalcode"), zipBilling);

        log.info("Order_06 - Checkout Page - Step 26 - Verify Shipping Address Information");
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("phone").contains(phoneShipping));
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("country"), countryShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("stateprovince"), stateShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("city"), cityShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("address1"), firstAddressShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("zippostalcode"), zipShipping);

        log.info("Order_06 - Checkout Page - Step 27 - Verify Payment method Information");
        Assert.assertEquals(userCheckoutPage.getPaymentMethod(), "Credit Card");

        log.info("Order_06 - Checkout Page - Step 28 - Verify Shipping method Information");
        Assert.assertEquals(userCheckoutPage.getShippingMethod(), "Ground");

        log.info("Order_06 - Checkout Page - Step 29 - Verify Product table Information");
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("sku"), SKU);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("product"), productName);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("unit-price"), price);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("quantity"), quantity);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("subtotal"), totalPrice);

        log.info("Order_06 - Checkout Page - Step 30 - Verify Gift Wrapping Information");
        Assert.assertEquals(userCheckoutPage.getGiftWrappingValue(), "No");

        log.info("Order_06 - Checkout Page - Step 31 - Verify Cart Total Table Information");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Sub-Total"), totalPrice);
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Shipping"), "$0.00");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Tax"), "$0.00");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Total"), "$3,600.00");

        log.info("Order_06 - Checkout Page - Step 32 - Get Total value from Cart Total Table");
        orderTotalValue = userCheckoutPage.getValueFromCartTotalTableByLabel("Total");

        log.info("Order_06 - Checkout Page - Step 33 - Click Confirm button");
        userCheckoutPage.clickToConfirmButton();

        log.info("Order_06 - Checkout Page - Step 34 - Verify Thank you message text");
        Assert.assertEquals(userCheckoutPage.getThankYouMessageText(), "Thank you");

        log.info("Order_06 - Checkout Page - Step 35 - Verify Order Successfully message text");
        Assert.assertEquals(userCheckoutPage.getOrderSuccessfullyMessage(), "Your order has been successfully processed!");

        log.info("Order_06 - Checkout Page - Step 36 - Get Order Number");
        orderNumber = userCheckoutPage.getOrderNumberValue();

        log.info("Order_06 - Checkout Page - Step 37 - Click To My Account Link");
        userCustomerInfoPO = userCheckoutPage.clickToMyAccountLink();

        log.info("Order_06 - Customer Info Page - Step 38 - Click to Orders Page");
        userOrdersPage = (UserOrdersPO) userCustomerInfoPO.openPagesAtMyAccountByName("Orders");

        log.info("Order_06 - Orders Page - Step 39 - Get Order Status and order total");
        orderStatus = userOrdersPage.getOrderInformationValueByOrderNumberAndLabel(orderNumber, "Order status");
        Assert.assertEquals(userOrdersPage.getOrderInformationValueByOrderNumberAndLabel(orderNumber, "Order Total"), orderTotalValue);

        log.info("Order_06 - Orders Page - Step 40 - Verify Order Number is displayed");
        Assert.assertTrue(userOrdersPage.isLastOrderNumberDisplayed(orderNumber));

        log.info("Order_06 - Orders Page - Step 41 - Click To Details button");
        userOrderDetailsPage = userOrdersPage.clickToDetailsButtonByOrderNumber(orderNumber);

        log.info("Order_06 - Orders Details Page - Step 42 - Verify Order Number");
        Assert.assertEquals(userOrderDetailsPage.getOrderNumberValue(), orderNumber);

        log.info("Order_06 - Orders Details Page - Step 43 - Verify Order Status");
        Assert.assertEquals(userOrderDetailsPage.getOrderInformationValueByClass("order-status"), orderStatus);

        log.info("Order_06 - Orders Details Page - Step 44 - Verify Order Total");
        Assert.assertEquals(userOrderDetailsPage.getOrderInformationValueByClass("order-total"), orderTotalValue);

        log.info("Order_06 - Orders Details Page - Step 45 - Verify Billing Address Information");
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("phone").contains(phoneBilling));
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("country"), countryBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("city"), cityBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("address1"), firstAddressBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("zippostalcode"), zipBilling);

        log.info("Order_06 - Orders Details Page - Step 46 - Verify Shipping Address Information");
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("phone").contains(phoneShipping));
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("country"), countryShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("stateprovince"), stateShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("city"), cityShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("address1"), firstAddressShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("zippostalcode"), zipShipping);

        log.info("Order_06 - Orders Details Page - Step 47 - Verify Payment Method Information");
        Assert.assertEquals(userOrderDetailsPage.getPaymentMethod(), "Credit Card");

        log.info("Order_06 - Orders Details Page - Step 48 - Verify Payment Status Information");
        Assert.assertEquals(userOrderDetailsPage.getPaymentStatus(), "Pending");

        log.info("Order_06 - Orders Details Page - Step 49 - Verify Shipping Method Information");
        Assert.assertEquals(userOrderDetailsPage.getShippingMethod(), "Ground");

        log.info("Order_06 - Orders Details Page - Step 50 - Verify Shipping Status Information");
        Assert.assertEquals(userOrderDetailsPage.getShippingStatus(), "Not yet shipped");

        log.info("Order_06 - Orders Details Page - Step 51 - Verify Product table Information");
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("sku"), SKU);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("product"), productName);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("unit-price"), price);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("quantity"), quantity);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("total"), totalPrice);

        log.info("Order_06 - Orders Details Page - Step 52 - Verify Gift Wrapping Information");
        Assert.assertEquals(userOrderDetailsPage.getGiftWrappingValue(), "No");

        log.info("Order_06 - Orders Details Page - Step 53 - Verify Cart total table Information");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Sub-Total"), totalPrice);
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Shipping"), "$0.00");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Tax"), "$0.00");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Total"), orderTotalValue);
    }

    @Description("Order_07 - Checkout: Re-Order")
    @Story("Order")
    @Test
    public void Order_07_Checkout_Re_Order() {
        log.info("Start test case 'Order_07 - Checkout: Re-Order'");
        log.info("Order_07 - Order Details Page - Step 01 - Click to 'Re-order' button");
        userShoppingCartPage = userOrderDetailsPage.clickToReOrderButton();

        log.info("Order_07 - Shopping Cart Page - Step 02 - Input to QTY. checkbox by product " + productName + " with value is '10'");
        userShoppingCartPage.inputToQTYCheckboxByProductName(productName, "10");

        log.info("Order_07 - Shopping Page - Step 03 - Get Total price by product " + productName);
        totalPrice = userShoppingCartPage.getTotalPriceByProductName(productName);

        log.info("Order_07 - Shopping Page - Step 04 - Select Gift wrapping drop down list with value 'No'");
        userShoppingCartPage.selectGiftWrappingDropDownList("No");

        log.info("Order_07 - Shopping Page - Step 05 - Select Term of service checkbox");
        userShoppingCartPage.selectTermOfServiceCheckbox();

        log.info("Order_07 - Shopping Page - Step 06 - Click to Checkout button");
        userCheckoutPage = userShoppingCartPage.clickToCheckoutButton();

        log.info("Order_07 - Checkout Page - Step 07 - Unselect Ship to the same address checkbox");
        userCheckoutPage.unselectShipToTheSameAddressCheckbox();

        log.info("Order_07 - Checkout Page - Step 08 - Select Billing Address Drop down list");
        userCheckoutPage.selectDropDownListByID("billing-address-select", "New Address");

        countryBilling = "United States";
        stateBilling = "California";
        cityBilling = "California ABCD";
        firstAddressBilling = "California EFGH";
        zipBilling = "400000";
        phoneBilling = "0966666666";

        log.info("Order_07 - Checkout Page - Step 09 - Input to Firstname textbox with value " + firstName);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_FirstName", firstName);

        log.info("Order_07 - Checkout Page - Step 10 - Input to Lastname textbox with value " + lastName);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_LastName", lastName);

        log.info("Order_07 - Checkout Page - Step 11 - Input to Email textbox with value " + email);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_Email", email);

        log.info("Order_07 - Checkout Page - Step 12 - Select Country dropdown with value " + countryBilling);
        userCheckoutPage.selectDropDownListByID("BillingNewAddress_CountryId", countryBilling);
        userCheckoutPage.sleepInSecond(3);

        log.info("Order_07 - Checkout Page - Step 13 - Select State/Provice dropdown with value " + stateBilling);
        userCheckoutPage.waitForLoadingIconInvisible();
        userCheckoutPage.sleepInSecond(3);
        userCheckoutPage.selectDropDownListByID("BillingNewAddress_StateProvinceId", stateBilling);

        log.info("Order_07 - Checkout Page - Step 14 - Input to City textbox with value " + cityBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_City", cityBilling);

        log.info("Order_07 - Checkout Page - Step 15 - Input to Address 1 textbox with value " + firstAddressBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_Address1", firstAddressBilling);

        log.info("Order_07 - Checkout Page - Step 16 - Input to Zip textbox with value " + zipBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_ZipPostalCode", zipBilling);

        log.info("Order_07 - Checkout Page - Step 17 - Input to Phone textbox with value " + phoneBilling);
        userCheckoutPage.inputToCheckboxByID("BillingNewAddress_PhoneNumber", phoneBilling);

        log.info("Order_07 - Checkout Page - Step 18 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-billing");

        log.info("Order_07 - Checkout Page - Step 19 - Select Shipping Address drop down list with value 'New Address'");
        userCheckoutPage.selectDropDownListByID("shipping-address-select","New Address");

        countryShipping = "United States";
        stateShipping = "Hawaii";
        cityShipping = "Hawaii ABCD";
        firstAddressShipping = "Hawaii EFGH";
        zipShipping = "500000";
        phoneShipping = "0123456789";

        log.info("Order_07 - Checkout Page - Step 20 - Input to Firstname textbox with value " + firstName);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_FirstName", firstName);

        log.info("Order_07 - Checkout Page - Step 21 - Input to Lastname textbox with value " + lastName);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_LastName", lastName);

        log.info("Order_07 - Checkout Page - Step 22 - Input to Email textbox with value " + email);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_Email", email);

        log.info("Order_07 - Checkout Page - Step 23 - Select Country dropdown with value " + countryShipping);
        userCheckoutPage.selectDropDownListByID("ShippingNewAddress_CountryId", countryShipping);
        userCheckoutPage.sleepInSecond(3);

        log.info("Order_07 - Checkout Page - Step 24 - Select State/Provice dropdown with value " + stateShipping);
        userCheckoutPage.waitForLoadingIconInvisible();
        userCheckoutPage.sleepInSecond(3);
        userCheckoutPage.selectDropDownListByID("ShippingNewAddress_StateProvinceId", stateShipping);

        log.info("Order_07 - Checkout Page - Step 25 - Input to City textbox with value " + cityShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_City", cityShipping);

        log.info("Order_07 - Checkout Page - Step 26 - Input to Address 1 textbox with value " + firstAddressShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_Address1", firstAddressShipping);

        log.info("Order_07 - Checkout Page - Step 27 - Input to Zip textbox with value " + zipShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_ZipPostalCode", zipShipping);

        log.info("Order_07 - Checkout Page - Step 28 - Input to Phone textbox with value " + phoneShipping);
        userCheckoutPage.inputToCheckboxByID("ShippingNewAddress_PhoneNumber", phoneShipping);

        log.info("Order_07 - Checkout Page - Step 29 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-shipping");

        log.info("Order_07 - Checkout Page - Step 30 - Select Shipping Method Radio Button with option Ground");
        userCheckoutPage.selectShippingMethodRadioButton("Next Day Air");

        log.info("Order_07 - Checkout Page - Step 31 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-shipping-method");

        log.info("Order_07 - Checkout Page - Step 32 - Select Payment Method Radio Button with option Check / Money Order");
        userCheckoutPage.selectPaymentMethodRadioButton("Check / Money Order");

        log.info("Order_07 - Checkout Page - Step 33 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-payment-method");

        log.info("Order_07 - Checkout Page - Step 34 - Verify Payment Information");
        Assert.assertTrue(userCheckoutPage.getPaymentInformation().contains(paymentInformation));

        log.info("Order_07 - Checkout Page - Step 35 - Click to Continue button");
        userCheckoutPage.clickToContinueButtonOfCheckoutStep("checkout-step-payment-info");

        log.info("Order_07 - Checkout Page - Step 36 - Verify Billing Address Information");
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userCheckoutPage.getBillingAddressInformationByClass("phone").contains(phoneBilling));
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("country"), countryBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("stateprovince"), stateBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("city"), cityBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("address1"), firstAddressBilling);
        Assert.assertEquals(userCheckoutPage.getBillingAddressInformationByClass("zippostalcode"), zipBilling);

        log.info("Order_07 - Checkout Page - Step 37 - Verify Shipping Address Information");
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userCheckoutPage.getShippingAddressInformationByClass("phone").contains(phoneShipping));
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("country"), countryShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("stateprovince"), stateShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("city"), cityShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("address1"), firstAddressShipping);
        Assert.assertEquals(userCheckoutPage.getShippingAddressInformationByClass("zippostalcode"), zipShipping);

        log.info("Order_07 - Checkout Page - Step 38 - Verify Payment method Information");
        Assert.assertEquals(userCheckoutPage.getPaymentMethod(), "Check / Money Order");

        log.info("Order_07 - Checkout Page - Step 39 - Verify Shipping method Information");
        Assert.assertEquals(userCheckoutPage.getShippingMethod(), "Next Day Air");

        log.info("Order_07 - Checkout Page - Step 40 - Verify Product table Information");
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("sku"), SKU);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("product"), productName);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("unit-price"), price);
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("quantity"), "10");
        Assert.assertEquals(userCheckoutPage.getCellValueByClassName("subtotal"), totalPrice);

        log.info("Order_07 - Checkout Page - Step 41 - Verify Gift Wrapping Information");
        Assert.assertEquals(userCheckoutPage.getGiftWrappingValue(), "No");

        log.info("Order_07 - Checkout Page - Step 42 - Verify Cart Total Table Information");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Sub-Total"), totalPrice);
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Shipping"), "$0.00");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Tax"), "$0.00");
        Assert.assertEquals(userCheckoutPage.getValueFromCartTotalTableByLabel("Total"), "$18,000.00");

        log.info("Order_07 - Checkout Page - Step 43 - Get Total value from Cart Total Table");
        orderTotalValue = userCheckoutPage.getValueFromCartTotalTableByLabel("Total");

        log.info("Order_07 - Checkout Page - Step 44 - Click Confirm button");
        userCheckoutPage.clickToConfirmButton();

        log.info("Order_07 - Checkout Page - Step 45 - Verify Thank you message text");
        Assert.assertEquals(userCheckoutPage.getThankYouMessageText(), "Thank you");

        log.info("Order_07 - Checkout Page - Step 46 - Verify Order Successfully message text");
        Assert.assertEquals(userCheckoutPage.getOrderSuccessfullyMessage(), "Your order has been successfully processed!");

        log.info("Order_07 - Checkout Page - Step 47 - Get Order Number");
        orderNumber = userCheckoutPage.getOrderNumberValue();

        log.info("Order_07 - Checkout Page - Step 48 - Click To My Account Link");
        userCustomerInfoPO = userCheckoutPage.clickToMyAccountLink();

        log.info("Order_07 - Customer Info Page - Step 49 - Click to Orders Page");
        userOrdersPage = (UserOrdersPO) userCustomerInfoPO.openPagesAtMyAccountByName("Orders");

        log.info("Order_07 - Orders Page - Step 50 - Get Order Status and order total");
        orderStatus = userOrdersPage.getOrderInformationValueByOrderNumberAndLabel(orderNumber, "Order status");
        Assert.assertEquals(userOrdersPage.getOrderInformationValueByOrderNumberAndLabel(orderNumber, "Order Total"), orderTotalValue);

        log.info("Order_07 - Orders Page - Step 51 - Verify Order Number is displayed");
        Assert.assertTrue(userOrdersPage.isLastOrderNumberDisplayed(orderNumber));

        log.info("Order_07 - Orders Page - Step 52 - Click To Details button");
        userOrderDetailsPage = userOrdersPage.clickToDetailsButtonByOrderNumber(orderNumber);

        log.info("Order_07 - Orders Details Page - Step 53 - Verify Order Number");
        Assert.assertEquals(userOrderDetailsPage.getOrderNumberValue(), orderNumber);

        log.info("Order_07 - Orders Details Page - Step 54 - Verify Order Status");
        Assert.assertEquals(userOrderDetailsPage.getOrderInformationValueByClass("order-status"), orderStatus);

        log.info("Order_07 - Orders Details Page - Step 55 - Verify Order Total");
        Assert.assertEquals(userOrderDetailsPage.getOrderInformationValueByClass("order-total"), orderTotalValue);

        log.info("Order_07 - Orders Details Page - Step 56 - Verify Billing Address Information");
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userOrderDetailsPage.getBillingAddressInformationByClass("phone").contains(phoneBilling));
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("country"), countryBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("stateprovince"), stateBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("city"), cityBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("address1"), firstAddressBilling);
        Assert.assertEquals(userOrderDetailsPage.getBillingAddressInformationByClass("zippostalcode"), zipBilling);

        log.info("Order_07 - Orders Details Page - Step 57 - Verify Shipping Address Information");
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("name").contains(firstName + " " + lastName));
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("email").contains(email));
        Assert.assertTrue(userOrderDetailsPage.getShippingAddressInformationByClass("phone").contains(phoneShipping));
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("country"), countryShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("stateprovince"), stateShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("city"), cityShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("address1"), firstAddressShipping);
        Assert.assertEquals(userOrderDetailsPage.getShippingAddressInformationByClass("zippostalcode"), zipShipping);

        log.info("Order_07 - Orders Details Page - Step 58 - Verify Payment Method Information");
        Assert.assertEquals(userOrderDetailsPage.getPaymentMethod(), "Check / Money Order");

        log.info("Order_07 - Orders Details Page - Step 59 - Verify Payment Status Information");
        Assert.assertEquals(userOrderDetailsPage.getPaymentStatus(), "Pending");

        log.info("Order_07 - Orders Details Page - Step 60 - Verify Shipping Method Information");
        Assert.assertEquals(userOrderDetailsPage.getShippingMethod(), "Next Day Air");

        log.info("Order_07 - Orders Details Page - Step 61 - Verify Shipping Status Information");
        Assert.assertEquals(userOrderDetailsPage.getShippingStatus(), "Not yet shipped");

        log.info("Order_07 - Orders Details Page - Step 62 - Verify Product table Information");
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("sku"), SKU);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("product"), productName);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("unit-price"), price);
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("quantity"), "10");
        Assert.assertEquals(userOrderDetailsPage.getCellValueByClassName("total"), totalPrice);

        log.info("Order_07 - Orders Details Page - Step 63 - Verify Gift Wrapping Information");
        Assert.assertEquals(userOrderDetailsPage.getGiftWrappingValue(), "No");

        log.info("Order_07 - Orders Details Page - Step 64 - Verify Cart total table Information");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Sub-Total"), totalPrice);
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Shipping"), "$0.00");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Tax"), "$0.00");
        Assert.assertEquals(userOrderDetailsPage.getValueFromCartTotalTableByLabel("Total"), orderTotalValue);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
