package pageUIs.users;

public class UserProductDetailsPUI {
    public static final String ADD_YOUR_REVIEW_LINK = "xpath=//a[@href='#addreview']";
    public static final String REVIEW_TITLE_TEXTBOX = "id=AddProductReview_Title";
    public static final String REVIEW_TEXT_TEXTBOX = "id=AddProductReview_ReviewText";
    public static final String DYNAMIC_RATING_RADIO_BUTTON_BY_LABEL = "xpath=//input[@aria-label='%s']";
    public static final String SUBMIT_REVIEW_BUTTON = "id=add-review";
    public static final String MY_ACCOUNT_LINK = "class=ico-account";
    public static final String ADD_TO_WISHLIST_BUTTON = "xpath=//div[@class='add-to-wishlist']/button";
    public static final String WISHLIST_LINK_FROM_NOTIFICATION = "xpath=//div[@id='bar-notification']//p[@class='content']/a";
    public static final String PROCESSOR_DROP_DOWN_LIST = "xpath=//label[text()=' Processor ']/parent::dt/following-sibling::dd/select[@name='product_attribute_1']";
    public static final String RAM_DROP_DOWN_LIST = "xpath=//label[text()=' RAM ']/parent::dt/following-sibling::dd/select[@name='product_attribute_2']";
    public static final String HDD_RADIO_BUTTON = "xpath=//label[text()=' HDD ']/parent::dt/following-sibling::dd//label[text()='%s']/preceding-sibling::input";
    public static final String OS_RADIO_BUTTON = "xpath=//label[text()=' OS ']/parent::dt/following-sibling::dd//label[text()='%s']/preceding-sibling::input";
    public static final String SOFTWARE_CHECKBOX = "xpath=//label[text()=' Software ']/parent::dt/following-sibling::dd//label[text()='%s']/preceding-sibling::input";
    public static final String ADD_TO_CART_BUTTON = "css=button.add-to-cart-button";
    public static final String TOTAL_ITEM_FROM_SHOPPING_CART_LINK = "xpath=//a[@class='ico-cart']/span[@class='cart-qty']";
    public static final String SHOPPING_CART_LINK = "class=ico-cart";
    public static final String TOTAL_ITEM_FROM_FLYOUT = "xpath=//div[@id='flyout-cart']//div[@class='count']/a";
    public static final String PRODUCT_NAME_FROM_FLYOUT = "xpath=//div[@id='flyout-cart']//div[@class='item first']//div[@class='name']/a";
    public static final String PRODUCT_ATTRIBUTE_FROM_FLYOUT = "xpath=//div[@id='flyout-cart']//div[@class='item first']//div[@class='attributes']";
    public static final String PRODUCT_PRICE_FROM_FLYOUT = "xpath=//div[@id='flyout-cart']//div[@class='item first']//div[@class='price']/span";
    public static final String PRODUCT_QUANTITY_FROM_FLYOUT = "xpath=//div[@id='flyout-cart']//div[@class='item first']//div[@class='quantity']/span";
    public static final String SUB_TOTAL_VALUE_FROM_FLYOUT = "xpath=//div[@id='flyout-cart']//div[@class='totals']/strong";
    public static final String GO_TO_CART_BUTTON_FROM_FLYOUT = "xpath=//div[@id='flyout-cart']//button[text()='Go to cart']";
    public static final String PRODUCT_PRICE = "xpath=//div[@class='product-price']/span";
    public static final String PRODUCT_QUANTITY_TEXTBOX = "xpath=//div[@class='add-to-cart']//input[@id='product_enteredQuantity_1']";
    public static final String UPDATE_BUTTON = "xpath=//button[text()='Update']";
}
