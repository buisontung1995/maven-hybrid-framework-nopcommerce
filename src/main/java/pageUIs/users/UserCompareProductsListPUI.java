package pageUIs.users;

public class UserCompareProductsListPUI {
    public static final String COLUMN_INDEX_BY_PRODUCT_NAME = "xpath=//tr[@class='product-name']//a[text()='%s']/parent::td/preceding-sibling::td";
    public static final String REMOVE_BUTTON_BY_COLUMN_INDEX = "xpath=//tr[@class='remove-product']/td[%s]";
    public static final String PRODUCT_NAME = "xpath=//tr[@class='product-name']//a[text()='%s']";
    public static final String PRODUCT_PRICE_BY_COLUMN_INDEX = "xpath=//tr[@class='product-price']/td[%s]";
    public static final String CLEAR_LIST_BUTTON = "class=clear-list";
    public static final String NO_ITEMS_TO_COMPARE_MESSAGE = "xpath=//div[@class='page-body']/div[@class='no-data']";
    public static final String COMPUTERS_MENU = "xpath=//ul[@class='top-menu notmobile']//a[text()='Computers ']";
}
