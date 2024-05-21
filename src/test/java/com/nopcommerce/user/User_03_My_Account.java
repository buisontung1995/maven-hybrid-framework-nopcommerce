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

public class User_03_My_Account extends BaseTest {

    private WebDriver driver;
    private String emailAddress, gender, firstName, lastName, dayDOB, monthDOB, yearDOB, companyName;
    private String aFirstName, aLastName, aFullName, aEmail, aCompany, aCountry, aStateProvince, aCity, aFirstAddress, aSecondAddress, aZip, aPhone, aFax;
    private String newPassword;
    private String productName, reviewDate, reviewTitle, reviewText, reviewRating;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserCustomerInfoPO userCustomerInfoPage;
    private UserAddressesPO userAddressesPage;
    private UserChangePasswordPO userChangePasswordPage;
    private UserProductDetailsPO userProductDetailsPage;
    private UserMyProductReviewsPO userMyProductReviewsPage;
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

        gender = "female";
        firstName = "Automation";
        lastName = "FC";
        dayDOB = "1";
        monthDOB = "January";
        yearDOB = "1999";
        emailAddress = "automationfc" + randomNumber() + ".vn@gmail.com";
        companyName = "Automation FC";

        aFirstName = "Automation";
        aLastName = "FC";
        aFullName = aFirstName + " " + aLastName;
        aEmail = "automationfc.vn@gmail.com";
        aCompany = "Automation FC";
        aCountry = "Viet Nam";
        aStateProvince = "Other";
        aCity = "Da Nang";
        aFirstAddress = "123/04 Le Lai";
        aSecondAddress = "234/05 Hai Phong";
        aZip = "550000";
        aPhone = "0123456789";
        aFax = "0987654321";

        newPassword = "abcd1234";

        productName = "Build your own computer";
        reviewDate = "";
        reviewTitle = "Good " + randomNumber();
        reviewText = "Good " + randomNumber();
        reviewRating = "Bad";
    }

    @Description("My_Account_01 - Customer Info")
    @Story("My Account")
    @Test
    public void My_Account_01_Customer_Info() {
        log.info("Start test case 'My_Account_01 - Customer Info'");
        log.info("My_Account_01 - Home Page - Step 01 - Click to My Account link");
        userCustomerInfoPage = userHomePage.clickToMyAccountLink();

        log.info("My_Account_01 - Customer Info Page - Step 02 - Click to '" + gender + "' button");
        userCustomerInfoPage.clickToGenderRadioButton(gender);

        log.info("My_Account_01 - Customer Info Page - Step 03 - Input to First Name Textbox with value '" + firstName + "'");
        userCustomerInfoPage.inputToFirstNameTextbox(firstName);

        log.info("My_Account_01 - Customer Info Page - Step 04 - Input to Last Name Textbox with value '" + lastName + "'");
        userCustomerInfoPage.inputToLastNameTextbox(lastName);

        log.info("My_Account_01 - Customer Info Page - Step 05 - Select Day Drop Down List with value '" + dayDOB + "'");
        userCustomerInfoPage.selectDOBDropDownList("DateOfBirthDay", dayDOB);

        log.info("My_Account_01 - Customer Info Page - Step 06 - Select Month Drop Down List with value '" + monthDOB + "'");
        userCustomerInfoPage.selectDOBDropDownList("DateOfBirthMonth", monthDOB);

        log.info("My_Account_01 - Customer Info Page - Step 07 - Select Year Drop Down List with value '" + yearDOB + "'");
        userCustomerInfoPage.selectDOBDropDownList("DateOfBirthYear", yearDOB);

        log.info("My_Account_01 - Customer Info Page - Step 08 - Input to Email Textbox with value '" + emailAddress + "'");
        userCustomerInfoPage.inputToEmailTextbox(emailAddress);

        log.info("My_Account_01 - Customer Info Page - Step 09 - Input to Company Name Textbox with value '" + companyName + "'");
        userCustomerInfoPage.inputToCompanyNameTextbox(companyName);

        log.info("My_Account_01 - Customer Info Page - Step 10 - Click To Save Button");
        userCustomerInfoPage.clickToSaveButton();

        log.info("My_Account_01 - Customer Info Page - Step 11 - Verify Updated Successfully Message is displayed");
        Assert.assertEquals(userCustomerInfoPage.getBarNotificationText(driver), "The customer info has been updated successfully.");

        log.info("My_Account_01 - Customer Info Page - Step 12 - Verify '" + gender + "' radio button is selected");
        Assert.assertTrue(userCustomerInfoPage.isGenderRadioButtonSelected(gender));

        log.info("My_Account_01 - Customer Info Page - Step 13 - Get value at First Name Textbox and compare with '" + firstName + "'");
        Assert.assertEquals(userCustomerInfoPage.getValueAtFirstNameTextbox(), firstName);

        log.info("My_Account_01 - Customer Info Page - Step 14 - Get value at Last Name Textbox and compare with '" + lastName + "'");
        Assert.assertEquals(userCustomerInfoPage.getValueAtLastNameTextbox(), lastName);

        log.info("My_Account_01 - Customer Info Page - Step 15 - Verify '" + dayDOB + "' is selected at Day Drop Down List");
        Assert.assertEquals(userCustomerInfoPage.getValueAtDOBDropDownByName("DateOfBirthDay"), dayDOB);

        log.info("My_Account_01 - Customer Info Page - Step 16 - Verify '" + monthDOB + "' is selected at Month Drop Down List");
        Assert.assertEquals(userCustomerInfoPage.getValueAtDOBDropDownByName("DateOfBirthMonth"), monthDOB);

        log.info("My_Account_01 - Customer Info Page - Step 17 - Verify '" + yearDOB + "' is selected at Year Drop Down List");
        Assert.assertEquals(userCustomerInfoPage.getValueAtDOBDropDownByName("DateOfBirthYear"), yearDOB);

        log.info("My_Account_01 - Customer Info Page - Step 18 - Get value at Email Textbox and compare with '" + emailAddress + "'");
        Assert.assertEquals(userCustomerInfoPage.getValueAtEmailTextbox(), emailAddress);

        log.info("My_Account_01 - Customer Info Page - Step 19 - Get value at Company Name Textbox and compare with '" + companyName + "'");
        Assert.assertEquals(userCustomerInfoPage.getValueAtCompanyNameTextbox(), companyName);
    }

    @Description("My_Account_02 - Addresses")
    @Story("My Account")
    @Test
    public void My_Account_02_Addresses() {
        log.info("Start test case 'My_Account_01 - Addresses'");
        log.info("My_Account_02 - Customer Info Page - Step 01 - Click to Addresses Menu");
        userAddressesPage = (UserAddressesPO) userCustomerInfoPage.openPagesAtMyAccountByName("Addresses");

        log.info("My_Account_02 - Addresses Page - Step 02 - Click to Add New Button");
        userAddressesPage.clickToAddNewButton();

        log.info("My_Account_02 - Addresses Page - Step 03 - Input to First name textbox with value '" + aFirstName + "'");
        userAddressesPage.inputToTextboxByID("Address_FirstName", aFirstName);

        log.info("My_Account_02 - Addresses Page - Step 04 - Input to Last name textbox with value '" + aLastName + "'");
        userAddressesPage.inputToTextboxByID("Address_LastName", aLastName);

        log.info("My_Account_02 - Addresses Page - Step 05 - Input to Email textbox with value '" + aEmail + "'");
        userAddressesPage.inputToTextboxByID("Address_Email", aEmail);

        log.info("My_Account_02 - Addresses Page - Step 06 - Input to Company textbox with value '" + aCompany + "'");
        userAddressesPage.inputToTextboxByID("Address_Company", aCompany);

        log.info("My_Account_02 - Addresses Page - Step 07 - Select Country Drop down list with value '" + aCountry + "'");
        userAddressesPage.selectCountryDropDownList(aCountry);

        log.info("My_Account_02 - Addresses Page - Step 08 - Select State/Province Drop down list with value '" + aStateProvince + "'");
        userAddressesPage.waitForLoadingIconInvisible();
        userAddressesPage.selectStateDropDownList(aStateProvince);

        log.info("My_Account_02 - Addresses Page - Step 09 - Input to City textbox with value '" + aCity + "'");
        userAddressesPage.inputToTextboxByID("Address_City", aCity);

        log.info("My_Account_02 - Addresses Page - Step 10 - Input to Address 1 textbox with value '" + aFirstAddress + "'");
        userAddressesPage.inputToTextboxByID("Address_Address1", aFirstAddress);

        log.info("My_Account_02 - Addresses Page - Step 11 - Input to Address 2 textbox with value '" + aSecondAddress + "'");
        userAddressesPage.inputToTextboxByID("Address_Address2", aSecondAddress);

        log.info("My_Account_02 - Addresses Page - Step 12 - Input to Zip/Postal Code textbox with value '" + aZip + "'");
        userAddressesPage.inputToTextboxByID("Address_ZipPostalCode", aZip);

        log.info("My_Account_02 - Addresses Page - Step 13 - Input to Phone number textbox with value '" + aPhone + "'");
        userAddressesPage.inputToTextboxByID("Address_PhoneNumber", aPhone);

        log.info("My_Account_02 - Addresses Page - Step 14 - Input to Fax number textbox with value '" + aFax + "'");
        userAddressesPage.inputToTextboxByID("Address_FaxNumber", aFax);

        log.info("My_Account_02 - Addresses Page - Step 15 - Click to save button");
        userAddressesPage.clickToSaveButton();

        log.info("My_Account_02 - Addresses Page - Step 16 - Verify Added Successfully Message is displayed");
        Assert.assertEquals(userAddressesPage.getBarNotificationText(driver), "The new address has been added successfully.");

        log.info("My_Account_02 - Addresses Page - Step 17 - Verify Name and compare with '" + aFullName + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("name"), aFullName);

        log.info("My_Account_02 - Addresses Page - Step 18 - Verify Email and compare with '" + aEmail + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("email"), "Email: " + aEmail);

        log.info("My_Account_02 - Addresses Page - Step 19 - Verify Phone and compare with '" + aPhone + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("phone"), "Phone number: " + aPhone);

        log.info("My_Account_02 - Addresses Page - Step 20 - Verify Fax and compare with '" + aFax + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("fax"), "Fax number: " + aFax);

        log.info("My_Account_02 - Addresses Page - Step 21 - Verify Company and compare with '" + aCompany + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("company"), aCompany);

        log.info("My_Account_02 - Addresses Page - Step 22 - Verify Country and compare with '" + aCountry + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("country"), aCountry);

        log.info("My_Account_02 - Addresses Page - Step 23 - Verify City and compare with '" + aCity + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("city"), aCity);

        log.info("My_Account_02 - Addresses Page - Step 24 - Verify Address 1  and compare with '" + aFirstAddress + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("address1"), aFirstAddress);

        log.info("My_Account_02 - Addresses Page - Step 25 - Verify Address 2 and compare with '" + aSecondAddress + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("address2"), aSecondAddress);

        log.info("My_Account_02 - Addresses Page - Step 26 - Verify Zip/Postal code and compare with '" + aZip + "'");
        Assert.assertEquals(userAddressesPage.getAddressInfoByClassName("zippostalcode"), aZip);

    }

    @Description("My_Account_03 - Change Password")
    @Story("My Account")
    @Test
    public void My_Account_03_Change_Password() {
        log.info("Start test case 'My_Account_03 - Change Password'");
        log.info("My_Account_03 - Addresses Page - Step 01 - Click to Change password Menu");
        userChangePasswordPage = (UserChangePasswordPO) userAddressesPage.openPagesAtMyAccountByName("Change password");

        log.info("My_Account_03 - Addresses Page - Step 02 - Input to Old password textbox with value '" + Common_01_Register.password + "'");
        userChangePasswordPage.inputToOldPasswordTextbox(Common_01_Register.password);

        log.info("My_Account_03 - Addresses Page - Step 03 - Input to New password textbox with value '" + newPassword + "'");
        userChangePasswordPage.inputToNewPasswordTextbox(newPassword);

        log.info("My_Account_03 - Addresses Page - Step 04 - Input to Confirm password textbox with value '" + newPassword + "'");
        userChangePasswordPage.inputToConfirmPasswordTextbox(newPassword);

        log.info("My_Account_03 - Addresses Page - Step 05 - Click to Change password button");
        userChangePasswordPage.clickToChangePasswordButton();

        log.info("My_Account_03 - Addresses Page - Step 06 - Get Changed successfully message");
        Assert.assertEquals(userChangePasswordPage.getBarNotificationText(driver), "Password was changed");

        log.info("My_Account_03 - Addresses Page - Step 07 - Click to Close Notification button");
        userChangePasswordPage.clickToCloseNotificationButton(driver);

        log.info("My_Account_03 - Addresses Page - Step 08 - Click to Log out Link");
        userHomePage = userChangePasswordPage.clickToLogOutLink();

        log.info("My_Account_03 - Home Page - Step 09 - Click to Log in Link");
        userLoginPage = userHomePage.clickToLoginLink();

        log.info("My_Account_03 - Login Page - Step 10 - Input to Email textbox with value '" + emailAddress + "'");
        userLoginPage.inputToEmailTextBox(emailAddress);

        log.info("My_Account_03 - Login Page - Step 11 - Input to Password textbox with value '" + Common_01_Register.password + "'");
        userLoginPage.inputToPasswordTextBox(Common_01_Register.password);

        log.info("My_Account_03 - Login Page - Step 12 - Click to Login button");
        userHomePage = userLoginPage.clickToLoginButton();

        log.info("My_Account_03 - Login Page - Step 13: Get Error Message");
        Assert.assertEquals(userLoginPage.getSummartErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
                + "The credentials provided are incorrect");
        userLoginPage.refreshCurrentPage(driver);

        log.info("My_Account_03 - Login Page - Step 14 - Input to Email textbox with value '" + emailAddress + "'");
        userLoginPage.inputToEmailTextBox(emailAddress);

        log.info("My_Account_03 - Login Page - Step 15 - Input to Password textbox with value '" + newPassword + "'");
        userLoginPage.inputToPasswordTextBox(newPassword);

        log.info("My_Account_03 - Login Page - Step 16 - Click to Login button");
        userHomePage = userLoginPage.clickToLoginButton();

        log.info("My_Account_03 - Home Page - Step 17 - Verify Homepage - My Account Link is displayed");
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

    }

    @Description("My_Account_04 - My Product Reviews")
    @Story("My Account")
    @Test
    public void My_Account_04_My_Product_Reviews() {
        log.info("Start test case 'My_Account_04 - My Product Reviews'");
        log.info("My_Account_04 - Home Page - Step 01: Click to 'Build your own computer' product");
        userProductDetailsPage = userHomePage.clickToProductDetailByName(productName);

        log.info("My_Account_04 - Product Details Page - Step 02: Click to 'Add your review' link");
        userProductDetailsPage.clickToAddYourReviewLink();

        log.info("My_Account_04 - Product Details Page - Step 03: Input to Review title textbox with value '" + reviewTitle + "'");
        userProductDetailsPage.inputToReviewTitleTextbox(reviewTitle);

        log.info("My_Account_04 - Product Details Page - Step 04: Input to Review text textbox with value '" + reviewText + "'");
        userProductDetailsPage.inputToReviewTextTextbox(reviewText);

        log.info("My_Account_04 - Product Details Page - Step 05: Select Rating with value '" + reviewRating + "'");
        userProductDetailsPage.selectRatingRadioButton(reviewRating);

        log.info("My_Account_04 - Product Details Page - Step 06: Click to 'Submit Review' button");
        userProductDetailsPage.clickToSubmitReviewButton();

        log.info("My_Account_04 - Product Details Page - Step 07: Get Submit Review successfully message");
        Assert.assertEquals(userProductDetailsPage.getBarNotificationText(driver), "Product review is successfully added.");

        log.info("My_Account_04 - Product Details Page - Step 08: Click to Close Notification button");
        userProductDetailsPage.clickToCloseNotificationButton(driver);

        log.info("My_Account_04 - Product Details Page - Step 09: Click to My Account link");
        userCustomerInfoPage = userProductDetailsPage.clickToMyAccountLink();

        log.info("My_Account_04 - Customer Info Page - Step 10: Click To My product reviews menu");
        userMyProductReviewsPage = (UserMyProductReviewsPO) userCustomerInfoPage.openPagesAtMyAccountByName("My product reviews");

        log.info("My_Account_04 - My Product Reviews Page - Step 11: Get Date of Recent Review By Product Name");
        reviewDate = userMyProductReviewsPage.getDateOfRecentReview(productName);

        log.info("My_Account_04 - My Product Reviews Page - Step 12: Get Review Title by Product Name and Review Date");
        Assert.assertEquals(userMyProductReviewsPage.getReviewTitleByProductNameAndReviewDate(productName, reviewDate), reviewTitle);

        log.info("My_Account_04 - My Product Reviews Page - Step 13: Get Review Rating by Product Name and Review Date");
        Assert.assertEquals(userMyProductReviewsPage.getReviewRatingByProductNameAndReviewDate(productName, reviewDate), reviewRating);

        log.info("My_Account_04 - My Product Reviews Page - Step 14: Get Review Text by Product Name and Review Date");
        Assert.assertEquals(userMyProductReviewsPage.getReviewTextByProductNameAndReviewDate(productName, reviewDate), reviewText);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
