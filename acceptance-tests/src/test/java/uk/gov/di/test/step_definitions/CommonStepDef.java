package uk.gov.di.test.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.gov.di.test.pages.BasePage;

import java.net.MalformedURLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CommonStepDef extends BasePage {
    @Then("the user is taken to the {string} page")
    public void theUserIsTakenToThePage(String pageTitle) {
        waitForPageLoad(pageTitle);
    }

    @When("the user clicks the continue button")
    public void theUserClicksTheContinueButton() {
        findAndClickContinue();
    }

    @Then("the user is returned to the service")
    @Then("the user is successfully reauthenticated and returned to the service")
    public void theUserIsReturnedToTheService() {
        waitForPageLoad("Example - GOV.UK - User Info");
    }

    @Then("the user is returned to the service with extended timeout")
    public void theUserIsReturnedToTheServiceWithExtendedTimeout() {
        waitForPageLoad("Example - GOV.UK - User Info", Duration.of(1, ChronoUnit.MINUTES));
    }

    @And("the user logs out")
    public void theUserLogsOut() {
        findAndClickButtonByText("Log out");
        waitForPageLoad("Signed out");
    }

    @When("the user closes and reopens their browser")
    public void theUserClosesAndReopnsTheirBrowser() throws MalformedURLException {
        closeWebdriver();
        setupWebdriver();
    }
}
