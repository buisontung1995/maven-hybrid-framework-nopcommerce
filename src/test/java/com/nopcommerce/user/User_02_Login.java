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
import utilities.Environment;

public class User_02_Login extends BaseTest {

    private WebDriver driver;
    private String emailAddress, password;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
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

        emailAddress = Common_01_Register.emailAddress;
        password = Common_01_Register.password;
    }

    @Description("Login_01 - Login with empty data")
    @Story("Login")
    @Test
    public void Login_01_Empty_Data() {
        log.info("Start test case 'Login_01 - Login with empty data'");
        log.info("Login_01 - Login Page - Step 01 - Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login_01 - Login Page - Step 02: Get Error Message");
        Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
        userLoginPage.refreshCurrentPage(driver);
    }

    @Description("Login_02 - Login with Invalid Email")
    @Story("Login")
    @Test
    public void Login_02_Invalid_Email() {
        log.info("Start test case 'Login_02 - Login with Invalid Email'");

        log.info("Login_02 - Login Page - Step 01 - Input to Email textbox with value 'abcd1234'");
        userLoginPage.inputToEmailTextBox("abcd1234");

        log.info("Login_02 - Login Page - Step 02 - Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login_02 - Login Page - Step 03: Get Error Message");
        Assert.assertEquals(userLoginPage.getErrorMessageAtEmailTextbox(), "Please enter a valid email address.");
        userLoginPage.refreshCurrentPage(driver);
    }

    @Description("Login_03 - Login with Unregistered Email")
    @Story("Login")
    @Test
    public void Login_03_Unregistered_Email() {
        log.info("Start test case 'Login_03 - Login with Unregistered Email'");

        log.info("Login_03 - Login Page - Step 01 - Input to Email textbox with value 'abcd@gmail.com'");
        userLoginPage.inputToEmailTextBox("abcd@gmail.com");

        log.info("Login_03 - Login Page - Step 02 - Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login_03 - Login Page - Step 03: Get Error Message");
        Assert.assertEquals(userLoginPage.getSummartErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
                + "No customer account found");
        userLoginPage.refreshCurrentPage(driver);
    }

    @Description("Login_04 - Login with Registered Email and Empty Password")
    @Story("Login")
    @Test
    public void Login_04_Registered_Email_Empty_Password() {
        log.info("Start test case 'Login_04 - Login with Registered Email and Empty Password'");

        log.info("Login_04 - Login Page - Step 01 - Input to Email textbox with value '" + emailAddress + "'");
        userLoginPage.inputToEmailTextBox(emailAddress);

        log.info("Login_04 - Login Page - Step 02 - Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login_04 - Login Page - Step 03: Get Error Message");
        Assert.assertEquals(userLoginPage.getSummartErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
                + "The credentials provided are incorrect");
        userLoginPage.refreshCurrentPage(driver);
    }

    @Description("Login_05 - Login with Registered Email and Invalid Password")
    @Story("Login")
    @Test
    public void Login_05_Registered_Email_Invalid_Password() {
        log.info("Start test case 'Login_05 - Login with Registered Email and Invalid Password'");

        log.info("Login_05 - Login Page - Step 01 - Input to Email textbox with value '" + emailAddress + "'");
        userLoginPage.inputToEmailTextBox(emailAddress);

        log.info("Login_05 - Login Page - Step 02 - Input to Password textbox with value '11111111'");
        userLoginPage.inputToPasswordTextBox("11111111");

        log.info("Login_05 - Login Page - Step 03 - Click to Login button");
        userLoginPage.clickToLoginButton();

        log.info("Login_05 - Login Page - Step 04: Get Error Message");
        Assert.assertEquals(userLoginPage.getSummartErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
                + "The credentials provided are incorrect");
        userLoginPage.refreshCurrentPage(driver);
    }

    @Description("Login_06 - Login Successfully")
    @Story("Login")
    @Test
    public void Login_06_Login_Successfully() {
        log.info("Start test case 'Login_06 - Login Successfully'");

        log.info("Login_06 - Login Page - Step 01 - Input to Email textbox with value '" + emailAddress + "'");
        userLoginPage.inputToEmailTextBox(emailAddress);

        log.info("Login_06 - Login Page - Step 02 - Input to Password textbox with value '" + password + "'");
        userLoginPage.inputToPasswordTextBox(password);

        log.info("Login_06 - Login Page - Step 03 - Click to Login button");
        userHomePage = userLoginPage.clickToLoginButton();

        log.info("Login_06 - Home Page - Step 04 - Verify Homepage - My Account Link is displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
