package pageUIs.users;

public class UserOrderDetailsPUI {
    public static final String ORDER_NUMBER_VALUE = "xpath=//div[@class='order-number']/strong";
	public static final String ORDER_INFORMATION_VALUE_BY_CLASS = "xpath=//ul[@class='order-overview-content']/li[@class='%s']";
    public static final String BILLING_ADDRESS_INFORMATION_BY_CLASS = "xpath=//div[@class='billing-info']/ul/li[@class='%s']";
    public static final String SHIPPING_ADDRESS_INFORMATION_BY_CLASS = "xpath=//div[@class='shipping-info']/ul/li[@class='%s']";
    public static final String PAYMENT_METHOD_INFORMATION = "xpath=//li[@class='payment-method']/span[@class='value']";
    public static final String PAYMENT_STATUS_INFORMATION = "xpath=//li[@class='payment-method-status']/span[@class='value']";
    public static final String SHIPPING_METHOD_INFORMATION = "xpath=//li[@class='shipping-method']/span[@class='value']";
    public static final String SHIPPING_STATUS_INFORMATION = "xpath=//li[@class='shipping-status']/span[@class='value']";
    public static final String CELL_VALUE_BY_CLASS_NAME = "xpath=//td[@class='%s']/span";
    public static final String GIFT_WRAPPING_VALUE = "xpath=//div[@class='section options']/div";
    public static final String DYNAMIC_VALUE_BY_LABEL_FROM_CART_TOTAL_TABLE = "xpath=//label[contains(text(), '%s')]/parent::td/following-sibling::td/span";
    public static final String COMPUTERS_MENU = "xpath=//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]";
    public static final String NOTEBOOKS_SUB_MENU = "xpath=//ul[@class='top-menu notmobile']//ul[@class='sublist first-level']//a[contains(text(),'Notebooks')]";
    public static final String RE_ORDER_BUTTON = "xpath=//button[text()='Re-order']";
}
