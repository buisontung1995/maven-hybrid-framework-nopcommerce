package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.users.UserCustomerInfoPUI;
import pageUIs.users.UserSideBarMyAccountPUI;

public class UserSideBarMyAccountPO extends BasePage {
    private WebDriver driver;

    public UserSideBarMyAccountPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserSideBarMyAccountPO openPagesAtMyAccountByName(String pageName) {
        waitForElementClickable(driver, pageUIs.users.UserSideBarMyAccountPUI.DYNAMIC_LINK_AT_MY_ACCOUNT_AREA, pageName);
        clickToElement(driver, pageUIs.users.UserSideBarMyAccountPUI.DYNAMIC_LINK_AT_MY_ACCOUNT_AREA, pageName);
        switch (pageName) {
            case "Customer info":
                return PageGeneratorManager.getUserCustomerInforPO(driver);
            case "Addresses":
                return PageGeneratorManager.getUserAddressesPO(driver);
            case "Orders":
                return PageGeneratorManager.getUserOrdersPO(driver);
            case "Change password":
                return PageGeneratorManager.getUserChangePasswordPO(driver);
            case "My product reviews":
                return PageGeneratorManager.getUserMyProductReviewsPO(driver);

            default:
                throw new RuntimeException("Invalid page name at My account area");
        }
    }


}
