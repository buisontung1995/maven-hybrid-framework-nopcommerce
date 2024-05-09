package com.nopcommerce.common;

import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserLoginPO;
import pageObjects.users.UserRegisterPO;
import utilities.Environment;

public class Common_01_Register_Cookies extends BaseTest {

	private WebDriver driver;
	private UserHomePO userHomePage;
	private UserRegisterPO userRegisterPage;
	private UserLoginPO userLoginPage;
	private String firstName, lastName, emailAddress, password;
	private Environment env;
	public static Set<Cookie> loggedCookies;


	@Parameters({"envName", "browserName", "browserVersion", "osName"})
	@BeforeTest(description = "Create new common User for all classes test")
	public void Register(@Optional("local") String envName, @Optional("chrome") String browserName, @Optional("latest") String browserVersion, @Optional("Windows 11") String osName) {
		String environmentConfig = System.getProperty("environment");
		ConfigFactory.setProperty("env", environmentConfig);
		env = ConfigFactory.create(Environment.class);

		driver = getBrowserDriver(env.appUrl(), envName, browserName, browserVersion, osName);
		userHomePage = PageGeneratorManager.getUserHomePO(driver);
		
		userHomePage = PageGeneratorManager.getUserHomePO(driver);
		driver.manage().window().maximize();
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + randomNumber() + "@mail.vn";
		password = "123456";
		
		log.info("Precondition - Step 01: Go to 'Register' page");	
		userRegisterPage = userHomePage.clickToRegisterLink();

		log.info("Precondition - Step 02: Input to 'Firstname' textbox with value is '" + firstName + "'");
		userRegisterPage.inputToFirstNameTextBox(firstName);
		
		log.info("Precondition - Step 03: Input to 'Lastname' textbox with value is '" + lastName + "'");
		userRegisterPage.inputToLastNameTextBox(lastName);
		
		log.info("Precondition - Step 04: Input to 'Email' textbox with value is '" + emailAddress + "'");
		userRegisterPage.inputToEmailTextBox(emailAddress);
		
		log.info("Precondition - Step 05: Input to 'Password' textbox with value is '" + password + "'");
		userRegisterPage.inputToPasswordTextBox(password);
		
		log.info("Precondition - Step 06: Input to 'Confirm password' textbox with value is '" + password + "'");
		userRegisterPage.inputToConfirmPasswordTextBox(password);
		
		log.info("Precondition - Step 07: Click to 'Register' button");
		userRegisterPage.clickToRegisterButton();
		
		log.info("Precondition - Step 08: Verify register successful message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Precondition - Step 09: Click to Home Page Logo");	
		userHomePage = userRegisterPage.clickToHomePageLogo();
		
		log.info("Precondition - Step 10: Click to 'Login' link");
		userLoginPage = userHomePage.clickToLoginLink();
		
		log.info("Precondition - Step 11: Input to 'Email' textbox with value is '" + emailAddress + "'");
		userLoginPage.inputToEmailTextBox(emailAddress);
		
		log.info("Precondition - Step 12: Input to 'Password' textbox with value is '" + password + "'");
		userLoginPage.inputToPasswordTextBox(password);
		
		log.info("Precondition - Step 13: Click to 'Login' button");
		userHomePage = userLoginPage.clickToLoginButton();
		
		loggedCookies = userHomePage.getAllCookies(driver);
		driver.quit();
		
	}
	
	@AfterTest
	public void afterClass() {
	}
	
	
	
}
