package uk.gov.di.test.pages;

import org.openqa.selenium.By;

public class DocAppPage extends BasePage {
    By payloadInputField = By.id("jsonPayload");
    By submitButton = By.name("submit");

    By docAppCredentials = By.id("user-info-doc-app-credential");

    By idToken = By.id("user-info-phone-number");

    By strength = By.id("strength");

    By validity = By.id("validity");

    By activityHistory = By.id("activityHistory");

    By verification = By.id("verification");

    public void enterPayLoad(String jsonPayLoad) {

        driver.findElement(payloadInputField).sendKeys(jsonPayLoad);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public Boolean docAppCredentialsDisplayed() {
        return driver.findElement(docAppCredentials).isDisplayed();
    }

    public Boolean idTokenDisplayed() {
        return driver.findElement(idToken).isDisplayed();
    }

    public void enterEvidence(String strengthValue, String validityValue, String activityHistoryValue, String verificationValue) {
        driver.findElement(strength).sendKeys(strengthValue);
        driver.findElement(validity).sendKeys(validityValue);
        driver.findElement(activityHistory).sendKeys(activityHistoryValue);
        driver.findElement(verification).sendKeys(verificationValue);
    }
}
