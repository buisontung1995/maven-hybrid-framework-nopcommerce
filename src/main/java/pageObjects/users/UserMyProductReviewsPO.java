package pageObjects.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.users.UserMyProductReviewsPUI;

public class UserMyProductReviewsPO extends UserSideBarMyAccountPO {
    private WebDriver driver;

    public UserMyProductReviewsPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getDateOfRecentReview(String productName) {
        waitForAllElementVisible(driver, UserMyProductReviewsPUI.REVIEW_DATE_BY_PRODUCT_NAME, productName);
        WebElement element = getListWebElement(driver, UserMyProductReviewsPUI.REVIEW_DATE_BY_PRODUCT_NAME, productName).get(0);
        return element.getText();
    }

    public String getReviewTitleByProductNameAndReviewDate(String productName, String reviewDate) {
        waitForElementVisible(driver, UserMyProductReviewsPUI.REVIEW_TITLE_BY_PRODUCT_NAME, productName, reviewDate);
        return getElementText(driver, UserMyProductReviewsPUI.REVIEW_TITLE_BY_PRODUCT_NAME, productName, reviewDate);
    }

    public String getReviewRatingByProductNameAndReviewDate(String productName, String reviewDate) {
        waitForElementVisible(driver, UserMyProductReviewsPUI.REVIEW_RATING_BY_PRODUCT_NAME, productName, reviewDate);
        String ratingValue = getElementAttribute(driver, UserMyProductReviewsPUI.REVIEW_RATING_BY_PRODUCT_NAME, "style", productName, reviewDate).trim().replace(";", "").substring(7);
        String ratingToString;
        switch (ratingValue) {
            case "20%":
                ratingToString = "Bad";
                break;
            case "40%":
                ratingToString = "Not Good";
                break;
            case "60%":
                ratingToString = "Not bad but also not excellent";
                break;
            case "80%":
                ratingToString = "Good";
                break;
            case "100%":
                ratingToString = "Excellent";
                break;
            default:
                ratingToString = "Not valid rating value";
                break;
        }
        return ratingToString;
    }


    public String getReviewTextByProductNameAndReviewDate(String productName, String reviewDate) {
        waitForElementVisible(driver, UserMyProductReviewsPUI.REVIEW_TEXT_BY_PRODUCT_NAME, productName, reviewDate);
        return getElementText(driver, UserMyProductReviewsPUI.REVIEW_TEXT_BY_PRODUCT_NAME, productName, reviewDate);
    }
}
