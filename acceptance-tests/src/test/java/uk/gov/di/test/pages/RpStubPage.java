package uk.gov.di.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class RpStubPage extends BasePage {
    public void goToRpStub() {
        driver.get(RP_URL.toString());
        waitForThisText("Request Object");
    }

    public void selectRpOptionsByIdAndContinue(String toggleOptions, String valueOptions) {
        if (!toggleOptions.isEmpty() && !toggleOptions.equalsIgnoreCase("default")) {
            String optionIds[] = toggleOptions.split(",");
            for (String id : optionIds) {
                driver.findElement(By.id(id)).click();
            }
        }
        if (!valueOptions.isEmpty() && !valueOptions.equalsIgnoreCase("default")) {
            String options[] = valueOptions.split(",");
            for (String option : options) {
                String[] optionStrings = option.split("=");
                String optionId = optionStrings[0];
                String optionValue = optionStrings[1];
                driver.findElement(By.id(optionId)).sendKeys(optionValue);
            }
        }
        findAndClickContinue();
    }

    public void selectRpAndContinue(String relyingPartyName) {
        driver.findElement(By.linkText("Change Relying Party")).click();
        waitForThisText("Select a Relying Party");
        Select select = new Select(driver.findElement(By.id("relying-party")));
        findAndClickButtonByText("Submit");
    }
}
