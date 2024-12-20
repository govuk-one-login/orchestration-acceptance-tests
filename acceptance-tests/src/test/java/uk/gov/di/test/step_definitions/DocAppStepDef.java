package uk.gov.di.test.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.gov.di.test.pages.BasePage;
import uk.gov.di.test.pages.DocAppPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocAppStepDef extends BasePage {

    private String jsonPayLoad;
    public DocAppPage docAppPage = new DocAppPage();
    protected static final String DOC_APP_URL =
            System.getenv()
                    .getOrDefault(
                            "DOC_APP_URL", "https://doc-app-rp-build.build.stubs.account.gov.uk/");

    @When("the user visits the doc app relying party")
    public void theUserVisitsTheDocAppRelyingParty() {
        driver.get(DOC_APP_URL.toString());
    }

    @And("the user sends a valid json payload")
    public void theUserSendsAValidJsonPayload() {
        jsonPayLoad = "{\"test\" : \"example\"}";
        docAppPage.enterPayLoad(jsonPayLoad);
        docAppPage.enterEvidence("3", "2", "1", "3");
        docAppPage.clickSubmitButton();
    }

    @Then("the user is taken to the user information page")
    public void theUserIsTakenToTheUserInformationPage() {
        waitForPageLoad("Example - GOV.UK - User Info");
        assertTrue(driver.getCurrentUrl().contains("/oidc/authorization-code/callback?code="));
        assertTrue(docAppPage.docAppCredentialsDisplayed());
        assertTrue(docAppPage.idTokenDisplayed());
    }
}
