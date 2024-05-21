package pageObjects.users;

import org.openqa.selenium.WebDriver;
import pageUIs.users.UserAddressesPUI;

public class UserAddressesPO extends UserSideBarMyAccountPO{
	private WebDriver driver;

	public UserAddressesPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

    public void clickToAddNewButton() {
		waitForElementClickable(driver, UserAddressesPUI.ADD_NEW_BUTTON);
		clickToElement(driver, UserAddressesPUI.ADD_NEW_BUTTON);
    }

	public void inputToTextboxByID(String textBoxID, String inputValue) {
		waitForElementVisible(driver, UserAddressesPUI.DYNAMIC_TEXTBOX_BY_ID, textBoxID);
		sendKeyToElement(driver, UserAddressesPUI.DYNAMIC_TEXTBOX_BY_ID, inputValue, textBoxID);
	}

	public void selectCountryDropDownList(String aCountry) {
		waitForElementVisible(driver, UserAddressesPUI.COUNTRY_DROP_DOWN);
		selectItemInDefaultDropdown(driver, UserAddressesPUI.COUNTRY_DROP_DOWN, aCountry);
	}

	public void selectStateDropDownList(String aStateProvince) {
		waitForElementVisible(driver, UserAddressesPUI.STATE_PROVINCE_DROP_DOWN);
		selectItemInDefaultDropdown(driver, UserAddressesPUI.STATE_PROVINCE_DROP_DOWN, aStateProvince);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserAddressesPUI.SAVE_BUTTON);
		clickToElement(driver, UserAddressesPUI.SAVE_BUTTON);
	}

	public void waitForLoadingIconInvisible() {
		waitForElementInvisible(driver, UserAddressesPUI.LOADING_ICON);
	}

	public String getAddressInfoByClassName(String className) {
		waitForElementVisible(driver, UserAddressesPUI.DYNAMIC_ADDRESS_INFO_BY_CLASS_NAME, className);
		return getElementText(driver, UserAddressesPUI.DYNAMIC_ADDRESS_INFO_BY_CLASS_NAME, className);
	}
}
