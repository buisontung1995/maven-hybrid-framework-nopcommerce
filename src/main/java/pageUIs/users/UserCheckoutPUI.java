package pageUIs.users;

public class UserCheckoutPUI {
	public static final String SHIP_TO_THE_SAME_ADDRESS_CHECKBOX = "id=ShipToSameAddress";
    public static final String DYNAMIC_CHECKBOX_BY_ID = "id=%s";
    public static final String DYNAMIC_DROP_DOWN_LIST_BY_ID = "id=%s";
    public static final String CONTINUE_BUTTON_BY_STEP_ID = "xpath=//div[@id='%s']//button[text()='Continue']";
    public static final String SHIPPING_METHOD_RADIO_BUTTON = "xpath=//div[@id='shipping-methods-form']//label[contains(text(),'%s')]/preceding-sibling::input";
    public static final String PAYMENT_METHOD_RADIO_BUTTON = "xpath=//ul[@id='payment-method-block']//label[text()='%s']/preceding-sibling::input";
    public static final String BILLING_ADDRESS_INFORMATION_BY_CLASS = "xpath=//div[@class='billing-info']/ul/li[@class='%s']";
    public static final String SHIPPING_ADDRESS_INFORMATION_BY_CLASS = "xpath=//div[@class='shipping-info']/ul/li[@class='%s']";
    public static final String PAYMENT_METHOD_INFORMATION = "xpath=//li[@class='payment-method']/span[@class='value']";
    public static final String SHIPPING_METHOD_INFORMATION = "xpath=//li[@class='shipping-method']/span[@class='value']";
    public static final String CELL_VALUE_BY_CLASS_NAME = "xpath=//td[@class='%s']/span";
    public static final String GIFT_WRAPPING_VALUE = "xpath=//div[@class='cart-options']/div";
    public static final String DYNAMIC_VALUE_BY_LABEL_FROM_CART_TOTAL_TABLE = "xpath=//label[contains(text(), '%s')]/parent::td/following-sibling::td/span";
    public static final String CONFIRM_BUTTON = "xpath=//button[text()='Confirm']";
    public static final String THANK_YOU_MESSAGE = "xpath=//div[@class='page checkout-page order-completed-page']/div[@class='page-title']/h1";
    public static final String ORDER_SUCCESSFULLY_MESSAGE = "xpath=//div[@class='section order-completed']/div/strong";
    public static final String ORDER_NUMBER_VALUE = "xpath=//div[@class='order-number']/strong";
    //public static final String LOADING_NEXT_STEP_TEXT_BY_STEP_ID = "xpath=//div[@id='%s']//span[text()='Loading next step...']";
    public static final String PAYMENT_INFORMATION = "xpath=//div[@class='section payment-info']//p[2]";
    public static final String MY_ACCOUNT_LINK = "class=ico-account";
    public static final String LOADING_ICON = "id=states-loading-progress";
}
