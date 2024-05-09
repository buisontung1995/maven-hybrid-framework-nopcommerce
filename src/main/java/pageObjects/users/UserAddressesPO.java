package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class UserAddressesPO extends UserSideBarMyAccountPO{
	private WebDriver driver;

	public UserAddressesPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
