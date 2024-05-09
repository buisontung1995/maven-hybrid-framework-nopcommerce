package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserRegisterPO;
import utilities.Environment;

public class User_01_Register extends BaseTest {

    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private String firstName, lastName, emailAddress, password;
    private Environment env;

    @Parameters({"envName", "browserName", "browserVersion", "osName"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("chrome") String browserName, @Optional("latest") String browserVersion, @Optional("Windows 11") String osName) {
        String environmentConfig = System.getProperty("environment");
        ConfigFactory.setProperty("env", environmentConfig);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(env.appUrl(), envName, browserName, browserVersion, osName);
        userHomePage = PageGeneratorManager.getUserHomePO(driver);


        firstName = "Automation";
        lastName = "FC";
        emailAddress = "afc" + randomNumber() + "@mail.vn";
        password = "123456";
    }


    @Description("Register_01 - Register with empty data")
    @Story("Register")
    @Test
    public void Register_01_Empty_Data() {
        log.info("Start test case 'Register_01_Empty_Data'");
        log.info("Home page - Step 01: Click to Register Link");
        userRegisterPage = userHomePage.clickToRegisterLink();

        log.info("Register page - Step 02: Click to Register Button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register page - Step 03: Get Error Message");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtFirstNameTextBox(), "First name is required.");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtLastNameTextBox(), "Last name is required.");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtEmailTextBox(), "Email is required.");
        //Assert.assertEquals(userRegisterPage.getErrorMessageAtPasswordTextBox(), "Password is required.");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextBox(), "Password is required.");

        userRegisterPage.refreshCurrentPage(driver);
    }

    @Description("Register_02 - Register with invalid data")
    @Story("Register")
    @Test
    public void Register_02_Invalid_Data() {
        log.info("Start test case 'Register_02_Invalid_Data'");
        log.info("Register page - Step 01: Input data to textbox with value " + firstName + "/" + lastName + "/123@456#789/" + password + "/" + password);
        userRegisterPage.inputToFirstNameTextBox(firstName);
        userRegisterPage.inputToLastNameTextBox(lastName);
        userRegisterPage.inputToEmailTextBox("123@456#789");
        userRegisterPage.inputToPasswordTextBox(password);
        userRegisterPage.inputToConfirmPasswordTextBox(password);

        log.info("Register page - Step 02: Click to Register Button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register page - Step 03: Get Error Message");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtEmailTextBox(), "Please enter a valid email address.");

        userRegisterPage.refreshCurrentPage(driver);
    }


    @Description("Register_03 - Register successfully")
    @Story("Register")
    @Test
    public void Register_03_Register_Successfully() {
        log.info("Start test case 'Register_03_Register_Successfully'");
        log.info("Register page - Step 01: Input data to textbox with value " + firstName + "/" + lastName + "/" + emailAddress + "/" + password + "/" + password);
        userRegisterPage.inputToFirstNameTextBox(firstName);
        userRegisterPage.inputToLastNameTextBox(lastName);
        userRegisterPage.inputToEmailTextBox(emailAddress);
        userRegisterPage.inputToPasswordTextBox(password);
        userRegisterPage.inputToConfirmPasswordTextBox(password);

        log.info("Register page - Step 02: Click to Register Button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register page - Step 03: Get and verify success message");
        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

        log.info("Register page - Step 04: Click to Log out link");
        userHomePage = userRegisterPage.clickToLogOutLink();
    }


    @Description("Register_04 - Register with exist email")
    @Story("Register")
    @Test
    public void Register_04_Exist_Email() {
        log.info("Start test case 'Register_04_Exist_Email'");
        log.info("Home page - Step 01: Click to Register Link");
        userRegisterPage = userHomePage.clickToRegisterLink();

        log.info("Register page - Step 02: Input data to textbox with value " + firstName + "/" + lastName + "/" + emailAddress + "/" + password + "/" + password);
        userRegisterPage.inputToFirstNameTextBox(firstName);
        userRegisterPage.inputToLastNameTextBox(lastName);
        userRegisterPage.inputToEmailTextBox(emailAddress);
        userRegisterPage.inputToPasswordTextBox(password);
        userRegisterPage.inputToConfirmPasswordTextBox(password);

        log.info("Register page - Step 03: Click to Register Button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register page - Step 04: Get and verify Existing Email message");
        Assert.assertEquals(userRegisterPage.getExistingEmailErrorMessage(), "The specified email already exists");

        userRegisterPage.refreshCurrentPage(driver);
    }


//	@Description("Register_05 - Register with password less than 6 characters")
//	@Story("Register")
//	@Test
//	public void Register_05_Password_Less_Than_6_Chars() {
//		log.info("Start test case 'Register_05_Password_Less_Than_6_Chars'");
//		log.info("Register page - Step 01: Input data to textbox with value " + firstName + "/" + lastName + "/" + emailAddress + "/123/123" );
//		userRegisterPage.inputToFirstNameTextBox(firstName);
//		userRegisterPage.inputToLastNameTextBox(lastName);
//		userRegisterPage.inputToEmailTextBox(emailAddress);
//		userRegisterPage.inputToPasswordTextBox("123");
//		userRegisterPage.inputToConfirmPasswordTextBox("123");
//
//		log.info("Register page - Step 02: Click to Register Button");
//		userRegisterPage.clickToRegisterButton();
//
//		log.info("Register page - Step 03: Get Error Message");
//		Assert.assertEquals(userRegisterPage.getErrorMessageAtPasswordTextBox(), "Password must meet the following rules:\n" + "must have at least 6 characters");
//
//		userRegisterPage.refreshCurrentPage(driver);
//	}


    @Description("Register_06 - Register with Confirm password not matched")
    @Story("Register")
    @Test
    public void Register_06_Confirm_Password_Not_Matched() {
        log.info("Start test case 'Register_06_Confirm_Password_Not_Matched'");
        log.info("Register page - Step 01: Input data to textbox with value " + firstName + "/" + lastName + "/" + emailAddress + "/123456/1234567");
        userRegisterPage.inputToFirstNameTextBox(firstName);
        userRegisterPage.inputToLastNameTextBox(lastName);
        userRegisterPage.inputToEmailTextBox(emailAddress);
        userRegisterPage.inputToPasswordTextBox("123456");
        userRegisterPage.inputToConfirmPasswordTextBox("123457");

        log.info("Register page - Step 02: Click to Register Button");
        userRegisterPage.clickToRegisterButton();

        log.info("Register page - Step 03: Get Error Message");
        Assert.assertEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextBox(), "The password and confirmation password do not match.");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

}
