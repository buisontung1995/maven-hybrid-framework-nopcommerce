package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserProductDetailsPUI;

public class UserProductDetailsPO extends BasePage {
	private WebDriver driver;

	public UserProductDetailsPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddYourReviewLink() {
		waitForElementClickable(driver, UserProductDetailsPUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, UserProductDetailsPUI.ADD_YOUR_REVIEW_LINK);
	}

	public void inputToReviewTitleTextbox(String reviewTitle) {
		waitForElementVisible(driver, UserProductDetailsPUI.REVIEW_TITLE_TEXTBOX);
		sendKeyToElement(driver, UserProductDetailsPUI.REVIEW_TITLE_TEXTBOX, reviewTitle);
	}

	public void inputToReviewTextTextbox(String reviewText) {
		waitForElementVisible(driver, UserProductDetailsPUI.REVIEW_TEXT_TEXTBOX);
		sendKeyToElement(driver, UserProductDetailsPUI.REVIEW_TEXT_TEXTBOX, reviewText);
	}

	public void selectRatingRadioButton(String rating) {
		waitForElementClickable(driver, UserProductDetailsPUI.DYNAMIC_RATING_RADIO_BUTTON_BY_LABEL, rating);
		checkDefaultCheckboxRadio(driver, UserProductDetailsPUI.DYNAMIC_RATING_RADIO_BUTTON_BY_LABEL, rating);
	}

	public void clickToSubmitReviewButton() {
		waitForElementClickable(driver, UserProductDetailsPUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(driver, UserProductDetailsPUI.SUBMIT_REVIEW_BUTTON);
	}

	public UserCustomerInfoPO clickToMyAccountLink() {
		waitForElementClickable(driver, UserProductDetailsPUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserProductDetailsPUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPO(driver);
	}
}
