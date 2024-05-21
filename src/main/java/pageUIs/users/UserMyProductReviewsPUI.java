package pageUIs.users;

public class UserMyProductReviewsPUI {
	public static final String REVIEW_DATE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::span[@class='user']/following-sibling::span[@class='date']/span";
	public static final String REVIEW_TITLE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::span[@class='user']/following-sibling::span[@class='date']/span[text()='%s']/ancestor::div[@class='review-content']/preceding-sibling::div/div[@class='review-title']/strong";
	public static final String REVIEW_RATING_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::span[@class='user']/following-sibling::span[@class='date']/span[text()='%s']/ancestor::div[@class='review-content']/preceding-sibling::div//div[@class='rating']/div";
	public static final String REVIEW_TEXT_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::span[@class='user']/following-sibling::span[@class='date']/span[text()='%s']/ancestor::div[@class='review-content']/div[@class='review-text']";
}
