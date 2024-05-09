package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class UserDownloadableProductsPO extends UserSideBarMyAccountPO {
	private WebDriver driver;

	public UserDownloadableProductsPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
