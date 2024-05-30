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
	
	public static UserCustomerInfoPO getUserCustomerInfoPO(WebDriver driver) {
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

	public static UserComputersPO getUserComputersPO(WebDriver driver) {
		return new UserComputersPO(driver);
	}

	public static UserNotebooksPO getUserNotebooksPO(WebDriver driver) {
		return new UserNotebooksPO(driver);
	}

	public static UserWishlistPO getUserWishlistPO(WebDriver driver) {
		return new UserWishlistPO(driver);
	}

	public static UserShoppingCartPO getUserShoppingCartPO(WebDriver driver) {
		return new UserShoppingCartPO(driver);
	}

	public static UserCompareProductsListPO getUserCompareProductsListPO(WebDriver driver) {
		return new UserCompareProductsListPO(driver);
	}

	public static UserRecentlyViewedProductsPO getUserRecentlyViewedProductsPO(WebDriver driver) {
		return new UserRecentlyViewedProductsPO(driver);
	}

	public static UserDesktopsPO getUserDesktopsPO(WebDriver driver) {
		return new UserDesktopsPO(driver);
	}

	public static UserCheckoutPO getUserCheckoutPO(WebDriver driver) {
		return new UserCheckoutPO(driver);
	}

	public static UserOrderDetailsPO getUserOrderDetailsPO(WebDriver driver) {
		return new UserOrderDetailsPO(driver);
	}

}
