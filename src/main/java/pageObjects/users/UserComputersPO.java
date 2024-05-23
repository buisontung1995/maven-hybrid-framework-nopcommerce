package pageObjects.users;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserComputersPUI;

public class UserComputersPO extends BasePage {
	private WebDriver driver;

	public UserComputersPO(WebDriver driver) {
		this.driver = driver;
	}

	public UserNotebooksPO clickToNotebooksSubMenu() {
		waitForElementClickable(driver, UserComputersPUI.NOTEBOOKS_SUB_MENU);
		clickToElement(driver, UserComputersPUI.NOTEBOOKS_SUB_MENU);
		return PageGeneratorManager.getUserNotebooksPO(driver);
	}
}
