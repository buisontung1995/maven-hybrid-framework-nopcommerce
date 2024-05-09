package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.users.UserAddressesPO;
import pageObjects.users.UserCustomerInforPO;
import pageObjects.users.UserDownloadableProductsPO;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserLoginPO;
import pageObjects.users.UserOrdersPO;
import pageObjects.users.UserRegisterPO;

public class PageGeneratorManager {
		
	public static UserHomePO getUserHomePO(WebDriver driver) {
		return new UserHomePO(driver);
	}
	
	public static UserRegisterPO getUserRegisterPO(WebDriver driver) {
		return new UserRegisterPO(driver);
	}
	
	public static UserLoginPO getUserLoginPO(WebDriver driver) {
		return new UserLoginPO(driver);
	}
	
	public static UserCustomerInforPO getUserCustomerInforPO(WebDriver driver) {
		return new UserCustomerInforPO(driver);
	}
	
	public static UserAddressesPO getUserAddressesPO(WebDriver driver) {
		return new UserAddressesPO(driver);
	}
	
	public static UserOrdersPO getUserOrdersPO(WebDriver driver) {
		return new UserOrdersPO(driver);
	}
	
	public static UserDownloadableProductsPO getUserDownloadableProductsPO(WebDriver driver) {
		return new UserDownloadableProductsPO(driver);
	}

}
