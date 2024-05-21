package uk.gov.di.test.pages;

import org.openqa.selenium.By;

public class CreateOrSignInPage extends BasePage {

    By signInButton = By.id("sign-in-button");

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
}
