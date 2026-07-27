package uk.gov.di.test.pages;

import org.openqa.selenium.By;

public class CreatePasskeyPage extends BasePage {

    private final By skipButton = By.xpath("//button[contains(text(), 'Skip for now')]");
    public static final String PATH = "/create-passkey";

    public void skipPasskeyPrompt() {
        if (driver.getCurrentUrl().contains(PATH)) {
            driver.findElement(skipButton).click();
        }
    }
}
