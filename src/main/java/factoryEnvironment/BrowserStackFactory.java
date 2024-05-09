package factoryEnvironment;

import commons.BrowserList;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackFactory {
    private String browserName;
    private String browserVersion;
    private String osName;
    private WebDriver driver;

    public BrowserStackFactory(String browserName, String browserVersion, String osName) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.osName = osName;
    }

    public WebDriver createDriver() {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName(osName);
                chromeOptions.setBrowserVersion(browserVersion);
                try {
                    driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), chromeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName(osName);
                firefoxOptions.setBrowserVersion(browserVersion);
                try {
                    driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), firefoxOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPlatformName(osName);
                edgeOptions.setBrowserVersion(browserVersion);
                try {
                    driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), edgeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }
        return driver;
    }
}
