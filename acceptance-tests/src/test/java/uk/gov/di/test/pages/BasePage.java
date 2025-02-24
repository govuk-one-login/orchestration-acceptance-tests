package uk.gov.di.test.pages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class BasePage {

    protected static final String CHROME_BROWSER = "chrome";
    protected static final String SELENIUM_URL =
            System.getenv().getOrDefault("SELENIUM_URL", "http://localhost:4444/wd/hub");

    protected static final String RP_URL =
            System.getenv()
                    .getOrDefault(
                            "RP_URL",
                            "https://acceptance-test-rp-build.build.stubs.account.gov.uk/");

    protected static final Boolean SELENIUM_LOCAL =
            Boolean.parseBoolean(System.getenv().getOrDefault("SELENIUM_LOCAL", "false"));
    protected static final Boolean SELENIUM_HEADLESS =
            Boolean.parseBoolean(System.getenv().getOrDefault("SELENIUM_HEADLESS", "false"));
    protected static final String SELENIUM_BROWSER =
            System.getenv().getOrDefault("SELENIUM_BROWSER", CHROME_BROWSER);
    protected static final Duration DEFAULT_PAGE_LOAD_WAIT_TIME = Duration.of(20, SECONDS);
    protected static WebDriver driver;
    protected static Scenario scenario;

    protected void setupWebdriver() throws MalformedURLException {
        if (driver == null) {
            switch (SELENIUM_BROWSER) {
                case CHROME_BROWSER:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(SELENIUM_HEADLESS);
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    if (SELENIUM_LOCAL) {
                        System.setProperty("webdriver.chrome.whitelistedIps", "");
                        driver = new ChromeDriver(chromeOptions);
                    } else {
                        driver = new RemoteWebDriver(new URL(SELENIUM_URL), chromeOptions);
                    }
                    break;
                default:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setHeadless(SELENIUM_HEADLESS);
                    firefoxOptions.setPageLoadTimeout(Duration.of(30, SECONDS));
                    firefoxOptions.setImplicitWaitTimeout(Duration.of(30, SECONDS));
                    if (SELENIUM_LOCAL) {
                        driver = new FirefoxDriver(firefoxOptions);
                    } else {
                        driver = new RemoteWebDriver(new URL(SELENIUM_URL), firefoxOptions);
                    }
            }
        }
    }

    protected void closeWebdriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected void waitForPageLoad(String titleContains) {
        waitForPageLoad(titleContains, DEFAULT_PAGE_LOAD_WAIT_TIME);
    }

    protected void waitForPageLoad(String titleContains, Duration timeout) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.titleContains(titleContains));
        waitForReadyStateComplete();
    }

    protected void findAndClickContinue() {
        waitForReadyStateComplete();
        WebElement continueButton =
                driver.findElement(By.xpath("//button[text()[normalize-space() = 'Continue']]"));
        continueButton.click();
    }

    protected void findAndClickContinueWelsh() {
        waitForReadyStateComplete();
        WebElement continueButton =
                driver.findElement(By.cssSelector("#main-content > div > div > form > button"));
        continueButton.click();
    }

    protected void findAndClickButtonByText(String buttonText) {
        waitForReadyStateComplete();
        WebElement button =
                driver.findElement(
                        By.xpath("//button[text()[normalize-space() = '" + buttonText + "']]"));
        button.click();
    }

    public void waitForThisText(String expectedText) {
        new WebDriverWait(driver, DEFAULT_PAGE_LOAD_WAIT_TIME)
                .until(
                        ExpectedConditions.visibilityOf(
                                driver.findElement(
                                        By.xpath(
                                                "//*[contains(text(), '" + expectedText + "')]"))));
    }

    protected void clearFieldAndEnter(By ele, String text) {
        driver.findElement(ele).clear();
        driver.findElement(ele).sendKeys(text);
    }

    public void setAnalyticsCookieTo(Boolean state) {
        driver.manage()
                .addCookie(new Cookie("cookies_preferences_set", "{\"analytics\":" + state + "}"));
    }

    public void waitForReadyStateComplete() {
        new WebDriverWait(driver, DEFAULT_PAGE_LOAD_WAIT_TIME)
                .until(
                        (ExpectedCondition<Boolean>)
                                wd ->
                                        ((JavascriptExecutor) wd)
                                                .executeScript("return document.readyState")
                                                .equals("complete"));
    }
}
