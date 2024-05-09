package pageObjects.users;

import org.openqa.selenium.WebDriver;

public class UserCustomerInforPO extends UserSideBarMyAccountPO{
	private WebDriver driver;

	public UserCustomerInforPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
