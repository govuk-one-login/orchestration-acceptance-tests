package uk.gov.di.test.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import uk.gov.di.test.pages.BasePage;
import uk.gov.di.test.pages.CheckYourPhonePage;
import uk.gov.di.test.pages.CreateOrSignInPage;
import uk.gov.di.test.pages.EnterYourEmailAddressToSignInPage;
import uk.gov.di.test.pages.EnterYourPasswordPage;

public class LoginStepDef extends BasePage {
    public EnterYourPasswordPage enterYourPasswordPage = new EnterYourPasswordPage();
    public CheckYourPhonePage checkYourPhonePage = new CheckYourPhonePage();
    public EnterYourEmailAddressToSignInPage enterYourEmailAddressToSignInPage =
            new EnterYourEmailAddressToSignInPage();
    public CreateOrSignInPage createOrSignInPage = new CreateOrSignInPage();

    @When("the user selects sign in")
    public void theUserSelectsSignIn() throws InterruptedException {
        Thread.sleep(2000);
        createOrSignInPage.clickSignInButton();
    }

    @And("user enters {string} email address")
    public void userEntersEmailAddress(String email) {
        enterYourEmailAddressToSignInPage.enterEmailAddressAndContinue(System.getenv().get(email));
    }

    @When("the user enters their password")
    public void theUserEntersTheirPassword() {
        enterYourPasswordPage.enterPasswordAndContinue(System.getenv().get("TEST_USER_PASSWORD"));
    }

    @When("the user enters the six digit security code from their phone")
    public void theUserEntersTheirPhoneCode() {
        checkYourPhonePage.enterCodeAndContinue(System.getenv().get("TEST_USER_PHONE_OTP"));
    }
}
