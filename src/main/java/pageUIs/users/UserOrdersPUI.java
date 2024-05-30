package pageUIs.users;

public class UserOrdersPUI {
    public static final String ORDER_INFORMATION_VALUE_BY_ORDER_NUMBER_AND_LABEL = "xpath=//strong[text()='Order Number: %s']/parent::div/following-sibling::ul/li[contains(text(), '%s')]/span";
    public static final String ORDER_NUMBER = "xpath=//div[@class='section order-item']/div[@class='title']/strong[text()='Order Number: %s']";
    public static final String DETAILS_BUTTON_BY_ORDER_NUMBER = "xpath=//strong[text()='Order Number: %s']/parent::div/following-sibling::div[@class='buttons']/button";
}
