package pageUIs.users;

public class UserShoppingCartPUI {
    public static final String SELECTED_PRODUCT_LINK = "xpath=//td[@class='product']/a[text()='%s']";
    public static final String WISHLIST_LINK = "class=ico-wishlist";
    public static final String AJAX_LOADING_ICON = "class=ajax-loading-block-window";
    public static final String EDIT_LINK_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/following-sibling::div[@class='edit-item']/a";
    public static final String REMOVE_BUTTON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";
    public static final String SHOPPING_CART_EMPTY_MESSAGE = "xpath=//div[@class='page-body']//div[@class='no-data']";
    public static final String COMPUTERS_MENU = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]";
    public static final String DESKTOPS_SUB_MENU = "xpath=//ul[@class='top-menu notmobile']//ul[@class='sublist first-level']//a[contains(text(),'Desktops')]";
    public static final String NOTEBOOKS_SUB_MENU = "xpath=//ul[@class='top-menu notmobile']//ul[@class='sublist first-level']//a[contains(text(),'Notebooks')]";
    public static final String QUANTITY_CHECKBOX_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='quantity']//input";
    public static final String TOTAL_PRICE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='subtotal']/span[@class='product-subtotal']";
    public static final String GIFT_WRAPPING_DROP_DOWN_LIST = "xpath=//label[contains(text(), 'Gift wrapping')]/parent::dt/following-sibling::dd/select";
    public static final String TERM_OF_SERVICE_CHECKBOX = "id=termsofservice";
    public static final String CHECKOUT_BUTTON = "id=checkout";
}
