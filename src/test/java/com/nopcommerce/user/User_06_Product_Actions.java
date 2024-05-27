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

public class User_06_Product_Actions extends BaseTest {

    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserProductDetailsPO userProductDetailsPage;
    private UserWishlistPO userWistlistPage;
    private UserShoppingCartPO userShoppingCartPage;
    private UserCompareProductsListPO userCompareProductsListPage;
    private UserComputersPO userComputersPage;
    private UserNotebooksPO userNotebooksPage;
    private UserRecentlyViewedProductsPO userRecentlyViewedProductsPage;
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

        productName = "Apple MacBook Pro 13-inch";
        log.info("Precondition - Home Page - Step 05: Click to " + productName + " product");
        userProductDetailsPage = userHomePage.clickToProductDetailByName(productName);
    }

    @Description("Product_Actions_01 - Add to Wishlist")
    @Story("Product actions")
    @Test
    public void Product_Actions_01_Add_To_Wishlist() {
        log.info("Start test case 'Product_Actions_01 - Add to Wishlist'");
        log.info("Product_Actions_01 - Product Details Page - Step 01 - Click to 'Add to wishlist' button");
        userProductDetailsPage.clickToAddToWishlistButton();

        log.info("Product_Actions_01 - Product Details Page - Step 02 - Verify Add to wishlist successfully notification");
        Assert.assertEquals(userProductDetailsPage.getBarNotificationText(driver), "The product has been added to your wishlist");

        log.info("Product_Actions_01 - Product Details Page - Step 03 - Click to Wishlist link from Notification");
        userWistlistPage = userProductDetailsPage.clickToWishlistLinkFromNotification();

        log.info("Product_Actions_01 - Wishlist Page - Step 04 - Verify product with name " + productName + " is displayed");
        Assert.assertTrue(userWistlistPage.isWishlistProductDisplayed(productName));

        log.info("Product_Actions_01 - Wishlist Page - Step 05 - Click to Wishlist URL for sharing");
        userWistlistPage.clickToWishlistURLForSharing();

        log.info("Product_Actions_01 - Wishlist Page - Step 06 - Get Wishlist Sharing title and compare");
        Assert.assertEquals(userWistlistPage.getWishlistSharingTitle(), "Wishlist of " + Common_01_Register.firstName + " " + Common_01_Register.lastName);
    }

    @Description("Product_Actions_02 - Add product to Cart from Wishlist page")
    @Story("Product actions")
    @Test
    public void Product_Actions_02_Add_To_Cart() {
        log.info("Start test case 'Product_Actions_02 - Add product to Cart from Wishlist page'");
        log.info("Product_Actions_02 - Wishlist Page - Step 01 - Back to Wishlist page");
        userWistlistPage.backToPage(driver);

        log.info("Product_Actions_02 - Wishlist Page - Step 01 - Select 'Add to cart' checkbox by product name is " + productName);
        userWistlistPage.selectCheckboxAddToCartByProductName(productName);

        log.info("Product_Actions_02 - Wishlist Page - Step 02 - Click 'Add to cart' button");
        userShoppingCartPage = userWistlistPage.clickToAddToCartButton();

        log.info("Product_Actions_02 - Shopping Cart Page - Step 03 - Verify product " + productName + " is displayed");
        Assert.assertTrue(userShoppingCartPage.isSelectedProductDisplayed(productName));

        log.info("Product_Actions_02 - Shopping Cart Page - Step 04 - Click to Wishlist link");
        userWistlistPage = userShoppingCartPage.clickToWishlistLink();

        log.info("Product_Actions_02 - Wishlist Page - Step 05 - Verify Wishlist empty message is displayed");
        Assert.assertTrue(userWistlistPage.isWishlistEmptyMessageDisplayed());
    }

    @Description("Product_Actions_03 - Remove Product in Wishlist page")
    @Story("Product actions")
    @Test
    public void Product_Actions_03_Remove_Product() {
        log.info("Start test case 'Product_Actions_03 - Remove Product in Wishlist page'");
        log.info("Product_Actions_03 - Wishlist Page - Step 01 - Click to Homepage Logo");
        userHomePage = userWistlistPage.clickToHomePageLogo();

        log.info("Product_Actions_03 - Home Page - Step 02: Click to " + productName + " product");
        userProductDetailsPage = userHomePage.clickToProductDetailByName(productName);

        log.info("Product_Actions_03 - Product Details Page - Step 03 - Click to 'Add to wishlist' button");
        userProductDetailsPage.clickToAddToWishlistButton();

        log.info("Product_Actions_03 - Product Details Page - Step 04 - Click to Wishlist link from Notification");
        userWistlistPage = userProductDetailsPage.clickToWishlistLinkFromNotification();

        log.info("Product_Actions_03 - Wishlist Page - Step 05 - Click to Remove button of product " + productName);
        userWistlistPage.clickToRemoveButtonByProductName(productName);

        log.info("Product_Actions_03 - Wishlist Page - Step 06 - Verify Wishlist empty message is displayed");
        Assert.assertTrue(userWistlistPage.isWishlistEmptyMessageDisplayed());
    }

    @Description("Product_Actions_04 - Add Product to Compare")
    @Story("Product actions")
    @Test
    public void Product_Actions_04_Compare_Product() {
        log.info("Start test case 'Product_Actions_04 - Add Product to Compare'");
        log.info("Product_Actions_04 - Wishlist Page - Step 01 - Click to Homepage Logo");
        userHomePage = userWistlistPage.clickToHomePageLogo();

        String firstCompareProductName = "Build your own computer";
        String secondCompareProductName = "Apple MacBook Pro 13-inch";

        log.info("Product_Actions_04 - Home Page - Step 02 - Get price of product " + firstCompareProductName);
        String priceFirstCompareProduct = userHomePage.getProductPriceByProductName(firstCompareProductName);

        log.info("Product_Actions_04 - Home Page - Step 03 - Click to 'Add to compare list button' of product " + firstCompareProductName);
        userHomePage.clickToAddToCompareListButtonByProductName(firstCompareProductName);

        log.info("Product_Actions_04 - Home Page - Step 04 - Verify Bar Notification");
        Assert.assertEquals(userHomePage.getBarNotificationText(driver), "The product has been added to your product comparison");

        log.info("Product_Actions_04 - Home Page - Step 05 - Get price of product " + secondCompareProductName);
        String priceSecondCompareProduct = userHomePage.getProductPriceByProductName(secondCompareProductName);

        log.info("Product_Actions_04 - Home Page - Step 06 - Click to 'Add to compare list button' of product " + secondCompareProductName);
        userHomePage.clickToAddToCompareListButtonByProductName(secondCompareProductName);

        log.info("Product_Actions_04 - Home Page - Step 07 - Verify Bar Notification");
        Assert.assertEquals(userHomePage.getBarNotificationText(driver), "The product has been added to your product comparison");

        log.info("Product_Actions_04 - Home Page - Step 08 - Click to 'Compare products list' link");
        userCompareProductsListPage = userHomePage.clickToCompareProductsListLink();

        log.info("Product_Actions_04 - Compare Products List Page - Step 09 - Verify 'Remove' button is displayed with both product name");
        Assert.assertTrue(userCompareProductsListPage.isRemoveButtonDisplayedByProductName(firstCompareProductName));
        Assert.assertTrue(userCompareProductsListPage.isRemoveButtonDisplayedByProductName(secondCompareProductName));

        log.info("Product_Actions_04 - Compare Products List Page - Step 10 - Verify both product name are displayed");
        Assert.assertTrue(userCompareProductsListPage.isProductNameDisplayed(firstCompareProductName));
        Assert.assertTrue(userCompareProductsListPage.isProductNameDisplayed(secondCompareProductName));

        log.info("Product_Actions_04 - Compare Products List Page - Step 11 - Get product price in table and compare with real price");
        Assert.assertEquals(userCompareProductsListPage.getProductPriceByProductName(firstCompareProductName), priceFirstCompareProduct);
        Assert.assertEquals(userCompareProductsListPage.getProductPriceByProductName(secondCompareProductName), priceSecondCompareProduct);

        log.info("Product_Actions_04 - Compare Products List Page - Step 12 - Click to 'Clear list' button");
        userCompareProductsListPage.clickToClearListButton();

        log.info("Product_Actions_04 - Compare Products List Page - Step 13 - Get displayed message and compare with 'You have no items to compare.'");
        Assert.assertEquals(userCompareProductsListPage.getNoItemsToCompareMessage(), "You have no items to compare.");

        log.info("Product_Actions_04 - Compare Products List Page - Step 14 - Verify Both Product are undisplayed");
        Assert.assertTrue(userCompareProductsListPage.isProductNameUndisplayed(firstCompareProductName));
        Assert.assertTrue(userCompareProductsListPage.isProductNameUndisplayed(secondCompareProductName));
    }

    @Description("Product_Actions_05 - Recently Viewed Products")
    @Story("Product actions")
    @Test
    public void Product_Actions_05_Recently_Viewed_Products() {
        log.info("Start test case 'Product_Actions_05 - Recently Viewed Products'");
        log.info("Product_Actions_05 - Compare Products List Page - Step 01 - Click to Computers Menu Link");
        userComputersPage = userCompareProductsListPage.clickToComputersMenu();

        log.info("Product_Actions_05 - Computers Page - Step 02 - Click to Notebooks Sub Menu Link");
        userNotebooksPage = userComputersPage.clickToNotebooksSubMenu();

        String firstViewedProductName = "Apple MacBook Pro 13-inch";
        String secondViewedProductName = "Asus N551JK-XO076H Laptop";
        String thirdViewedProductName = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
        String forthViewedProductName = "HP Spectre XT Pro UltraBook";
        String fifthViewedProductName = "Lenovo Thinkpad X1 Carbon Laptop";

        log.info("Product_Actions_05 - Notebooks Page - Step 03 - Click to product " + firstViewedProductName + "then back");
        userProductDetailsPage = userNotebooksPage.clickToProductByProductName(firstViewedProductName);
        userProductDetailsPage.backToPage(driver);
        userNotebooksPage = PageGeneratorManager.getUserNotebooksPO(driver);

        log.info("Product_Actions_05 - Notebooks Page - Step 04 - Click to product " + secondViewedProductName + "then back");
        userProductDetailsPage = userNotebooksPage.clickToProductByProductName(secondViewedProductName);
        userProductDetailsPage.backToPage(driver);
        userNotebooksPage = PageGeneratorManager.getUserNotebooksPO(driver);

        log.info("Product_Actions_05 - Notebooks Page - Step 05 - Click to product " + thirdViewedProductName + "then back");
        userProductDetailsPage = userNotebooksPage.clickToProductByProductName(thirdViewedProductName);
        userProductDetailsPage.backToPage(driver);
        userNotebooksPage = PageGeneratorManager.getUserNotebooksPO(driver);

        log.info("Product_Actions_05 - Notebooks Page - Step 06 - Click to product " + forthViewedProductName + "then back");
        userProductDetailsPage = userNotebooksPage.clickToProductByProductName(forthViewedProductName);
        userProductDetailsPage.backToPage(driver);
        userNotebooksPage = PageGeneratorManager.getUserNotebooksPO(driver);

        log.info("Product_Actions_05 - Notebooks Page - Step 07 - Click to product " + fifthViewedProductName + " then back");
        userProductDetailsPage = userNotebooksPage.clickToProductByProductName(fifthViewedProductName);
        userProductDetailsPage.backToPage(driver);
        userNotebooksPage = PageGeneratorManager.getUserNotebooksPO(driver);

        log.info("Product_Actions_05 - Notebooks Page - Step 08 - Click to 'Recently viewed products' link");
        userRecentlyViewedProductsPage = userNotebooksPage.clickToRecentlyViewedProductsLink();

        log.info("Product_Actions_05 - Recently Viewed Products Page - Step 09 - Verify Product " + fifthViewedProductName + " is displayed");
        Assert.assertTrue(userRecentlyViewedProductsPage.isProductDisplayedByProductName(fifthViewedProductName));

        log.info("Product_Actions_05 - Recently Viewed Products Page - Step 10 - Verify Product " + forthViewedProductName + " is displayed");
        Assert.assertTrue(userRecentlyViewedProductsPage.isProductDisplayedByProductName(forthViewedProductName));

        log.info("Product_Actions_05 - Recently Viewed Products Page - Step 11 - Verify Product " + thirdViewedProductName + " is displayed");
        Assert.assertTrue(userRecentlyViewedProductsPage.isProductDisplayedByProductName(thirdViewedProductName));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
