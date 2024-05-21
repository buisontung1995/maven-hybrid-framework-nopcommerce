package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.users.UserBasePUI;
import pageUIs.users.UserChangePasswordPUI;
import pageUIs.users.UserSideBarMyAccountPUI;

public class BasePage {

	public void openURL(WebDriver driver, String URL) {
		driver.get(URL);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String valueToSendlKey) {
		waitForAlertPresence(driver).sendKeys(valueToSendlKey);
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allTabIDs = driver.getWindowHandles();
		for (String id : allTabIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String actualID : allWindowIDs) {
			driver.switchTo().window(actualID);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String currentWindow : allWindowIDs) {
			if (!currentWindow.equals(parentID)) {
				driver.switchTo().window(currentWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private By getByLocator(String locatorValue) {
		By by = null;

		if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("Xpath=") || locatorValue.startsWith("XPath=") || locatorValue.startsWith("XPATH=")) {
			by = By.xpath(locatorValue.substring(6));
		} else if (locatorValue.startsWith("css=") || locatorValue.startsWith("Css=") || locatorValue.startsWith("CSS=")) {
			by = By.cssSelector(locatorValue.substring(4));
		} else if (locatorValue.startsWith("id=") || locatorValue.startsWith("Id=") || locatorValue.startsWith("ID=")) {
			by = By.id(locatorValue.substring(3));
		} else if (locatorValue.startsWith("class=") || locatorValue.startsWith("Class=") || locatorValue.startsWith("CLASS=")) {
			by = By.className(locatorValue.substring(6));
		} else if (locatorValue.startsWith("tagname=") || locatorValue.startsWith("Tagname=") || locatorValue.startsWith("TAGNAME=")) {
			by = By.tagName(locatorValue.substring(8));
		} else if (locatorValue.startsWith("name=") || locatorValue.startsWith("Name=") || locatorValue.startsWith("NAME=")) {
			by = By.name(locatorValue.substring(5));
		} else {
			throw new RuntimeException("Locator type is not valid");
		}

		return by;
	}

	private By getByLocator(String locatorValue, String... dynamicValues) {
		By by = null;

		if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("Xpath=") || locatorValue.startsWith("XPath=") || locatorValue.startsWith("XPATH=")) {
			by = By.xpath(String.format(locatorValue.substring(6), (Object[]) dynamicValues));
		} else if (locatorValue.startsWith("css=") || locatorValue.startsWith("Css=") || locatorValue.startsWith("CSS=")) {
			by = By.cssSelector(String.format(locatorValue.substring(4), (Object[]) dynamicValues));
		} else if (locatorValue.startsWith("id=") || locatorValue.startsWith("Id=") || locatorValue.startsWith("ID=")) {
			by = By.id(String.format(locatorValue.substring(3), (Object[]) dynamicValues));
		} else if (locatorValue.startsWith("class=") || locatorValue.startsWith("Class=") || locatorValue.startsWith("CLASS=")) {
			by = By.className(String.format(locatorValue.substring(6), (Object[]) dynamicValues));
		} else if (locatorValue.startsWith("tagname=") || locatorValue.startsWith("Tagname=") || locatorValue.startsWith("TAGNAME=")) {
			by = By.tagName(String.format(locatorValue.substring(8), (Object[]) dynamicValues));
		} else if (locatorValue.startsWith("name=") || locatorValue.startsWith("Name=") || locatorValue.startsWith("NAME=")) {
			by = By.name(String.format(locatorValue.substring(5), (Object[]) dynamicValues));
		} else {
			throw new RuntimeException("Locator type is not valid");
		}

		return by;
	}

	protected WebElement getWebElement(WebDriver driver, String locatorValue) {
		return driver.findElement(getByLocator(locatorValue));
	}

	protected WebElement getWebElement(WebDriver driver, String locatorValue, String... dynamicValues) {
		return driver.findElement(getByLocator(locatorValue, dynamicValues));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorValue) {
		return driver.findElements(getByLocator(locatorValue));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorValue, String... dynamicValues) {
		return driver.findElements(getByLocator(locatorValue, dynamicValues));
	}

	protected void clickToElement(WebDriver driver, String locatorValue) {
		getWebElement(driver, locatorValue).click();
	}

	protected void clickToElement(WebDriver driver, String locatorValue, String... dynamicValues) {
		getWebElement(driver, locatorValue, dynamicValues).click();
	}

	protected void sendKeyToElement(WebDriver driver, String locatorValue, String textValue) {
		WebElement element = getWebElement(driver, locatorValue);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void sendKeyToElement(WebDriver driver, String locatorValue, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, locatorValue, dynamicValues);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void clearValueOfElementByPressKey(WebDriver driver, String locatorValue) {
		WebElement element = getWebElement(driver, locatorValue);
		element.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
	}

	protected void clearValueOfElementByPressKey(WebDriver driver, String locatorValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, locatorValue, dynamicValues);
		element.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
	}

	protected String getElementText(WebDriver driver, String locatorValue) {
		return getWebElement(driver, locatorValue).getText();
	}

	protected String getElementText(WebDriver driver, String locatorValue, String... dynamicValues) {
		return getWebElement(driver, locatorValue, dynamicValues).getText();
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorValue, String textItem) {
		new Select(getWebElement(driver, locatorValue)).selectByVisibleText(textItem);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorValue, String textItem, String... dynamicValues) {
		new Select(getWebElement(driver, locatorValue, dynamicValues)).selectByVisibleText(textItem);
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorValue) {
		return new Select(getWebElement(driver, locatorValue)).getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorValue, String... dynamicValues) {
		return new Select(getWebElement(driver, locatorValue, dynamicValues)).getFirstSelectedOption().getText();
	}

	protected boolean isMultipleDropdown(WebDriver driver, String locatorValue) {
		return new Select(getWebElement(driver, locatorValue)).isMultiple();
	}

	protected boolean isMultipleDropdown(WebDriver driver, String locatorValue, String... dynamicValues) {
		return new Select(getWebElement(driver, locatorValue, dynamicValues)).isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItemText) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		List<WebElement> allDropdownItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement item : allDropdownItems) {
			if (item.getText().trim().equals(expectedItemText)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	protected String getElementAttribute(WebDriver driver, String locatorValue, String attributeName) {
		return getWebElement(driver, locatorValue).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String locatorValue, String attributeName, String... dynamicValues) {
		return getWebElement(driver, locatorValue, dynamicValues).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String locatorValue, String propertyValue) {
		return getWebElement(driver, locatorValue).getCssValue(propertyValue);
	}

	protected String getElementCssValue(WebDriver driver, String locatorValue, String propertyValue, String... dynamicValues) {
		return getWebElement(driver, locatorValue, dynamicValues).getCssValue(propertyValue);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locatorValue) {
		return getListWebElement(driver, locatorValue).size();
	}

	protected int getElementSize(WebDriver driver, String locatorValue, String... dynamicValues) {
		return getListWebElement(driver, locatorValue, dynamicValues).size();
	}

	protected void checkDefaultCheckboxRadio(WebDriver driver, String locatorValue) {
		WebElement element = getWebElement(driver, locatorValue);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkDefaultCheckboxRadio(WebDriver driver, String locatorValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, locatorValue, dynamicValues);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckDefaultCheckbox(WebDriver driver, String locatorValue) {
		WebElement element = getWebElement(driver, locatorValue);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckDefaultCheckbox(WebDriver driver, String locatorValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, locatorValue, dynamicValues);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorValue) {
		return getWebElement(driver, locatorValue).isDisplayed();
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorValue, String... dynamicValues) {
		return getWebElement(driver, locatorValue, dynamicValues).isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorValue) {
		return getWebElement(driver, locatorValue).isEnabled();
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorValue, String... dynamicValues) {
		return getWebElement(driver, locatorValue, dynamicValues).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorValue) {
		return getWebElement(driver, locatorValue).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorValue, String... dynamicValues) {
		return getWebElement(driver, locatorValue, dynamicValues).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorValue) {
		driver.switchTo().frame(getWebElement(driver, locatorValue));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorValue) {
		new Actions(driver).moveToElement(getWebElement(driver, locatorValue)).perform();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorValue, String... dynamicValues) {
		new Actions(driver).moveToElement(getWebElement(driver, locatorValue, dynamicValues)).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorValue, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locatorValue), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorValue, Keys key, String... dynamicValues) {
		new Actions(driver).sendKeys(getWebElement(driver, locatorValue, dynamicValues), key).perform();
	}

	protected void rightClickToElement(WebDriver driver, String locatorValue) {
		new Actions(driver).contextClick(getWebElement(driver, locatorValue)).perform();
	}

	protected void rightClickToElement(WebDriver driver, String locatorValue, String... dynamicValues) {
		new Actions(driver).contextClick(getWebElement(driver, locatorValue, dynamicValues)).perform();
	}

	protected void dragAndDropToElement(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceXpath), getWebElement(driver, targetXpath)).perform();
	}

	protected void doubleClickToElement(WebDriver driver, String locatorValue) {
		new Actions(driver).doubleClick(getWebElement(driver, locatorValue)).perform();
	}

	protected void doubleClickToElement(WebDriver driver, String locatorValue, String... dynamicValues) {
		new Actions(driver).doubleClick(getWebElement(driver, locatorValue, dynamicValues)).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locatorValue) {
		WebElement element = getWebElement(driver, locatorValue);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locatorValue));
	}

	protected void clickToElementByJS(WebDriver driver, String locatorValue, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locatorValue, dynamicValues));
	}

	protected void scrollToElement(WebDriver driver, String locatorValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorValue));
	}

	protected void scrollToElement(WebDriver driver, String locatorValue, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorValue, dynamicValues));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locatorValue, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorValue));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));

		ExpectedCondition<Boolean> jQueryLoaded = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoaded = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoaded) && explicitWait.until(jsLoaded);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorValue) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorValue));
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorValue, String... dynamicValues) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorValue, dynamicValues));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorValue) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorValue));
		return status;
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorValue, String... dynamicValues) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorValue, dynamicValues));
		return status;
	}

	protected void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
		;
	}

	protected void waitForElementVisible(WebDriver driver, String locatorValue) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorValue)));
	}

	protected void waitForElementVisible(WebDriver driver, String locatorValue, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorValue, dynamicValues)));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorValue) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorValue)));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorValue, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorValue, dynamicValues)));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorValue) {
		overrideImplicitTimeout(driver, shortTimeout);
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorValue)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorValue, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorValue, dynamicValues)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	protected void waitForAllElementInvisible(WebDriver driver, String locatorValue) {
		overrideImplicitTimeout(driver, shortTimeout);
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorValue)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	protected void waitForAllElementInvisible(WebDriver driver, String locatorValue, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorValue, dynamicValues)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	protected void waitForElementClickable(WebDriver driver, String locatorValue) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorValue)));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorValue, String... dynamicValues) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorValue, dynamicValues)));
	}

	public String getBarNotificationText(WebDriver driver) {
		waitForElementVisible(driver, UserBasePUI.BAR_NOTIFICATION);
		return getElementText(driver, UserBasePUI.BAR_NOTIFICATION);
	}

	public void clickToCloseNotificationButton(WebDriver driver) {
		waitForElementClickable(driver, UserBasePUI.CLOSE_NOTIFICATION_BUTTON);
		clickToElement(driver, UserBasePUI.CLOSE_NOTIFICATION_BUTTON);
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
