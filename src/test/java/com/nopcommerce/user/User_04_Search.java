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
import pageObjects.users.UserHomePO;
import pageObjects.users.UserLoginPO;
import pageObjects.users.UserSearchPO;
import utilities.Environment;

public class User_04_Search extends BaseTest {

    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserSearchPO userSearchPage;
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

        log.info("Precondition - Home Page - Step 05 - Click to Search link at footer");
        userSearchPage = userHomePage.clickToSearchLink();
    }

    @Description("Search_01 - Search with empty data")
    @Story("Search")
    @Test
    public void Search_01_Empty_Data() {
        log.info("Start test case 'Search_01 - Search with empty data'");
        log.info("Search_01 - Search Page - Step 01: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_01 - Search Page - Step 02: Get Search error message");
        Assert.assertEquals(userSearchPage.getSearchErrorMessage(), "Search term minimum length is 3 characters");

        userSearchPage.refreshCurrentPage(driver);
    }

    @Description("Search_02 - Search with data not exist")
    @Story("Search")
    @Test
    public void Search_02_Data_Not_Exist() {
        log.info("Start test case 'Search_02 - Search with data not exist'");
        log.info("Search_02 - Search Page - Step 01: Input to Search textbox with value 'Macbook Pro 2050'");
        userSearchPage.inputToSearchTextbox("Macbook Pro 2050");

        log.info("Search_01 - Search Page - Step 01: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_01 - Search Page - Step 02: Get Search error message");
        Assert.assertEquals(userSearchPage.getSearchErrorMessage(), "No products were found that matched your criteria.");
        userSearchPage.refreshCurrentPage(driver);
    }

    @Description("Search_03 - Search with Relative Data")
    @Story("Search")
    @Test
    public void Search_03_Relative_Data() {
        log.info("Start test case 'Search_03 - Search with Relative Data'");
        log.info("Search_03 - Search Page - Step 01: Input to Search textbox with value 'Lenovo'");
        userSearchPage.inputToSearchTextbox("Lenovo");

        log.info("Search_03 - Search Page - Step 02: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_03 - Search Page - Step 03: Get Total Search Result");
        Assert.assertEquals(userSearchPage.getTotalSearchResult(), 2);
        userLoginPage.refreshCurrentPage(driver);
    }

    @Description("Search_04 - Search with Absolute Data")
    @Story("Search")
    @Test
    public void Search_04_Absolute_Data() {
        log.info("Start test case 'Search_04 - Search with Absolute Data'");
        log.info("Search_04 - Search Page - Step 01: Input to Search textbox with value 'Lenovo Thinkpad X1 Carbon Laptop'");
        userSearchPage.inputToSearchTextbox("Lenovo Thinkpad X1 Carbon Laptop");

        log.info("Search_04 - Search Page - Step 02: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_04 - Search Page - Step 03: Get Total Search Result");
        Assert.assertEquals(userSearchPage.getTotalSearchResult(), 1);
        userSearchPage.refreshCurrentPage(driver);
    }

    @Description("Search_05 - Advanced Search with Parent Categories")
    @Story("Search")
    @Test
    public void Search_05_Advanced_Search_With_Parent_Categories() {
        log.info("Start test case 'Search_05 - Advanced Search with Parent Categories'");
        log.info("Search_05 - Search Page - Step 01: Input to Search textbox with value 'Apple Macbook Pro'");
        userSearchPage.inputToSearchTextbox("Apple Macbook Pro");

        log.info("Search_05 - Search Page - Step 02: Select Advanced Search Checkbox");
        userSearchPage.selectAdvancedSearchCheckbox();

        log.info("Search_05 - Search Page - Step 03: Select 'Computers' value in Category Drop Down list");
        userSearchPage.selectCategoryDropDownList("Computers");

        log.info("Search_05 - Search Page - Step 04: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_05 - Search Page - Step 05: Get Search error message");
        Assert.assertEquals(userSearchPage.getSearchErrorMessage(), "No products were found that matched your criteria.");
        userSearchPage.refreshCurrentPage(driver);
    }

    @Description("Search_06 - Advanced Search with Sub Categories")
    @Story("Search")
    @Test
    public void Search_06_Advanced_Search_With_Sub_Categories() {
        log.info("Start test case 'Search_06 - Advanced Search with Sub Categories'");
        log.info("Search_06 - Search Page - Step 01: Input to Search textbox with value 'Apple Macbook Pro'");
        userSearchPage.inputToSearchTextbox("Apple Macbook Pro");

        log.info("Search_06 - Search Page - Step 02: Select Advanced Search Checkbox");
        userSearchPage.selectAdvancedSearchCheckbox();

        log.info("Search_06 - Search Page - Step 03: Select 'Computers' value in Category Drop Down list");
        userSearchPage.selectCategoryDropDownList("Computers");

        log.info("Search_06 - Search Page - Step 04: Select Automatically Search Sub Categories checkbox");
        userSearchPage.selectAutomaticallySearchSubCategoriesCheckbox();

        log.info("Search_06 - Search Page - Step 05: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_06 - Search Page - Step 06: Get Total Search Result");
        Assert.assertEquals(userSearchPage.getTotalSearchResult(), 1);
        userSearchPage.refreshCurrentPage(driver);
    }

    @Description("Search_07 - Advanced Search with Incorrect Manufacturer")
    @Story("Search")
    @Test
    public void Search_07_Advanced_Search_With_Incorrect_Manufacturer() {
        log.info("Start test case 'Search_07 - Advanced Search with Incorrect Manufacturer'");
        log.info("Search_07 - Search Page - Step 01: Input to Search textbox with value 'Apple Macbook Pro'");
        userSearchPage.inputToSearchTextbox("Apple Macbook Pro");

        log.info("Search_07 - Search Page - Step 02: Select Advanced Search Checkbox");
        userSearchPage.selectAdvancedSearchCheckbox();

        log.info("Search_07 - Search Page - Step 03: Select 'Computers' value in Category Drop Down list");
        userSearchPage.selectCategoryDropDownList("Computers");

        log.info("Search_07 - Search Page - Step 04: Select Automatically Search Sub Categories checkbox");
        userSearchPage.selectAutomaticallySearchSubCategoriesCheckbox();

        log.info("Search_07 - Search Page - Step 05: Select 'Computers' value in Category Drop Down list");
        userSearchPage.selectManufacturerDropDownList("HP");

        log.info("Search_07 - Search Page - Step 06: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_07 - Search Page - Step 07: Get Search error message");
        Assert.assertEquals(userSearchPage.getSearchErrorMessage(), "No products were found that matched your criteria.");
        userSearchPage.refreshCurrentPage(driver);
    }

    @Description("Search_08 - Advanced Search with Correct Manufacturer")
    @Story("Search")
    @Test
    public void Search_08_Advanced_Search_With_Correct_Manufacturer() {
        log.info("Start test case 'Search_08 - Advanced Search with Correct Manufacturer'");
        log.info("Search_08 - Search Page - Step 01: Input to Search textbox with value 'Apple Macbook Pro'");
        userSearchPage.inputToSearchTextbox("Apple Macbook Pro");

        log.info("Search_08 - Search Page - Step 02: Select Advanced Search Checkbox");
        userSearchPage.selectAdvancedSearchCheckbox();

        log.info("Search_08 - Search Page - Step 03: Select 'Computers' value in Category Drop Down list");
        userSearchPage.selectCategoryDropDownList("Computers");

        log.info("Search_08 - Search Page - Step 04: Select Automatically Search Sub Categories checkbox");
        userSearchPage.selectAutomaticallySearchSubCategoriesCheckbox();

        log.info("Search_08 - Search Page - Step 05: Select 'Computers' value in Category Drop Down list");
        userSearchPage.selectManufacturerDropDownList("Apple");

        log.info("Search_08 - Search Page - Step 06: Click to Search button");
        userSearchPage.clickToSearchButton();

        log.info("Search_08 - Search Page - Step 07: Get Total Search Result");
        Assert.assertEquals(userSearchPage.getTotalSearchResult(), 1);
        userSearchPage.refreshCurrentPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
