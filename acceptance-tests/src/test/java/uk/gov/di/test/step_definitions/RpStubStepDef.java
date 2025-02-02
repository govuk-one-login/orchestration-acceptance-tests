package uk.gov.di.test.step_definitions;

import io.cucumber.java.en.When;
import uk.gov.di.test.pages.BasePage;
import uk.gov.di.test.pages.RpStubPage;

public class RpStubStepDef extends BasePage {

    RpStubPage rpStubPage = new RpStubPage();

    @When("the user comes from the stub relying party with options: {string}")
    public void theExistingUserVisitsTheStubRelyingParty(String toggleOptions) {
        rpStubPage.goToRpStub();
        rpStubPage.selectRpOptionsByIdAndContinue(toggleOptions, "default");
        setAnalyticsCookieTo(false);
    }

    @When("the user comes from the stub relying party with options: {string} and {string}")
    public void theExistingUserVisitsTheStubRelyingPartyAndSetsValueOptions(
            String toggleOptions, String valueOptions) {
        rpStubPage.goToRpStub();
        rpStubPage.selectRpOptionsByIdAndContinue(toggleOptions, valueOptions);
        setAnalyticsCookieTo(false);
    }
}
