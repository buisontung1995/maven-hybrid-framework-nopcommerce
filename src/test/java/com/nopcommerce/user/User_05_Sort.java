package com.nopcommerce.user;

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

public class User_05_Sort extends BaseTest {

    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserComputersPO userComputersPage;
    private UserNotebooksPO userNotebooksPage;
    private Environment env;

    @Parameters({"envName", "browserName", "browserVersion", "osName"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("chrome") String browserName, @Optional("latest") String browserVersion, @Optional("Windows 11") String osName) {
        String environmentConfig = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentConfig);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(env.appUrl(), envName, browserName, browserVersion, osName);
        userHomePage = PageGeneratorManager.getUserHomePO(driver);

        log.info("Precondition - Home page - Step 01: Click to Computers menu");
        userComputersPage = userHomePage.clickToComputersMenu();

        log.info("Precondition - Computers page - Step 02: Click to Notebooks sub menu");
        userNotebooksPage = userComputersPage.clickToNotebooksSubMenu();
    }

    @Description("Sort_01 - Sort with name A to Z")
    @Story("Sort")
    @Test
    public void Sort_01_Sort_With_Name_A_To_Z() {
        log.info("Start test case 'Sort_01 - Sort with name A to Z'");
        log.info("Sort_01 - Notebooks Page - Step 01: Select 'Name: A to Z' in Sort by dropdown list");
        userNotebooksPage.selectSortByDropDownList("Name: A to Z");
        userNotebooksPage.sleepInSecond(3);

        log.info("Sort_01 - Notebooks Page - Step 02: Verify is Product Name sort by Ascending");
        Assert.assertTrue(userNotebooksPage.isProductNameSortByAscending());
    }

    @Description("Sort_02 - Sort with name Z to A")
    @Story("Sort")
    @Test
    public void Sort_02_Sort_With_Name_Z_To_A() {
        log.info("Start test case 'Sort_02 - Sort with name Z to A'");
        log.info("Sort_02 - Notebooks Page - Step 01: Select 'Name: Z to A' in Sort by dropdown list");
        userNotebooksPage.selectSortByDropDownList("Name: Z to A");
        userNotebooksPage.sleepInSecond(3);

        log.info("Sort_02 - Notebooks Page - Step 02: Verify is Product Name sort by Descending");
        Assert.assertTrue(userNotebooksPage.isProductNameSortByDescending());
    }

    @Description("Sort_03 - Sort with price low to high")
    @Story("Sort")
    @Test
    public void Sort_03_Sort_With_Price_Low_To_High() {
        log.info("Start test case 'Sort_03 - Sort with price low to high'");
        log.info("Sort_03 - Notebooks Page - Step 01: Select 'Price: Low to High' in Sort by dropdown list");
        userNotebooksPage.selectSortByDropDownList("Price: Low to High");
        userNotebooksPage.sleepInSecond(3);

        log.info("Sort_03 - Notebooks Page - Step 02: Verify is Product Price sort by Ascending");
        Assert.assertTrue(userNotebooksPage.isProductPriceSortByAscending());
    }

    @Description("Sort_04 - Sort with price high to low")
    @Story("Sort")
    @Test
    public void Sort_04_Sort_With_Price_High_To_Low() {
        log.info("Start test case 'Sort_04 - Sort with price high to low'");
        log.info("Sort_04 - Notebooks Page - Step 01: Select 'Price: High to Low' in Sort by dropdown list");
        userNotebooksPage.selectSortByDropDownList("Price: High to Low");
        userNotebooksPage.sleepInSecond(3);

        log.info("Sort_04 - Notebooks Page - Step 02: Verify is Product Name sort by Descending");
        Assert.assertTrue(userNotebooksPage.isProductPriceSortByDescending());
    }

    @Description("Sort_05 - Displayed with 3 products per page")
    @Story("Sort")
    @Test
    public void Sort_05_Displayed_With_3_Products_Per_Page() {
        log.info("Start test case 'Sort_05 - Displayed with 3 products per page'");
        log.info("Sort_05 - Notebooks Page - Step 01: Select '3' in Displayed Drop down");
        userNotebooksPage.selectDisplayedDropDownList("3");
        userNotebooksPage.sleepInSecond(3);

        log.info("Sort_05 - Notebooks Page - Step 02: Verify Product Displayed Per Page is less than or equal to 3");
        Assert.assertTrue(userNotebooksPage.getTotalProductsDisplayedPerPage() <= 3);

        log.info("Sort_05 - Notebooks Page - Step 03: Verify Next Page icon is displayed");
        Assert.assertTrue(userNotebooksPage.isNextPageIconDisplayed());

        log.info("Sort_05 - Notebooks Page - Step 04: Click to Next page icon");
        userNotebooksPage.clickToNextPageIcon();

        log.info("Sort_05 - Notebooks Page - Step 05: Verify Product Displayed Per Page is less than or equal to 3");
        Assert.assertTrue(userNotebooksPage.getTotalProductsDisplayedPerPage() <= 3);

        log.info("Sort_05 - Notebooks Page - Step 06: Verify Previous Page icon is displayed");
        Assert.assertTrue(userNotebooksPage.isPreviousPageIconDisplayed());
        userNotebooksPage.refreshCurrentPage(driver);
    }

    @Description("Sort_06 - Displayed with 6 products per page")
    @Story("Sort")
    @Test
    public void Sort_06_Displayed_With_6_Products_Per_Page() {
        log.info("Start test case 'Sort_06 - Displayed with 6 products per page'");
        log.info("Sort_06 - Notebooks Page - Step 01: Select '6' in Displayed Drop down");
        userNotebooksPage.selectDisplayedDropDownList("6");
        userNotebooksPage.sleepInSecond(3);

        log.info("Sort_06 - Notebooks Page - Step 02: Verify Product Displayed Per Page is less than or equal to 6");
        Assert.assertTrue(userNotebooksPage.getTotalProductsDisplayedPerPage() <= 6);

        log.info("Sort_06 - Notebooks Page - Step 03: Verify Next Page icon is not displayed");
        Assert.assertTrue(userNotebooksPage.isNextPageIconUndisplayed());
        userNotebooksPage.refreshCurrentPage(driver);
    }

    @Description("Sort_07 - Displayed with 9 products per page")
    @Story("Sort")
    @Test
    public void Sort_06_Displayed_With_9_Products_Per_Page() {
        log.info("Start test case 'Sort_05 - Displayed with 9 products per page'");
        log.info("Sort_07 - Notebooks Page - Step 01: Select '9' in Displayed Drop down");
        userNotebooksPage.selectDisplayedDropDownList("9");
        userNotebooksPage.sleepInSecond(3);

        log.info("Sort_07 - Notebooks Page - Step 02: Verify Product Displayed Per Page is less than or equal to 9");
        Assert.assertTrue(userNotebooksPage.getTotalProductsDisplayedPerPage() <= 9);

        log.info("Sort_07 - Notebooks Page - Step 02: Verify Next Page icon is not displayed");
        Assert.assertTrue(userNotebooksPage.isNextPageIconUndisplayed());
        userNotebooksPage.refreshCurrentPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
