package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.users.*;

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
	
	public static UserCustomerInfoPO getUserCustomerInforPO(WebDriver driver) {
		return new UserCustomerInfoPO(driver);
	}
	
	public static UserAddressesPO getUserAddressesPO(WebDriver driver) {
		return new UserAddressesPO(driver);
	}
	
	public static UserOrdersPO getUserOrdersPO(WebDriver driver) {
		return new UserOrdersPO(driver);
	}
	
	public static UserChangePasswordPO getUserChangePasswordPO(WebDriver driver) {
		return new UserChangePasswordPO(driver);
	}

	public static UserProductDetailsPO getUserProductDetailsPO(WebDriver driver) {
		return new UserProductDetailsPO(driver);
	}

	public static UserMyProductReviewsPO getUserMyProductReviewsPO(WebDriver driver) {
		return new UserMyProductReviewsPO(driver);
	}

	public static UserSearchPO getUserSearchPO(WebDriver driver) {
		return new UserSearchPO(driver);
	}

}
