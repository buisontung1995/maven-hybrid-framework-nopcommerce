package pageUIs.users;

public class UserWishlistPUI {
	public static final String WISHLIST_PRODUCT_LINK = "xpath=//td[@class='product']/a[text()='%s']";
    public static final String WISHLIST_URL_FOR_SHARING = "xpath=//div[@class='share-info']/a[@class='share-link']";
    public static final String WISHLIST_SHARING_TITLE = "xpath=//div[@class='page-title']/h1";
    public static final String ADD_TO_CART_CHECKBOX_BY_PRODUCT_NAME = "xpath=//td[@class='product']/a[text()='%s']/parent::td/preceding-sibling::td[@class='add-to-cart']/input";
    public static final String ADD_TO_CART_BUTTON = "name=addtocartbutton";
    public static final String EMPTY_WISHLIST_MESSAGE = "xpath=//div[@class='page-body']/div[@class='no-data']";
    public static final String HOME_PAGE_LOGO = "xpath=//div[@class='header-logo']//img";
    public static final String REMOVE_BUTTON = "xpath=//td[@class='product']/a[text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";
}
