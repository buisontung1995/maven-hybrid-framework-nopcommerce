package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class UserOrdersPO extends UserSideBarMyAccountPO{
	private WebDriver driver;

	public UserOrdersPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
