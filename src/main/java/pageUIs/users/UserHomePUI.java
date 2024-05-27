package pageUIs.users;

public class UserHomePUI {
	public static final String REGISTER_LINK = "class=ico-register";
	public static final String LOGIN_LINK = "class=ico-login";
	public static final String MY_ACCOUNT_LINK = "class=ico-account";
	public static final String DYNAMIC_PRODUCT_NAME_LINK = "xpath=//h2[@class='product-title']/a[text()='%s']";
	public static final String SEARCH_LINK = "xpath=//a[text()='Search']";
	public static final String COMPUTERS_MENU = "xpath=//ul[@class='top-menu notmobile']//a[text()='Computers ']";
	public static final String ADD_TO_COMPARE_LIST_BUTTON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='add-info']//button[@title='Add to compare list']";
	public static final String PRICE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='add-info']//span[@class='price actual-price']";
	public static final String COMPARE_PRODUCTS_LIST_LINK = "xpath=//a[text()='Compare products list']";
}
