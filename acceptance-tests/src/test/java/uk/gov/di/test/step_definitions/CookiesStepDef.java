package uk.gov.di.test.step_definitions;

import io.cucumber.java.en.When;
import uk.gov.di.test.pages.BasePage;

public class CookiesStepDef extends BasePage {
    protected static final String URL_WITH_COOKIES =
            System.getenv().getOrDefault("URL_WITH_COOKIES", "https://build.account.gov.uk/");

    private void removeBsidCookie() {
        driver.manage().deleteCookieNamed("bsid");
    }

    @When("the user closes their browser")
    public void theUserClosesTheirBrowser() {
        driver.get(URL_WITH_COOKIES.toString());
        waitForPageLoad("looking for has been removed - GOV.UK One Login");
        removeBsidCookie();
    }
}
