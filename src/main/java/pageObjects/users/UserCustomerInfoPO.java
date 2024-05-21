package pageObjects.users;

import org.openqa.selenium.WebDriver;
import pageUIs.users.UserCustomerInfoPUI;

public class UserCustomerInfoPO extends UserSideBarMyAccountPO{
	private WebDriver driver;

	public UserCustomerInfoPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

    public void clickToGenderRadioButton(String gender) {
		waitForElementClickable(driver, UserCustomerInfoPUI.GENDER_RADIO_BUTTON, gender);
		checkDefaultCheckboxRadio(driver, UserCustomerInfoPUI.GENDER_RADIO_BUTTON, gender);
    }

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void selectDOBDropDownList(String dropdownName, String valueDOB) {
		waitForElementClickable(driver, UserCustomerInfoPUI.DOB_DROP_DOWN, dropdownName);
		selectItemInDefaultDropdown(driver, UserCustomerInfoPUI.DOB_DROP_DOWN, valueDOB, dropdownName);
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToCompanyNameTextbox(String companyName) {
		waitForElementVisible(driver, UserCustomerInfoPUI.COMPANY_NAME_TEXTBOX);
		sendKeyToElement(driver, UserCustomerInfoPUI.COMPANY_NAME_TEXTBOX, companyName);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserCustomerInfoPUI.SAVE_BUTTON);
		clickToElement(driver, UserCustomerInfoPUI.SAVE_BUTTON);
	}

	public boolean isGenderRadioButtonSelected(String gender) {
		waitForElementVisible(driver, UserCustomerInfoPUI.GENDER_RADIO_BUTTON, gender);
		return isElementSelected(driver, UserCustomerInfoPUI.GENDER_RADIO_BUTTON, gender);
	}

	public String getValueAtFirstNameTextbox() {
		waitForElementVisible(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX);
		return getElementAttribute(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getValueAtLastNameTextbox() {
		waitForElementVisible(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getValueAtDOBDropDownByName(String dropdownName) {
		waitForElementVisible(driver, UserCustomerInfoPUI.DOB_DROP_DOWN, dropdownName);
		return getSelectedItemDefaultDropdown(driver, UserCustomerInfoPUI.DOB_DROP_DOWN, dropdownName);
	}

	public String getValueAtEmailTextbox() {
		waitForElementVisible(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX);
		return getElementAttribute(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX, "value");
	}

	public String getValueAtCompanyNameTextbox() {
		waitForElementVisible(driver, UserCustomerInfoPUI.COMPANY_NAME_TEXTBOX);
		return getElementAttribute(driver, UserCustomerInfoPUI.COMPANY_NAME_TEXTBOX, "value");
	}


}
