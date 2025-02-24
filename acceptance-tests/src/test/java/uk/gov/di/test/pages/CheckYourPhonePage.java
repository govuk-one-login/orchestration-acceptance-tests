package uk.gov.di.test.pages;

import org.openqa.selenium.By;

public class CheckYourPhonePage extends BasePage {
    By codeField = By.id("code");

    public void enterCode(String code) {
        clearFieldAndEnter(codeField, code);
    }

    public void enterCodeAndContinue(String code) {
        enterCode(code);
        findAndClickContinue();
    }
}
